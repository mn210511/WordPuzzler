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

	

	private Stage stage;
	private GameData game;
	
	
	
	
	
	public ResultScreenWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultScreenWindow(Stage stage, GameData game) {

		this.stage = stage;
		this.game = game;
		
	}
	
	
	public void changeScene() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.RESULT_SCREEN_FXML));

		Parent root = loader.load();
		
		ResultScreenController ctrl = loader.getController();
		
		ctrl.sendData(game);

		//System.out.println("Spiel gesetzt" + game.toString());
		Scene scene = new Scene(root, 700, 800);
		Stage stageResult = new Stage(StageStyle.DECORATED);
		stageResult.setScene(scene);

		stageResult.initModality(Modality.APPLICATION_MODAL);
		stageResult.showAndWait();
	if(ctrl.scene != null) {
		stage.setScene(ctrl.getScene());
	}
	}
	
	
}
