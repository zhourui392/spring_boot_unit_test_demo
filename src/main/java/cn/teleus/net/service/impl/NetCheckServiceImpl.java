package cn.teleus.net.service.impl;

import cn.teleus.common.lang.Strings;
import cn.teleus.net.mapper.BaseMapper;
import cn.teleus.net.entity.NetCheck;
import cn.teleus.net.mapper.NetCheckMapper;
import cn.teleus.net.service.NetCheckService;
import cn.teleus.net.service.PermissionService;
import cn.teleus.net.service.RedisService;
import cn.teleus.service.base.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NetCheckServiceImpl extends BaseServiceImpl<String> implements NetCheckService {
    private static final Logger logger = LoggerFactory.getLogger(NetCheckServiceImpl.class);
    @Resource
    private NetCheckMapper netCheckMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private PermissionService permissionService;

    @Override
    public BaseMapper<String> getBaseMapper() {
        return netCheckMapper;
    }

    @Override
    public String getInfoById(String netId) {
        //check has auth;
        boolean permission = permissionService.hasPermission(netId);
        if (permission){
            //get from redis
            String netInfo = redisService.getById(netId);
            if (Strings.isBlank(netInfo)){
                String result = netCheckMapper.selectByPrimaryKey(netId);
                return result;
            }
            return netInfo;
        }
        return "NoAuth";
    }
}