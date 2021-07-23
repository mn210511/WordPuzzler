package app;

import frontend.controllers.GameViewController;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * KeyHandler for the GameViewController Class
 * Depending on the entered Arrow Key the Position to be filled changes.
 * 
 * @author mn210
 *
 */
public class Navigator implements EventHandler<KeyEvent>{
GameViewController controller;
	
	public Navigator(GameViewController controller) {
		this.controller = controller;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.DOWN) {
			handleDown();
			controller.getGridBoard().requestFocus();
			event.consume();
	
		}
		if(event.getCode() == KeyCode.LEFT) {
			handleLeft();
			controller.getGridBoard().requestFocus();
			event.consume();
	
		}
		
		if(event.getCode() == KeyCode.RIGHT) {
			handleRight();
			controller.getGridBoard().requestFocus();
	
		}
		if(event.getCode() == KeyCode.UP) {
			handleUp();
			controller.getGridBoard().requestFocus();
	
		}
	}

	/**
	 * sets the position to the cell below the current one. 
	 */
	private void handleDown() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()+1), controller.getCurrentPosition().getX());
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getY()==GameViewController.BOARD_ROWS) {
			controller.getCurrentPosition().realocate((0), controller.getCurrentPosition().getX());
		}
		// remove the pseudo Class from all nodes so that only the cell that was pressed last looks 'pressed'
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
	/**
	 * sets the position to the cell above the current one. 
	 */
	private void handleUp() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()-1), controller.getCurrentPosition().getX());
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getY()== -1) {
			controller.getCurrentPosition().realocate((GameViewController.BOARD_ROWS-1), controller.getCurrentPosition().getX());
		}
		// remove the pseudo Class from all nodes so that only the cell that was pressed last looks 'pressed'
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
	/**
	 * sets the position to the cell before the current one. 
	 */
	private void handleLeft() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), controller.getCurrentPosition().getX()-1);
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getX()==-1) {
			controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), controller.getNumberColumns()-1);
		}
		// remove the pseudo Class from all nodes so that only the cell that was pressed last looks 'pressed'
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
	/**
	 * sets the position to the cell after the current one. 
	 */
	private void handleRight() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), controller.getCurrentPosition().getX()+1);
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getX()== controller.getNumberColumns()-1) {
			controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), 0);
		}
		// remove the pseudo Class from all nodes so that only the cell that was pressed last looks 'pressed'
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
	

}
