package be.ephec.nsjc.jws.model.test;

import be.ephec.nsjc.jws.model.Header;
import be.ephec.nsjc.jws.model.Response;
import be.ephec.nsjc.jws.model.ResponseCode;

import java.util.Set;

import static org.junit.Assert.*;


public class ResponseTest {

    private static Response res;

    @org.junit.Before
    public void setUp() {
        // Init a response with HTTPCode OK, 1 header and "Hello world" as html body

        Header headerContentType = new Header("Content-Type", "text/html; charset=UTF-8");

        Set<Header> headers = new Set<Header>();
        headers.add(headerContentType);

        res = new Response(ResponseCode.OK, headers, "<!DOCTYPE html>\r\n<html>\r\n\t<body>\r\n\t\tHello world !\r\n\t</body>\r\n</html>");
    }

    @org.junit.Test
    public void addHeader() throws Exception {
        Header headerContentLength = new Header("Content-Length", "50");

        assertFalse(res.hasHeader(headerContentLength));
        res.addHeader(headerContentLength.getLabel());
        assertTrue(res.hasHeader(headerContentLength));
    	
    }

    @org.junit.Test
    public void delHeader() throws Exception {
        Header headerContentType = new Header("Content-Type", "text/html; charset=UTF-8");
        Header headerContentLength = new Header("Content-Length", "50");

        // Remove a header label that's not in the response
        assertFalse(res.hasHeader(headerContentLength.getLabel()));
        res.delHeader(headerContentLength.getLabel());
        assertFalse(res.hasHeader(headerContentLength.getLabel()));

        // Remove a header label that's in the response
        assertTrue(res.hasHeader(headerContentType.getLabel()));
        res.delHeader(headerContentType.getLabel());
        assertFalse(res.hasHeader(headerContentType.getLabel()));

    }

    @org.junit.Test
    public void delHeade1r() throws Exception {
        Header headerContentTypePlain = new Header("Content-Type", "text/plain; charset=UTF-8");
        Header headerContentTypeHTML = new Header("Content-Type", "text/html; charset=UTF-8");

        // Remove a header that's not in the response
        assertFalse(res.hasHeader(headerContentTypePlain));
        res.delHeader(headerContentTypePlain);
        assertFalse(res.hasHeader(headerContentTypePlain));

        // Remove a header that's in the response
        assertTrue(res.hasHeader(headerContentTypeHTML));
        res.delHeader(headerContentTypeHTML);
        assertFalse(res.hasHeader(headerContentTypeHTML));
    }

    @org.junit.Test
    public void hasHeader() throws Exception {
        Header headerContentType = new Header("Content-Type", "text/html; charset=UTF-8");
        Header headerContentLength = new Header("Content-Length", "50");

        assertTrue(res.hasHeader(headerContentType.getLabel()));
        assertFalse(res.hasHeader(headerContentLength.getLabel()));
    }

    @org.junit.Test
    public void hasHeader1() throws Exception {
        Header headerContentTypePlain = new Header("Content-Type", "text/plain; charset=UTF-8");
        Header headerContentTypeHTML = new Header("Content-Type", "text/html; charset=UTF-8");
        assertFalse(res.hasHeader(headerContentTypePlain));
        assertTrue(res.hasHeader(headerContentTypeHTML));

    }

    @org.junit.Test
    public void testToString() throws Exception {
        String[] expected = {
                "HTTP1.1 200 OK",
                "Content-Type: text/html; charset=UTF-8",
                "",
                "<!DOCTYPE html>",
                "<html>",
                "    <body>",
                "        Hello world !",
                "    </body>",
                "</html>"
        };


        assertEquals(String.join("\r\n", expected).replaceAll("    ", "\t"), res.toString());
    }
}