package cn.xinguan.utils;

import java.util.UUID;

import cn.xinguan.damain.NatureInfo;

/**
 * 自动生成用户id
 * 
 * @author MingJun Chen
 * 
 */
public class MemberUtils {
	public static String generateID(NatureInfo natureInfo) {
		String uuid = UUID.randomUUID().toString();
		int hashcode = Math.abs(uuid.hashCode());

		return hashcode + "";
	}

}
