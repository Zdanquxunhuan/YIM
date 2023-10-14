package com.yuge.yimcommon.yulog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Aspect
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE) //解决回滚异常问题（先被切面捕捉到，异常无法抛出，无法被回滚机制捕捉）
public class YuLogAspect {

    @Autowired
    private ObjectMapper objectMapper;

    //  a tool that returns default values for Java primitive types.
    private static final Map<Class<?>, Object> DEFAULT_VALUES =
            Stream.of(boolean.class, byte.class, char.class, double.class, float.class, int.class,
                    long.class, short.class)
                    .collect(toMap(clazz -> clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));

    private static <T> T getDefaultValue(Class<T> clazz) {
        return (T) DEFAULT_VALUES.get(clazz);
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerBean() {
    }

    @Pointcut("within(@com.yuge.yimcommon.yulog.YuLogMetrics *)")
    public void withYuMethodLogAnnotation() {
    }

    @Around("controllerBean() || withYuMethodLogAnnotation()")
    public Object doLog(ProceedingJoinPoint pjp) throws Throwable {

        String logRecord = "";
        // 获取方法的名称和参数类型
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        //Get it from the method first, then from the class. Build one if you don't have one
        YuLogMetrics yuLogMetrics = signature.getMethod().getAnnotation(YuLogMetrics.class);
        if (yuLogMetrics == null) {
            yuLogMetrics = signature.getMethod().getDeclaringClass().getAnnotation(YuLogMetrics.class);
        }

        //For Controller and Repository, we need to initialize a @Metrics annotation
        if (yuLogMetrics == null) {
            @YuLogMetrics
            final class c {
            }
            yuLogMetrics = c.class.getAnnotation(YuLogMetrics.class);
        }

        //Get some additional information from the context to enrich our log
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request != null) {
                logRecord = String.format("【%s】", request.getRequestURI());
            }
        }

        if (yuLogMetrics.logParameters()) {
            log.info(String.format("【入参日志】调用%s的参数是：【%s】", logRecord, objectMapper.writeValueAsString(pjp.getArgs())));
        }

        // Implement the execution of the join point method,
        // as well as the success and failure of the dot, and log when an exception occurs

        // Here we have temporarily replaced the logging implementation with the standard implementation t
        // hat requires information to be connected to a logging service, such as Micrometer
        Object returnValue;
        Instant start = Instant.now();
        try {
            returnValue = pjp.proceed();
            if (yuLogMetrics.logAfterExecuteMethodSuccess())
                log.info(String.format("【成功打点】调用 %s 成功，耗时 %d ms", logRecord, Duration.between(start, Instant.now()).toMillis()));
        } catch (Exception ex) {
            if (yuLogMetrics.logAfterExecuteMethodFail())
                log.info(String.format("【失败打点】调用 %s 失败，耗时 %d ms", logRecord, Duration.between(start, Instant.now()).toMillis()));
            if (yuLogMetrics.logException())
                log.error(String.format("【异常日志】调用 %s 出现异常!", logRecord), ex);

            if (yuLogMetrics.ignoreExcepReturnDef())
                returnValue = getDefaultValue(signature.getReturnType());
            else
                throw ex;
        }

        if (yuLogMetrics.logReturn())
            log.info(String.format("【出参日志】调用 %s 的返回是：【%s】", logRecord, returnValue));
        return returnValue;
    }
}
