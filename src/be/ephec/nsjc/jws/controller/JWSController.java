package be.ephec.nsjc.jws.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import be.ephec.nsjc.jws.model.HTTPTrace;
import be.ephec.nsjc.jws.view.AbstractView;

public class JWSController {
	private AbstractView view;
	private HTTPTrace trace;
	
	public JWSController(HTTPTrace trace) {
		this.trace = trace;
	}
	
	public void logToView(String log){
		view.log(log);
	}
	
	public void handleInput(String reqLine) throws IOException{
		InetAddress locale = InetAddress.getByName("0:0:0:0:0:0:0:1");
		Socket s = new Socket(locale, 6587);
		String toSend = reqLine + "\r\nUser-Agent: JWS Console/1.0\r\n\r\n";
		s.getOutputStream().write(toSend.getBytes());
		s.close();
	}
	
	public void setView(AbstractView v){
		this.view = v;
	}
	
	
}
