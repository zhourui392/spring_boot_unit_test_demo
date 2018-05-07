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
    public void remove(String maId){
        maId = Strings.toLowerCase(maId);
        String token = maIdMap.remove(maId);
        if (token != null){
            tokenMap.remove(token);
        }
    }

    public String get(String maId){
        if (maId == null){
            return null;
        }
        maId = Strings.toLowerCase(maId);
        return maIdMap.get(maId);
    }

    public List<String> getAllMaId() {
        List<String> maIds = new ArrayList<>();
        maIdMap.keySet().forEach(maId -> maIds.add(maId));
        return maIds;
    }

    public void removeByToken(String token) {
        String maId = tokenMap.remove(token);
        if (maId != null){
            maId = Strings.toLowerCase(maId);
            maIdMap.remove(maId);
        }
    }
}
