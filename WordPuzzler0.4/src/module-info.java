module wordPuzzler {
	// Java FX
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	
	
	//JDBC API
	requires java.sql;
	

	opens frontend.controllers;
	opens app;
	opens backend.controller;

	opens backend.entities;
	opens frontend.views;
}