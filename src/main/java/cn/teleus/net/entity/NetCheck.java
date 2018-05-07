package cn.teleus.net.entity;


import cn.teleus.net.entity.base.BaseNetCheck;
import cn.teleus.net.entity.dto.NetCheckDTO;

public class NetCheck extends BaseNetCheck {

    public static final byte TYPE_NORMAL = 1;

    public NetCheckDTO beDTO(){
        NetCheckDTO dto = new NetCheckDTO();
        dto.setId(getId());
        dto.setBandWidth(getBandWidth());
        dto.setCheckTime(getCheckTime());
        dto.setCheckType(getCheckType());
        dto.setNetShake(getNetShake());
        dto.setEboxSN(getEboxSN());
        dto.setServerName(getServerName());
        return dto;
    }
    public static NetCheck fromDTO(NetCheckDTO dto){
        NetCheck netCheck = new NetCheck();
        netCheck.setId(dto.getId());
        netCheck.setBandWidth(dto.getBandWidth());
        netCheck.setCheckTime(dto.getCheckTime());
        netCheck.setCheckType(dto.getCheckType());
        netCheck.setNetShake(dto.getNetShake());
        netCheck.setEboxSN(dto.getEboxSN());
        netCheck.setServerName(dto.getServerName());
        return netCheck;
    }
}