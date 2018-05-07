package cn.teleus;

import cn.teleus.config.NettyConfig;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication

@EnableConfigurationProperties({NettyConfig.class})
public class NetApplication {
	@Resource
	private NettyConfig nettyConfig;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
        config.setPort(nettyConfig.getPort());
		config.setPingInterval(3000);
		config.setPingTimeout(10000);
		final SocketIOServer server = new SocketIOServer(config);
		return server;
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}

	public static void main(String[] args) {
		SpringApplication.run(NetApplication.class, args);
	}
}
