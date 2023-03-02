package applicationE;

public class Student extends User {
	
	private String ID;
	private String birthday;
	private String birthplace;

	protected Student(String username, String password, String name, String surname, 
			String ID, String birthday, String birthplace) {
		
		super(username, password, name, surname);
		this.ID = ID;
		this.birthday = birthday;
		this.birthplace = birthplace;
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	
}
