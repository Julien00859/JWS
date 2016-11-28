package be.ephec.nsjc.jws.internal.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.BeforeClass;
import org.junit.Test;

import be.ephec.nsjc.jws.internal.RequestHandler;
import be.ephec.nsjc.jws.model.Request;

public class RequestHandlerTest {
	
	private static RequestHandler requestHandler;
	private static BufferedReader reader;
	private static final String request = "POST / HTTP/1.1\r\n"
			+ "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"
			+ "Host: www.tutorialspoint.com\r\n"
			+ "Accept-Language: en-us\r\n"
			+ "Accept-Encoding: gzip, deflate\r\n"
			+ "Connection: Keep-Alive\r\n"
			+ "\r\n"
			+ "A simple body\r\n"
			+ "On two lines";

	@BeforeClass
	public static void setUpBeforeClass() {
		InputStream is = new ByteArrayInputStream(request.getBytes());
		reader = new BufferedReader(new InputStreamReader(is));
		requestHandler = new RequestHandler(reader);
	}

	@Test
	public void testToString() throws IOException {
		Request r = requestHandler.parseRequest();
		assertEquals("Request [method=POST, requestURI=/, httpVersion=HTTP/1.1, headers=[Header [label=Accept-Encoding, value=gzip, deflate], Header [label=Host, value=www.tutorialspoint.com], Header [label=User-Agent, value=Mozilla/4.0 (compatible; MSIE5.01; Windows NT)], Header [label=Accept-Language, value=en-us], Header [label=Connection, value=Keep-Alive]], body=A simple body\r\nOn two lines]", r.toString());
	}
	
	@Test
	public void testGetIgnoreBody() throws IOException{
		String req = "GET / HTTP/1.1\r\n"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"
				+ "Host: www.tutorialspoint.com\r\n"
				+ "Accept-Language: en-us\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: Keep-Alive\r\n"
				+ "\r\n"
				+ "A simple body\r\n"
				+ "On two lines";
		InputStream is = new ByteArrayInputStream(req.getBytes());
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(is));
		RequestHandler requestHandler2 = new RequestHandler(reader2);
		Request r2 = requestHandler2.parseRequest();
		assertEquals("Request [method=GET, requestURI=/, httpVersion=HTTP/1.1, headers=[Header [label=Accept-Encoding, value=gzip, deflate], Header [label=Host, value=www.tutorialspoint.com], Header [label=User-Agent, value=Mozilla/4.0 (compatible; MSIE5.01; Windows NT)], Header [label=Accept-Language, value=en-us], Header [label=Connection, value=Keep-Alive]], body=]", r2.toString());
	}
	
	@Test
	public void testBadHTML() throws IOException{
		String req = "yolo this is bad HTML";
		InputStream is = new ByteArrayInputStream(req.getBytes());
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(is));
		RequestHandler requestHandler2 = new RequestHandler(reader2);
		Request r2 = requestHandler2.parseRequest();
		assertEquals(null, r2);

	}

}
