package com.tentcoo.data.lock;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Created by rover on 2018/1/31.
 */
public class DistributedLockUtil implements Watcher {

    ZooKeeper zk = null; //zookeeper原生api去实现一个分布式锁

    private String root = "/zkLocks";//根节点必须先存在

    private String myZonode; //表示当前获取到的锁名称-也就是节点名称

    private String waitNode; // 表示当前等待的节点

    private CountDownLatch latch;

    //zookeeper服务 url
    private static final String CONNECTION_STRING = "127.0.0.1:2181";

    private static final int SESSION_TIMEOUT = 10000; //超时时间

    /**
     * 构造函数初始化
     *
     * @param config 表示zookeeper连接串
     */
    public DistributedLockUtil(String config) {
        try {
            zk = new ZooKeeper(config, SESSION_TIMEOUT, this);
            Stat stat = zk.exists(root, false); //判断是不是已经存在zkLocks节点，不需要监听root节点
            if (stat == null) { //如果不存在，则创建根节点
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent event) {
        if (this.latch != null) { //如果计数器不为空话话，释放计数器锁
            this.latch.countDown();
        }
    }

    /**
     * 获取锁的方法
     */
    public boolean lock(String name) {
        if (tryLock(name)) {
            return true;
        }
        try {
            return waitLock(waitNode, SESSION_TIMEOUT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 释放锁操作的方法
     */
    public void unlock() {
        try {
            zk.delete(myZonode, -1);
            myZonode = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    private boolean tryLock(String name) {
        String splitStr = name; //lock_0000000001
        try {
            //创建一个有序的临时节点，赋值给myznode
            myZonode = zk.create(root + "/" + splitStr, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            List<String> subNodes = zk.getChildren(root, false);
            Collections.sort(subNodes); //讲所有的子节点排序
            if (myZonode.equals(root + "/" + subNodes.get(0))) {
                //当前客户端创建的临时有序节点是zkLocks下节点中的最小的节点，表示当前的客户端能够获取到锁
                return true;
            }
            //否则的话,监听比自己小的节点 locks/lock_0000000003
            String subMyZnode = myZonode.substring((myZonode.lastIndexOf("/") + 1));
            waitNode = subNodes.get(Collections.binarySearch(subNodes, subMyZnode) - 1);// 获取比当前节点小的节点
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean waitLock(String lower, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(root + "/" + lower, true); //获取节点状态，并添加监听
        if (stat != null) {
            this.latch = new CountDownLatch(1); //实例化计数器，让当前的线程等待
            this.latch.await(waitTime, TimeUnit.MILLISECONDS);
            this.latch = null;
        }
        return true;
    }

    /**
     * 测试的Demo
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

//        final Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {

                public void run() {
                    try {
//                      semaphore.acquire();
                        DistributedLockUtil dlUtil = new DistributedLockUtil(CONNECTION_STRING);
                        boolean lock = dlUtil.lock("product_");
                        if (lock) {
                            //业务代码
                            System.out.println(Thread.currentThread().getName() +"-->"+"开始执行业务代码");
                            Thread.sleep(300);
                            dlUtil.unlock();
                        }
//                      semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } ;
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

}
