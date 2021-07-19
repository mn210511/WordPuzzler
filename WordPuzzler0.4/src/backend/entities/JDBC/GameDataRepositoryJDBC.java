package backend.entities.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import app.MessageBox;
import backend.entities.GameData;
import backend.entities.GameDataRepository;
import backend.entities.Player;

public class GameDataRepositoryJDBC implements GameDataRepository {


	private String url, host, password;


	public GameDataRepositoryJDBC(String url, String host, String password) {
		this.url = url;
		this.host = host;
		this.password = password;

	}

	/**;
	 * loads all Gamedata-Objects that are related to the logged in User from the databases.
	 * an returns them to the caller of the method
	 * @return a List with Gamedata-Objects
	 */
	@Override
	public List<GameData> getPerScore(Player player) {
		return loadData(player);
	}

/**
 * delets all Gamedata-objects from the database that are related to the current user
 * @param username the name of the user
 */
@Override
public void deleteUserData(String username) {
	try (Connection conn = DriverManager.getConnection(url, host, password)){
		String query = "DELETE from gameData WHERE name = ?";
		PreparedStatement prepStatement = conn.prepareStatement(query);
		
		prepStatement.setString(1, username);
		
		prepStatement.executeUpdate();
		
	} catch (Exception e) {
		System.err.println("Fehler beim Löschen der User-Daten" + e.getMessage());
	}
	
		
}
	/**
	 * saves a GameData object in the database
	 * @param data the object to be saved
	 */
	@Override
	public void insertData(GameData data) {
		try (Connection conn = DriverManager.getConnection(url, host, password)) {

			String query = "INSERT INTO gameData (name, score, matchStart, matchEnd, duration) values (?, ?, ?, ?, ?)";
			PreparedStatement pStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, data.getPlayer().getUsername());
			pStatement.setInt(2, data.getScore());
			pStatement.setTimestamp(3, Timestamp.valueOf(data.getMatchstart()));
			pStatement.setTimestamp(4, Timestamp.valueOf(data.getMatchend()));
			pStatement.setLong(5, data.getDuration());

			int rowsAccected = pStatement.executeUpdate();
			if (rowsAccected != 1) {
				System.out.println(rowsAccected);
				System.err.println("Datensatz nicht hinzugefuegt");
			}

			ResultSet keys = pStatement.getGeneratedKeys();
			System.out.println(keys);

		} catch (Exception e) {
			System.err.println("Fehler beim Hinzufügen der GameData" + e.getMessage());
		}

	}
	
	
	/**
	 * loads all gameData-Objects that are related to the current user and saves them in a List
	 * @param username username of the current user.
	 * @return a List with GameData-Objects
	 */
	private List<GameData> loadData(Player player) {
		List<GameData> perScore = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			
			String stmt = "select * from gameData where name = ?";
			PreparedStatement pStatement = conn.prepareStatement(stmt);
			pStatement.setString(1, player.getUsername());

			ResultSet result = pStatement.executeQuery();

			while (result.next()) {

				GameData tmp = new GameData();

				tmp.setPlayer(player);
				tmp.setId(result.getInt("id"));
				tmp.setScore(result.getInt("score"));
				tmp.setDate(result.getDate("matchEnd").toLocalDate());
				tmp.setMatchstart(LocalDateTime.of(result.getDate("matchStart").toLocalDate(),
						result.getTime("matchStart").toLocalTime()));
				tmp.setMatchend(LocalDateTime.of(result.getDate("matchEnd").toLocalDate(),
						result.getTime("matchEnd").toLocalTime()));
				tmp.setDuration();

				perScore.add(tmp);

			}
			
		} catch (Exception e) {
			System.err.println("Fehler bei der Verbindung mit der Datenbank" + e.getMessage());
		}
		return perScore;

	}

	@Override
	public int avgScore(Player player) {
		int ret = 0;
		try (Connection conn = DriverManager.getConnection(url, host, password)){
			
			String stmt = "Select AVG(score) from gamedata where name = ?";
			PreparedStatement pStatement = conn.prepareStatement(stmt);
			pStatement.setString(1, player.getUsername());
			ResultSet result = pStatement.executeQuery();
			
			while(result.next()) {
			ret = (int)result.getLong("avg(score)");
			}
			
		} catch (SQLException e) {
			MessageBox.show("Error", e.getMessage());
		}
		
		
		
		
		return ret;
		
	}
	
	

}
