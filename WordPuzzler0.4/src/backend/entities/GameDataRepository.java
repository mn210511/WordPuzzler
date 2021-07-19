package backend.entities;

import java.util.List;

public interface GameDataRepository {

	/**;
	 * loads all Gamedata-Objects that are related to the logged in User from the databases.
	 * an returns them to the caller of the method
	 * @return a List with Gamedata-Objects
	 */
	List<GameData> getPerScore(Player player);

	/**
	 * delets all Gamedata-objects from the database that are related to the current user
	 * @param username the name of the user
	 */
	void deleteUserData(String username);

	/**
	 * saves a GameData object in the database
	 * @param data the object to be saved
	 */
	void insertData(GameData data);
	
	/**
	 * loads the avg score from the player object from the database
	 * @param player the player whos avg score should be loaded
	 * @return the average score
	 */
	int avgScore(Player player);

}