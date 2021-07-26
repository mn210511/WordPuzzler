package frontend.controllers;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import app.MessageBox;
import backend.entities.GameData;
import backend.entities.Player;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreBoardController extends CommonPropertyController {
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
	private TableColumn<GameData, LocalDateTime> persStart;

	@FXML
	private TableColumn<GameData, LocalDateTime> persEnd;

	@FXML
	private TableColumn<GameData, Long> persDuration;

	@FXML
	private Button btnDelUserData;

	@FXML
	private Button btnMainMenue;

	@FXML
	private LineChart<String, Integer> chartLastMatches;

	@FXML
	private CategoryAxis x;

	@FXML
	private NumberAxis y;
	
    @FXML
    private Label lblAvgScore;
 
   
    @FXML
    private StackedBarChart<String, Long> chartMatchLength;
    @FXML
    private CategoryAxis sbcX;

    @FXML
    private NumberAxis sbcY;
	private ListProperty<Player> leaderboard;

	public ListProperty<Player> getLeaderboard() {
		return leaderboard;
	}

	private ListProperty<GameData> personalBoard;

	public ScoreBoardController() {
		leaderboard = new SimpleListProperty<>();
		personalBoard = new SimpleListProperty<>();

	}



	@FXML
	public void initialize() {

		colRang.setCellValueFactory(new PropertyValueFactory<>("placement"));
		colUser.setCellValueFactory(new PropertyValueFactory<>("username"));
		colScore.setCellValueFactory(new PropertyValueFactory<>("highscore"));
		tblScores.itemsProperty().bind(leaderboard);

		persScore.setCellValueFactory(new PropertyValueFactory<>("score"));

		persStart.setCellFactory(this::createLocalDateCell);
		persEnd.setCellFactory(this::createLocalDateCell);

		persStart.setCellValueFactory(new PropertyValueFactory<>("matchstart"));
		persEnd.setCellValueFactory(new PropertyValueFactory<>("matchend"));
		persDuration.setCellFactory(this::createDurationCell);
		persDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		tblPersonalScores.itemsProperty().bind(personalBoard);



		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		XYChart.Series<String, Long> series2 = new XYChart.Series<>(); 
		
		Object[] tmp = gameDataRepo.getPerScore(player).toArray();
		
		int index = 0;
		
		//check if there are more than 5 entities
		if (tmp.length > 5) {
			index = tmp.length-5;
		}
		
		// add the last 5 matches into the charts
		for (int i = index; i < tmp.length; i++) {
			GameData entity = (GameData)tmp[i];
			series.getData().add(new XYChart.Data<String, Integer>(String.valueOf(entity.getMatchend()), entity.getScore()));
			series2.getData().add(new XYChart.Data<String, Long>(String.valueOf(entity.getMatchend()), TimeUnit.MILLISECONDS.toSeconds(entity.getDuration())));
			
		}
		


		chartLastMatches.getData().addAll(series);
		chartMatchLength.getData().addAll(series2);
		
		
		lblAvgScore.setText(String.valueOf(gameDataRepo.avgScore(player)));
		
	}

	@FXML
	public void load() {

		reload();
	}

	/**
	 * fills the leaderboard and the board with the personal statistiks
	 */
	private void reload() {

		this.leaderboard.set(FXCollections.observableArrayList(playerRepo.getLeaderBoard()));
		this.personalBoard.set(FXCollections.observableArrayList(gameDataRepo.getPerScore(player)));
	}

	@FXML
	private void onMainMenue(ActionEvent ae) {

		try {
			MainMenueWindow window = new MainMenueWindow(((Stage) btnMainMenue.getScene().getWindow()));
			window.changeScene();
		} catch (IOException e) {
			MessageBox.show("Error", "Fehler beim Laden des Hauptmenüs", AlertType.ERROR, ButtonType.CLOSE);
			e.printStackTrace();
		}

	}
/**
 * Handler for the Delete-Button. Tells the GameDataRepository to delete all the GameDAta and Player data from the Database that are related 
 * to the current Player. 
 * @param ae
 */
	@FXML
	private void onDelete(ActionEvent ae) {
		if (MessageBox.show("Delete UserData", "Wirklich alle User-Daten löschen?", AlertType.CONFIRMATION,
				ButtonType.OK, ButtonType.CANCEL) == ButtonType.OK) {
			gameDataRepo.deleteUserData(player.getUsername());
			playerRepo.deletePlayer(player);
			Player tmp = new Player(player.getUsername());
			// add empty(excl. the Username) Player-Object to the database
			// if not the next time he plays a game his data will not be safed because
			// the database is missing the key to the player object
			playerRepo.insertPlayer(tmp);
			// refresh the player
			player = tmp;
			reload();
		}

	}

	/**
	 * formatting for the LocalDate Cells
	 * @param column
	 * @return the formatted TableCell
	 */
	private TableCell<GameData, LocalDateTime> createLocalDateCell(TableColumn<GameData, LocalDateTime> column) {

		return new TableCell<GameData, LocalDateTime>() {
			private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

			@Override
			protected void updateItem(LocalDateTime item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText("");
				} else {
					this.setText(format.format(item));
				}
			}
		};

	}

	/**
	 * formatting for the Duration Cell
	 * @param column
	 * @return the formatted TableCell
	 */
	private TableCell<GameData, Long> createDurationCcreateell(TableColumn<GameData, Long> column) {

		return new TableCell<GameData, Long>() {

			@Override
			protected void updateItem(Long item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText("");
				} else {
					Duration d = Duration.ofMillis(item);
					this.setText(String.format("%02d:%02d:%02d", d.toHoursPart(), d.toMinutesPart(), d.toSecondsPart()));

				}
			}
		};

	}

}
