package frontend.controllers;

import java.io.IOException;

import app.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenueWindow {

	

	private Stage stage;
	
	
	
	
	
	/**
	 * default constructor
	 */
	public MainMenueWindow() {
	
	}
	
	public MainMenueWindow(Stage stage) {

		this.stage = stage;
	}
	
	/**
	 * changes the the scene to the mainmenue-Scene
	 * @throws IOException
	 */
	public void changeScene() throws IOException {
FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.MAIN_MENUE_FXML));
		
		Parent root = loader.load();
		//MainMenueController ctrl = loader.getController();

		Scene scene = new Scene(root, 700, 800);
		
		
		stage.setScene(scene);
	}
	/**
	 * returns the mainMenue-Scene to the caller
	 * @return the mainMenue-Scene
	 * @throws IOException
	 */
	public Scene getScene() throws IOException{
FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.MAIN_MENUE_FXML));		
		Parent root = loader.load();
		 
		return new Scene(root, 700, 800);
	}
}
