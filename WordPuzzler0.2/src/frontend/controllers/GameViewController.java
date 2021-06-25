package frontend.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;

import app.GameBoard;
import backend.controller.PointJudge;
import backend.controller.Validator;
import backend.entities.GameData;
import backend.entities.Player;
import backend.entities.Position;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameViewController {

	@FXML
	private TextArea txtScore;

	@FXML
	private Button btnSubmit;
	@FXML
	private Button btnNew;
	@FXML
	private GridPane gridBoard;

	@FXML
	private Label lblUserName;

	@FXML
	private Label lblScore;

	private final static int BOARD_ROWS = 10;
	private final static int BEGINN_WORD_ROW = 3;
	private boolean setSuccessfull = false;
	private char selectedLetter;

	private Player player;
	private GameData game;
	private HashSet<Integer> usedColumn;
	private GameBoard gameBoard;
	private Position currentPosition = new Position(0, 0);

	public void setPlayer(Player player) {
		this.player = player;
		lblUserName.setText(player.getUsername());
		game.setPlayer(player);
		lblScore.setText(String.valueOf(game.getScore()));

	}

	public GameViewController() {
		game = new GameData(LocalDateTime.now());
		System.out.println("neues Spiel erstellt / Matchstart:" + game.getMatchstart());

	}

	@FXML
	private void initialize() {

		System.out.println("CSS Klasse Gridpane:" + gridBoard.getStyleClass());
		gridBoard.getStyleClass().add("grid-pane");
		gridBoard.setAlignment(Pos.CENTER);

		// does not work as it should. on KeyPress it automatically presses the buttons
		// because they have the focus

//		gridBoard.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			
//			@Override
//			public void handle(KeyEvent event) {
//				if(event.getCode() == KeyCode.ENTER) {
//					currentPosition.realocate((currentPosition.getX()+1), currentPosition.getY());
//				}
//				
//			};
//				
//		});

		String beginn = "SAMPLE";
		txtScore.setEditable(false);
		usedColumn = new HashSet<>();
		char[] beginnArray = beginn.toCharArray();

		// the 'virtuelle' pitch
		gameBoard = new GameBoard(BOARD_ROWS, beginnArray.length);

		createPitch(beginnArray);

		// anzeige des Startwortes
		for (int i = 0; i < beginnArray.length; i++) {
			addLetterToGrid(new Position(BEGINN_WORD_ROW, i), beginnArray[i]);

			gameBoard.setLetterAtPosition(beginnArray[i], new Position(BEGINN_WORD_ROW, i));

		}

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
				tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseHandler());
				tmp.getStyleClass().add("letter");
				p.getChildren().add(new Text(""));
				p.getStyleClass().add("stack-pane");
				p.setPrefSize(30, 30);
				p.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseHandler());
				gridBoard.add(p, x, y);
				// this lines align the nodes that we prepare for the text to the center
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
			// TODO: remove this later
			txtScore.setText(txtScore.getText().concat("\n" + selectedLetter));

			addLetterToGrid(currentPosition, selectedLetter);
		}
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
	 * if successful the words column will be saved in the usedColumn set so that
	 * the user can't change it again.
	 * 
	 * @param ev
	 */
	@FXML
	private void onSubmit(ActionEvent ev) {

		String result = gameBoard.getLastWord(currentPosition.getX());

		if (Validator.testWord(result)) {
			usedColumn.add(currentPosition.getX());
			game.setScore(game.getScore() + PointJudge.rate(result));
			txtScore.setText(txtScore.getText() + "\n" + result);
			lblScore.setText("" + game.getScore());
			setSuccessfull = true;
			for (int i = 0; i < 10; i++) {
				if (!usedColumn.contains(i)) {
					currentPosition.realocate(0, i);

					System.out.println("y = " + currentPosition.getY());

					System.out.println("x = " + currentPosition.getX());
					break;
				}
			}
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
								Node t = ((StackPane) n).getChildren().get(0);
								((Text) t).setText("");
							} else {
								System.out.println("Fehler beim Befuellen der Stackpane");
							}

						}
					}
				}

				if (i == BEGINN_WORD_ROW - 1) {
					i++;
				}
			}
		}
	}

	/**
	 * returns changes the Scene to the MainMenue scene
	 * 
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onMainMenue(ActionEvent ae) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/models/views/MainMenue.fxml"));

		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 800);

		Stage stage = (Stage) btnSubmit.getScene().getWindow();
		stage.setScene(scene);

	}

}
