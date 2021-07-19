package frontend.controllers;

import app.Constant;
import backend.controller.Validator;
import backend.entities.GameDataRepository;
import backend.entities.Player;
import backend.entities.PlayerRepository;
import backend.entities.JDBC.GameDataRepositoryJDBC;
import backend.entities.JDBC.PlayerRepositoryJDBC;

public abstract class CommonPropertyController {

	public static PlayerRepository playerRepo = new PlayerRepositoryJDBC(Constant.MARIA_DB_URL, "root", "");
	public static Player player = new Player();
	public static GameDataRepository gameDataRepo = new GameDataRepositoryJDBC(Constant.MARIA_DB_URL, "root", "");
	public static Validator val = new Validator();
	
	
	
}
