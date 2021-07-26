package backend.entities;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GameData {

	private int id;
	private Player player;
	private Long duration;
	private int score;
	private LocalDate date;
	private LocalDateTime matchstart;
	private LocalDateTime matchend;
	
	public GameData(Player player, LocalDateTime matchstart) {
		super();
		this.player = player;
		this.matchstart = matchstart;

	}
	
	public GameData(LocalDateTime matchstart) {
		this.matchstart = matchstart;
	}
	
	public GameData() {
		// TODO Auto-generated constructor stub
	}


	// without ID for the SQL Test Script
	public GameData(Player player, LocalDate date, LocalDateTime matchstart, LocalDateTime matchend, int score) {
		super();
		this.player = player;
		this.date = date;
		this.matchstart = matchstart;
		this.matchend = matchend;
		this.score = score;
		
	}
	/**
	 * setter for the matchstart attribute
	 * @param matchstart
	 */
	public void setMatchstart(LocalDateTime matchstart) {
		this.matchstart = matchstart;
	}
	
	/**
	 * getter for the duration attribute
	 * @return the Duration
	 */
	public Long getDuration() {
		return duration;
	}
	/**
	 * setter for the duration attribute
	 */
	public void setDuration() {
		Duration span = Duration.between(matchstart, matchend);
		this.duration = span.toMillis();
	}
	/**
	 * getter for the matchend attribute
	 * @return the matchend
	 */
	public LocalDateTime getMatchend() {
		return matchend;
	}
	/**
	 * setter for the matchend attribute
	 * @param matchend
	 */
	public void setMatchend(LocalDateTime matchend) {
		this.matchend = matchend;
	}
	/**
	 * getter for the id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * setter for id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * setter for the player attribute
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	/**
	 * getter for the player attribute
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * getter for the matchStart Attribute
	 * @return the matchStart
	 */
	public LocalDateTime getMatchstart() {
		return matchstart;
	}
	/**
	 * getter for the date attribute
	 * @return
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * setter for the date attribute
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * setter for the score attribute
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * getter for the score attribute
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	
	
	@Override
	public String toString() {
		
		return player.getUsername().toString() + "" + matchstart.toString() + " | " + matchend.toString() + "|" + Instant.ofEpochMilli(duration) + " \t" + getScore();
	}
	
	
}
