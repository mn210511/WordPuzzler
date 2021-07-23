package frontend.controllers;

import java.io.IOException;

import app.MessageBox;
import backend.entities.GameData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultScreenController extends CommonPropertyController {
	@FXML
	private ImageView imgResScreen;

	@FXML
	private Text txtScore;

	@FXML
	private Button btnNewGame;

	@FXML
	private Button btnScoreBoard;

	@FXML
	private Button btnMainMenue;

	@FXML
	private Label lblScore;

	Scene selectedScene;

	@FXML
	public void initialize() {

	}

	/**
	 * calls the getScene Method from the MainMenueWindow and safes it in the
	 * selectedScene attribute
	 * 
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onMainMenue(ActionEvent ae) {
		try {
			MainMenueWindow window = new MainMenueWindow(((Stage) btnMainMenue.getScene().getWindow()));

			selectedScene = window.getScene();
			Stage stg = (Stage) btnMainMenue.getScene().getWindow();
			stg.close();

		} catch (IOException e) {
			MessageBox.show("Error", "Fehler beim Laden des Hauptmenüs", AlertType.ERROR, ButtonType.CLOSE);
			e.printStackTrace();
		}

	}

	/**
	 * calls the getScene Method from the GameWindow and safes it in the
	 * selectedScene attribute
	 * 
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onNewGame(ActionEvent ae) {
try {
		GameWindow window = new GameWindow((Stage) btnMainMenue.getScene().getWindow());
		
			selectedScene = window.getScene();
			Stage stg = (Stage) btnMainMenue.getScene().getWindow();
		stg.close();
		} catch (IOException e) {
			MessageBox.show("Error", "Fehler beim Laden des Spielfelds", AlertType.ERROR, ButtonType.CLOSE);
			e.printStackTrace();
		}
		
	}

	/**
	 * calls the getScene Method from the ScoreBoardWindow and safes it in the
	 * selectedScene attribute
	 * 
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onScoreBoard(ActionEvent ae)  {

		try {
			ScoreBoardWindow window = new ScoreBoardWindow((Stage) btnMainMenue.getScene().getWindow());
			selectedScene = window.getScene();
			Stage stg = (Stage) btnMainMenue.getScene().getWindow();
			stg.close();
		} catch (IOException e) {
			MessageBox.show("Error", "Fehler beim Laden des Ranglisten Fensters", AlertType.ERROR, ButtonType.CLOSE);
			e.printStackTrace();
		}

	}

	/**
	 * method to set the the score from the label from outside
	 * 
	 * @param game
	 */
	public void sendData(GameData game) {

		lblScore.setText("" + game.getScore());

	}

	/**
	 * returns the selectedScene
	 * 
	 * @return the selected Scene
	 */
	public Scene getScene() {
		return selectedScene;
	}

}
