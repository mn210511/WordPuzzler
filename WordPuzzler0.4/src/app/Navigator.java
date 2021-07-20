package app;

import frontend.controllers.GameViewController;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
	
		}
		if(event.getCode() == KeyCode.LEFT) {
			handleLeft();
			controller.getGridBoard().requestFocus();
	
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

	
	private void handleDown() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()+1), controller.getCurrentPosition().getX());
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getY()==GameViewController.BOARD_ROWS) {
			controller.getCurrentPosition().realocate((0), controller.getCurrentPosition().getX());
		}
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
	private void handleUp() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()-1), controller.getCurrentPosition().getX());
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getY()== -1) {
			controller.getCurrentPosition().realocate((GameViewController.BOARD_ROWS-1), controller.getCurrentPosition().getX());
		}
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
	
	private void handleLeft() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), controller.getCurrentPosition().getX()-1);
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getX()==-1) {
			controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), controller.getNumberColumns()-1);
		}
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
	
	private void handleRight() {
		controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), controller.getCurrentPosition().getX()+1);
		// if User reaches the end of the board reset position
		if(controller.getCurrentPosition().getX()== controller.getNumberColumns()-1) {
			controller.getCurrentPosition().realocate((controller.getCurrentPosition().getY()), 0);
		}
		for (Node n : controller.getGridBoard().getChildren()) {
			((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
			if (GridPane.getColumnIndex(n) == controller.getCurrentPosition().getX() && GridPane.getRowIndex(n) == controller.getCurrentPosition().getY()) {
				((StackPane) n).pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
			}
		}
	}
}
