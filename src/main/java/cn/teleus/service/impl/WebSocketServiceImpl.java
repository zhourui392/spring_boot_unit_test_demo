package cn.teleus.service.impl;

import cn.teleus.common.lang.Strings;
import cn.teleus.data.ServerNameInfo;
import cn.teleus.data.TokenWithMaId;
import cn.teleus.handler.ConnectMessageEventHandler;
import cn.teleus.service.WebSocketService;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhour on 2017/6/23.
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Resource
    TokenWithMaId tokenWithMaId;
    @Override
    public boolean sendNetCheckCommand(String maId) {
        if (Strings.isNotBlank(maId)){
            String token = tokenWithMaId.get(maId);
            if (Strings.isNotBlank(token)){
                SocketIOClient session = ConnectMessageEventHandler.clientMap.get(token);
                if (session != null){
                    ServerNameInfo serverNameInfo = ServerNameInfo.getByName("dev");
                    session.sendEvent("checkNetWork",serverNameInfo);
                    return true;
                }
            }
        }
        return false;
    }
}
