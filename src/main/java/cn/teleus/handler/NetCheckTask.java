package cn.teleus.handler;

import cn.teleus.data.ServerNameInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by xiehua on 2017/12/5.
 */
@Component
public class NetCheckTask {
//    @Scheduled(cron = "0/5 * * * * ?")
    public void changeAccessCode(){
        ConnectMessageEventHandler.clientMap.values().forEach(socketIOClient -> {
            ServerNameInfo serverNameInfo = ServerNameInfo.getByName("dev");
            socketIOClient.sendEvent("checkNetWork",serverNameInfo);
        });
    }
}
