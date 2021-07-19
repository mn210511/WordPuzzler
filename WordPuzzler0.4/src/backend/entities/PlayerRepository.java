package backend.entities;

import java.util.List;

public interface PlayerRepository {

	List<Player> getLeaderBoard();

	void deletePlayer(Player player);

	void insertPlayer(Player player);

	void updatePlayer(Player player);

	Player byName(String name);

}