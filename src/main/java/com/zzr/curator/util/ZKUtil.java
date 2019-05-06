package com.zzr.curator.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;

/**
 * Created by zhaozhirong on 2019/5/5.
 */
public class ZKUtil {

   private static final String ZK_ADDRESS = "127.0.0.1:2181";

   public static CuratorFramework client = null;

   private static final String ZK_DEFAULT_DATA = "zk_default_data";

   static {
       client = CuratorFrameworkFactory.builder().defaultData(ZK_DEFAULT_DATA.getBytes()).connectString(ZK_ADDRESS).retryPolicy( new RetryNTimes(10, 5000)).build();
//       client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryNTimes(10, 5000));
       client.start();
   }

   public static String createNode(String path,String data) throws Exception {
       return client.create().creatingParentsIfNeeded().forPath(path,data.getBytes());
   }

   public static String createNode(String path) throws Exception {
       return client.create().creatingParentsIfNeeded().forPath(path);
   }

   public static void deleteNode(String path) throws Exception {
       client.delete().forPath(path);
   }

   public static void deleteNodeChildren(String path) throws Exception {
       client.delete().deletingChildrenIfNeeded().forPath(path);
   }

   public static void updateData(String path,String data) throws Exception {
        client.setData().forPath(path,data.getBytes());
   }

    public static void updateData(String path) throws Exception {
        client.setData().forPath(path);
    }

   public static String getData(String path) throws Exception {
       return new String(client.getData().forPath(path));
   }

   public static List<String> getChildren(String path) throws Exception {
       return client.getChildren().forPath(path);
   }

    public static void main(String[] args) throws Exception {
        updateData("/logbackLevel","40");
    }
}
