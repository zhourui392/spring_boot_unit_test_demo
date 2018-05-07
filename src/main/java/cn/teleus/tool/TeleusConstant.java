package cn.teleus.tool;

/**
 * Created by zhour on 2016/11/29.
 */
public class TeleusConstant {
    public static final String KEY_METHOD = "method";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_MSG = "msg";
    public static final String KEY_SERVICE_TYPE = "serviceType";
    public static final String KEY_CLIENT_TYPE = "clientType";
    public static final String KEY_CLIENT_ID = "clientId";
    public static final String KEY_DEVICE_SN = "deviceSN";
    public static final String KEY_CENTER_CODE = "centerCode";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_PAD_STATUS = "padStatus";
    public static final String KEY_BODY = "body";

    public static final String KEY_MSG_FROM = "from";
    public static final String KEY_MSG_TO = "to";

    public static final String METHOD_CONNECTED_ACK = "connectedAck";

    public static final String METHOD_GET_PAD_TOKEN_ACK = "getPadTokenAck";

    public static final String METHOD_LEAVE = "leave";

    public static final String METHOD_QUALITY_USER_LEAVE = "qualityUserLeave";

    public static final String METHOD_CALL = "calling";
    public static final String METHOD_CALL_ACK = "responseCall";

    public static final String METHOD_CANCEL_CALL = "cancelCall";

    public static final String METHOD_INVITE = "invite";
    public static final String METHOD_INVITE_ACK = "inviteAck";

    public static final String METHOD_ONE_TO_ONE = "onetoone";

    public static final String METHOD_DYNAMIC_PWD = "dynamicPwd";

    public static final String METHOD_SEND_TO_DISCONNECTED_ACK = "sendToDisconnectedAck";

    public static final String KEY_GROUP_KEY = "groupKey";

    public static final String KEY_CON_ID = "conId";

    public static final String KEY_MAX_GROUP_MEMBER = "maxGroupMember";



    public static final int CLIENT_TYPE_PAD = 1;
    public static final int CLIENT_TYPE_USER = 2;
    public static final int CLIENT_TYPE_TV = 3;
    public static final int CLIENT_TYPE_QUALITY = 4;
    public static final int CLIENT_TYPE_PAD_USER = 5;   //动态密码登录

    public static final int MAX_GROUP_MEMBER_DEFAULT = 6;

    public static final int PAD_STATUS_OFFLINE = 0;
    public static final int PAD_STATUS_ONLINE = 1;
    public static final int PAD_STATUS_BUSY = 2;

    public static final String KEY_TVDATAS = "tvDatas";
    public static final String KEY_TV_NAME = "userName";
    public static final String KEY_TV_CHINESENAME = "chineseName";
    public static final String KEY_TV_VIDEONUM = "videoNum";
    public static final String KEY_TV_LAYOUT = "layout";
    public static final String KEY_TV_SCREENS = "screens";
    public static final String KEY_QUALITY_USER_NAME = "qualityUserName";



    public static final String KEY_EBOX_SN = "eboxSN";
    public static final String KEY_ROOM_NAME = "roomName";

    public static final String KEY_ERROR_CODE = "errorCode";
    public static final int ERROR_CODE_TOO_MANY_JOIN_MEMBER = 5000;
    public static final int ERROR_GROUP_KEY_NOT_EXIST = 5001;

    public static final int ERROR_NO_DOCKER_NOLINE = 5002;
}
