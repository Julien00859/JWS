package be.ephec.nsjc.jws.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import be.ephec.nsjc.jws.internal.ServerThread;
import be.ephec.nsjc.jws.model.HTTPTrace;
import be.ephec.nsjc.jws.model.Request;
import be.ephec.nsjc.jws.model.Response;
import be.ephec.nsjc.jws.view.GuiView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

public class GuiController {
	
	private GuiView view;
	private HTTPTrace trace;
	
	@FXML
	ComboBox<String> methodCombo;
	@FXML
	TextField urlInput;
	@FXML
	Button goButton;
	@FXML
	WebView webView;
	@FXML
	Label serverState;
	@FXML
	Button shutdownButton;
	@FXML
	Button startButton;
	@FXML
	Button rebootButton;
	@FXML
	TextArea logArea;

	WebEngine engine;
	private ServerThread server;
	
	
	
	@FXML
    private void initialize() {
		methodCombo.getItems().addAll("GET", "POST", "DELETE");
		engine = webView.getEngine();
		
		
    }
	
	@FXML
	public void handleGo(ActionEvent event){
		String method = methodCombo.getValue();
		if(method == null) method = "GET";
		switch(method){
			case "GET":
				engine.load(urlInput.getText());
				break;
			case "POST":
				final FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(goButton.getScene().getWindow());
                if (file != null) {
                	InetAddress locale;
					try {
						locale = InetAddress.getByName("0:0:0:0:0:0:0:1");
						Socket s = new Socket(locale, 6587);
	            		String path = urlInput.getText().split(":6587")[1];
	            		String toSend = "POST "+path+" HTTP/1.1"+
	            				"Content-Type: multipart/mixed; boundary=boundary42\r\n"+
	            				"\r\n"+
	            				"--boundary42\r\n"+
	            				"\r\n"+
	            				"Contenu du fichier\r\n"+
	            				"--boundary42--\r\n";
	            		s.getOutputStream().write(toSend.getBytes());
	            		s.close();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		
                }
				break;
			case "DELETE":
				;
				break;
		}
		
	}
	
	@FXML
	public void handleStopButton(ActionEvent event){
		server.setRunning(false);
	}
	
	@FXML
	public void handleStartButton(ActionEvent event){
		server.setRunning(true);
		Thread t = new Thread(server);
		t.start();
	}
	
	public void setView(GuiView view){
		this.view = view;
	}

	public void addHTTPTrace(HTTPTrace trace) {
		this.trace = trace;
		trace.getRequestList().addListener(new ListChangeListener<Request>(){
			@Override
			public void onChanged(Change<? extends Request> c) {
				c.next();
				if(c.wasAdded()){
					logArea.setText(logArea.getText()+"\n"+c.getList().get(c.getList().size()-1).toString());
				}
			}
			
		});
		trace.getResponseList().addListener(new ListChangeListener<Response>(){
			@Override
			public void onChanged(Change<? extends Response> c) {
				c.next();
				if(c.wasAdded()){
					logArea.setText(logArea.getText()+"\n"+c.getList().get(c.getList().size()-1).toString());
				}
			}
			
		});
	}

	public void setServer(ServerThread st) {
		this.server = st;
		//On start server is running
		startButton.setDisable(true);
		shutdownButton.setDisable(false);
		rebootButton.setDisable(false);
		server.getObservableRunning().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				startButton.setDisable(newValue);
				shutdownButton.setDisable(!newValue);
				rebootButton.setDisable(!newValue);
				
			}
			
		});
	}
}
