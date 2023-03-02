package applicationE;

public class Admin extends User {

	private String email;
	
	private Admin(String username, String password, String name, String surname, String email)
	{
		super(username,password,name,surname);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
