package com.learn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "测试接受")
@Data
public class ReceiveModel {
	@ApiModelProperty(value = "名字")
	private String name;
	@ApiModelProperty(value = "属性")
	private String propertyId;

	public ReceiveModel() {
	}

	public ReceiveModel(String name, String propertyId) {
		this.name = name;
		this.propertyId = propertyId;
	}
}
