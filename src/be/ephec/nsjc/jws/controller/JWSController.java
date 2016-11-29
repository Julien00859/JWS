package be.ephec.nsjc.jws.controller;

import be.ephec.nsjc.jws.model.HTTPTrace;
import be.ephec.nsjc.jws.view.AbstractView;

public class JWSController {
	private AbstractView view;
	private HTTPTrace trace;
	
	public JWSController(AbstractView view, HTTPTrace trace) {
		this.view = view;
		this.trace = trace;
	}
	
	public void logToView(String log){
		view.log(log);
	}
	
	public void handleInput(String reqLine){
		
	}
	
	
}
