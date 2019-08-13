package com.learn.system.enums;

public enum ExceptionMsgEnum {
	
	PARAMS_EMPTY("入参为空"),
	DATA_NOT_MATCH("数据不匹配");
	
	private String desc;

	private ExceptionMsgEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	

}
