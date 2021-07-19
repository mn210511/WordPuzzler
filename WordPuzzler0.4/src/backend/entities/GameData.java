package backend.entities;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GameData {
	private static int counter;
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
		this.id = ++counter;
	}
	
	public GameData(LocalDateTime matchstart) {
		this.matchstart = matchstart;
	}
	
	public GameData() {
		// TODO Auto-generated constructor stub
	}
	// Dummy fürs Testen
	public GameData(Player player, LocalDate date, LocalDateTime matchstart, LocalDateTime matchend, int score, int id) {
		super();
		this.player = player;
		this.date = date;
		this.matchstart = matchstart;
		this.matchend = matchend;
		this.score = score;
		this.id = id;
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
	
	public void setMatchstart(LocalDateTime matchstart) {
		this.matchstart = matchstart;
	}
	
	public Long getDuration() {
		return duration;
	}
	public void setDuration() {
		Duration span = Duration.between(matchstart, matchend);
		this.duration = span.toMillis();
	}
	public LocalDateTime getMatchend() {
		return matchend;
	}
	public void setMatchend(LocalDateTime matchend) {
		this.matchend = matchend;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
	public LocalDateTime getMatchstart() {
		return matchstart;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	
	
	
	@Override
	public String toString() {
		
		return player.getUsername().toString() + "" + matchstart.toString() + " | " + matchend.toString() + "|" + Instant.ofEpochMilli(duration) + " \t" + getScore();
	}
	
	
}
