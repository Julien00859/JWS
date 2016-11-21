package be.ephec.nsjc.jws.model.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import be.ephec.nsjc.jws.model.Header;

public class HeaderTest {
	
	static Header toTest;
	
	@BeforeClass
	public static void setUpClass(){
		toTest = new Header("Content-Length", "2048");
	}

	
	
	@Test
	public void testEquals(){
		Header h = new Header("Via", "Something");
		assertNotEquals(true, toTest.equals(h));
		assertNotEquals(true, toTest.equals("NotAHeader"));
		h = new Header("Content-Length", "2048");
		assertEquals(true, toTest.equals(h));
	}
	
	@Test
	public void testToString(){
		assertEquals("Header [label=Content-Length, value=2048]", toTest.toString());
	}

}
