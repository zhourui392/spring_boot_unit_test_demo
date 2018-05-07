package cn.teleus.handler;

import com.corundumstudio.socketio.SocketIOClient;

public abstract class BaseMessageEventHandler {
	String getToken(SocketIOClient client){
		return client.getSessionId().toString();
	}
}
