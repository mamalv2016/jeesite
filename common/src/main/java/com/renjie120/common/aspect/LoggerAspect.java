package com.renjie120.common.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
  
@Component
@Aspect
public class LoggerAspect {
	public static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
 
	 @Around("@annotation(com.renjie120.common.aspect.LoggerPoint)")
	public Object trafficInterface(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("LoggerAspect-------------------");
		return null;
//		String gid = UUID.randomUUID().toString();
//
//		Method method = getMethod(pjp);
//		LoggerPoint loggerPoint = method.getAnnotation(LoggerPoint.class);
//		PointerKey pointerKey = loggerPoint.pointKey();
//
//		Object[] args = pjp.getArgs();
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//
//		for (Object arg : args) {
//			paramMap.put(arg.getClass().getSimpleName(), arg);
//		}
//
//		String requestJson = JsonUtils.toJsonStr(paramMap);
//		System.out.println("请求对象：：："+requestJson);
//
//		Object returnObj = null;
//		String errorMsg = null;
//		try {
//			returnObj = pjp.proceed();
//			return returnObj;
//		} catch (Exception e) {
//			errorMsg = ExceptionUtils.getStackTrace(e);
//			logger.error(e.getMessage(), e);
//			throw e;
//		} finally {
//			if (returnObj == null) {
//				returnObj = errorMsg;
//			}
//
//			System.out.println("返回对象：：："+JsonUtils.toJsonStr(returnObj)); 
//		}
	}

	private Method getMethod(ProceedingJoinPoint pjp) {
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		return method;
	}
}
