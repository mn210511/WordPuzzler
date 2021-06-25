package backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonalBoard {

	List<GameData> perScore;
	
	
	
	public PersonalBoard(String username) {
		this.perScore = new ArrayList<>();
		load(username);
	}
	
	
	public List<GameData> getPerScore() {
		return perScore;
	}
	
	
	
	private void load(String username) {
	Player p = new Player("Nicolas", 1000, 1);
	perScore.add(new GameData(p, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 1000));
	perScore.get(0).setDuration();
	perScore.add(new GameData(p, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 2000));
	perScore.add(new GameData(p, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 1234));
	}
	
}
