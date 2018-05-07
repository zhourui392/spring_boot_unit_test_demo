package cn.teleus.net.service.impl;

import cn.teleus.net.service.RedisService;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Override
    public String getById(String id) {
        //operate redis using jedis.
        return null;
    }
}
