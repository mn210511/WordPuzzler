package backend.entities;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

	private List<Player> leaderboard;
	
	public Leaderboard() {
		leaderboard = new ArrayList<>();
		leaderboard.add(new Player("Melanie", 1000, 1));
		leaderboard.add(new Player("Nicolas", 999, 2));
		leaderboard.add(new Player("Son Goku", 500, 3));
	}
	
	public void setLeaderboard(List<Player> leaderboard) {
		this.leaderboard = leaderboard;
	}
	
	public List<Player> getLeaderboard() {
		return leaderboard;
	}
	
public void addEntry(Player entry) {
	leaderboard.add(entry);
}
	

}
