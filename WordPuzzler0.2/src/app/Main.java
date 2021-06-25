package app;

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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/views/mainMenue.fxml"));
		Parent root = loader.load();
	//	test1Controller ctrl = loader.getController();
		Scene scene = new Scene(root, 800, 700);
		scene.getStylesheets().add(getClass().getResource("/frontend/views/styles.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Erste Tests");
		primaryStage.show();
		
		
	}

	
	
	
	
}
