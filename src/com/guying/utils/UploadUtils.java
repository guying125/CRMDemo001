package com.guying.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 * @author QJYang
 *
 */
public class UploadUtils {
	
	/**
	 * 传入文件的名称，返回唯一的名称
	 * @param filename 文件名称
	 * @return	唯一的文件名称
	 */
	public static String  getUUIDName(String filename) {
		// 先查找
		int index = filename.lastIndexOf(".");
		// 截取后缀
		String lastname = filename.substring(index, filename.length());
		// 唯一字符串 fasd-fasdad-afadp-ad-af-asd-afqd-dasd（要去除横线）
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		
		return uuid+lastname;
	}

}
