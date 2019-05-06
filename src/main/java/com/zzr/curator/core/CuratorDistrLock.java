package com.zzr.curator.core;

import com.zzr.curator.util.ZKUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaozhirong on 2019/5/5.
 */
public class CuratorDistrLock {

    private static final String ZK_LOCK_PATH = "/zkTestLock";

    public static void main(String[] args) {
        System.out.println("zk client start successfully!");
        Thread t1 = new Thread(() -> {
           doWithLock(ZKUtil.client);
        });
        Thread t2 = new Thread(() -> {
            doWithLock(ZKUtil.client);
        });
        System.out.println("t1 start");
        t1.start();
        System.out.println("t2 start");
        t2.start();
        System.out.println("end");
    }

    private static void doWithLock(CuratorFramework client){
        InterProcessMutex lock = new InterProcessMutex(client,ZK_LOCK_PATH);
        try {
            if(lock.acquire(10 * 1000, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName() + " hold lock");
                Thread.sleep(5000L);
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                lock.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
