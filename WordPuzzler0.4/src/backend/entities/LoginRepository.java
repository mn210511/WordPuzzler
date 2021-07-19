package backend.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.MessageBox;

public class LoginRepository {


	private String url, host, password;

	public LoginRepository(String url, String host, String password) {
		this.url = url;
		this.host = host;
		this.password = password;

	}



	/**
	 * inserts a login entity into the database
	 * 
	 * @param login
	 */
	public void insertData(Login login) {
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String query = "INSERT INTO logins (username, password) VALUES (?, ?)";
			PreparedStatement prepStatement = conn.prepareStatement(query);

			prepStatement.setString(1, login.getUsername());
			prepStatement.setString(2, login.getPassword());

			prepStatement.executeUpdate();


	
		} catch (Exception e) {
			System.err.println("Fehler beim Hinzufuegen des logins" + e.getMessage());
		}
	}

	/**
	 * checks if the username and the password of the given login matches.
	 * 
	 * @param log
	 * @return true = it matches, false = does not match
	 */
	public boolean checkLogin(Login log) {

		Login fromDB = new Login();
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			PreparedStatement pStmt = conn.prepareStatement("Select * from logins WHERE username = ?");
			pStmt.setString(1, log.getUsername());
			ResultSet res = pStmt.executeQuery();

			while (res.next()) {

				fromDB = new Login(res.getString("username"), res.getString("password"));

			}

		} catch (Exception e) {
			MessageBox.show("Error", "Fehler beim prüfen des logins");
			return false;
		}
	
		return log.getPassword().equals(fromDB.getPassword()) ?  true :  false;
		
	}

	/**
	 * checks if a Username is already in use
	 * 
	 * @param username the username to check
	 * @return true if the name is already used, false if the username is unused
	 */
	public boolean UserExists(String username) {
		
		try (Connection conn = DriverManager.getConnection(url, host, password)){
			
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM logins where username = ?");
			pStmt.setString(1, username);
			ResultSet res = pStmt.executeQuery();
			
			while (res.next()) {
				Login tmp = new Login(res.getString("username"), res.getString("password"));
				if(username.equalsIgnoreCase(tmp.getUsername())) {
					return true;
				}
			}
					
		} catch (Exception e) {
			MessageBox.show("Error", e.getMessage());
		}
		
		return false;
	}

}
