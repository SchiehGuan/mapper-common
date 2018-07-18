package com.hfocean.common.mapper.exception;

import java.util.Locale;

/**
 * 基础错误接口默认实现
 * @author Administrator
 *
 */
public enum BaseBusinessError implements IBaseBusinessError{

	INTER_ERROR(500, "系统异常，请稍后重试", "inter_error"),

	SERVICE_ERROR(500, "暂无服务", "service_error"),
	
	PARAMETER_ERROR(400, "请求参数缺失或格式不正确", "parameter_error"),
	
	UNAUTHORIZED(401, "用户未登录", "unauthorized"),
	
	FORBIDDEN(403, "权限不足", "forbidden"),

	LOCKED_ACCOUNT(403, "禁用账号", "locked_account"),

	LOGIN_ERROR(403, "帐号或密码错误", "login_error"),

	NOT_FOUND(404, "没有找到内容", "not_found"),

	;
	
	int status;
	
	String message;
	
	String code;
	
	private BaseBusinessError(int status,String message,String code){
		this.status = status;
		this.message = message;
		this.code = code;
	}
	
	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage(Locale locale) {
		return message;
	}
	
}
