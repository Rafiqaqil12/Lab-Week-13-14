package demo.ftmk.Teacher;

import demo.ftmk.subject.Subject;

/**
 * An entity class that represent an object of a Teacher
 * 
 * @author emalianakasmuri
 *
 */

public class Teacher {
	
	
	private int id;
	private String name;
	private Subject subject;
	
	
	public Teacher () {  }
	
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	

}
