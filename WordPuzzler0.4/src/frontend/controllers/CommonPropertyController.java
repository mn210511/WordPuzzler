package frontend.controllers;

import app.Constant;
import backend.entities.GameDataRepository;
import backend.entities.Player;
import backend.entities.PlayerRepository;
import backend.entities.JDBC.GameDataRepositoryJDBC;
import backend.entities.JDBC.PlayerRepositoryJDBC;
import backend.services.Dictionary;

public abstract class CommonPropertyController {

	public static PlayerRepository playerRepo = new PlayerRepositoryJDBC(Constant.MARIA_DB_URL, Constant.MARIA_DB_USER, Constant.MARIA_DB_PASSWORD);
	public static Player player = new Player();
	public static GameDataRepository gameDataRepo = new GameDataRepositoryJDBC(Constant.MARIA_DB_URL, Constant.MARIA_DB_USER, Constant.MARIA_DB_PASSWORD);
	public static Dictionary val = new Dictionary();
	
	
	
}
