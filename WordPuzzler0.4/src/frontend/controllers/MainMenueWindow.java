package frontend.controllers;

import java.io.IOException;

import app.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenueWindow {

	

	private Stage stage;
	
	
	
	
	
	
	public MainMenueWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public MainMenueWindow(Stage stage) {

		this.stage = stage;
	}
	
	public void changeScene() throws IOException {
FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.MAIN_MENUE_FXML));
		
		Parent root = loader.load();
		//MainMenueController ctrl = loader.getController();

		Scene scene = new Scene(root, 700, 800);
		
		
		stage.setScene(scene);
	}
	
	public Scene getScene() throws IOException{
FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.MAIN_MENUE_FXML));		
		Parent root = loader.load();
		 
		return new Scene(root, 700, 800);
	}
}
