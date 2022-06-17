package hogwarts.teacher;

import java.util.ArrayList;
import java.util.List;

import hogwarts.rest.subject.Subject;

/**
 * This class manage the data related to the teachers in Hogwarts.
 * 
 * @author Aqil
 *
 */
public class TeacherRepository {
	
	private String names[] = {"Filius Flitwick", "Horace Slughorn", 
			"Severus Snape", "Remus Lupin"};
	private String subjects[] = {"Transfiguration", "Potion", 
			"Defense Against the Dark Art", "Divination"};
	
	
	/**
	 * This method gets a teacher based on the teacher's id
	 * 
	 * @param teacherId
	 * @return An instance of teacher
	 */
	public Teacher getTeacher (int teacherId) {
		
		// Get a list of teacher
		List<Teacher> teachers = this.getHogwartTeachers();
		
		// Find a teacher from the list
		if (teacherId > teachers.size()) {
			
			Teacher teacher = new Teacher();
			teacher.setName("Record is not found");
			
			return teacher;
		
		} else 
			return teachers.get(teacherId);
			
	}
	
	/**
	 * This method validates the existence of a teacher based on the name.
	 * 
	 * @param queryString: Full name or partial name of the teacher
	 * 
	 * @return 
	 * 	true: Valid teacher. The teacher is not exist in Hogwarts.
	 * 	false: invalid teacher.  The teacher is not exist in Hogwarts.
	 */
	public boolean isValidTeacher (String queryString) {
		
		// Convert to lower case for uniformity
		String lowerCaseQuery = queryString.toLowerCase();
		
		
		// Find the teacher in the list
		String lowerCaseName = "";
		for (String name:names) {
			
			lowerCaseName = name.toLowerCase();
			
			// Found matching teacher
			if (lowerCaseName.contains(lowerCaseQuery))
				return true;
			
			lowerCaseName = "";
			
		}
		
		return false;
	}
	
	/**
	 * This method assign subjects to each teacher in Hogwarts
	 * @return A list of teachers with assigned subject
	 */
	public List<Teacher> getHogwartsTeacherSubject () {
		
		// Get a list of teacher in Hogwarts
		List<Teacher> teachers = this.getHogwartTeachers();
		
		// Add subject to teacher
		for (int index=0; index < subjects.length; index++) {
			
			// Wrap subject
			Subject subject = new Subject();
			subject.setName(subjects[index]);
			
			// Assign subject to teacher
			teachers.get(index).setSubject(subject);
		}
		
		return teachers;
	}
	
	/**
	 * This method generates a list of Hogwarts teachers
	 * @return a list of Hogwarts teachers
	 */
	public List<Teacher> getHogwartTeachers () {
		
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		// Add each teacher into a list
		for (int index=0; index < names.length; index++) {
			
			Teacher teacher = new Teacher();
			teacher.setId(index + 1);
			teacher.setName(names[index]);
			
			teachers.add(teacher);
			
		}
		
		return teachers;
		
	}
	
	/**
	 * This method finds teacher according to the surname.  
	 * This method covers full and partial search.
	 * 
	 * @param param - surname 
	 * @return A list of teachers that match the param
	 */
	public List<Teacher> getHogwartsTeachers(String param) {
		
		// Standardized search parameter
		param = param.toLowerCase();
		
		// Data structure to store search result  
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		// Find the matching surname
		for (String name:names) {
			
			// Get surname
			String tokens[] = name.toLowerCase().split(" ");
			String surname = tokens[1];
			
			// Validate with matching param
			if(surname.contains(param)) {
				
				// Wrap in Teacher
				Teacher teacher = new Teacher();
				teacher.setName(name);
				
				// Add to list
				teachers.add(teacher);
			}
			
		}
		
		return teachers;
	}

}
