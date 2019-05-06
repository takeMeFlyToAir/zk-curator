package com.zzr.curator.config;

import org.apache.curator.framework.CuratorFramework;

/**
 * Created by zhaozhirong on 2019/5/5.
 */
public interface IZKListener {

    void executor(CuratorFramework client);

}
