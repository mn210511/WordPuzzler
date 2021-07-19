package app;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MessageBox {

	/**
	 * shows the user a Messagebox with the given message an titel
	 * 
	 * @param titel the titel of the messagebox
	 * @param message the message to be displayed
	 * @return
	 */
	public static ButtonType show(String titel, String message) {
		return show(titel, message, AlertType.INFORMATION, ButtonType.OK);
	}
	
	/**
	 * shows the user a Messagebox with the given message, titel, alterype and buttons
	 * 
	 * @param titel titel the titel of the messagebox
	 * @param message message the message to be displayed
	 * @param type selected AlertTyper
	 * @param button buttons that should be in the messagebox
	 * @return
	 */
	public static ButtonType show(String titel, String message, AlertType type, ButtonType ...button) {
		Alert a = new Alert(type, message, button );
		a.setTitle(titel);
		a.setHeaderText("");
		a.setContentText(message);
		a.setAlertType(type);
		return a.showAndWait().get();
		
	}
}
