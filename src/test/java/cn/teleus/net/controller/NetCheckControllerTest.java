package cn.teleus.net.controller;

import cn.teleus.common.Root;
import cn.teleus.net.entity.NetCheck;
import cn.teleus.net.mapper.NetCheckMapper;
import cn.teleus.net.mapper.PermissionMapper;
import cn.teleus.net.service.NetCheckService;
import cn.teleus.net.service.PermissionService;
import cn.teleus.net.service.RedisService;
import cn.teleus.net.service.impl.NetCheckServiceImpl;
import cn.teleus.net.service.impl.PermissionServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhourui
 * @mail zhourui0125@gmail.com
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(NetCheckController.class)
@ActiveProfiles("junit")
public class NetCheckControllerTest {

    @Autowired
    MockMvc mvc;

    /**
     * 需要向里注入bean的使用@SpyBean注解
     */
    @SpyBean(name = "netCheckService")
    NetCheckServiceImpl netCheckService;

    @SpyBean(name = "permissionService")
    PermissionServiceImpl permissionService;


    /**
     * mapper等不需要依赖的类直接使用MockBean
     */
    @MockBean
    NetCheckMapper netCheckMapper;

    @MockBean
    PermissionMapper permissionMapper;

    @MockBean
    RedisService redisService;

    //data
    String netId = "10";
    String redisResult = "redis";
    String noPermission = "NoAuth";
    String dbResult = "db";

    String getNetInfoByIdUrl = "/netCheck/" + netId;

    @Test
    public void testNoPermission() throws Exception {
        //data
        given(permissionMapper.hasPermission(netId)).willReturn(false);

        this.mvc.perform(get(getNetInfoByIdUrl))
                .andExpect(status().isOk()).andExpect(result ->{
                    String response = result.getResponse().getContentAsString();
                    Root root = JSONObject.parseObject(response,Root.class);
                    assertEquals(1,root.getStatus().intValue());
                    assertEquals(noPermission,root.getData().toString());
                }
        );
    }

    @Test
    public void testGetFromRedis() throws Exception {
        //data
        given(permissionMapper.hasPermission(netId)).willReturn(true);
        given(redisService.getById(netId)).willReturn(redisResult);


        this.mvc.perform(get(getNetInfoByIdUrl))
                .andExpect(status().isOk()).andExpect(result ->{
                    String response = result.getResponse().getContentAsString();
                    Root root = JSONObject.parseObject(response,Root.class);
                    assertEquals(redisResult,root.getData().toString());
                }
        );
    }

    @Test
    public void testGetFromDB() throws Exception {
        //data
        given(permissionMapper.hasPermission(netId)).willReturn(true);
        given(redisService.getById(netId)).willReturn(null);
        given(netCheckMapper.selectByPrimaryKey(netId)).willReturn(dbResult);

        this.mvc.perform(get(getNetInfoByIdUrl))
                .andExpect(status().isOk()).andExpect(result ->{
                    String response = result.getResponse().getContentAsString();
                    Root root = JSONObject.parseObject(response,Root.class);
                    assertEquals(dbResult,root.getData().toString());
                }
        );
    }
}
