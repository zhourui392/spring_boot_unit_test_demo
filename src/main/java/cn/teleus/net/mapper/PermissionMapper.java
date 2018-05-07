package cn.teleus.net.mapper;

public interface PermissionMapper extends BaseMapper<String> {
    boolean hasPermission(String netId);
}