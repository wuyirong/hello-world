package com.tentcoo.log.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by rover on 2018/1/15.
 */
public class MyLog extends MyLogImpl{

    private static final ConcurrentMap<String,MyLog> _pool = new ConcurrentHashMap<String, MyLog>();

    public static synchronized Set<String> getLoggers() {
        return _pool.keySet();
    }
    public static synchronized void clearLoggers() 	{
        _pool.clear();
    }

    public static synchronized MyLog getLog(String clz) {
        MyLog log = _pool.get(clz);
        if (log==null) {
            log = new MyLog();
            log.setName(clz);
            _pool.put(clz, log);
        }
        return log;
    }

    public static MyLog getLog(Class<?> clz){
        return getLog(clz.getName());
    }

}
