package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import backend.entities.GameData;
import backend.entities.Login;
import backend.entities.Player;

public class CreateDatabase {

	private final static String URL = Constant.MARIA_DB_URL;
	private final static String USER = Constant.MARIA_DB_USER;
	private final static String PASSWORD = Constant.MARIA_DB_PASSWORD;
	
	private final static String CREATE_LOGIN_TABLE = "CREATE TABLE logins ("
			+ "id INT NOT NULL AUTO_INCREMENT,"
			+ "username VARCHAR (250) NOT NULL UNIQUE,"
			+ "password VARCHAR (15) NOT NULL,"
			+ "PRIMARY KEY (id)"
			+ ");";
	
	
	private final static String CREATE_PLAYERS_TABLE = "CREATE TABLE players ("
			+ "id INT NOT NULL AUTO_INCREMENT,"
			+ "name VARCHAR (250) NOT NULL UNIQUE,"
			+ "highscore INT NULL,"
			+ "PRIMARY KEY (id),"
			+ "FOREIGN KEY (name) REFERENCES logins (username)"
			+ ");";
	
	private final static String CREATE_GAMEDATA_TABLE = "CREATE TABLE gameData ("
			+ "id INT NOT NULL AUTO_INCREMENT,"
			+ "name VARCHAR (250) NOT NULL,"
			+ "score INT NULL,"
			+ "matchStart DATETIME NOT NULL,"
			+ "matchEnd DATETIME NOT NULL,"
			+ "duration LONG NOT NULL,"
			+ "PRIMARY KEY (id),"
			+ "FOREIGN KEY (name) REFERENCES players (name)"
			+ ");";
	
	private final static String DROP_LOGIN_TABLE = "DROP TABLE logins";
	private final static String DROP_PLAYER_TABLE = "DROP TABLE players";
	private final static String DROP_GAMEDATA_TABLE = "DROP TABLE gameData";
	
	
	private static void createLoginTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(CREATE_LOGIN_TABLE);
	}
	
	
	
	private static void createPlayersTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(CREATE_PLAYERS_TABLE);
	}
	
	private static void createGameDataTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(CREATE_GAMEDATA_TABLE);
	}
	
	private static void dropLoginTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(DROP_LOGIN_TABLE);
	}
	private static void dropPlayerTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(DROP_PLAYER_TABLE);
	}
	private static void dropGameDataTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(DROP_GAMEDATA_TABLE);
	}
	
	
	private static void insertLogin(Login login, Connection conn) throws SQLException {
	
			String query = "INSERT INTO logins (username, password) VALUES (?, ?)";
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			prepStatement.setString(1, login.getUsername());
			prepStatement.setString(2, login.getPassword());
			
			int rowsChanged = prepStatement.executeUpdate();
			System.out.println("Rows changed: " + rowsChanged);

	}
	private static  void insertPlayer(Player player, Connection conn) throws SQLException {

			String query = "INSERT INTO players (name, highscore) VALUES (?, ?)";
			PreparedStatement pStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pStatement.setString(1, player.getUsername());
			pStatement.setInt(2, player.getHighscore());

			int rowsChanged = pStatement.executeUpdate();
			System.out.println("Rows changed: " + rowsChanged);
	
	}
	private static void insertGameData(GameData data, Connection conn) throws SQLException {
	
			String query = "INSERT INTO gameData (name, score, matchStart, matchEnd, duration) values (?, ?, ?, ?, ?)";
			PreparedStatement pStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, data.getPlayer().getUsername());
			pStatement.setInt(2, data.getScore());
			pStatement.setTimestamp(3, Timestamp.valueOf(data.getMatchstart()));
			pStatement.setTimestamp(4, Timestamp.valueOf(data.getMatchend()));
			data.setDuration();
			pStatement.setLong(5, data.getDuration());

			int rowsChanged = pStatement.executeUpdate();
	
			System.out.println("Rows changed: " + rowsChanged);
	}
	
	
	private static List<GameData> readAllGameData(Connection conn) throws SQLException {
		List<GameData> gameDatas = new ArrayList<>();
			

			Statement stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery("select * from gameData");

			while (result.next()) {

				GameData tmp = new GameData();

				tmp.setPlayer(new Player(result.getString("name")));
				tmp.setId(result.getInt("id"));
				tmp.setScore(result.getInt("score"));
				tmp.setDate(result.getDate("matchEnd").toLocalDate());
				tmp.setMatchstart(LocalDateTime.of(result.getDate("matchStart").toLocalDate(),
						result.getTime("matchStart").toLocalTime()));
				tmp.setMatchend(LocalDateTime.of(result.getDate("matchEnd").toLocalDate(),
						result.getTime("matchEnd").toLocalTime()));
				tmp.setDuration();
				
				gameDatas.add(tmp);

			}
			
	
		return gameDatas;

	}
	
	private static Login getLoginByName(Connection conn, String name) throws SQLException {
		Login log = new Login();
		
		String query = "SELECT * FROM logins where username = ?";
		PreparedStatement prepStatment = conn.prepareStatement(query);
		
		prepStatment.setString(1, name);
		ResultSet res = prepStatment.executeQuery();
		
		while(res.next()) {
			log.setID(res.getInt("id"));
			log.setUsername(res.getString("username"));
			log.setPassword(res.getString("password"));
			
			
		}
			
		return log;
	}
	
	private static void insertMultipleGames(List<GameData> games, Connection conn) throws SQLException {
		
		PreparedStatement pStmt = conn.prepareStatement("INSERT INTO gameData (name, score, matchStart, matchEnd, duration) values (?, ?, ?, ?, ?)");
		
		for (GameData gameData : games) {
			
			pStmt.setString(1, gameData.getPlayer().getUsername());
			pStmt.setInt(2, gameData.getScore());
			pStmt.setTimestamp(3, Timestamp.valueOf(gameData.getMatchstart()));
			pStmt.setTimestamp(4, Timestamp.valueOf(gameData.getMatchend()));
			gameData.setDuration();
			pStmt.setLong(5, gameData.getDuration());
		
			
			pStmt.addBatch();
			
			
		}
		
		int[] rowsChanged = pStmt.executeBatch();
		System.out.println("Rows changed: " + rowsChanged[0] + ", " + rowsChanged[1]);
		
	}
	
	
	
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			
//			dropGameDataTable(conn);
//			dropPlayerTable(conn);
//			dropLoginTable(conn);
			
			System.out.println("Tabelle GameData, Players und Logins gelöscht");
			
			
			createLoginTable(conn);
			System.out.println("Tabelle login wurde erstellt");
				
			Login login = new Login("Darth Vader", "Luke");
			insertLogin(login, conn);
			login = getLoginByName(conn, "Darth Vader");
			System.out.println("Login hinzugefuegt und gelesen mit ID: " + login.getID());
			
			Login login2 = new Login("Luke Skywalker", "lovedSon");
			insertLogin(login2, conn);
			login = getLoginByName(conn, "Luke Skywalker");
			System.out.println("Login hinzugefuegt und gelesen mit ID: " + login.getID());
			
			
			createPlayersTable(conn);
			System.out.println("Tabelle players wurde erstellt");
			Player player = new Player("Darth Vader", 100);
			
			insertPlayer(player, conn);
					
			
			createGameDataTable(conn);
			System.out.println("Tabelle gameData wurde erstellt");
			GameData game = new GameData(player, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 100);

			insertGameData(game, conn);
			GameData game2 = new GameData(player, LocalDate.of(2021, 03, 12), LocalDateTime.of(2021, 03, 12, 12, 00), LocalDateTime.of(2021, 03, 12, 13, 00), 80);

			GameData game3 = new GameData(player, LocalDate.of(2021, 04, 12), LocalDateTime.of(2021, 04, 12, 11, 00), LocalDateTime.of(2021, 04, 12, 13, 00), 70);
		
			insertMultipleGames(List.of(game2, game3), conn);
			
				
				
			readAllGameData(conn).forEach(System.out::println);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
