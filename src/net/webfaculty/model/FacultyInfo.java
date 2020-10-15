package net.webfaculty.model;

import java.sql.Date;

public class FacultyInfo {
	
    private String name;
    private int hours;
    private Date start;
    private String teacher_name;
    private String status;
    private int mark;
   
	public FacultyInfo(String name, int hours, Date start, String teacher_name, String status, int mark) {
		super();
		this.name = name;
		this.hours = hours;
		this.start = start;
		this.teacher_name = teacher_name;
		this.status = status;
		this.mark = mark;
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
