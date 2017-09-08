package com.renjie120.common.validate.aspect;

import com.lvmama.lvf.common.utils.DateUtils;
import com.lvmama.lvtraffic.common.validate.annotation.valid.*;
import com.lvmama.lvtraffic.common.validate.enums.LvtrafficCode;
import com.lvmama.lvtraffic.common.validate.exception.ValidateException;
import com.lvmama.lvtraffic.common.validate.inte.ValidateAble;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component("promotionValidateInterceptorAspect")
public class ValidateInterceptor {
	private final static Logger logger = Logger.getLogger(ValidateInterceptor.class);

	@SuppressWarnings("rawtypes")
    @Before("@annotation(com.lvmama.lvtraffic.common.validate.annotation.config.ValidateMethod)")
	public void before(JoinPoint joinPoint) throws Exception {
			logger.info("ValidateInterceptor...Object:" + joinPoint.getTarget() + ",Method:" + joinPoint.getSignature().getName());
			Object[] objects = joinPoint.getArgs();
			for (Object obj : objects) {
				if (null != obj) {
//					if (obj instanceof OtaRequest) {
//						OtaRequest ropRequestBody = (OtaRequest) obj;
//						obj = ropRequestBody.getData();
//						if (ropRequestBody.getData() != null) {
//							logger.info(ropRequestBody.getData().toString());
//						}
//					}

					for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {

						logger.debug(clazz);
						Annotation[] annotations = clazz.getAnnotations();

						// 处理clazz annotation
						handlerClazz(annotations, obj);

						Field[] fields = clazz.getDeclaredFields();
						for (Field field : fields) {
							logger.debug(field);
							Annotation[] fieldAnnotations = field.getAnnotations();
							// 处理field annotation
							handlerField(fieldAnnotations, obj, field);
						}

						Method[] methods = clazz.getDeclaredMethods();
						for (Method method : methods) {
							logger.debug(method);
							Annotation[] methodAnnotations = method
									.getAnnotations();
							// 处理getter annotation
							handlerMethod(methodAnnotations, obj, method);
						}

					}

				} else {
					// TODO 传入的参数为空
				}
			}
	}

	/**
	 * 处理clazz annotation
	 * 
	 * @param annotations
	 * @param obj
	 * @throws Exception
	 */
	public static void handlerClazz(Annotation[] annotations, Object obj) throws Exception {
		if (null != annotations && annotations.length > 0) {
			for (Annotation annotation : annotations) {
				logger.debug(annotation);
				validate(annotation, obj);
			}
		}
	}

	/**
	 * 处理field annotation
	 * 
	 * @param annotations
	 * @param obj
	 * @param field
	 * @throws Exception
	 */
	public static void handlerField(Annotation[] annotations, Object obj, Field field) throws Exception {
		if (null != annotations && annotations.length > 0) {
			for (Annotation annotation : annotations) {
				logger.debug(annotation);
				field.setAccessible(true);
				Object result = field.get(obj);
				validate(annotation, result);
			}
		}
	}

	/**
	 * 处理getter annotation
	 * 
	 * @param annotations
	 * @param obj
	 * @param method
	 * @throws Exception
	 */
	public static void handlerMethod(Annotation[] annotations, Object obj, Method method) throws Exception {
		if (null != annotations && annotations.length > 0) {
			for (Annotation annotation : annotations) {
				logger.debug(annotation);
				Object result = method.invoke(obj);
				validate(annotation, result);
			}
		}
	}

	/**
	 * 验证POJO
	 * 
	 * @param annotation
	 * @param object
	 * @throws Exception
	 */
	public static void validate(Annotation annotation, Object object) throws Exception {
		if (annotation instanceof NotNull) {
			if (null == object) {
				throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((NotNull) annotation).message());
			}
		} else if (annotation instanceof NotEmpty) {
            if (null == object || "" == object.toString()) {
                throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((NotEmpty) annotation).message());
            }
        } else if (annotation instanceof ObjectNotEmpty) {
			if (null == object) {
				throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((ObjectNotEmpty) annotation).message());
			} else if (object instanceof List) {
				if (null == object || ((List) object).size() <= 0) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((ObjectNotEmpty) annotation).message());
				}
				for (Object o : (List) object) {
					if (null == o) {
						throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((ObjectNotEmpty) annotation).message());
					} else if (o instanceof String) {
						if (StringUtils.isEmpty((String) o)) {
							throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((ObjectNotEmpty) annotation).message());
						}
					}
					else if (o instanceof ValidateAble) {
						Field[] fields = o.getClass().getFields();
						for (Field field : fields) {
							handlerField(field.getAnnotations(), o, field);
						}
					}
				}
			}
			else if (object instanceof ValidateAble) {
				Field[] fields = object.getClass().getFields();
				for (Field field : fields) {
					handlerField(field.getAnnotations(), object, field);
				}
			}
		} else if (annotation instanceof Size) {
			int min = ((Size) annotation).min();
			int max = ((Size) annotation).max();
			if (object instanceof String) {
				if (((String) object).length() < min
						|| ((String) object).length() > max) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Size) annotation).message());
				}
			}
		}else if (annotation instanceof IsEnum) {
			String enumType = ((IsEnum) annotation).enumType();
			if (object instanceof String) {
				try{
					Class clz = Class.forName(enumType);
					Method m = clz.getMethod("valueOf", String.class);
					m.invoke(clz,(String) object);
				}catch(Exception e){
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((IsEnum) annotation).message());
				}
			}
		} else if (annotation instanceof Email) {
			if (null != object) {
				String sResult = (String) object;
				String regex = ((Email) annotation).regex();
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(sResult);
				if (!m.find()) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Email) annotation).message());
				}
			} else {
				throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Email) annotation).message());
			}

		} else if (annotation instanceof Tel) {
			if (null != object) {
				String sResult = (String) object;
				String regex = ((Tel) annotation).regex();
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(sResult);
				if (!m.find()) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Tel) annotation).message());
				}
			} else {
				throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Tel) annotation).message());
			}

		} else if (annotation instanceof Length) {
			int min = ((Length) annotation).min();
			int max = ((Length) annotation).max();
			if (object instanceof String) {
				if (((String) object).length() < min
						|| ((String) object).length() > max) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Length) annotation).message());
				}
			}
		} else if (annotation instanceof NotZero) {
			if (object instanceof Integer) {
				if ((Integer) object == 0) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((NotZero) annotation).message());
				}
			} else if (object instanceof Long) {
				if ((Long) object == 0) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((NotZero) annotation).message());
				}
			} else if (object instanceof Float) {
				if ((Float) object == 0) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((NotZero) annotation).message());
				}
			} else if (object instanceof Double) {
				if ((Double) object == 0) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((NotZero) annotation).message());
				}
			}
		} else if (annotation instanceof Range) {
			int min = ((Range) annotation).min();
			int max = ((Range) annotation).max();
			if (object instanceof Integer) {
				if ((((Integer) object) < min) || ((Integer) object) > max) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Range) annotation).message());
				}
			} else if (object instanceof Long) {
				if ((((Long) object) < min) || ((Long) object) > max) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Range) annotation).message());
				}
			} else if (object instanceof Float) {
				if ((((Float) object) < min) || ((Float) object) > max) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Range) annotation).message());
				}
			} else if (object instanceof Double) {
				if ((((Double) object) < min) || ((Double) object) > max) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Range) annotation).message());
				}
			}
		} else if (annotation instanceof Future) {
			if (null == object) {
				throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Future) annotation).message());
			} else if (object instanceof String) {
				if (StringUtils.isEmpty(object.toString()) || DateUtils.parseDate(DateUtils.formatDate(new Date())).compareTo(DateUtils.parseDate(object.toString())) == 1) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Future) annotation).message());
				}
			} else if (object instanceof Date) {
				if (new Date().before((Date) object)) {
					throw new ValidateException(LvtrafficCode.PARAMS_ERROR, LvtrafficCode.PARAMS_ERROR.getMessage() + "：" + ((Future) annotation).message());
				}
			}
		}

	}
}
