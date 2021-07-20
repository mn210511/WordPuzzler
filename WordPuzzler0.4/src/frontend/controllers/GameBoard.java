package frontend.controllers;

import backend.entities.Position;

public class GameBoard {

	
	
	private char[][] virtuellBoard;
		
		
	/**
	 * costum constructor with rows and columns for the size of virtuellBoard array	
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	public GameBoard(int rows, int columns) {
			virtuellBoard = new char[rows][columns];
			System.out.println("board erzeugt" + rows + " " + columns);
	}
	
	/**
	 * set a letter into the array
	 * @param c the letter to set
	 * @param p the position in the array
	 */
	public void setLetterAtPosition(char c, Position p) {
		this.virtuellBoard[p.getY()][p.getX()] = c;
		
		
	}

	/**
	 * deletes the letter from the array on the given position
	 * @param p the position
	 */
	public void deleteLetterFromPosition(Position p) {
		if(this.virtuellBoard[p.getY()][p.getX()]!= 0) {
			System.out.println(	String.valueOf(virtuellBoard[p.getY()][p.getX()])	 + " - geloescht");
			this.virtuellBoard[p.getY()][p.getX()] = 0;
			
		}	
		
	}
	
	/**
	 * checks if there is content on the given Position
	 * @param p the position to check
	 * @return true if filled false if empty
	 */
	public boolean positionFilled(Position p) {
		return (this.virtuellBoard[p.getY()][p.getX()]!= 0) ?  true : false;
	}
	
	/**
	 * creates a word from the letters in the given columns an returns it to the caller
	 * @param column the column where the letters are
	 * @return the builded word as String
	 * @throws Exception if the word contains empty spaces
	 */
	public String getWordFromColumn(int column) throws Exception {
		String result = "";
		int indexWordStart = 0;
		int indexWordEnd = 0;
		// determine first index of the word
		for (int i = 0; i < virtuellBoard.length; i++) {
			if (virtuellBoard[i][column] != 0) {
				indexWordStart = i;
				break;
			}
		
		}
		// determine last index of the word
		for (int i = virtuellBoard.length-1; i > -1; i--) {
			if (virtuellBoard[i][column] != 0) {
			indexWordEnd = i;
			break;	
			}
		}
			
		while (indexWordStart <= indexWordEnd) {
			result = result.concat(String.valueOf(virtuellBoard[indexWordStart][column]));
			if(virtuellBoard[indexWordStart][column] == 0) {
				throw new Exception("Wort enthält ein Leerzeichen");
			}
			indexWordStart++;
		}
			
			
			
		return result;
	}
	
	
//	public String getWordFromColumn(int column) {
//		String result = "";
//		for (int i = 0; i < virtuellBoard.length; i++) {
//			if (virtuellBoard[i][column] != 0) {
//				
//				result = result.concat(String.valueOf(virtuellBoard[i][column]));
//				System.out.println("output" + result);
//			}
//
//		}
//		return result;
//	}
	
	
	
	
}
