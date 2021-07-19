package backend.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import app.Constant;
import app.MessageBox;
import frontend.controllers.ResultScreenController;

public class Validator {
	private static List<String> dictionary;
	
	static {
		try {
			dictionary = Files.lines(Path.of(Constant.DICTIONARY)).collect(Collectors.toList());

		} catch (IOException e) {
			MessageBox.show("Error", e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * checks if the entered word is valid
	 * 
	 * @param word the word too check
	 * @return true = word is valid, false = word is not valid
	 */
	public boolean testWord(String word) {
	
		if (dictionary.contains(word.toLowerCase())) {
			return true;
		}

		return false;

	}

	/**
	 * adds the given word in the 'dictionary' List
	 * @param word
	 * @return
	 */
	public boolean addWord(String word) {
		return dictionary.add(word);

	}

	/**
	 * writes the 'dictionary'-List into the dictionary file to save the added words for the future
	 */
	public void write() {
		try {
			Files.write(Path.of(Constant.DICTIONARY), dictionary, Charset.forName("UTF-8"));
		} catch (IOException e) {
			MessageBox.show("Error", e.getMessage());
		}
	}
	
	/**
	 * searches a random word from the dictionary and returns it
	 * 
	 * @return a randow word from the dictionary
	 */
	public String getRandomWord() {
		Random rng = new Random();
		return dictionary.get(rng.ints(0, dictionary.size()).findFirst().getAsInt());
	}

}
