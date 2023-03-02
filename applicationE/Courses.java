package applicationE;

public abstract class Courses implements Cloneable{

	private String code;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Courses courseClone = (Courses) super.clone();
		//courseClone.code = (String) (name.clone());
		
		return courseClone;
	}
}
