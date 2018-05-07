package cn.teleus.net.service;

import cn.teleus.service.base.BaseService;

public interface NetCheckService extends BaseService<String> {

    /**
     *
     * @param netId
     * @return
     */
    String getInfoById(String netId);
}