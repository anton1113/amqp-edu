package com.arash.edu.amqp.aop;

import com.arash.edu.amqp.msg.Message;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
public class SimpleControllerLogger {

    @Before("execution (* com.arash.edu.amqp.controller.*.*(..)) && args(request)")
    private <T extends Message> void logControllerBefore(JoinPoint joinPoint, T request) {
        log.info("%s, %s", generateSignature(joinPoint), request.toString());
    }

    @AfterReturning(pointcut = "execution(* com.arash.edu.amqp.controller.*.*(..))", returning = "response")
    private <T extends Message> void logControllerAfter(JoinPoint joinPoint, T response) {
        log.info("%s, %s", generateSignature(joinPoint), response.toString());
    }

    private String generateSignature(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return className.concat(".").concat(methodName);
    }
}
