package frontend.controllers;

import java.io.IOException;

import app.Constant;
import backend.entities.GameData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ResultScreenWindow {

	

	private Stage primaryStage;
	private GameData game;
	
	
	
	
	/** 
	 * default constructor
	 */
	public ResultScreenWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultScreenWindow(Stage primaryStage, GameData game) {

		this.primaryStage = primaryStage;
		this.game = game;
		
	}
	
	/**
	 * opens the resultscreen in a new Window and waits for the users selected Menue to 
	 * set the selected scene in the mainStage
	 * @throws IOException
	 */
	public void showResultScreen() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.RESULT_SCREEN_FXML));

		Parent root = loader.load();
		
		ResultScreenController ctrl = loader.getController();
		
		ctrl.sendData(game);

	
		Scene scene = new Scene(root, 700, 800);
		Stage stageResult = new Stage(StageStyle.DECORATED);
		stageResult.setScene(scene);

		stageResult.initModality(Modality.APPLICATION_MODAL);
		stageResult.showAndWait();
	if(ctrl.scene != null) {
		// set the selected Scene in the primaryStage
		primaryStage.setScene(ctrl.getScene());
	}
	}
	
	
}
