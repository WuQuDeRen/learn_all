package com.learn.system.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

public class OpenPlatformSignUtil {
	/**
	 * 生成签证
	 * @author ji_fei
	 * @param appSecret
	 * @param param
	 * @return
	 */
	public static String generateSign(String appSecret, Map<String, Object> param) {
	    TreeMap<String, Object> map = new TreeMap<>(param);
	    StringBuilder signString = new StringBuilder();
	    for(String k : map.keySet()){
	        Object v = map.get(k);
	        signString.append(k).append("=").append(v).append("&");
	    }
	    signString.delete(signString.length() - 1, signString.length());
	    String base64 = Base64Util.encrypt(signString.toString());      
	    
	    return DigestUtils.sha1Hex(appSecret.concat(":").concat(base64));
	}
}
