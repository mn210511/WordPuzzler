package backend.entities;

public class Player {

	private String username;
	private int highscore;
	private int placement;
	private int id;
	
	
	public Player(String username, int highscore, int placement) {
		super();
		this.username = username;
		this.highscore = highscore;
		this.placement = placement;

		
	}
	// construktor for the 'dummy'-data
	public Player(String username, int highscore) {
		super();
		this.username = username;
		this.highscore = highscore;
	
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
	if(highscore >= this.highscore) this.highscore = highscore;
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
	
	

}
