package com.renjie120.common.annotation.log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renjie120.common.enums.PointerKey;
import com.renjie120.common.utils.JsonUtils;

//@Component
//@Aspect
public class LoggerAspect {
	public static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	
	private Method getMethod(ProceedingJoinPoint pjp){
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		return method;
	}
	
	//@Around("@annotation(com.renjie120.common.aspect.LoggerPoint)")
	public Object trafficInterface(ProceedingJoinPoint pjp) throws Throwable {
		Method method = this.getMethod(pjp);
		LoggerPoint loggerPoint = method.getAnnotation(LoggerPoint.class);
		PointerKey pointerKey = loggerPoint.pointKey(); 
		
		Object[] args = pjp.getArgs();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		for(Object arg:args){
			paramMap.put(arg.getClass().getSimpleName(), arg);
		}
		
		String requestJson = JsonUtils.toJsonStr(paramMap);
		String gid= UUID.randomUUID().toString();
		
		System.out.println("请求参数："+requestJson);
		
		Object returnObj = null;
		String errorMsg = null;
		try {
			System.out.println("当前执行："+method.getName()+"---"+pointerKey.name()+",gid="+gid);
			returnObj = pjp.proceed();
			return returnObj;
		} catch (Exception e) {
			errorMsg = ExceptionUtils.getStackTrace(e);
			logger.error(e.getMessage(),e);
			throw e;
		} finally {
			if (returnObj == null) {
				returnObj = errorMsg;
			}
			System.out.println("响应："+JsonUtils.toJsonStr(returnObj));
		}    
	}
 
}
