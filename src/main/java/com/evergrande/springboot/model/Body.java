package com.evergrande.springboot.model;

public class Body {

	private final static String SUCCESS_CODE = "200";
	private final static String ERROR_CODE = "300";
	private String status;
	private String message;

	public String getStatus() {
		return status;
	}

	public Body() {
		this.message = "";
		success();
	}

	public Body setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Body setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(status == null ? "" : "\"status\" : \"" + status + "\",");
		sb.append(message == null ? "" : "\"message\" : \"" + message + "\",");
		sb = sb.toString().equals("{") ? sb.append("}") : sb.replace(sb.length() - 1, sb.length(), "}");
		return sb.toString();
	}

	/**
	 * 设置status值200
	 * 
	 * @return
	 */
	public Body success() {
		return setStatus(SUCCESS_CODE);
	}

	/**
	 * 设置status值300
	 * 
	 * @return
	 */
	public Body error() {
		return setStatus(ERROR_CODE);
	}

	public static void main(String[] args) {
		Body rb = new Body();
		rb.setMessage("200");
		System.out.println(rb.toString());
	}

}
