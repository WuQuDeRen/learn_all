package com.learn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class TestVo {
	@ApiModelProperty("名字")
	private String name;
	@ApiModelProperty("测试")
	private String test;
	public TestVo() {
		super();
	}
	public TestVo(String name, String test) {
		super();
		this.name = name;
		this.test = test;
	}
	
}
