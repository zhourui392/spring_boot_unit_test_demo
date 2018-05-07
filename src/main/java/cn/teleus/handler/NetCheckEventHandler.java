package cn.teleus.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;

import java.util.Map;

@Component
class NetCheckEventHandler extends BaseMessageEventHandler {
	private static final Logger logger = LoggerFactory.getLogger(NetCheckEventHandler.class);
	private final SocketIOServer server;

	@Autowired
    NetCheckEventHandler(SocketIOServer server) {
		this.server = server;
	}


	// 绑定PAD SN 和Token
	@OnEvent(value = "reportNetCheck")
	private void getPadTokenBySN(SocketIOClient client, AckRequest request, Map<String,Object> msg) {
		//接收网络检测结果
		String eboxSN = (String) msg.get("eboxSN");

	}
}
