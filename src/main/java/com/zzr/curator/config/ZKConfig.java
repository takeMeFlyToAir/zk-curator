package com.zzr.curator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaozhirong on 2019/5/5.
 */

@Component
@ConfigurationProperties(prefix = "com.zzr.curator")
// PropertySource默认取application.properties
// @PropertySource(value = "config.properties")
public class ZKConfig {

    private String connectionString;

    private String logbackLevel;

    private List<IZKListener> listeners;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getLogbackLevel() {
        return logbackLevel;
    }

    public void setLogbackLevel(String logbackLevel) {
        this.logbackLevel = logbackLevel;
    }

    public List<IZKListener> getListeners() {
        List<IZKListener> listeners = new ArrayList<>();
        listeners.add(new LogbackLevelListener(logbackLevel));
        return listeners;
    }

    public void setListeners(List<IZKListener> listeners) {
        this.listeners = listeners;
    }
}
