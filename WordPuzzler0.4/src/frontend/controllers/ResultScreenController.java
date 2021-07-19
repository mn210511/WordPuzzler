package frontend.controllers;

import java.io.IOException;

import backend.entities.GameData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultScreenController extends CommonPropertyController{
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
  
   
    Scene scene;

    
    
	@FXML
	public void initialize() {
	
		
	}
	@FXML
	private void onMainMenue(ActionEvent ae) throws IOException {

		MainMenueWindow window = new MainMenueWindow(((Stage) btnMainMenue.getScene().getWindow()));
		scene = window.getScene();
		Stage stg = (Stage) btnMainMenue.getScene().getWindow();
		stg.close();

	}
	@FXML
	private void onNewGame(ActionEvent ae) throws IOException {

		GameWindow window = new GameWindow((Stage) btnMainMenue.getScene().getWindow());
		scene = window.getScene();
		Stage stg = (Stage) btnMainMenue.getScene().getWindow();
		stg.close();
	}
		
	@FXML
	private void onScoreBoard(ActionEvent ae) throws IOException {

	ScoreBoardWindow window = new ScoreBoardWindow((Stage) btnMainMenue.getScene().getWindow());
	scene = window.getScene();
	Stage stg = (Stage) btnMainMenue.getScene().getWindow();
	stg.close();
	
	}
	
	public void sendData(GameData game) {
			
		lblScore.setText("" + game.getScore());
		
		}
	
	public Scene getScene() {
		return scene;
	}
	
	
    
}
