package frontend.controllers;

import java.io.IOException;

import app.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameWindow {

	

	private Stage primaryStage;
	
	
	
	
	
	/**
	 * default constructor
	 */
	public GameWindow() {
		
	}
	/**
	 * constructor with a Stage parameter. Needed to pass over the primaryStage
	 * @param primaryStage the primaryStage where we want the new Scene to be set
	 */
	public GameWindow(Stage primaryStage) {
	
		this.primaryStage = primaryStage;
	}
	
	/**
	 * loads the gameView from the fxml and changes sets it into the primaryStage
	 * @throws IOException
	 */
	public void changeScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GAME_VIEW_FXML));

		Parent root = loader.load();

	
	
	
		Scene scene = new Scene(root, 1500, 1000);
		scene.getStylesheets().add(getClass().getResource(Constant.STYLE_SHEET).toExternalForm());
		
		primaryStage.setScene(scene);
	}
	
	/**
	 * loads the gameView from the fxml and returns the scene to the caller
	 * @return
	 * @throws IOException
	 */
	public Scene getScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GAME_VIEW_FXML));

		Parent root = loader.load();
		
		return new Scene(root, 1500, 1000);
	}
}
