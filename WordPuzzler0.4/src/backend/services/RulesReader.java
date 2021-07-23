package backend.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class RulesReader{

	/**
	 * reads the rules.txt file and saves it in a String
	 * @return the rules as a String
	 * @throws IOException
	 */
	public String getRules() throws IOException {
		String output = "";
		List<String> tmp = Files.lines(Path.of("rules.txt")).collect(Collectors.toList());
		for (String string : tmp) {
			output = output.concat(string + "\n");
		}
		return output;
	}
}
