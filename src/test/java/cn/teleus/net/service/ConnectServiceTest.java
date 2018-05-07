package cn.teleus.net.service;

import cn.teleus.data.TokenWithMaId;
import cn.teleus.service.ConnectService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

/**
 * Created by zhour on 2017/10/12.
 * 测试Service等类型
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConnectService.class,TokenWithMaId.class})
@ActiveProfiles("junit")
public class ConnectServiceTest{

    @Resource
    ConnectService connectService;

    @Resource
    TokenWithMaId tokenWithMaId;

    private String sessionId;
    private String maId;
    @Before
    public void before(){
        sessionId = UUID.randomUUID().toString();
        maId = "RTb00001";
    }

    @Test
    public void testProcessClientInfo(){
        connectService.processClientInfo(sessionId,maId);
        Assert.assertEquals(tokenWithMaId.get(maId),sessionId);
    }
}
