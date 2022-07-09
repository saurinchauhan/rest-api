package com.apis.aspects;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogHandler {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.apis.*.*.*(..))")
	protected void resources() {
		
	}

	@Before("resources()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Starting method {}#{}({}) ",joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),Arrays.toString(joinPoint.getArgs()));
	}
}
