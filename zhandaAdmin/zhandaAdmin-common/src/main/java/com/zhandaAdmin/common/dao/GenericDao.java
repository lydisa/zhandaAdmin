package com.zhandaAdmin.common.dao;

import java.io.Serializable; 
import java.lang.reflect.ParameterizedType; 
import java.lang.reflect.Type; 
import java.util.Collection; 
import java.util.Iterator; 
import java.util.List; 
import org.apache.commons.beanutils.PropertyUtils; 
import org.hibernate.Criteria; 
import org.hibernate.LockMode; 
import org.hibernate.criterion.DetachedCriteria; 
import org.hibernate.criterion.Example; 
import org.hibernate.criterion.MatchMode; 
import org.hibernate.criterion.Order; 
import org.hibernate.criterion.Projections; 
import org.hibernate.criterion.Restrictions; 
import org.springframework.orm.hibernate4.support.HibernateDaoSupport; 


@SuppressWarnings("unchecked") 
public class GenericDao<T extends Serializable, PK extends Serializable> 
extends HibernateDaoSupport implements IGenericDao<T, PK> { 
// ʵ��������(�ɹ��췽���Զ���ֵ) 
private Class<T> entityClass; 

// ���췽��������ʵ�����Զ���ȡʵ�������� 
public GenericDao() { 
this.entityClass = null; 
Class c = getClass(); 
Type t = c.getGenericSuperclass(); 
if (t instanceof ParameterizedType) { 
Type[] p = ((ParameterizedType) t).getActualTypeArguments(); 
this.entityClass = (Class<T>) p[0]; 
} 
} 

// -------------------- �������������ӡ��޸ġ�ɾ������ -------------------- 

// ����������ȡʵ�塣���û����Ӧ��ʵ�壬���� null�� 
public T get(PK id) { 
return (T) getHibernateTemplate().get(entityClass, id); 
} 

// ����������ȡʵ�岢���������û����Ӧ��ʵ�壬���� null�� 
public T getWithLock(PK id, LockMode lock) { 
T t = (T) getHibernateTemplate().get(entityClass, id, lock); 
if (t != null) { 
this.flush(); // ����ˢ�£�������������Ч�� 
} 
return t; 
} 

// ����������ȡʵ�塣���û����Ӧ��ʵ�壬�׳��쳣�� 
public T load(PK id) { 
return (T) getHibernateTemplate().load(entityClass, id); 
} 

// ����������ȡʵ�岢���������û����Ӧ��ʵ�壬�׳��쳣�� 
public T loadWithLock(PK id, LockMode lock) { 
T t = (T) getHibernateTemplate().load(entityClass, id, lock); 
if (t != null) { 
this.flush(); // ����ˢ�£�������������Ч�� 
} 
return t; 
} 

// ��ȡȫ��ʵ�塣 
public List<T> loadAll() { 
return (List<T>) getHibernateTemplate().loadAll(entityClass); 
} 

// loadAllWithLock() ? 

// ����ʵ�� 
public void update(T entity) { 
getHibernateTemplate().update(entity); 
} 

// ����ʵ�岢���� 
public void updateWithLock(T entity, LockMode lock) { 
getHibernateTemplate().update(entity, lock); 
this.flush(); // ����ˢ�£�������������Ч�� 
} 

// �洢ʵ�嵽���ݿ� 
public void save(T entity) { 
getHibernateTemplate().save(entity); 
} 

// saveWithLock()�� 

// ���ӻ����ʵ�� 
public void saveOrUpdate(T entity) { 
getHibernateTemplate().saveOrUpdate(entity); 
} 

// ���ӻ���¼����е�ȫ��ʵ�� 
public void saveOrUpdateAll(Collection<T> entities) { 
	Iterator<T> iterator = entities.iterator();
	while(iterator.hasNext())
	{
		T entity =iterator.next();
		getHibernateTemplate().saveOrUpdate(entity);
	}
} 

// ɾ��ָ����ʵ�� 
public void delete(T entity) { 
getHibernateTemplate().delete(entity); 
} 

// ������ɾ��ָ����ʵ�� 
public void deleteWithLock(T entity, LockMode lock) { 
getHibernateTemplate().delete(entity, lock); 
this.flush(); // ����ˢ�£�������������Ч�� 
} 

// ��������ɾ��ָ��ʵ�� 
public void deleteByKey(PK id) { 
this.delete(this.load(id)); 
} 

// ��������������ɾ��ָ����ʵ�� 
public void deleteByKeyWithLock(PK id, LockMode lock) { 
this.deleteWithLock(this.load(id), lock); 
} 

// ɾ�������е�ȫ��ʵ�� 
public void deleteAll(Collection<T> entities) { 
getHibernateTemplate().deleteAll(entities); 
} 

// -------------------- HSQL ---------------------------------------------- 

// ʹ��HSQL���ֱ�����ӡ����¡�ɾ��ʵ�� 
public int bulkUpdate(String queryString) { 
return getHibernateTemplate().bulkUpdate(queryString); 
} 

// ʹ�ô�������HSQL������ӡ����¡�ɾ��ʵ�� 
public int bulkUpdate(String queryString, Object[] values) { 
return getHibernateTemplate().bulkUpdate(queryString, values); 
} 

// ʹ��HSQL���������� 
public List find(String queryString) { 
return getHibernateTemplate().find(queryString); 
} 

// ʹ�ô�������HSQL���������� 
public List find(String queryString, Object[] values) { 
return getHibernateTemplate().find(queryString, values); 
} 

// ʹ�ô������Ĳ�����HSQL���������� 
public List findByNamedParam(String queryString, String[] paramNames, 
Object[] values) { 
return getHibernateTemplate().findByNamedParam(queryString, paramNames, 
values); 
} 

// ʹ��������HSQL���������� 
public List findByNamedQuery(String queryName) { 
return getHibernateTemplate().findByNamedQuery(queryName); 
} 

// ʹ�ô�����������HSQL���������� 
public List findByNamedQuery(String queryName, Object[] values) { 
return getHibernateTemplate().findByNamedQuery(queryName, values); 
} 

// ʹ�ô���������������HSQL���������� 
public List findByNamedQueryAndNamedParam(String queryName, 
String[] paramNames, Object[] values) { 
return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, 
paramNames, values); 
} 

// ʹ��HSQL���������ݣ����� Iterator 
public Iterator iterate(String queryString) { 
return getHibernateTemplate().iterate(queryString); 
} 

// ʹ�ô�����HSQL���������ݣ����� Iterator 
public Iterator iterate(String queryString, Object[] values) { 
return getHibernateTemplate().iterate(queryString, values); 
} 

// �رռ������ص� Iterator 
public void closeIterator(Iterator it) { 
getHibernateTemplate().closeIterator(it); 
} 

// -------------------------------- Criteria ------------------------------ 

// ������Ự�޹صļ�����׼ 
public DetachedCriteria createDetachedCriteria() { 
return DetachedCriteria.forClass(this.entityClass); 
} 

// ������Ự�󶨵ļ�����׼ 
public Criteria createCriteria() { 
return this.createDetachedCriteria().getExecutableCriteria( 
this.currentSession()); 
} 

// ���������׼������ 
public List findByCriteria(DetachedCriteria criteria) { 
return getHibernateTemplate().findByCriteria(criteria); 
} 

// ���������׼�����ݣ�����ָ����Χ�ļ�¼ 
public List findByCriteria(DetachedCriteria criteria, int firstResult, 
int maxResults) { 
return getHibernateTemplate().findByCriteria(criteria, firstResult, 
maxResults); 
} 

// ʹ��ָ����ʵ�弰���Լ�������������������ԣ�ʵ��ֵ������ 
public List<T> findEqualByEntity(T entity, String[] propertyNames) { 
Criteria criteria = this.createCriteria(); 
Example exam = Example.create(entity); 
exam.excludeZeroes(); 
String[] defPropertys = getSessionFactory().getClassMetadata( 
entityClass).getPropertyNames(); 
for (String defProperty : defPropertys) { 
int ii = 0; 
for (ii = 0; ii < propertyNames.length; ++ii) { 
if (defProperty.equals(propertyNames[ii])) { 
criteria.addOrder(Order.asc(defProperty)); 
break; 
} 
} 
if (ii == propertyNames.length) { 
exam.excludeProperty(defProperty); 
} 
} 
criteria.add(exam); 
return (List<T>) criteria.list(); 
} 

// ʹ��ָ����ʵ�弰���Լ������������� like ��ʵ��ֵ������ 
public List<T> findLikeByEntity(T entity, String[] propertyNames) { 
Criteria criteria = this.createCriteria(); 
for (String property : propertyNames) { 
try { 
Object value = PropertyUtils.getProperty(entity, property); 
if (value instanceof String) { 
criteria.add(Restrictions.like(property, (String) value, 
MatchMode.ANYWHERE)); 
criteria.addOrder(Order.asc(property)); 
} else { 
criteria.add(Restrictions.eq(property, value)); 
criteria.addOrder(Order.asc(property)); 
} 
} catch (Exception ex) { 
// ������Ч�ļ����ο����ݡ� 
} 
} 
return (List<T>) criteria.list(); 
} 

// ʹ��ָ���ļ�����׼��ȡ�����׼�ļ�¼�� 
public Integer getRowCount(DetachedCriteria criteria) { 
criteria.setProjection(Projections.rowCount()); 
List list = this.findByCriteria(criteria, 0, 1); 
return (Integer) list.get(0); 
} 

// ʹ��ָ���ļ�����׼�������ݣ�����ָ��ͳ��ֵ(max,min,avg,sum) 
public Object getStatValue(DetachedCriteria criteria, String propertyName, 
String StatName) { 
if (StatName.toLowerCase().equals("max")) 
criteria.setProjection(Projections.max(propertyName)); 
else if (StatName.toLowerCase().equals("min")) 
criteria.setProjection(Projections.min(propertyName)); 
else if (StatName.toLowerCase().equals("avg")) 
criteria.setProjection(Projections.avg(propertyName)); 
else if (StatName.toLowerCase().equals("sum")) 
criteria.setProjection(Projections.sum(propertyName)); 
else 
return null; 
List list = this.findByCriteria(criteria, 0, 1); 
return list.get(0); 
} 

// -------------------------------- Others -------------------------------- 

// ����ָ����ʵ�� 
public void lock(T entity, LockMode lock) { 
getHibernateTemplate().lock(entity, lock); 
} 

// ǿ�Ƴ�ʼ��ָ����ʵ�� 
public void initialize(Object proxy) { 
getHibernateTemplate().initialize(proxy); 
} 

// ǿ���������»������ݵ����ݿ⣨������������ύʱ�Ÿ��£� 
public void flush() { 
getHibernateTemplate().flush(); 
} 
} 
//���ϴ������������ռ� 