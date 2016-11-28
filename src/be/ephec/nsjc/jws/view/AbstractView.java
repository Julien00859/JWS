package be.ephec.nsjc.jws.view;

import java.util.Observable;
import java.util.Observer;

import be.ephec.nsjc.jws.controller.JWSController;
import be.ephec.nsjc.jws.model.HTTPTrace;
import be.ephec.nsjc.jws.model.Request;
import be.ephec.nsjc.jws.model.Response;

public abstract class AbstractView implements Observer{
	protected HTTPTrace trace;
	protected JWSController controller;
	
	
	public AbstractView(HTTPTrace trace, JWSController controller) {
		this.trace = trace;
		this.controller = controller;
		this.trace.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg){
		//TODO 
	}
	

	
	
	
}
