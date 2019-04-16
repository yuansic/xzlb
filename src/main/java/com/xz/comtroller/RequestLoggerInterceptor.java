package com.xz.comtroller;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Aspect
public class RequestLoggerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggerInterceptor.class);

	private static String LOGGER_MESSAGE = "URL : {} - HTTP_METHOD : {} - IP : {} - CLASS_METHOD : {} - ARGS : {} - RESPONSE : {}";

	private static String LOGGER_MESSAGE_REQ = "开始计时: {} - URI : {} - HTTP_METHOD : {} - IP : {} - CLASS_METHOD : {} - ARGS : {}";
	private static String LOGGER_MESSAGE_RES = "计时结束: {} - 耗时：{} - URI: {} - 最大内存: {}m - 已分配内存: {}m - 已分配内存中的剩余空间: {}m - 最大可用内存: {}m - ARGS : {} - RESPONSE : {}";


	@Around("execution(* com.sijibao.mls.bop.controller..*.*(..))")
	public Object andleBody(ProceedingJoinPoint pjp) throws Throwable {

		Object[] args = pjp.getArgs();//请求参数
		List argList = new ArrayList();
		for (Object arg : args) {
			if (!(arg instanceof BeanPropertyBindingResult)){
				argList.add(arg);
			}
		}

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		long beginTime = System.currentTimeMillis();//1、开始时间  
		LOGGER.info(LOGGER_MESSAGE_REQ, new SimpleDateFormat("HH:mm:ss.SSS").format(beginTime), request.getRequestURI(), request.getMethod()
				, request.getRemoteAddr(), pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()
				, JSONObject.toJSONString(argList));

		Object retVal = pjp.proceed(); // 连接点方法返回值

		// 记录下请求内容
		long endTime = System.currentTimeMillis(); 	//2、结束时间  
		LOGGER.info(LOGGER_MESSAGE_RES,
				/*beginTime,*/
				new SimpleDateFormat("HH:mm:ss.SSS").format(endTime), formatDateTime(endTime - beginTime),
				request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
				(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024,
				JSONObject.toJSONString(argList),
				retVal);
		
		
		/*
		LOGGER.info(LOGGER_MESSAGE, request.getRequestURL().toString(), request.getMethod()
		                          , request.getRemoteAddr(), pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()
		                          , JSONObject.toJSONString(argList), retVal);
        */
		return retVal;
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
	private String formatDateTime(long timeMillis){
		if(timeMillis == 0){
			return "";
		}
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
	}

}
