package hogwarts.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hogwarts.teacher.Teacher;
import hogwarts.teacher.TeacherRepository;

import javax.ws.rs.core.GenericEntity;

/**
 * This class represent the provide web service related to manage the 
 * teacher information in Hogwarts .
 * 
 * This class tells the framework when the URI is 
 * http://localhost:8080/RESTProvider/services/teacher
 * it is referring to all the services declared in this class.
 * 
 * @author aqil
 *
 */


@Path("/teacher")
public class TeacherService {
	
	
	
	
	/**
	 * This service validates the existence of the teacher.
	 * 
	 * @param teacher: The teacher to be validated
	 * 
	 * @return true/false in JSON
	 * 	true: Valid teacher. The teacher is not exist in Hogwarts.
	 * 	false: invalid teacher.  The teacher is not exist in Hogwarts.
	 */
	@POST
	@Path("/validateteacher")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean validateTeacher (Teacher teacher) {
		
		TeacherRepository teacherRepository = new TeacherRepository();
		
		boolean flag = teacherRepository.isValidTeacher(teacher.getName());
		
		return flag;
		
	}
	

	/**
	 * This service retrieve a list of teachers with the subject assigned to 
	 * them.
	 * 
	 * @return A list of teacher with assigned subject in JSON
	 */
	@GET
	@Path("/getteacherssubject")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeachersSubject() {
		
		// Get teachers and subjects
		TeacherRepository teacherRepository = new TeacherRepository();
		List<Teacher> teachers = teacherRepository.getHogwartsTeacherSubject();
		
		// Parse to GenericEntity
		GenericEntity<List<Teacher>> genericEntity 
			= new GenericEntity<List<Teacher>>(teachers) {};
		
		// Return through Response
		return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		
	}
	
	/**
	 * This service retrieve a list of teachers.
	 *  
	 * @return A list of teachers in JSON
	 */
	@GET
	@Path("/getteachers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeachers() {
		
		
		// Get a list of teachers from the repository
		TeacherRepository teacherRepository = new TeacherRepository();
		List<Teacher> teachers = teacherRepository.getHogwartTeachers();
		
		// Parse to GenericEntity
		GenericEntity<List<Teacher>> genericEntity = 
				new GenericEntity<List<Teacher>>(teachers) {};
		
		
		return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		
	}
	
	
	/**
	 * This method demonstrate adding a new teacher using POST. The new teacher
	 * is not added into the list maintained by the Repository object, instead
	 * the method assign a static id to the object
	 * 
	 * @param a new teacher
	 * 
	 * @return A object of teacher in JSON
	 */
	@POST
	@Path("/addteacher")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Teacher addTeacher(Teacher teacher) {
		
		// Get a list of teachers from the repository
		TeacherRepository teacherRepository = new TeacherRepository();
		List<Teacher> teachers = teacherRepository.getHogwartTeachers();
		
		teacher.setId(teachers.size() + 1);
		
		return teacher;
		
	}
	
	/**
	 * This service retrieve a teacher based on the teacher id
	 * 
	 * @param id: Teacher id
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getteacher/{teacherid}")
	public Teacher getTeacher (@PathParam("teacherid") int id) {
		
		// Get the teacher from the repository
		TeacherRepository teacherRepository = new TeacherRepository();
		Teacher teacher = teacherRepository.getTeacher(id);
		
		return teacher;
	}
	
	
	/**
	 * This method demonstrate a GET service and return an object of techer.
	 * 
	 * @return an object of Teacher in JSON
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getteacher")
	public Teacher getFixedTeacher () {
		
		Teacher teacher = new Teacher(1, "Minerva McGonagall");
		
		return teacher;
	}
	
	
	/**
	 * This service demonstrate a invocation of service in default path.
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDefaultTeacherMessage () {
		
		
		String text = "Alahamora! Brilliant connection "
				+ "to http://localhost:8080/RESTProvider/services/teacher";
		
		// Display on console to indicate method is invoked 
		System.out.println(text);
		
		return text;
	}
	
	/**
	 * 
	 * This method to count the number of teachers in hogwarts
	 * @return
	 */
	@GET
	@Path("/countteachers")
	@Produces(MediaType.TEXT_PLAIN)
	public int countTeachers() {
		
		TeacherRepository teacherRepository = new TeacherRepository();
		
		List<Teacher> teachers = teacherRepository.getHogwartTeachers();
		
		int countTeachers = teachers.size();
		
		return countTeachers;
	}
	
	
	/**
	 * 
	 * This method to search surname of the teachers
	 * @return
	 */
	@POST
	@Path("/searchteachers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchTeachers(Teacher teacher) {
		
		TeacherRepository teacherRepository = new TeacherRepository();
		
		List<Teacher> teachers = 
				teacherRepository.getHogwartsTeachers(teacher.getName());
		
		GenericEntity<List<Teacher>> genericEntity = 
				new GenericEntity<List<Teacher>>(teachers) {};
		
		return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		
		
	}
	

}
