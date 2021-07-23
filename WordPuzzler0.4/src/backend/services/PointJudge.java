package backend.services;

import backend.entities.Points;

public class PointJudge {

	

	/**
	 * method to 'rate' the given String. checks each letter in the word for his value and sums it.
	 * @param in the word to rate
	 * @return the value of the word
	 */
	public static int rate(String in) {
		int score = 0;
		char[] splitIn = in.toCharArray();
		
		for (char c : splitIn) {
		for (Points p : Points.values()) {
			if(p.toString().toLowerCase().equals(String.valueOf(c).toLowerCase())) {
				System.out.println(p.getValue());
				score += p.getValue();
				
			}
		}
		}
			
		
		return score;
	}
}
