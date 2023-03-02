package applicationE;

public class NonTechnicalElectiveCourse extends Courses {
	
	private String midterm;
	private String finals;
	
	public NonTechnicalElectiveCourse(String name, String midterm, String finals) {
		setName(name);
		this.midterm = midterm;
		this.finals = finals;
	}
	
	public NonTechnicalElectiveCourse(String name, String code) {
		setName(name);
		setCode(code);
		
	}

	public String getMidterm() {
		return midterm;
	}

	public void setMidterm(String midterm) {
		this.midterm = midterm;
	}

	public String getFinals() {
		return finals;
	}

	public void setFinals(String finals) {
		this.finals = finals;
	}
	
	

}
