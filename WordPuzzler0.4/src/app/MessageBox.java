package app;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MessageBox {

	public static ButtonType show(String titel, String message) {
		return show(titel, message, AlertType.INFORMATION, ButtonType.OK);
	}
	
	
	public static ButtonType show(String titel, String message, AlertType type, ButtonType ...button) {
		Alert a = new Alert(type, message, button );
		a.setTitle(titel);
		a.setHeaderText("");
		a.setContentText(message);
		a.setAlertType(type);
		return a.showAndWait().get();
		
	}
}
