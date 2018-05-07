package cn.teleus.service;

import cn.teleus.data.TokenWithMaId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConnectService {

    @Resource
    TokenWithMaId tokenWithMaId;

    public void processClientInfo(String sessionId, String maId) {
        tokenWithMaId.put(maId,sessionId);
    }
}
