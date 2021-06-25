package backend.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;

public class Validator {

//	public static void main(String[] args) {
//	
//		
//		System.out.println(testWord("wem"));
//		
//		
//
//	}
	
	/**
	 * checks if the entered word is valid
	 * @param word the word too check 
	 * @return true = word is valid, false = word is not valid
	 */
	public static boolean testWord(String word) {

		try (BufferedReader reader = new BufferedReader(new FileReader("dict.txt", Charset.forName("UTF-8")))){
			String line;			
			while((line = reader.readLine()) != null) {
				if(word.toLowerCase().equals(line.toLowerCase())) {
					return true;
				}			
			}
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return false;
		
	}
	

}
