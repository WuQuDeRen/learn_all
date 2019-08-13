package com.learn.system.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

public class Base64Util {

    public static String encrypt(String s){
        if(StringUtils.isEmpty(s)){
            return "";
        }
        s = new String(Base64.encodeBase64(s.getBytes()));
        return s;
    }

    public static String decrypt(String s){
        if(StringUtils.isEmpty(s)){
            return "";
        }
        s = new String(Base64.decodeBase64(s.getBytes()));
        return s;
    }

}