package be.ephec.nsjc.jws.view;

import java.util.Observer;

import be.ephec.nsjc.jws.controller.JWSController;
import be.ephec.nsjc.jws.model.HTTPTrace;

public interface AbstractView extends Observer{

	public abstract void log(String log);
}
