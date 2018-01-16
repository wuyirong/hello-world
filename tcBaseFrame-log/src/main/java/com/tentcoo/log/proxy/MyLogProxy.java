package com.tentcoo.log.proxy;

import com.tentcoo.log.annotation.LogAnnotation;
import com.tentcoo.log.util.MyLog;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by rover on 2018/1/15.
 */
public class MyLogProxy implements InvocationHandler {

    private static MyLog logger = MyLog.getLog(MyLogProxy.class);

    private MyLogProxy() { }

    private static Object target;

    // 这里的参数o就是要代理的对象
    public static Object getInstance(Object o) {
        MyLogProxy pm = new MyLogProxy();
        pm.target = o;
        Object result = Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), pm);
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("######################日志动态代理输出.");
        if(method.isAnnotationPresent(LogAnnotation.class)){
            LogAnnotation la = method.getAnnotation(LogAnnotation.class);
            //class.getDeclaredMethod("method", String.class);
            //String[] paramNames = AsmUtil.getMethodParamNames(proxy.getClass().getDeclaredMethod(method.getName(), String.class));
            //System.out.println("paramNames="+Arrays.toString(paramNames));
            //MyLog.getLog(MyLogProxy.class).info(la.value()+"--->执行的是"+method.getName()+"方法.");
        }
        Object result = method.invoke(target, args);
        logger.info("######invoke method: " + method.getName()
                + "; args: " + (null == args ? "null" : Arrays.asList(args).toString())
                + "; return: " + result);
        return result;
    }

}
