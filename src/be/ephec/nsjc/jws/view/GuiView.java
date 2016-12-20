package be.ephec.nsjc.jws.view;

import java.io.IOException;

import be.ephec.nsjc.jws.controller.GuiController;
import be.ephec.nsjc.jws.model.HTTPTrace;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiView extends Application {
	
	private Stage stage;
	private AnchorPane pane;
	private BorderPane container;
	
	private static GuiController controller;
	private static HTTPTrace trace;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		this.stage.setTitle("JWS – Java Web Server");
		try {
            // Load root layout from fxml file.
			FXMLLoader containerLoader = new FXMLLoader();
			containerLoader.setLocation(GuiView.class.getResource("GuiViewContainer.fxml"));
			container = (BorderPane) containerLoader.load();
			
            FXMLLoader paneLoader = new FXMLLoader();
            paneLoader.setLocation(GuiView.class.getResource("GuiView.fxml"));
            pane = (AnchorPane) paneLoader.load();
            controller = paneLoader.getController();
            
            Scene scene = new Scene(container);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            container.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void loadView(){
		launch();
	}
	
	public static GuiController getController(){
		return controller;
	}
}
