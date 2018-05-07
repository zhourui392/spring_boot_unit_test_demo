package cn.teleus.data;

public class ServerNameInfo {
    private String serverName;
    private String serverIp;
    private String serverPort;

    public ServerNameInfo(String serverName, String serverIp, String serverPort) {
        this.serverName = serverName;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public static ServerNameInfo getByName(String name){
        if ("dev".equals(name)){
            return new ServerNameInfo("dev","dev.teleus.cn","80");
        }
        return new ServerNameInfo("dev","dev.teleus.cn","80");
    }

    public static ServerNameInfo getByIP(String ip){
        if ("dev.teleus.cn".equals(ip)){
            return new ServerNameInfo("dev","dev.teleus.cn","80");
        }
        return new ServerNameInfo("dev","dev.teleus.cn","80");
    }
}
