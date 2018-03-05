package com.tentcoo.utils;

public class JsonResult {
	private boolean success;
	private String msg;

	public JsonResult() {
	}

	public JsonResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public JsonResult setErrorMsg(String msg) {
		this.success = false;
		this.msg = msg;
		return this;
	}

	public JsonResult setSuccessMsg(String msg) {
		this.success = true;
		this.msg = msg;
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
