package com.tentcoo.consumer.aspect;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.tentcoo.data.api.SysLogService;
import com.tentcoo.data.pojo.SysLog;
import com.tentcoo.log.annotation.LogAnnotation;
import com.tentcoo.log.util.MyLog;
import com.tentcoo.utils.IPUtil;
import com.tentcoo.utils.IdGen;
import com.tentcoo.utils.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by rover on 2018/1/16.
 */
@Aspect
@Component
public class MyWebLogAspect {

    private static MyLog logger = MyLog.getLog(MyWebLogAspect.class);

    @Reference
    private SysLogService sysLogService;

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
        long beginTime = System.currentTimeMillis();
        //获取注解里的值
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("3.second around:" + logAnnotation.value()+",methodName="+methodName);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String jsonInfo = JSON.toJSONString(request.getParameterMap());
        logger.info("req.json="+jsonInfo);

        SysLog sysLog = new SysLog();
        sysLog.setId(IdGen.uuid());
        //sysLog.setOperation(methodName);
        sysLog.setOperation(logAnnotation.value());
        //请求的方法名
        String className = pjp.getTarget().getClass().getName();
        sysLog.setMethod(className + "." + methodName + "()");
        sysLog.setParams(jsonInfo);
        //设置IP地址
        sysLog.setIp(IPUtil.getIpAddr(request));
        //用户名
        Object uObject = null;//SecurityUtils.getSubject().getPrincipal();
        sysLog.setUsername(uObject==null?"NullUser":uObject.toString());
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        sysLog.setTime(time);
        sysLog.setCreateDate(new Date());
        //保存系统日志
        sysLogService.save(sysLog);
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
