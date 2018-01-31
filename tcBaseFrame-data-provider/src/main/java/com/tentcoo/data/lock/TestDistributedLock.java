package com.tentcoo.data.lock;

import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

/**
 * Created by rover on 2018/1/27.
 */
public class TestDistributedLock {

    public static void main(String[] args) {

        final ZkClientExt zkClientExt1 = new ZkClientExt("localhost:2181", 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex1 = new SimpleDistributedLockMutex(zkClientExt1, "/zkLocks");//根节点必须先存在

        final ZkClientExt zkClientExt2 = new ZkClientExt("localhost:2181", 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex2 = new SimpleDistributedLockMutex(zkClientExt2, "/zkLocks");//根节点必须先存在

        try {
            mutex1.acquire();
            System.out.println("Client1 locked");
            Thread client2Thd = new Thread(new Runnable() {

                public void run() {
                    try {
                        mutex2.acquire();
                        System.out.println("Client2 locked");
                        mutex2.release();
                        System.out.println("Client2 released lock");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client2Thd.start();
            Thread.sleep(5000);
            mutex1.release();
            System.out.println("Client1 released lock");

            client2Thd.join();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
