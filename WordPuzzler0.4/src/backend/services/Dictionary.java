package backend.services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import app.Constant;
import app.MessageBox;

public class Dictionary {
	private static List<String> dictionary;
	
	static {
		try {
			dictionary = Files.lines(Path.of(Constant.DICTIONARY)).collect(Collectors.toList());

		} catch (IOException e) {
			MessageBox.show("Error", "Fehler beim Laden des Wörterbuches \n gelegte Wörter können nicht geprüft werden");
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
			MessageBox.show("Error", "Wörterbuch konnte nicht gespeichert werden \n Hinzugefügte Wörter sind verloren gegangen");
			e.printStackTrace();
		}
	}
	
	/**
	 * searches a random word (bigger then 6 letters and smaller then 16 letters) from the dictionary and returns it
	 * 
	 * @return a randow word from the dictionary
	 */
	public String getRandomWord() {
		Random rng = new Random();
		String ret = dictionary.get(rng.ints(0, dictionary.size()).findFirst().getAsInt());
		if(ret.length() > 15 || ret.length() < 6) {
			 ret = getRandomWord();
		} 
		return ret;
		 
	}

}
