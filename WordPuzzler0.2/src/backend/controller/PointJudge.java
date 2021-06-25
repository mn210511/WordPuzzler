package backend.controller;

import backend.entities.Points;

public class PointJudge {

	

	
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
