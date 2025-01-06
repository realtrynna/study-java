package com.realtrynna.spring_start.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
public class TimeTraceAop {
    @Around("execution(* com.realtrynna.spring_start.service..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("시작 조인 포인트" + joinPoint.toString());

        try {
            /**
             * 다음 메서드로
             */
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long ms = end - start;

            System.out.println("종료" + ms);
        }
    }
}
