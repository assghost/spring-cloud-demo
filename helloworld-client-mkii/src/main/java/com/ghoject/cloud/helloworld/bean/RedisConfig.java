package com.ghoject.cloud.helloworld.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    //连接超时时间
    private long timeout = 30 * 1000;

    private String host;

    private int port;

    private int database;

    //private String timeout;

    private JedisConfig jedis;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public JedisConfig getJedis() {
        return jedis;
    }

    public void setJedis(JedisConfig jedis) {
        this.jedis = jedis;
    }

    public static class JedisConfig {
        private  JedisPool pool;

        public JedisPool getPool() {
            return pool;
        }

        public void setPool(JedisPool pool) {
            this.pool = pool;
        }
    }

    public static class JedisPool {

        private int maxIdle;

        private int minIdle;

        private int maxActive;

        private String maxWait;

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public String getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(String maxWait) {
            this.maxWait = maxWait;
        }
    }
}


