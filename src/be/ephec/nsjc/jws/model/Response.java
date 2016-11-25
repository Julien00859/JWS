package be.ephec.nsjc.jws.model;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

import com.sun.xml.internal.ws.server.sei.EndpointArgumentsBuilder.Header;

public class Response {
    private Set<Header> headers = new Set<Header>();
    private String body;
    private ResponseCode HTTPCode;

    /** Full constructor of Reponse
     * @param HTTPCode : the HTTP code
     * @param headers : a list of HTTP headers
     * @param body : the HTTP body
     */
    public Response(ResponseCode HTTPCode, Set<Header> headers, String body) {
        this.HTTPCode = HTTPCode;
        this.headers = headers;
        this.body = body;
    }

    /** Constructor of Response without any header
     * @param HTTPCode : the HTTP code
     * @param body : the HTTP body
     */
    public Response(ResponseCode HTTPCode, String body) {
        this.HTTPCode = HTTPCode;
        this.body = body;
    }

    /** Constructor of Reponse without any headers and withut body
     * @param HTTPCode: the HTTP code
     */
    public Response(ResponseCode HTTPCode) {
        this.HTTPCode = HTTPCode;
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

    /** Convert the response to a sendable format
     * @return : a byte array of the current response
     */
    public byte[] toByteArray() {
        return this.toString().getBytes(StandardCharsets.UTF_8);
    }

    /** Convert the response to a printable format
     * @return : a string of the current response
     */
    public String toString() {
        String res = "";
        res += "HTTP1.1 " + this.HTTPCode.getCode() + " " + this.HTTPCode.getDescr() + "\r\n";

        Iterator<Header> it = this.headers.iterator();
        Header header;

        while(it.hasNext()) {
            header = it.next();
            res += header.getLabel() + ": " + header.getValue() + "\r\n";
        }

        res += "\r\n" + this.body;
        return res;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public String getBody() {
        return this.body;
    }

    public void setHTTPCode(ResponseCode HTTPCode) {
        this.HTTPCode = HTTPCode;
    }
    public ResponseCode getHTTPCode() {
        return this.HTTPCode;
    }
    
    @Orverride
    public boolean equals(Object other) {
    	if (other == null) return false;
    	if (other == this) return true;
    	if (other.getClass() != this.getClass()) return false;
    	
    	Response otherRes = (Response) other;
    	
    	if (!this.HTTPCode.equals(otherRes.HTTPCode)) return false;
    	if (!this.body.equals(otherRes.body)) return false;
    	if (this.headers.size() != otherRes.headers.size()) return false;
    	
    	Iterator it<Header> = this.headers.iterator();
    	Header header;
    	
    	while (it.hasNext()) {
    		header = it.next();
    		if (!otherRes.headers.contains(header)) return false;
    	}
    	
    	return true;
    }
}