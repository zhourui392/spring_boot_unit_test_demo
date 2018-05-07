package cn.teleus.net.controller;

import cn.teleus.common.Root;
import cn.teleus.common.page.PageQuery;
import cn.teleus.data.TokenWithMaId;
import cn.teleus.net.service.NetCheckService;
import cn.teleus.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class NetCheckController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(NetCheckController.class);

    @Resource
    private NetCheckService netCheckService;

    /**
     * 获取NetInfo信息
     */
    @ResponseBody
    @RequestMapping(value="/netCheck/{id}", method= RequestMethod.GET)
    public String getNetInfoById(@PathVariable(value = "id") String netId) {
        String netInfo = netCheckService.getInfoById(netId);
        return Root.getRootOKAndSimpleMsg().setData(netInfo).toJsonString();
    }
}