package cn.teleus.data;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by zhour on 2017/2/9.
 */
@Component
public class TokenWithMaId {
    /**
     * maId,token
     */
    private static ConcurrentMap<String,String> maIdMap = new ConcurrentHashMap<>();
    /**
     * token,maId
     */
    private static ConcurrentMap<String,String> tokenMap = new ConcurrentHashMap<>();

    public void put(String maId, String token){
        if (StringUtils.isNotBlank(maId)){
            maId = Strings.toLowerCase(maId);
            maIdMap.put(maId,token);
            tokenMap.put(token,maId);
        }
    }
    public String get(String maId){
        if (maId == null){
            return null;
        }
        maId = Strings.toLowerCase(maId);
        return maIdMap.get(maId);
    }
}
