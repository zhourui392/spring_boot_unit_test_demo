package cn.teleus.handler;

import cn.teleus.data.TokenWithMaId;
import cn.teleus.net.service.NetCheckService;
import cn.teleus.service.ConnectService;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import cn.teleus.tool.TeleusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class MsgEventHandler {
	private static final Logger logger = LoggerFactory.getLogger(MsgEventHandler.class);
	private final SocketIOServer server;

	public static ConcurrentMap<String,SocketIOClient> clientMap = new ConcurrentHashMap<>();


	@Resource
	private ConnectService connectService;

	@Autowired
	MsgEventHandler(SocketIOServer server) {
		this.server = server;
	}

	@OnConnect
	private void onConnect(SocketIOClient client) {
		String token = client.getSessionId().toString();
		clientMap.put(token,client);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(TeleusConstant.KEY_TOKEN, token);
		client.sendEvent(TeleusConstant.METHOD_CONNECTED_ACK, jsonObject);
	}

	@OnEvent(value = "clientInfo")
	private void clintInfo(SocketIOClient client, AckRequest request, Map<String,Object> msg) {
		String sessionId = client.getSessionId().toString();
		String maId = (String)msg.get("maId");
		maId = maId.trim();

		connectService.processClientInfo(sessionId,maId);

	}
}
