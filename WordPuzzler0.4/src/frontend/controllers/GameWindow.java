package frontend.controllers;

import java.io.IOException;

import app.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameWindow {

	

	private Stage stage;
	
	
	
	
	
	
	public GameWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public GameWindow(Stage stage) {
	
		this.stage = stage;
	}
	
	public void changeScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GAME_VIEW_FXML));

		Parent root = loader.load();

		//GameViewController ctrl = loader.getController();
		
	;
		Scene scene = new Scene(root, 1000, 900);
		scene.getStylesheets().add(getClass().getResource(Constant.STYLE_SHEET).toExternalForm());
		
		stage.setScene(scene);
	}
	
	public Scene getScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GAME_VIEW_FXML));

		Parent root = loader.load();
		
		return new Scene(root, 1000, 900);
	}
}
