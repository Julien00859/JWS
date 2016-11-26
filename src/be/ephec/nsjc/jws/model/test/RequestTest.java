package be.ephec.nsjc.jws.model.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.BeforeClass;
import org.junit.Test;

import be.ephec.nsjc.jws.model.Header;
import be.ephec.nsjc.jws.model.Request;

public class RequestTest {
	
	private static Request r;

	@BeforeClass
	public static void setUpClass(){
		r = new Request("GET", "/", "HTTP/1.1");
		r.addHeader(new Header("Content-Length", String.valueOf(4)));
		r.addHeader(new Header("Content-Encoding", "gzip"));
		r.setBody("Test");
	}

	@Test
	public void testToString() {
		assertEquals(r.toString(), "Request [method=GET, requestURI=/, httpVersion=HTTP/1.1, headers=[Header [label=Content-Encoding, value=gzip], Header [label=Content-Length, value=4]], body=Test]");
	}
	
	@Test
	public void testHeaderCount(){
		assertEquals(2, r.getHeaders().size());
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat(
	        "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    String date = dateFormat.format(calendar.getTime());
		r.addHeader(new Header("Date", date));
		assertEquals(3, r.getHeaders().size());
	}

}
