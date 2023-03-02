package applicationE;

public abstract class User {
	
	public  static String username;
	private String password;
	public  String name;
	public  String surname;
	
	protected User(String username, String password, String name, String surname)
	{
		User.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		User.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	

}
