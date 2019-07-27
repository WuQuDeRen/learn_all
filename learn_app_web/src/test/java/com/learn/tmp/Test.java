package com.learn.tmp;

import com.alibaba.fastjson.JSON;
import com.learn.system.util.AssembleUtil;
import com.learn.system.util.HttpClientUtils;
import com.learn.system.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.collections.CollectionUtils;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {


	private static Logger logger = LoggerFactory.getLogger(Test.class);

	// 钉钉机器人消息通知（在钉钉群里添加 机器人，进入机器人管理页面，获取 post 请求的 url）
	public static void main(String[] args) throws InterruptedException {
		String dingdingRobotRL = "https://oapi.dingtalk.com/robot/send?access_token=0b9caa075219dd250eea763e20f4ca9cadbd8a31829414092d89f78c67658742";

		Map<String, Object> requestBody = Maps.newHashMap();
		requestBody.put("msgtype", "text");
		Map<String, String> requestContent = Maps.newHashMap();
		requestContent.put("content", "来了，老弟");
		requestBody.put("text", requestContent);

		HttpClientUtils.httpPost(dingdingRobotRL, JSON.toJSONString(requestBody));




	}
}
