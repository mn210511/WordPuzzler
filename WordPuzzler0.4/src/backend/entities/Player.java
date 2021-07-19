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
	/**
	 * constructor for the sql-script
	 * @param username
	 * @param highscore
	 */
	public Player(String username, int highscore) {
		super();
		this.username = username;
		this.highscore = highscore;


		
	}

	/**
	 * default constructor
	 */
	public Player() {
		
	}
	
	
public Player(String username) {
		this.username = username;
	}

/**
 * getter for the userName attribute
 * @return the username
 */
	public String getUsername() {
		return username;
	}

	/**
	 * setter for the username attribute
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
/**
 * getter for the highscore attribute
 * @return the highscore
 */
public int getHighscore() {
	return highscore;
}

/**
 * setter for the highscore attribute
 * @param highscore
 */
	public void setHighscore(int highscore) {
	if(highscore >= this.highscore) this.highscore = highscore;
	}

	/**
	 * getter for the placement attribute
	 * @return the placement
	 */
	public int getPlacement() {
		return placement;
	}

	/**
	 * setter for the placement attribute
	 * @param placement
	 */
	public void setPlacement(int placement) {
		this.placement = placement;
	}
	/**
	 * setter for the id attribute
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * getter for the id attribute
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	

}
