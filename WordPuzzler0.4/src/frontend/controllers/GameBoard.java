package frontend.controllers;

import backend.entities.Position;

public class GameBoard {

	
	
	private char[][] virtuellBoard;
		
		
		
	public GameBoard(int rows, int columns) {
			virtuellBoard = new char[rows][columns];
			System.out.println("board erzeugt" + rows + " " + columns);
	}
	
	public void setLetterAtPosition(char c, Position p) {
		this.virtuellBoard[p.getY()][p.getX()] = c;
		
		
	}

	
	public void deleteLetterFromPosition(Position p) {
		if(this.virtuellBoard[p.getY()][p.getX()]!= 0) {
			System.out.println(	String.valueOf(virtuellBoard[p.getY()][p.getX()])	 + " - geloescht");
			this.virtuellBoard[p.getY()][p.getX()] = 0;
			
		}	
		
	}
	
	public boolean positionFilled(Position p) {
		return (this.virtuellBoard[p.getY()][p.getX()]!= 0) ?  true : false;
	}
	
	
	public String getLastWord(int column) {
		String result = "";
		for (int i = 0; i < virtuellBoard.length; i++) {
			if (virtuellBoard[i][column] != 0) {
				result = result.concat(String.valueOf(virtuellBoard[i][column]));
				System.out.println("output" + result);
			}

		}
		return result;
	}
	
	

	
	
	
	
}
