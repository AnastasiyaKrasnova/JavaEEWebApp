package net.webfaculty.model;

import java.sql.Date;

public class FacultyInfo {
	
	private int faculty_id;
	private int student_id;
    private String name;
    private int hours;
    private Date start;
    private String teacher_name;
    private String status;
    private int mark;
    
	public FacultyInfo(int faculty_id, int student_id, String name, int hours, Date start, String teacher_name,
			String status, int mark) {
		super();
		this.faculty_id = faculty_id;
		this.student_id = student_id;
		this.name = name;
		this.hours = hours;
		this.start = start;
		this.teacher_name = teacher_name;
		this.status = status;
		this.mark = mark;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
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
   
	
    

}
