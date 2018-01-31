package com.tentcoo.data.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by rover on 2018/1/27.
 */
public interface DistributedLock {

    //获取锁，若没有得到就等待
    public void acquire() throws Exception;

    //获取锁，直到超时
    public boolean acquire(long time, TimeUnit unit) throws Exception;

    //释放锁
    public void release() throws Exception;

}
