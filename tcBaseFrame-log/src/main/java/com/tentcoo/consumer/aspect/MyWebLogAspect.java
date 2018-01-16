package com.tentcoo.consumer.aspect;

import com.alibaba.fastjson.JSON;
import com.tentcoo.log.annotation.LogAnnotation;
import com.tentcoo.log.util.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by rover on 2018/1/16.
 */
@Aspect
@Component
public class MyWebLogAspect {

    private static MyLog logger = MyLog.getLog(MyWebLogAspect.class);

    // and @annotation(org.springframework.web.bind.annotation.RequestMapping)
    // and @annotation(com.tentcoo.log.annotation.LogAnnotation)
    //@Pointcut("execution(* com.tentcoo.data.api..*(..))") // and com.tentcoo.data.service.impl..*.*(..))
    @Pointcut(value = "@annotation(com.tentcoo.log.annotation.LogAnnotation)")
    public void logAnnoAspect(){

    }

    @Before("logAnnoAspect()")
    public void doBefore(JoinPoint joinPoint){
        //logger.info("1.MyWebLogAspect.doBefore()");
    }

    @AfterReturning("logAnnoAspect()")
    public void  doAfterReturning(JoinPoint joinPoint){
        //logger.info("2.MyWebLogAspect.doAfterReturning()");
    }

    @Around("@annotation(logAnnotation)")
    public Object around(ProceedingJoinPoint pjp, LogAnnotation logAnnotation) {
        //获取注解里的值
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("3.second around:" + logAnnotation.value()+",methodName="+methodName);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String jsonInfo = JSON.toJSONString(request.getParameterMap());
        logger.info("req.json="+jsonInfo);
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
