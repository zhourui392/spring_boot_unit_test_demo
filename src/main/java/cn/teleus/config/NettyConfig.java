package cn.teleus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wss.server")
public class NettyConfig {
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
