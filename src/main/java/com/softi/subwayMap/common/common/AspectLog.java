package com.softi.subwayMap.common.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
public class AspectLog {

    private final String POINT_CUT = "execution(public * com.softi.subwayMap.modules.data.service.impl.*.*(..))";
    //@Pointcut(POINT_CUT)
    public void getPointCut(){};

    //@AfterThrowing(pointcut = POINT_CUT,throwing = "exception")
    public void getException(JoinPoint joinPoint, Throwable exception){
        log.error("异常信息 e={} "+joinPoint.getSignature().getName(),exception.getMessage(),exception);

    }
}
