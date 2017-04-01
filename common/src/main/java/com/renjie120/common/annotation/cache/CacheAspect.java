package com.renjie120.common.annotation.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.renjie120.common.utils.CustomizedPropertyPlaceholderConfigurer;
import com.renjie120.common.utils.JsonUtils;
import com.renjie120.common.utils.MD5Utils;
import com.thinkgem.jeesite.common.utils.CacheUtils;

//@Aspect
//@Component
public class CacheAspect {

	@Value("project.cacheExpireTime")
	private String cacheExpireTime;

	@Value("project.isCacheEnable")
	private String isCacheEnable;

	public final static String PREFIX = "_RENJIE120_CACHE_";

	public static Logger logger = LoggerFactory.getLogger(CacheAspect.class);

//	@Around("@annotation(com.renjie120.common.annotation.cache.CachePoint)")
	public Object cache(ProceedingJoinPoint pjp) throws Throwable {

		Method method = method(pjp);
		if (!this.isCacheEnable()) {
			// 总开关控制是否运用缓存
			return pjp.proceed();
		}

		String cacheKey = null;

		try {
			cacheKey = getCacheKey(method, pjp.getArgs());
		} catch (Exception ex) {
			ex.printStackTrace();
//			logger.error(TraceContext.getTraceId() + " " + method.getName()
//					+ " get cache key error!", ex);
		}

		if (null == cacheKey) {
			return pjp.proceed();
		}

		CachePoint cachePoint = method.getAnnotation(CachePoint.class);
		try {
			CacheBox cacheBox = (CacheBox)CacheUtils.get(cacheKey);

			if (vaildExpire(cacheBox) && cachePoint.enable()) {
				String json = cacheBox.getJson();
//				logger.info(TraceContext.getTraceId() + " " + method.getName()
//						+ " user cache:json size" + json.length());
				return cachePoint.value().convertTo(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error(TraceContext.getTraceId() + " " + method.getName()
//					+ " get cache error!", e);
		}

		Object obj = pjp.proceed();

		try {
			CacheBox cacheBox = new CacheBox();
			cacheBox.setExpireTime(new Date().getTime()
					+ getCacheExpireTime(cachePoint));
			cacheBox.setKey(cacheKey);
			String json = cachePoint.value().convertFrom(obj); 
			cacheBox.setJson(json);
			CacheUtils.put(cacheKey, cacheBox);
//			logger.info(TraceContext.getTraceId() + " " + method.getName()
//					+ " user proceed:json size" + json.length());
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error(TraceContext.getTraceId() + " " + method.getName()
//					+ " set cache error!", e);
		}
		return obj; 
	}

	private boolean vaildExpire(CacheBox cacheBox) {
		return null != cacheBox
				&& new Date().getTime() < cacheBox.getExpireTime();
	}

	private Method method(ProceedingJoinPoint pjp) {
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		return methodSignature.getMethod();
	}

	private String getCacheKey(Method method, Object[] args) throws Exception {
		Annotation[][] annoss = method.getParameterAnnotations();
		StringBuilder json = new StringBuilder("");
		for (int i = 0; i < annoss.length; i++) {
			Annotation[] annos = annoss[i];
			for (Annotation anno : annos) {
				if (anno instanceof CacheKey) {
					json.append(JsonUtils.toJsonStr(args[i]));
				}
			}
		} 
		if (StringUtils.isBlank(json.toString())) {
			return null;
		}
		String key = MD5Utils.generateMd5(method.getClass().getName()
				+ "." + method.getName() + ":" + json.toString());
		return PREFIX + key;
	}

	private Long getCacheExpireTime(CachePoint cachePoint) {
		try {
			if (StringUtils.isNotBlank(cachePoint.expireTimeKey())) {
				return Long
						.valueOf((String) CustomizedPropertyPlaceholderConfigurer
								.getContextProperty(cachePoint.expireTimeKey()));
			}
			return Long
					.valueOf((String) CustomizedPropertyPlaceholderConfigurer
							.getContextProperty(cacheExpireTime));
		} catch (Exception ex) {
//			logger.error(TraceContext.getTraceId()
//					+ " get cache expiretime error!", ex);
			return 30000L;
		}
	}

	public boolean isCacheEnable() {
		return Boolean.valueOf(CustomizedPropertyPlaceholderConfigurer
				.getContextProperty(isCacheEnable) + "");
	}
}
