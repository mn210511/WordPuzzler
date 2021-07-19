package backend.entities;

public class Login {

	private String password;
	private String username;
	private int ID;
	
	public Login(String username, String password) throws Exception {
		this.username = username;
		this.password = password;
		if(password.length()<4) {
			throw new Exception("Passwort zu kurz");
		}
	}
	
	public Login() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	public int getID() {
		return ID;
	}
	
}
