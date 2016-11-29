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
		System.out.println(reqLine);
		InetAddress locale = InetAddress.getLocalHost();
		Socket s = new Socket(locale, 6587);
		String toSend = reqLine + "\r\n\r\n";
		s.getOutputStream().write(toSend.getBytes());
	}
	
	public void setView(AbstractView v){
		this.view = v;
	}
	
	
}
