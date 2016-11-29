package be.ephec.nsjc.jws.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import be.ephec.nsjc.jws.controller.JWSController;
import be.ephec.nsjc.jws.model.HTTPTrace;
import be.ephec.nsjc.jws.model.Request;
import be.ephec.nsjc.jws.model.Response;

public class ServerThread implements Runnable {
	
	private boolean running;
	ServerSocket serverSocket;
	HTTPTrace trace;
	
	ArrayList<JWSController> controllers = new ArrayList<JWSController>();
	
	public ServerThread(HTTPTrace trace){
		this.trace = trace;
		this.running = true;
	}
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(6587);
			while(running){
				Socket clientSocket = serverSocket.accept();
				for(JWSController ctrl : controllers){
					ctrl.logToView("New Connection from "+clientSocket.getRemoteSocketAddress().toString());
				}
				BufferedReader bf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				RequestHandler reqHandler = new RequestHandler(bf);
				Request req = reqHandler.parseRequest();
				int counter = trace.addRequest(req);
				//TODO Create the response
				if(req != null){
					ResponseBuilder respBuilder = new ResponseBuilder(req);
					Response res = respBuilder.buildResponse();
					if(res != null){
						clientSocket.getOutputStream().write(res.toByteArray());
						trace.addResponse(counter, res);
					}
				}
				trace.reset();
				
				
			}
			serverSocket.close();
		} catch (IOException e) {
			//TODO Log error
			System.exit(1);
		}
		
	}

	/**
	 * @return true if the server is running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param running - true if the server should be started, false if stopped
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/**
	 * Add a controller to the server thread
	 * @param controller the controller
	 */
	public void addController(JWSController controller){
		this.controllers.add(controller);
	}
}
