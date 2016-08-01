package com.zhandaAdmin.controller.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class SysLogAspectJ {

//    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void cutController(){
        
    }
    
    @Around("within(@org.springframework.stereotype.Controller *) && @annotation(infoItemAble)")
    public Object recordSysLog(ProceedingJoinPoint point,InfoItemAble infoItemAble) throws Throwable{
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        System.out.println("请求方法:" + (point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()"));
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        //controller入参
        Object[] args = point.getArgs();
        System.out.println("controller入参:"+args.toString());
        //controller返回参数
        if(infoItemAble.formId()!=""){
            System.out.println("获取到formId啦"+infoItemAble.formId());
        }
        Object result = point.proceed();
        System.out.println("controller返回值:"+result);
        return result;
    }
    
    
    
}