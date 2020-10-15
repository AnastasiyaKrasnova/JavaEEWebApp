package net.webfaculty.model;

public class StudentMark {
	private String first_name;
	private String last_name;
    private String email;
    private String status;
    private int mark;
    private int student_id;
    private int faculty_id;
    
	public StudentMark(String first_name, String last_name, String email, String status, int mark, int student_id,
			int faculty_id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.status = status;
		this.mark = mark;
		this.student_id = student_id;
		this.faculty_id = faculty_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
    
	
    
    
}
