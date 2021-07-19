package frontend.controllers;

import java.io.IOException;

import app.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScoreBoardWindow {

	

	private Stage stage;
	
	
	
	
	
	/**
	 * default constructor
	 */
	public ScoreBoardWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public ScoreBoardWindow(Stage stage) {

		this.stage = stage;
	}
	/**
	 * changes the scene in the main-Stage to the scoreBoard scene
	 * @throws IOException
	 */
	public void changeScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.SCOAR_BOARD_FXML));

		Parent root = loader.load();
		
		ScoreBoardController ctrl = loader.getController();

		ctrl.load();
		
		Scene scene = new Scene(root, 1200, 700);
	
		stage.setScene(scene);
	}
	/**
	 * gives the caller the Scoarboard-Scene
	 * @return the scoreboard-Scene
	 * @throws IOException
	 */
	public Scene getScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.SCOAR_BOARD_FXML));

		Parent root = loader.load();
		ScoreBoardController ctrl = loader.getController();

		ctrl.load();
		return new Scene(root, 1200, 700);
	}
	
}
