package net.webfaculty.model;

public class StudToFac {
	 	private int student_id;
	 	private int faculty_id;
	 	private int mark;
	 	private String status;
	 	
	 	
		public StudToFac(int student_id, int faculty_id, int mark, String status) {
			super();
			this.student_id = student_id;
			this.faculty_id = faculty_id;
			this.mark = mark;
			this.status = status;
		}
		

		public StudToFac(int student_id, int faculty_id, String status) {
			super();
			this.student_id = student_id;
			this.faculty_id = faculty_id;
			this.status = status;
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

		public int getMark() {
			return mark;
		}

		public void setMark(int mark) {
			this.mark = mark;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	 	
	 	
}
