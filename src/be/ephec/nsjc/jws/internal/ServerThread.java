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
		System.out.println("Constructor");
	}
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(6587);
			while(true){
				System.out.println("Boucle");
				Socket clientSocket = serverSocket.accept();
				System.out.println("accept");
				//TODO Log connection
				BufferedReader bf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				System.out.println("Buffer");
				//TODO Parse the request
				RequestHandler reqHandler = new RequestHandler(bf);
				Request req = reqHandler.parseRequest();
				System.out.println("req parsed");
				//TODO Create the response
				if(req != null){
					ResponseBuilder respBuilder = new ResponseBuilder(req);
					Response res = respBuilder.buildResponse();
					if(res != null){
						clientSocket.getOutputStream().write(res.toByteArray());
					}
				}
				System.out.println("request built");
				//TODO Log response
				
				System.out.println("sent");
				
			}
			//serverSocket.close();
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
