package be.ephec.nsjc.jws;

import be.ephec.nsjc.jws.controller.JWSController;
import be.ephec.nsjc.jws.internal.ServerThread;
import be.ephec.nsjc.jws.model.HTTPTrace;
import be.ephec.nsjc.jws.view.ConsoleView;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		HTTPTrace trace = new HTTPTrace();
		
		JWSController controller = new JWSController(trace);
		ConsoleView view = new ConsoleView(trace, controller);
		controller.setView(view);
		ServerThread st = new ServerThread(trace);
		Thread t = new Thread(st);
		t.start();
		t.join();
	}

}
