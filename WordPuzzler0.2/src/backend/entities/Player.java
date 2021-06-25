package backend.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private static int counter;
	private String username;
	private int highscore;
	private int placement;
	private int id;
	private List<GameData> playedGames;
	
	public Player(String username, int highscore, int placement) {
		super();
		this.username = username;
		this.highscore = highscore;
		this.placement = placement;
		this.id = ++counter;
		this.playedGames = new ArrayList<>();
	}
	
	public Player() {
		
	}
	
public Player(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

public int getHighscore() {
	return highscore;
}

	public void setHighscore(int highscore) {
		for (GameData gameData : playedGames) {
			if(gameData.getScore() > highscore) {
				highscore = gameData.getScore();
			}
		}
	}

	public int getPlacement() {
		return placement;
	}

	public void setPlacement(int placement) {
		this.placement = placement;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public List<GameData> getPlayedGames() {
		return playedGames;
	}

}
