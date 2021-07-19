package frontend.controllers;

import java.io.IOException;

import app.Constant;
import app.MessageBox;
import backend.entities.Login;
import backend.entities.LoginRepository;
import backend.entities.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenueController extends CommonPropertyController{
	@FXML
	private TextField txtUserName;
	@FXML
	private Button btnNewGame;
	@FXML
	private Button btnScoreboard;
	@FXML
	private Button btnEnd;
	@FXML
	private ImageView imgLogo;
	

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnNewAccount;
	@FXML
	private Label lblUsername;

    @FXML
    private VBox loginInformation;
	@FXML
	private PasswordField txtPassword;
	
	private BooleanProperty notLoggedIn = new SimpleBooleanProperty();

	LoginRepository loginRepository;


	
	@FXML
	public void initialize() {
		notLoggedIn.set(true);

		btnNewGame.disableProperty().bind(notLoggedIn);
		
		// once the player is logged in he should not be forced to do a new login everytime he goes back to the main menue. 
		// thats why we check if the username is null. 
		if(player.getUsername()!=null) {
			loginInformation.setVisible(true);
			lblUsername.setText(player.getUsername());
			notLoggedIn.set(false);
			
		}


		txtUserName.textProperty().addListener((o, oldVal, newVal) -> notEmpty());
		txtPassword.textProperty().addListener((o, oldVal, newVal) -> notEmpty());

		loginRepository = new LoginRepository(Constant.MARIA_DB_URL, "root", "");
				
		
	
	}


	/**
	 * Action handler for the newGame Button. Creates a new GameWindow which method changes the scene
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onNewGame(ActionEvent ae) throws IOException {
		System.out.println("player:'" + player.getUsername() +"' started a new game");
		
		GameWindow window = new GameWindow((Stage) btnEnd.getScene().getWindow());
		window.changeScene();

	}

	/**
	 * Action handler for the scoreBoard Button. Creates a new ScoreBoardWindow which method changes the scene
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onScoreBoard(ActionEvent ae) throws IOException {
		if(player == null) {
			player = new Player();
		}	
		ScoreBoardWindow window = new ScoreBoardWindow((Stage) btnEnd.getScene().getWindow());
		window.changeScene();
	}

	/**
	 * Action handler for the exit Button. The application will be closed after calling.
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onExit(ActionEvent ae) throws IOException {

		Stage stage = (Stage) btnEnd.getScene().getWindow();
		stage.close();

	}

	/**
	 * checks if the textfield Username or Password ist empty. Depending on the result the buttons for login and new Account will be disabled or enabled
	 */
	@FXML
	private void notEmpty() {
		
		
		if (txtUserName.getText().equals("") || txtPassword.getText().equals("")) {
			btnLogin.setDisable(true);
			btnNewAccount.setDisable(true);
		} else {
			btnLogin.setDisable(false);
			btnNewAccount.setDisable(false);
		}
		
	}


/**
 * Action Handler for the login Button. checks if the username and password are matching. 
 * @param ae
 * @throws IOException
 */
	@FXML
	private void onLogin(ActionEvent ae) throws IOException {

		try {
			Login tmp = new Login(txtUserName.getText(), txtPassword.getText());

			if (loginRepository.checkLogin(tmp)) {
				
				notLoggedIn.set(false);
				lblUsername.setText(tmp.getUsername());
				loginInformation.setVisible(true);
				player = playerRepo.byName(tmp.getUsername());
				System.out.println("player :'" + player.getUsername() + "' logged in");
				
				// if the User has deleted his data the player will be null an we have to create a new Player Object
				if(player.getUsername() == null) {
					playerRepo.insertPlayer(new Player(tmp.getUsername()));
					player = new Player(tmp.getUsername());
				}
				
			} else {
				MessageBox.show("Error", "Username oder Passwort falsch");
			}

		} catch (Exception e) {
		
			MessageBox.show("Error", e.getLocalizedMessage());
		}

	}

	/**
	 * Action handler for the new Account button. checks if a username is already in use if not it creates a new login entity and saves it in 
	 * the database. 
	 * 
	 * @param ae
	 * @throws IOException
	 */
	@FXML
	private void onNewAccount(ActionEvent ae) throws IOException {

		if (loginRepository.UserExists(txtUserName.getText())) {
			MessageBox.show("Error", "Username: " + txtUserName.getText() + " already in use");
			txtUserName.setText("");
			txtPassword.setText("");
		} else {
			try {
				//add the login-Information to the database
				loginRepository.insertData(new Login(txtUserName.getText(), txtPassword.getText()));
				// insert a new player entity into the database
				playerRepo.insertPlayer(new Player(txtUserName.getText()));
			} catch (Exception e) {
				MessageBox.show("Error", e.getMessage());
			}
		}

	}

}
