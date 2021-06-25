package frontend.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import backend.entities.GameData;
import backend.entities.Leaderboard;
import backend.entities.PersonalBoard;
import backend.entities.Player;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreBoardController {
	@FXML
	private ImageView imgHeader;
	@FXML
	private VBox boxScoreboard;
	@FXML
	private TableView<Player> tblScores;
	@FXML
	private TableColumn<Player, Integer> colRang;
	@FXML
	private TableColumn<Player, String> colUser;
	@FXML
	private TableColumn<Player, Integer> colScore;
	@FXML
	private VBox boxPersonalScore;
	@FXML
	private TableView<GameData> tblPersonalScores;
	

    @FXML
    private TableColumn<GameData, Integer> persScore;

    @FXML
    private TableColumn<GameData, LocalDate> persDate;

    @FXML
    private TableColumn<GameData, LocalDateTime> persDuration;

	
	
	
	
	
	
	
	
	@FXML
	private Button btnDelUserData;
	
	@FXML
    private Button btnMainMenue;

	private PersonalBoard pboard = new PersonalBoard("Nicolas");
	
	private Leaderboard lboar = new Leaderboard();
	
	private ListProperty<Player> leaderboard;
	
	public ListProperty<Player> getLeaderboard() {
		return leaderboard;
	}
	
	private ListProperty<GameData> personalBoard;
	
	
	
	public ScoreBoardController() {
		leaderboard = new SimpleListProperty<>();
		personalBoard = new SimpleListProperty<>();
		load();
	}
	
	
	

	@FXML
	public void initialize() {
		
		colRang.setCellValueFactory(new PropertyValueFactory<>("placement"));
		colUser.setCellValueFactory(new PropertyValueFactory<>("username"));
		colScore.setCellValueFactory(new PropertyValueFactory<>("highscore"));
		tblScores.itemsProperty().bind(leaderboard);
		
		persScore.setCellValueFactory(new PropertyValueFactory<>("score"));
		persDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		persDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		tblPersonalScores.itemsProperty().bind(personalBoard);
	}
	@FXML
	public void load() {
		List<Player> tmp = lboar.getLeaderboard();
		List<GameData> tmp1 = pboard.getPerScore();
		this.leaderboard.set(FXCollections.observableArrayList(tmp));
		this.personalBoard.set(FXCollections.observableArrayList(tmp1));
	}
	
	
	
	@FXML
	private void onMainMenue(ActionEvent ae) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/models/views/mainMenue.fxml"));
		
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 800);
		
		Stage stage = (Stage)btnMainMenue.getScene().getWindow();
		stage.setScene(scene);
		
	}
	
	
	
}
