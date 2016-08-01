package com.zhandaAdmin.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.zhandaAdmin.data.dao.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * Created by admin on 2016/7/20.
 */
public class InfoDemo {
	public Map<String, Object> getBusiInfo(Object bean, Object busiQryCond,
			String fieldName, Integer index, List<AttrPath> path,
			BusiObj rootBusiObj, Object rootDto) throws IllegalAccessException {
		path.add(new AttrPath(fieldName, index));
		if (rootBusiObj == null) {
			rootBusiObj = getBusiObjByFormId(1l);
		}
		Map<String, Object> infos = new HashMap<String, Object>();
		Class clazz = bean.getClass();
		// 获取dto类名
		if (StringUtils.isBlank(fieldName)) {
			String[] classPath = clazz.getName().split("\\.");
			fieldName = classPath[classPath.length - 1];
		}
		// 根据dto类名获取该dto下所有属性的信息项模板
		List<AttrInfoTemplet> infoTplts = getAttrInfoTempletsByFieldName(
				fieldName, busiQryCond);
		// 根据信息项模板和实例数据生成信息项实例
		Map<String, Object> attrInfos = createInfos(infoTplts, bean, path,
				rootBusiObj, rootDto);
		infos = attrInfos;
		if (infos == null) {
			infos = new HashMap<String, Object>();
		}
		// 遍历所有属性,当属性是继承AppBaseDTO的对象时继续匹配该属性的对象的信息项
		Field[] fields;
		fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Object value = fields[i].get(bean);
			// 当属性是数组时
			if (value instanceof List) {
				List<Object> list = (List<Object>) value;
				List<Object> listInfo = new ArrayList<Object>();
				for (int j = 0; j < list.size(); j++) {
					Object subInfo = getAttrInfos(list.get(j),
							fields[i].getName(), j, busiQryCond, path,
							rootBusiObj, rootDto);
					if (subInfo != null) {
						listInfo.add(subInfo);
					}
				}
				if (listInfo.size() > 0) {
					infos.put(fields[i].getName(), listInfo);
				}
				continue;
			}
			Object info = getAttrInfos(fields[i].get(bean), fields[i].getName(),
					index, busiQryCond, path, rootBusiObj, rootDto);
			if (info != null) {
				infos.put(fields[i].getName(), info);
			}
		}
		if (infos.size() == 0) {
			return null;
		}
		path.remove(path.size() - 1);
		return infos;
	}

	// 当属性是对象时继续深入匹配信息项
	private Object getAttrInfos(Object value, String fieldName, Integer index,
			Object busiQryCond, List<AttrPath> path, BusiObj rootBusiOBJ,
			Object rootDto) throws IllegalAccessException {
		if (value == null) {
			return null;
		}
		// 当属性是子对象时迭代
		if (value instanceof BaseEntity) {
			Map<String, Object> subBusiInfos = getBusiInfo(value, busiQryCond,
					fieldName, index, path, rootBusiOBJ, rootDto);
			return subBusiInfos;
		}
		return null;
	}

	// 根据信息项列表和实例数据生成信息项实例
	private Map<String, Object> createInfos(List<AttrInfoTemplet> infoTplts,
			Object bean, List<AttrPath> path, BusiObj busiObj, Object rootDto)
			throws IllegalAccessException {
		if (infoTplts == null || infoTplts.size() == 0) {
			return null;
		}
		Map<String, Object> attrInfos = new HashMap<String, Object>();
		for (AttrInfoTemplet templet : infoTplts) {
			AttrInfo info = new AttrInfo();
			BeanUtils.copyProperties(templet, info);
			// 非虚拟属性，直接根据fieldName匹配
			if (templet.getObj().getReferenceId() == null) {
				// 测试 打印路径
				System.out.println("路径:" + printPath(path, templet.getField()));
				Object value = getValueFromFieldName(bean, templet.getField());
				if (value != null) {
					info.setValue(value.toString());
				}
			} else {
				List<String> referencePath = getValuePath(
						templet.getObj().getReferenceObj());
				// TODO 虚拟属性，根据引用的业务属性ID、rootObj,rootDTO和path获取引用的实际值
				Object value = findReferenceValue(referencePath, path, rootDto);
				if (value != null) {
					info.setValue(value.toString());
				}
				// 首先根据引用业务属性ID找到它在rootObj中对应的业务属性的路径，
				// 接着用路径在实际的dto中找到实际的值
			}

			attrInfos.put(templet.getField(), info);
		}
		return attrInfos;
	}

	// 获取bean中对应属性名的值
	private Object getValueFromFieldName(Object bean, String fieldName)
			throws IllegalAccessException {
		Field[] fields;
		fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(fieldName)) {
				fields[i].setAccessible(true);
				return fields[i].get(bean);
			}
		}
		return null;
	}


	// 打印路径
	private String printPath(List<AttrPath> path, String fieldName) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < path.size(); i++) {
			str.append(path.get(i).attrName);
			if (path.get(i).index != null) {
				str.append(path.get(i).index);
			}
			str.append(".");
		}
		str.append(fieldName);
		return str.toString();
	}

	// 获取该类下所有属性的信息项配置模板
	private List<AttrInfoTemplet> getAttrInfoTempletsByFieldName(
			String className, Object busiQryCond) {
		BusiObj obj = getBusiObjByFormId(1l);
		// TODO 传业务对象名，在所有的模板中找出其对应业务属性的父对象名字等于业务对象名的信息项模板
		List<AttrInfoTemplet> tplts = new ArrayList<AttrInfoTemplet>();
		if (className.equals("companys")) {
			AttrInfoTemplet info = new AttrInfoTemplet();
			info.setField("comName");
			info.setLabel("公司名");
			info.setObj(getBusiObjByObjId(obj, 3l));
			info.setType("input");
			tplts.add(info);

			AttrInfoTemplet info2 = new AttrInfoTemplet();
			info2.setField("mainCompanyName");
			info2.setLabel("总公司名");
			info2.setType("input");
			info2.setObj(getBusiObjByObjId(obj, 4l));
			tplts.add(info2);
		}
//		;
//		if (className.equals("mainCompany")) {
//			AttrInfoTemplet info = new AttrInfoTemplet();
//			info.setField("comName");
//			info.setLabel("总公司名");
//			info.setObj(getBusiObjByObjId(obj, 6l));
//			info.setType("input");
//			tplts.add(info);
//
//			// AttrInfoTemplet info2 = new AttrInfoTemplet();
//			// info2.setField("comId");
//			// info2.setLabel("总公司Id");
//			// info2.setType("input");
//			// tplts.add(info2);
//		}
		return tplts;
	}

	private BusiObj getBusiObjByFormId(Long id) {
		BusiObj obj = new BusiObj();
		obj.setName("testEntity");
		obj.setId(1L);
		List<BusiObj> attrs = new ArrayList<BusiObj>();
		attrs.add(new BusiObj());
		attrs.get(0).setParentObj(obj);
		attrs.get(0).setId(2l);
		attrs.get(0).setParentId(1l);
		attrs.get(0).setName("companys");
		attrs.get(0).setAttrs(new ArrayList<BusiObj>());
		attrs.get(0).getAttrs().add(new BusiObj());
		attrs.get(0).getAttrs().get(0).setId(3l);
		attrs.get(0).getAttrs().get(0).setParentId(2l);
		attrs.get(0).getAttrs().get(0).setName("comName");
		attrs.get(0).getAttrs().get(0).setCol("comName");
		attrs.get(0).getAttrs().get(0).setParentObj(attrs.get(0));
		attrs.add(new BusiObj());
		attrs.get(1).setId(5l);
		attrs.get(1).setParentId(1l);
		attrs.get(1).setParentObj(obj);
		attrs.get(1).setName("mainCompany");
		attrs.get(1).setAttrs(new ArrayList<BusiObj>());
		attrs.get(1).getAttrs().add(new BusiObj());
		attrs.get(1).getAttrs().get(0).setParentId(5l);
		attrs.get(1).getAttrs().get(0).setParentObj(attrs.get(1));
		attrs.get(1).getAttrs().get(0).setId(6l);
		attrs.get(1).getAttrs().get(0).setName("comName");
		attrs.get(1).getAttrs().get(0).setCol("comName");
		attrs.get(1).getAttrs().get(0).setParentObj(attrs.get(1));
		attrs.get(0).getAttrs().add(new BusiObj());
		attrs.get(0).getAttrs().get(1).setId(4l);
		attrs.get(0).getAttrs().get(1).setParentId(2l);
		attrs.get(0).getAttrs().get(1).setName("mainCompanyName");
		attrs.get(0).getAttrs().get(1).setReferenceId(6l);
		attrs.get(0).getAttrs().get(1)
				.setReferenceObj(attrs.get(1).getAttrs().get(0));
		attrs.get(0).getAttrs().get(1).setParentObj(attrs.get(0));
		obj.setAttrs(attrs);
		return obj;
	}

	private BusiObj getBusiObjByObjId(BusiObj obj, Long id) {
		if (obj.getId().equals(id)) {
			return obj;
		}
		if (obj.getAttrs() != null) {
			for (int i = 0; i < obj.getAttrs().size(); i++) {
				BusiObj result = getBusiObjByObjId(obj.getAttrs().get(i), id);
				if (result != null)
					return result;
			}
		}
		return null;
	}

	private List<String> getValuePath(BusiObj obj) {
		List<String> path = new ArrayList<String>();
		do {
			path.add(0, obj.getName());
			obj = obj.getParentObj();
		} while (obj != null);
		return path;
	}

	private Object findReferenceValue(List<String> refencePath,
			List<AttrPath> path, Object rootDto) throws IllegalAccessException {
		if (refencePath == null || path == null || rootDto == null) {
			return null;
		}
		for (int i = 1; i < refencePath.size(); i++) {
			rootDto = getValueFromFieldName(rootDto, refencePath.get(i));
			if (rootDto == null) {
				return null;
			}
			if (rootDto instanceof List) {
				if(i>path.size()-1||!path.get(i).equals(refencePath.get(i))){
					rootDto = ((List) rootDto).get(0);
				}else {
					rootDto = ((List) rootDto).get(path.get(i).index);
				}
			}
		}

		return rootDto;
	}

	public void paveMap(Map<String,Object> map){
		do{
			Set<Map.Entry<String, Object>> entrySet = map.entrySet();
			for(Map.Entry<String, Object> entry :entrySet){
				if(entry.getValue() instanceof Map){
					Map subMap = ((Map)entry.getValue());
					if(subMap.values().size()==1){
						Object key = subMap.keySet().toArray()[0];
						Object value = subMap.values().toArray()[0];
						map.put(key.toString(),value);
						map.remove(entry.getKey());
					}
				}
			}
		}while(false);


	}

	public static void main(String arg[]) throws IllegalAccessException,
			IOException, InvocationTargetException, NoSuchMethodException {

	}

}
