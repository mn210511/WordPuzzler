package frontend.controllers;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import app.MessageBox;
import backend.entities.GameData;
import backend.entities.Position;
import backend.services.KeyBoardHandler;
import backend.services.PointJudge;
import backend.services.RulesReader;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameViewController extends CommonPropertyController {

	@FXML
	private TextArea txtRules;
	@FXML
	private Button btnDelete;

	@FXML
	private Button btnFinish;
	@FXML
	private Button btnSubmit;

	@FXML
	private GridPane gridBoard;
	@FXML
	private StackPane stackKeyboard;
	@FXML
	private Label lblUserName;

	@FXML
	private VBox keyBoxQWERT;
	@FXML
	private HBox gameBoardContainer;
	@FXML
	private Label lblTimer;

	@FXML
	private VBox keyBoxABC;

	@FXML
	private VBox historyContainer;

	private	Timer myTimer;

	private String rules;

	@FXML
	private Label lblScore;

	public final static int BOARD_ROWS = 10;
	private final static int BEGINN_WORD_ROW = 3;
	private int numberColumns = 0;
	private boolean setSuccessfull = false;
	private char selectedLetter;

	private GameData game;
	private HashSet<Integer> usedColumn;
	private GameBoardController gameBoard;
	private Position currentPosition = new Position(0, 0);


	public GameViewController() {
		game = new GameData(LocalDateTime.now());
		System.out.println("neues Spiel erstellt / Matchstart:" + game.getMatchstart());

	}

	public GridPane getGridBoard() {
		return gridBoard;
	}
	public Position getCurrentPosition() {
		return currentPosition;
	}
	public int getNumberColumns() {
		return numberColumns;
	}
	
	@FXML
	private void initialize() {

		System.out.println("CSS Klasse Gridpane:" + gridBoard.getStyleClass());
		gridBoard.getStyleClass().add("grid-pane");
		gridBoard.setAlignment(Pos.CENTER);

		lblUserName.setText(player.getUsername());
		lblScore.setText("0");
		System.out.println("player name:'" + player.getUsername() + "' is playing");
		game.setPlayer(player);
		

		//adding the eventHandler, needed to navigate through the board with the arrow keys
		gridBoard.setOnKeyPressed(new KeyBoardHandler(this));

		String beginn = dictionary.getRandomWord().toUpperCase();
		txtRules.setEditable(false);
		usedColumn = new HashSet<>();
		char[] beginnArray = beginn.toCharArray();
		numberColumns = beginnArray.length;
		// the 'virtuelle' pitch
		gameBoard = new GameBoardController(BOARD_ROWS, beginnArray.length);

		createPitch(beginnArray);

		// anzeige des Startwortes
		for (int i = 0; i < beginnArray.length; i++) {
			addLetterToGrid(new Position(BEGINN_WORD_ROW, i), beginnArray[i]);

			gameBoard.setLetterAtPosition(beginnArray[i], new Position(BEGINN_WORD_ROW, i));

		}
		myTimer = new Timer("timer-gameTime");
		myTimer.schedule(

				new TimerTask() {

					@Override
					public void run() {
						timerRun();

					}
				},

				500, 1000);
		// ends the timer if the window is closed
		Platform.runLater(() -> {
			lblScore.getScene().getWindow().addEventFilter(javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST, we -> {
				myTimer.cancel();
			});

		});

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					rules = new RulesReader().getRules();
				} catch (IOException e) {
					MessageBox.show("Error", "Regeln konnten nicht geladen werden");
					e.printStackTrace();
					
				}
				Platform.runLater(() -> {
					txtRules.setText(rules);
				});
			}
		});

		t1.start();
		//set the focus to anywhere to make the keyhandler work 
		gridBoard.requestFocus();
	}

	/**
	 * Method for the run implementation of the timer that shows the past time from
	 * matchstart to now
	 */
	private void timerRun() {
		Duration d = Duration.between(game.getMatchstart(), LocalDateTime.now());
		Platform.runLater(() -> {
			lblTimer.setText(String.format("%02d:%02d:%02d", d.toHoursPart(), d.toMinutesPart(), d.toSecondsPart()));

		});

	}

	/**
	 * method to initialize the field.
	 * 
	 * we are adding stackpanes to the gridpane according to the number of rows that
	 * we set in the BOARD_ROWS variable. and columns according to the number of
	 * letters of the predefined word. During creation we add letters in the panes
	 * and give them styleclasses and some styling.
	 * 
	 * @param beginnArray
	 */
	private void createPitch(char[] beginnArray) {
		// Array fuer die Raender der Zellen
		StackPane[][] cells = new StackPane[BOARD_ROWS][beginnArray.length];
		// Befuellen des Arrays
		for (int i = 0; i < BOARD_ROWS; i++) {
			for (int j = 0; j < beginnArray.length; j++) {
				cells[i][j] = new StackPane();
			}

		}

		int x = 0;
		int y = 0;
		for (StackPane[] pane : cells) {

			for (StackPane p : pane) {
				Text tmp = new Text("");
				// tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseHandler());
				tmp.getStyleClass().add("letter");
				p.getChildren().add(tmp);
				p.getStyleClass().add("stack-pane");
			
				p.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseHandler());
				gridBoard.add(p, x, y);
				// align the nodes that we prepare for the text to the center
				GridPane.setHalignment(p, HPos.CENTER);
				GridPane.setValignment(p, VPos.CENTER);

				x++;
			}
			y++;
			x = 0;
		}
	}

	/**
	 * inner class for the mouseclicks on the cells. safes the x und y coordinates
	 * in the 'position' Object.
	 * 
	 * @author mn210
	 *
	 */
	private class MouseHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent me) {
			for (Node n : gridBoard.getChildren()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			}
			Object n = me.getSource();
			Node node = (Node) n;
			((StackPane) node).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			if (!usedColumn.contains(GridPane.getColumnIndex(node))) {
				currentPosition.realocate(GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
				System.out.println("y = " + currentPosition.getY() + " x =" + currentPosition.getX());
				setSuccessfull = false;
			}
			gridBoard.requestFocus();
		}
		
	}

	/**
	 * actionHandler for the Letter-Buttons. safes the representing Text from the
	 * button inside the selectedLetter variable. sets the value of the variable
	 * into the gameBoard object and shows the letter in the gridPane.
	 * 
	 * @param ev
	 */

	@FXML
	private void clickedLetter(ActionEvent ev) {
		
		setSuccessfull = false;
		Object obj = ev.getSource();
		Button clickedButton = (Button) obj;
		selectedLetter = clickedButton.getText().charAt(0);

		// without this we would be able to change the Core-Word
		if (currentPosition.getY() != BEGINN_WORD_ROW) {

			gameBoard.setLetterAtPosition(selectedLetter, currentPosition);

			addLetterToGrid(currentPosition, selectedLetter);
		}
		//TODO: test more
		gridBoard.requestFocus();
	}
	/**
	 * overload of the clickedLetter method with a String for the KeyEventHandler
	 * @param letter
	 */
	public void clickedLetter(String letter) {
		
	
		selectedLetter = letter.charAt(0);

		// without this we would be able to change the Core-Word
		if (currentPosition.getY() != BEGINN_WORD_ROW) {

			gameBoard.setLetterAtPosition(selectedLetter, currentPosition);

			addLetterToGrid(currentPosition, selectedLetter);
		}
		//TODO: test more
		gridBoard.requestFocus();
	}

	/**
	 * sets a letter in the gridPane to show the user his input.
	 * 
	 * @param row    the row where the letter should be set
	 * @param col    the columns where the letter should be set
	 * @param letter the letter to be set
	 */
	private void addLetterToGrid(Position p, char letter) {
		for (Node n : gridBoard.getChildren()) {
			if (GridPane.getColumnIndex(n) == p.getX() && GridPane.getRowIndex(n) == p.getY()) {
				
				if (n instanceof StackPane) {
					Node t = ((StackPane) n).getChildren().get(0);
					// only add it if it does not contain it already
					if(!n.getStyleClass().contains("cellFilled")) {
						n.getStyleClass().add("cellFilled");
					}
					
					((Text) t).setText(String.valueOf(letter));

				} else {
					System.out.println("Fehler beim Befuellen der Stackpane");
				}

			}
		}
	}

	/**
	 * ActionHandler for the 'Submit' Button. Submits the last Word that the User
	 * wanted to build. refreshes the Users score if the word has been successfully
	 * validated by the 'Validator' - Object.
	 * 
	 * the value of the word will be determined by the static Method of the
	 * 'PointJudge' class
	 * 
	 * if successful the words column will be saved in the usedColumn-set so that
	 * the user can't change it again.
	 * 
	 * @param ev
	 */
	@FXML
	private void onSubmit(ActionEvent ev) {

		// Try catch block because if the word has an empty space the getWordFromColumn will throw an exception
		try {
			String result = gameBoard.getWordFromColumn(currentPosition.getX());

			if (dictionary.testWord(result)) {
				usedColumn.add(currentPosition.getX());
				int points = PointJudge.rate(result);
				game.setScore(game.getScore() + points);
				// txtRules.setText(txtRules.getText() + "\n" + result);
				lblScore.setText("" + game.getScore());
				setSuccessfull = true;
				Label tmp = new Label(result + " - " + points + "pts");
				tmp.getStyleClass().add("label-history");
				historyContainer.getChildren().add(tmp);

				if (usedColumn.size() == numberColumns) {

					try {
						finishGame();
					} catch (IOException e) {
						MessageBox.show("Error", "Ergebniss Bildschirm konnte nicht geladen werden");
						System.err.println("Fehler beim laden des ResultScreens" + e.getMessage());
					}
				}
				for (int i = 0; i < numberColumns; i++) {
					if (!usedColumn.contains(i)) {
						currentPosition.realocate(0, i);

						System.out.println("y = " + currentPosition.getY());

						System.out.println("x = " + currentPosition.getX());
						break;
					}
				}
			} else {
				if (MessageBox.show("word not found",
						"Wort \"%s\" nicht ist nicht im Wörterbuch \n Wort hinzufügen?".formatted(result),
						AlertType.CONFIRMATION, ButtonType.YES, ButtonType.NO) == ButtonType.YES) {
					if (dictionary.addWord(result.toLowerCase()) == true) {
						onSubmit(ev);
					} else {
						MessageBox.show("Error", "Wort hinzufügen war nicht erfolgreich");
					}

				}

			}
			// remove the focus from this button
			gridBoard.requestFocus();
		} catch (Exception e) {
			MessageBox.show("Fehler", e.getMessage());
		
		}

	}

	/**
	 * deletes all input in the selected columns. - Only if the values of the
	 * columns hasn't been submitted -
	 * 
	 * @param ev
	 */
	@FXML
	private void onDelete(ActionEvent ev) {

		if (setSuccessfull == false) {
			for (int i = 0; i < BOARD_ROWS; i++) {
				if (gameBoard.positionFilled(new Position(i, currentPosition.getX()))) {
					gameBoard.deleteLetterFromPosition(new Position(i, currentPosition.getX()));

					for (Node n : gridBoard.getChildren()) {
						if (GridPane.getColumnIndex(n) == currentPosition.getX() && GridPane.getRowIndex(n) == i) {
							if (n instanceof StackPane) {
								// index 1 because it is the 2nd class that we have set and we do not want to remove the first 
								// because it gives the cell her standard look
								n.getStyleClass().remove(1);
								Node t = ((StackPane) n).getChildren().get(0);
								;
								((Text) t).setText("");
							} else {
								System.out.println("Fehler beim Befuellen der Stackpane");
							}

						}
					}
				}
				// if 'i' hits the row with the beginnWord we want to skip
				// the startword should never be changed
				if (i == BEGINN_WORD_ROW - 1) {
					i++;
				}
			}
		}
		// remove the focus from this button;
		gridBoard.requestFocus();
	}

	/**
	 * returns changes the Scene to the MainMenue scene
	 * 
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onMainMenue(ActionEvent ae) throws IOException {

		MainMenueWindow window = new MainMenueWindow(((Stage) btnSubmit.getScene().getWindow()));
		window.changeScene();
	}

	/**
	 * Action for the switch-Button. switches to shown Keyboard to either a QWERT or
	 * ABC Version.
	 * 
	 * @param ae
	 */
	@FXML
	private void onChange(ActionEvent ae) {

		if (!keyBoxABC.isVisible()) {
			keyBoxQWERT.setVisible(false);
			keyBoxABC.setVisible(true);

		} else {
			keyBoxQWERT.setVisible(true);
			keyBoxABC.setVisible(false);

		}

	}

	/**
	 * Action for the Button that ends the game before the board is full
	 * 
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onSurrender(ActionEvent ae) throws IOException {
		myTimer.cancel();
		finishGame();

	}

	/**
	 * Methode that does all the required action to end a game. The gamedata's
	 * matchend will be set and the duration will be calculated. The score will be
	 * set in the player object and the playerRepo and GameDataRepo will be updated
	 * 
	 * @throws IOException
	 */
	private void finishGame() throws IOException {

		game.setMatchend(LocalDateTime.now());
		game.setDuration();
		player.setHighscore(game.getScore());
		ResultScreenWindow window = new ResultScreenWindow(((Stage) btnSubmit.getScene().getWindow()), game);

		playerRepo.updatePlayer(player);
		gameDataRepo.insertData(game);

		disableControlls();

		window.showResultScreen();
	}

	/**
	 * disables the controls when the game is finished
	 */
	private void disableControlls() {
		keyBoxABC.setDisable(true);
		keyBoxQWERT.setDisable(true);
		btnSubmit.setDisable(true);
		btnFinish.setDisable(true);
		btnDelete.setDisable(true);

	}
}
