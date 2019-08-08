package com.learn.system.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc  发送消息
 * @author ji_fei
 * @date  2019-08-02 16:08
 */
public class DingdingUtil {

    private static Logger logger = LoggerFactory.getLogger(DingdingUtil.class);

    private static String DINGDING_URL = "https://oapi.dingtalk.com/robot/send?access_token=24e263e5d7778c70c2733376db76449fd24bf504ffe4290f3e81f183e9c98b03";

    private static String MSG_TYPE = "text";

    private static String environment = "dev";



    public static void sendMsg(String msg) {
        logger.info("desc => 发送钉钉消息 Param => msg【{}】", JSONUtil.toString(msg));

        try {
            Map body = new HashMap();
            body.put("msgtype", MSG_TYPE);
            Map contentMap = new HashMap();
            contentMap.put("content", "环境：environment\n时间：" + JSONUtil.toString(new Date())+ "\n" + msg);
            body.put("text", JSON.toJSONString(contentMap));
            Map<String, String> header = Maps.newHashMapWithExpectedSize(1);
            header.put("Accept", "application/json");

            logger.info("sendMsg result => body【{}】", body);
            String result  = HttpClientUtils.postStr(DINGDING_URL, body, header);
            logger.info("sendMsg result => result【{}】", result);
        } catch (Exception e) {
            logger.error("推送钉钉异常 params => msg【{}】", msg, e);
        }
    }

}
