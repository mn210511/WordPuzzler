package frontend.controllers;

import java.io.IOException;

import app.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenueWindow {

	

	private Stage primaryStage;
	
	
	
	
	
	/**
	 * default constructor
	 */
	public MainMenueWindow() {
	
	}
	/**
	 * constructor with a Stage parameter. Needed to pass over the primaryStage
	 * @param primaryStage
	 */
	public MainMenueWindow(Stage primaryStage) {

		this.primaryStage = primaryStage;
	}
	
	/**
	 * changes the the scene to the mainmenue-Scene
	 * @throws IOException
	 */
	public void changeScene() throws IOException {
FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.MAIN_MENUE_FXML));
		
		Parent root = loader.load();
		//MainMenueController ctrl = loader.getController();

		Scene scene = new Scene(root, 650, 800);
		
		
		primaryStage.setScene(scene);
	}
	/**
	 * returns the mainMenue-Scene to the caller
	 * @return the mainMenue-Scene
	 * @throws IOException
	 */
	public Scene getScene() throws IOException{
FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.MAIN_MENUE_FXML));		
		Parent root = loader.load();
		 
		return new Scene(root, 650, 800);
	}
}
