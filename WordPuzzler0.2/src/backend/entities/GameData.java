package backend.entities;

import java.time.Duration;
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
	
	
	// Dummy fürs Testen
	public GameData(Player player, LocalDate date, LocalDateTime matchstart, LocalDateTime matchend, int score) {
		super();
		this.player = player;
		this.date = date;
		this.matchstart = matchstart;
		this.matchend = matchend;
		this.score = score;
		this.id = ++counter;
	}
	
	
	
	public Long getDuration() {
		return duration;
	}
	public void setDuration() {
		Duration span = Duration.between(matchstart, matchend);
		this.duration = span.toMillis()+2000;
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
	
	
	
	
	
	
}
