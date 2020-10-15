package net.webfaculty.model;

import java.sql.Date;

public class Faculty {
	 	private int id;
	    private String name;
	    private int hours;
	    private Date start;
	    private String teacher_name;
	    
		public Faculty(int id, String name, int hours, Date start, String teacher_name) {
			super();
			this.id = id;
			this.name = name;
			this.hours = hours;
			this.start = start;
			this.teacher_name = teacher_name;
		}

		public Faculty(String name, int hours, Date start, String teacher_name) {
			super();
			this.name = name;
			this.hours = hours;
			this.start = start;
			this.teacher_name = teacher_name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		
		
	    
	    
}

