package backend.entities.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.entities.Player;
import backend.entities.PlayerRepository;

public class PlayerRepositoryJDBC implements PlayerRepository {

	private String url, host, password;

	public PlayerRepositoryJDBC(String url, String host, String password) {

		this.url = url;
		this.host = host;
		this.password = password;

	}

	@Override
	public List<Player> getLeaderBoard() {
		return loadData();
	}

	@Override
	public void deletePlayer(Player player) {
		try (Connection conn = DriverManager.getConnection(url, host, password)){
			String query = "DELETE FROM players WHERE name = ?";
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			prepStatement.setString(1, player.getUsername());
			
			prepStatement.executeUpdate();
				
			
		} catch (Exception e) {
			System.err.println("Fehler beim loeschen des player-Objectes" + e.getMessage());
		}
	}
	
	
	@Override
	public void insertPlayer(Player player) {
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String query = "INSERT INTO players (name, highscore) VALUES (?, ?)";
			PreparedStatement pStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pStatement.setString(1, player.getUsername());
			pStatement.setInt(2, player.getHighscore());

			pStatement.executeUpdate();

		} catch (Exception e) {
			System.err.println("Fehler beim hinzuf[egen des Datensatzes" + e.getMessage());
		}
	}

	@Override
	public void updatePlayer(Player player) {
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String query = "UPDATE players set highscore = ? WHERE name = ?";
			PreparedStatement pStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, player.getHighscore());
			pStatement.setString(2, player.getUsername());

			pStatement.executeUpdate();

		} catch (Exception e) {
			System.err.println("Fehler beim updaten des Datensatzes" + e.getMessage());
		}
	}

	private List<Player> loadData() {
		List<Player> leaderBoard = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, host, password)) {

			String stmt = "select * from players ORDER BY highscore DESC";
			PreparedStatement pStatement = conn.prepareStatement(stmt);

			ResultSet result = pStatement.executeQuery();

			while (result.next()) {

				Player tmp = new Player();

				tmp.setId(result.getInt("id"));
				tmp.setHighscore(result.getInt("highscore"));
				tmp.setUsername(result.getString("name"));

				leaderBoard.add(tmp);
				tmp.setPlacement((leaderBoard.indexOf(tmp) + 1));
			}

		} catch (Exception e) {
			System.err.println("Fehler bei beim laden des Leaderboards" + e.getMessage());
		}

		return leaderBoard;

	}

	@Override
	public Player byName(String name) {
		Player entity = new Player();

		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String query = "SELECT * FROM players WHERE name = ?";

			PreparedStatement prepStatement = conn.prepareStatement(query);

			prepStatement.setString(1, name);

			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				entity.setUsername(result.getString("name"));
				entity.setHighscore(result.getInt("highscore"));
				entity.setId(result.getInt("id"));
			}

		} catch (Exception e) {
			System.err.println("Fehler beim lesen des player Datensatzes");
		}

		return entity;

	}

}
