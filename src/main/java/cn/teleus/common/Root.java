package cn.teleus.common;

import com.alibaba.fastjson.JSONObject;

public class Root {
	private Integer status = 0;
	private String msg = "";
	private Object data;
	
	public static String MSG_OK = "成功";
	public static String MSG_FAIL = "失败";
	
	public static int STATUS_OK = 1;
	public static int STATUS_FAIL = 0;
	public static int STATUS_PARAMS_ERROR = 101;
	
	public Integer getStatus() {
		return status;
	}
	public Root setStatus(Integer status) {
		this.status = status;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public Root setData(Object data) {
		this.data = data;
		return this;
	}
	public String toJsonString(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", getMsg());
		jsonObject.put("status", getStatus());
		jsonObject.put("data", getData());
		return jsonObject.toJSONString();
	}
	
	/**
	 * 简便的方法获取成功状态的ROOT
	 * @param msg 成功后的信息
	 */
	public static Root getRootOK(String msg){
		Root root = new Root();
		root.setStatus(STATUS_OK);
		root.setMsg(msg);
		return root;
	}
	
	/**
	 * 默认成功状态的ROOT
	 */
	public static Root getRootOKAndSimpleMsg(){
		Root root = new Root();
		root.setStatus(STATUS_OK);
		root.setMsg(MSG_OK);
		return root;
	}
	
	/**
	 * 简便的方法获取失败状态的ROOT
	 * @param msg 失败后的信息
	 */
	public static Root getRootFail(String msg){
		Root root = new Root();
		root.setStatus(STATUS_FAIL);
		root.setMsg(msg);
		return root;
	}

	/**
	 * 简便的方法获取失败状态的ROOT
	 * @param msg 失败后的信息
	 */
	public static Root getRootParamsError(String msg){
		Root root = new Root();
		root.setStatus(STATUS_PARAMS_ERROR);
		root.setMsg(msg);
		return root;
	}

	/**
	 * 简便的方法获取失败状态的ROOT
	 * @param msg 失败后的信息
	 * @param errorCode 错误代码
	 */
	public static Root getRootFail(String msg,Integer errorCode){
		Root root = new Root();
		root.setStatus(STATUS_FAIL);
		root.setMsg(msg);
		root.setStatus(errorCode);
		return root;
	}

	/**
	 * 简便的方法获取失败状态的ROOT
	 * @param errorCode 错误代码
	 */
	public static Root getRootFail(Integer errorCode){
		return getRootFail(MSG_FAIL,errorCode);
	}
	
	/**
	 * 默认失败状态的ROOT
	 */
	public static Root getRootFailAndSimpleMsg(){
		Root root = new Root();
		root.setStatus(STATUS_FAIL);
		root.setMsg(MSG_FAIL);
		return root;
	}
}
