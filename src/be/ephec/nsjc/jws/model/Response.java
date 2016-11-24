package be.ephec.nsjc.jws.model;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Response {
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body;
    private ResponseCode HTTPCode;

    /** Full constructor of Reponse
     * @param HTTPCode : the HTTP code
     * @param headers : a list of HTTP headers
     * @param body : the HTTP body
     */
    public Response(ResponseCode HTTPCode, HashMap<String, String> headers, String body) {
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
        this.headers.put(header.getLabel(), header.getValue());
    }

    /** Remove a header from the current list of headers
     * @param header : the header to remove
     * @return : a Header with the label and its value if it exists or null if it doesn't
     */
    public Header delHeader(Header header) {
        if (this.hasHeader(header)) {
            return this.delHeader(header.getLabel());
        }
        return null;
    }

    /** Remove a header from the current list of headers
     * @param label : the header label to remove
     * @return : a Header with the label and its value if it exists or null if it doesn't
     */
    public Header delHeader(String label) {
        if (this.headers.containsKey(label)) {
            return new Header(label, this.headers.remove(label));
        }
        return null;
    }

    /** Check if the header is in the list of headers
     * @param header : the header that will be checked
     * @return : whether the object is in the hashmap or not
     */
    public boolean hasHeader(Header header) {
        return this.hasHeader(header.getLabel()) && this.headers.get(header.getLabel()) == header.getValue();
    }

    /** Check if the label is in the list of headers
     * @param headerLabel : the label that will be checked
     * @return : whether the label is in the hashmap or not
     */
    public boolean hasHeader(String headerLabel) {
        return this.headers.containsKey(headerLabel);
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

        Set<String> labels = this.headers.keySet();
        Iterator<String> it = labels.iterator();
        String label;

        while(it.hasNext()) {
            label = it.next();
            res += label + ": " + this.headers.get(label) + "\r\n";
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
}