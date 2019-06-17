package com.learn.controller.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.vo.ReceiveModel;
import com.learn.vo.TestVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "", tags = "Test接口")
@RestController
public class TestController {

	private Logger logger = LoggerFactory.getLogger(TestController.class);

	@ApiOperation(value = "测试B操作", httpMethod = "GET")
	@RequestMapping(value = "/b")
	public String getJsonB() {
		logger.info("--请求---{}", "b");
		return "{\"name\": \"b\"}";
	}

	@ApiOperation(value = "测试A操作", httpMethod = "GET")
	@RequestMapping(value = "/a")
	public TestVo getJsonA(@RequestBody @ApiParam(required = true, name="用户对象",value="传入json格式") ReceiveModel model) {
		return new TestVo("fj", "test_b");
	}
}
