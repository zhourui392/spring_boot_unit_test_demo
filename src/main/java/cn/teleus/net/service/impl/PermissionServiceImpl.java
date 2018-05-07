package cn.teleus.net.service.impl;

import cn.teleus.net.mapper.PermissionMapper;
import cn.teleus.net.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public boolean hasPermission(String netId) {
        return permissionMapper.hasPermission(netId);
    }
}
