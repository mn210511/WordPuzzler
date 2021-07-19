package backend.entities;

import java.util.List;

public interface PlayerRepository {

	/**
	 * calls the loadData method
	 * @return a List with all player objects
	 */
	List<Player> getLeaderBoard();

	/**
	 * deletes a player from the Database
	 * @param player the player who should be deleted
	 */
	void deletePlayer(Player player);

	/**
	 * inserts a player into the database
	 * @param player the player who should be added
	 */
	void insertPlayer(Player player);

	/**
	 * updates a player object in the database
	 * @param player the player who should be updaded
	 */
	void updatePlayer(Player player);

	/**
	 * searches the database for a player object which name equals the parameter
	 * @param name the name to search for
	 * @return the player with the searched name
	 */
	Player byName(String name);

}