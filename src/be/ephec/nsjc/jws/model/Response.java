package be.ephec.nsjc.jws.model;

public class Response {
    private HashMap<String, String> headers = new HashMap();
    private String body;
    private ReponseCode HTTPCode;

    public Response(ResponseCode HTTPCode, HashMap<String, String> headers, String body) {
        /** Full constructor of Reponse
         * param code : the HTTP code
         * param headers : a list of HTTP headers
         * param body : the HTTP body
         */
        this.HTTPCode = HTTPCode;
        this.headers = headers;
        this.body = body;
    }

    public Response(ResponseCode HTTPCode, char[] body) {
        /** Constructor of Response without any header
         * param code : the HTTP code
         * param body : the HTTP body
         */
        this.HTTPCode = HTTPCode;
        this.body = body;
    }

    public Response(ResponseCode code) {
        /** Constructor of Reponse without any headers and withut body
         * param code: the HTTP code
         */
        this.code = code;
    }

    public void addHeader(Header header) {
        /** Append a header to the current list of headers
         * param header : the header to append
         */
        this.headers.set(header.label, header.value);
    }

    public Header delHeader(String label) {
        /** Remove a header from the current list of headers
         * param label : the header label to remove
         * return : a Header with the label and its value if it exists or null
         */
        if (this.headers.containsKey(label)) {
            return new Header(label, this.headers.remove(label));
        }
        return null;
    }

    public boolean hasHeader(Header header) {
        /** Check if the header is in the list of headers
         * param header : the header that will be checked
         * return : whether the object is in the hashmap or not
         */
        return this.hasHeader(header.label);
    }

    public boolean hasHeader(String headerLabel) {
        /** Check if the label is in the list of headers
         * param headerLabel : the label that will be checked
         * return : whether the label is in the hashmap or not
         */
        return this.headers.containsKey(headerLabel);
    }

    public byte[] toByteArray() {
        /** Convert the response to a sendable format
         * return : a byte array of the current response
         */
        return this.toString().getBytes(StandardCharsets.UTF_8);
    }

    public String toString() {
        /** Convert the response to a printable format
         * return : a string of the current response
         */
        String res = "";
        res += "HTTP1.1 " + this.HTTPCode.code + " " + this.HTTPCode.desc + "\r\n";
        for (String label in this.headers) {
            res += label + " " + this.headers.get(label) + "\r\n"
        }
        res += this.body;
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