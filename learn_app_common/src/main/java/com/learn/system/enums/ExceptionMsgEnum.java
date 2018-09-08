package com.learn.system.enums;

public enum ExceptionMsgEnum {
	
	PARAMS_EMPTY("入参为空");
	
	private String desc;

	private ExceptionMsgEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	

}
