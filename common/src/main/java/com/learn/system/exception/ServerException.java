package com.learn.system.exception;


import java.text.MessageFormat;

import com.learn.system.enums.ExceptionMsgEnum;

public class ServerException extends OptimusExceptionBase{
	private static final long serialVersionUID = -9202495170920439226L;

	private final static int HTTP_CODE = HttpStatusCodes.STATUS_500;

	public ServerException(ExceptionMsgEnum deepdriveMsgEnum) {
		super(HTTP_CODE, deepdriveMsgEnum.toString(), deepdriveMsgEnum.getDesc());
	}
	
	public ServerException(ExceptionMsgEnum deepdriveMsgEnum,Object ... arguments) {
		super(HTTP_CODE, deepdriveMsgEnum.toString(), MessageFormat.format(deepdriveMsgEnum.getDesc(), arguments));
	}
	
	public ServerException(ExceptionMsgEnum deepdriveMsgEnum,Throwable throwable) {
		super(HTTP_CODE, deepdriveMsgEnum.toString(), deepdriveMsgEnum.getDesc(),throwable);
	}
	
	public ServerException(ExceptionMsgEnum deepdriveMsgEnum,Throwable throwable,Object ... arguments) {
		super(HTTP_CODE, deepdriveMsgEnum.toString(), MessageFormat.format(deepdriveMsgEnum.getDesc(), arguments),throwable);
	}

	public ServerException(String exceptionMsg, String exceptionDes) {
		super(HTTP_CODE, exceptionMsg, exceptionDes);
	}
	
}
