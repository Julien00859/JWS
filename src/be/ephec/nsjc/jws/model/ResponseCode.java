package be.ephec.nsjc.jws.model;

public enum ResponseCode {
	CONTINUE(100, "Continue"),
	SWITCH_PROTOCOLS(101, "Switching Protocol"),
	
	OK(200, "OK"),
	
	BAD_REQUEST(400, "Bad request"),
	NOT_FOUND(404, "Not Found"),
	
	INTERNAL_ERROR(500, "Internal Server Error"),
	NOT_IMPL(501, "Not Implemented");
	
	private int code;
	private String descr;
	
	ResponseCode(int code, String descr){
		this.code = code;
		this.descr = descr;
	}

	public int getCode() {
		return code;
	}
	
	public String getDescr() {
		return descr;
	}
	
}
