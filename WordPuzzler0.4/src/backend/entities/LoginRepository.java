package backend.entities;

public interface LoginRepository {

	/**
	 * inserts a login entity into the database
	 * 
	 * @param login
	 */
	void insertData(Login login);

	/**
	 * checks if the username and the password of the given login matches.
	 * 
	 * @param log
	 * @return true = it matches, false = does not match
	 */
	boolean checkLogin(Login log);

	/**
	 * checks if a Username is already in use
	 * 
	 * @param username the username to check
	 * @return true if the name is already used, false if the username is unused
	 */
	boolean UserExists(String username);

}