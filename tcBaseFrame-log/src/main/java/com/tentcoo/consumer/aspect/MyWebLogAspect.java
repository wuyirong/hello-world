package com.tentcoo.consumer.aspect;

import com.tentcoo.log.util.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by rover on 2018/1/16.
 */
@Aspect
@Component
public class MyWebLogAspect {

    private static MyLog logger = MyLog.getLog(MyWebLogAspect.class);

    @Pointcut("execution(* com.tentcoo.data.api..*.*(..))")
    public void welogAspect(){

    }

    @Before("welogAspect()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("MyWebLogAspect.doBefore()");
    }

    @AfterReturning("welogAspect()")
    public void  doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，返回内容
        logger.info("MyWebLogAspect.doAfterReturning()");
    }

}
