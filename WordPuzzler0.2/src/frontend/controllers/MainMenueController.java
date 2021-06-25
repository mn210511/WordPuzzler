package frontend.controllers;

import java.io.IOException;

import backend.entities.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenueController {
	@FXML
	private TextField txtUserName;
	@FXML
	private Button btnNewGame;
	@FXML
	private Button btnScoreboard;
	@FXML
	private Button btnEnd;
	@FXML
	private ImageView logo;

	private String username;

	@FXML
	public void initialize() {
		btnNewGame.setDisable(true);
		txtUserName.textProperty().addListener((o, oldVal, newVal) -> notEmpty());

	}

	@FXML
	private void onNewGame(ActionEvent ae) throws IOException {
		username = txtUserName.getText();
		Player player = new Player(username);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/views/gameView.fxml"));

		Parent root = loader.load();

		GameViewController ctrl = loader.getController();
		
		ctrl.setPlayer(player);
		Scene scene = new Scene(root, 700, 800);
		scene.getStylesheets().add(getClass().getResource("/frontend/views/styles.css").toExternalForm());
		Stage stage = (Stage) btnEnd.getScene().getWindow();
		
		stage.setScene(scene);


	}

	@FXML
	private void onScoreBoard(ActionEvent ae) throws IOException {
		username = txtUserName.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/models/views/scoreBoard.fxml"));

		Parent root = loader.load();
		
		ScoreBoardController ctrl = loader.getController();

		
		
		Scene scene = new Scene(root, 700, 800);

		Stage stage = (Stage) btnEnd.getScene().getWindow();
		stage.setScene(scene);

	}

	@FXML
	private void onExit(ActionEvent ae) throws IOException {

		Stage stage = (Stage) btnEnd.getScene().getWindow();
		stage.close();

	}

	@FXML
	private void notEmpty() {
		if (txtUserName.getText().equals("")) {
			btnNewGame.setDisable(true);
		} else {
			btnNewGame.setDisable(false);
		}
	}

}
