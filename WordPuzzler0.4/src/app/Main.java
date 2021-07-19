package app;

import backend.controller.Validator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.MAIN_MENUE_FXML));
		Parent root = loader.load();
	//	test1Controller ctrl = loader.getController();
		Scene scene = new Scene(root, 600, 900);
		scene.getStylesheets().add(getClass().getResource(Constant.STYLE_SHEET).toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Erste Tests");
		primaryStage.show();
		
		
	}

	@Override
	public void init() throws Exception {
		
		super.init();

		
	}
	
	
	@Override
	public void stop() throws Exception {
		
		new Validator().write();
		
		super.stop();
	}
	
	
}

