package be.ephec.nsjc.jws;

import be.ephec.nsjc.jws.controller.GuiController;
import be.ephec.nsjc.jws.controller.JWSController;
import be.ephec.nsjc.jws.internal.ServerThread;
import be.ephec.nsjc.jws.model.HTTPTrace;
import be.ephec.nsjc.jws.view.ConsoleView;
import be.ephec.nsjc.jws.view.GuiView;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		HTTPTrace trace = new HTTPTrace();
		
		JWSController controller = new JWSController(trace);
		ConsoleView view = new ConsoleView(trace, controller);
		controller.setView(view);
		
		ServerThread st = new ServerThread(trace);
		st.addController(controller);
		Thread t = new Thread(st);
		t.start();
		
		
		//JavaFX things... Controller instance is automatically given, just "launch the view"
		Thread guiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GuiView.loadView();
			}
		});
		guiThread.start();
		GuiController guiController = GuiView.getController();
		while(guiController == null){
			guiController = GuiView.getController();
			Thread.yield();
		}
		guiController.addHTTPTrace(trace);
		guiController.setServer(st);
		
		t.join();
	}

}
