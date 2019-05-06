package com.zzr.curator.core;

import com.zzr.curator.util.ZKUtil;
import org.apache.curator.framework.recipes.cache.*;

/**
 * Created by zhaozhirong on 2019/5/5.
 */
public class CuratorWatcher {
    private static final String ZK_PATH = "/zk/test/test";

    public static void main(String[] args) throws Exception {
        nodeCache();
    }

    /**
     * Path cache 孩子节点----创建，删除，数据更新
     */
    private static void pathCache() throws Exception {
        PathChildrenCache watcher = new PathChildrenCache(
                ZKUtil.client,
                ZK_PATH,
                true
        );
        watcher.getListenable().addListener((client,event) -> {
            ChildData data = event.getData();
            if(data == null){
                System.out.println("No data in event[" + event +"]");
            }else {
                System.out.println("Receive event: "
                        + "type=[" + event.getType() + "]"
                        + ", path=[" + data.getPath() + "]"
                        + ", data=[" + new String(data.getData()) + "]"
                        + ", stat=[" + data.getStat() + "]");
            }
        });
        watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        System.out.println("Register zk watcher successfully!");
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * node cache 具体节点----创建，删除，数据更新
     */
    private static void nodeCache() throws Exception {

        NodeCache watcher = new NodeCache(
                ZKUtil.client,
                ZK_PATH
        );

        watcher.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                String data = new String(watcher.getCurrentData().getData());
                System.out.println("节点路径为："+watcher.getCurrentData().getPath()+" 数据: "+data);            }
        });
        watcher.start(true);
        System.out.println("Register zk watcher successfully!");
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * tree cache 是nodeCache和pathCache的结合体
     */
    private static void treeCache() throws Exception {

        TreeCache watcher = new TreeCache(
                ZKUtil.client,
                ZK_PATH
        );

        watcher.getListenable().addListener((client,event) -> {
            ChildData data = event.getData();
            if(data == null){
                System.out.println("No data in event[" + event +"]");
            }else {
                System.out.println("Receive event: "
                        + "type=[" + event.getType() + "]"
                        + ", path=[" + data.getPath() + "]"
                        + ", data=[" + new String(data.getData()) + "]"
                        + ", stat=[" + data.getStat() + "]");
            }
        });
        watcher.start();
        System.out.println("Register zk watcher successfully!");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
