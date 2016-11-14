package be.ephec.nsjc.jws.model;

public enum ResponseCode {
	CONTINUE(100, "Continue"),
	SWITCH_PROTOCOLS(101, "Switching Protocol"),
	
	OK(200, "OK"),
	CREATED(201, "Created"),
	ACCEPTED(202, "Accepted"),
	NO_AUTH_INFO(203, "Non-Authoritative Information"),
	NO_CONTENT(204, "No content"),
	RESET_CONTENT(205, "Reset content"),
	PARTIAL_CONTENT(206, "Partial content"),
	
	MULTIPLE_CHOISES(300, "Multiples choises"),
	MOVED(301, "Moved permanently"),
	FOUND(302, "Found"),
	SEE_OTHER(303, "See Other"),
	NOT_MODIFIED(304, "Not Modified"),
	USE_PROXY(305, "Use proxy"),
	TEMP_REDIR(307, "Temporary Redirect"),
	PERM_REDIR(308, "Permanent Redirect"),
	TOO_MANY_REDIR(310, "Too many Redirects"),
	
	BAD_REQUEST(400, "Bad request"),
	UNAUTHORIZED(401, "Unauthorized"),
	PAYMENT_REQUIRED(402, "Payment Required"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	METH_NOT_ALLOWED(405, "Method Not Allowed"),
	NOT_ACCEPTABLE(406, "Not Acceptable"),
	PROXY_AUTH_REQ(407, "Proxy Authentication Required"),
	REQ_TIMEOUT(408, "Request Time-out"),
	CONFLICT(409, "Conflict"),
	GONE(410, "Gone"),
	LENGTH_REQ(411, "Length Required"),
	PREREQ_FAILED(412, "Precondition Failed"),
	REQ_ENT_TOO_LARGE(413, "Request Entity Too Large"),
	TOO_LONG(414, "Request-URI Too Long"),
	MEDIA_TYPE(415, "Unsupported Media Type"),
	REQ_RANGE_UNSATIS(416, "Requested range unsatisfiable"),
	EXP_FAILED(417, "Expectation failed"),
	TEAPOT(418, "I’m a teapot"),
	BAD_MAP(421, "Bad mapping / Misdirected Request"),
	
	INTERNAL_ERROR(500, "Internal Server Error"),
	NOT_IMPL(501, "Not Implemented"),
	BAD_GATEWAY(502, "Bad Gateway"),
	SERV_UNVAILABLE(503, "Service Unavailable"),
	GATEWAY_TIMEOUT(504, "Gateway Time-out"),
	BAD_HTTP_VERSION(505, "HTTP Version not supported"),
	QUOTA_EXCEEDED(509, "Bandwidth Limit Exceeded"),
	UNKNOWN_ERROR(520, "");
	
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
