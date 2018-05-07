package cn.teleus.net.entity.dto;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class NetCheckDTO{
    private Integer id;

    @NotNull(message = "EBox不能为空")
    private String eboxSN;

    private String serverName;

    private String bandWidth;

    private String netShake;

    private Byte checkType;

    private Date checkTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEboxSN() {
        return eboxSN;
    }

    public void setEboxSN(String eboxSN) {
        this.eboxSN = eboxSN == null ? null : eboxSN.trim();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public String getBandWidth() {
        return bandWidth;
    }

    public void setBandWidth(String bandWidth) {
        this.bandWidth = bandWidth == null ? null : bandWidth.trim();
    }

    public String getNetShake() {
        return netShake;
    }

    public void setNetShake(String netShake) {
        this.netShake = netShake == null ? null : netShake.trim();
    }

    public Byte getCheckType() {
        return checkType;
    }

    public void setCheckType(Byte checkType) {
        this.checkType = checkType;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}