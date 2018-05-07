package cn.teleus.common;

import java.util.UUID;

public class CommonTool {
	/**
	 * uuid
	 * @return
     */
	public static String uuid(){
		return UUID.randomUUID().toString();
	}

	/**
	 * 去掉uuid中的-
	 * @return
     */
	public static String uuid32(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}