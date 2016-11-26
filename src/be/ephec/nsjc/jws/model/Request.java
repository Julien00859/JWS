package be.ephec.nsjc.jws.model;

import java.util.HashSet;
import java.util.Iterator;

public class Request {

	private String method;
	private String requestURI;
	private String httpVersion;
	private HashSet<Header> headers;
	private String body;
	
	
	public Request(String method, String requestURI, String httpVersion) {
		this.method = method;
		this.requestURI = requestURI;
		this.httpVersion = httpVersion;
		this.headers = new HashSet<Header>();
		this.body = "";
	}

	public Request(String method, String requestURI, String httpVersion, HashSet<Header> headers) {
		this.method = method;
		this.requestURI = requestURI;
		this.httpVersion = httpVersion;
		this.headers = headers;
		this.body = "";
	}

	public Request(String method, String requestURI, String httpVersion, HashSet<Header> headers, String body) {
		this.method = method;
		this.requestURI = requestURI;
		this.httpVersion = httpVersion;
		this.headers = headers;
		this.body = body;
	}
	
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the requestURI
	 */
	public String getRequestURI() {
		return requestURI;
	}

	/**
	 * @param requestURI the requestURI to set
	 */
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	/**
	 * @return the httpVersion
	 */
	public String getHttpVersion() {
		return httpVersion;
	}

	/**
	 * @param httpVersion the httpVersion to set
	 */
	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	/**
	 * @return the headers
	 */
	public HashSet<Header> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(HashSet<Header> headers) {
		this.headers = headers;
	}

	/** Convert the Request to a printable format
     * @return : a string of the current request
     */
	@Override
	public String toString() {
		return "Request [method=" + method + ", requestURI=" + requestURI + ", httpVersion=" + httpVersion
				+ ", headers=" + headers + ", body=" + body + "]";
	}

	/** Append a header to the current list of headers
     * @param header : the header to append
     */
    public void addHeader(Header header) {
        this.headers.add(header);
    }
	

	/** Remove a header from the current list of headers
     * @param header : the header to remove
     */
    public void delHeader(Header header) {
    	this.headers.remove(header);
    }
    
    /** Remove a label from the current list of headers
     * @param label : the label
     */
    public void delHeader(String label) {
    	Iterator<Header> it = this.headers.iterator();
    	Header header;
    	
    	while(it.hasNext()) {
    		header = it.next();
    		if (header.getLabel().equals(label)) {
    			this.headers.remove(header);
    		}
    	}
    }

    /** Check if the header is in the list of headers
     * @param header : the header that will be checked
     * @return : whether the object is in the set or not
     */
    public boolean hasHeader(Header header) {
    	return this.headers.contains(header);
    }

    /** Check if the label is in the list of headers
     * @param label : the label that will be checked
     * @return : whether a header for the label is in the set or not
     */
    public boolean hasHeader(String label) {
        Iterator<Header> it = this.headers.iterator();
        Header header;
        
        while (it.hasNext()) {
        	header = it.next();
        	if (header.getLabel().equals(label)) {
        		return true;
        	}
        }
        return false;
    }
    /** Return the header having this label
     * @param label : the label that will be checked
     * @return : the Header with this label or null if there is no header with this label
     */
    public Header getHeader(String label){
    	Iterator<Header> it = this.headers.iterator();
        Header header;
        
        while (it.hasNext()) {
        	header = it.next();
        	if (header.getLabel().equals(label)) {
        		return header;
        	}
        }
        return null;
    }
    
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody(){
		return this.body;
	}
	
	
	
	
}

