package ftmk.rest.ws.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.ftmk.Teacher.Teacher;

public class RESTClientManager {
	
	private String baseURI = 
			"http://localhost:8080/RESTProvider/services/teacher";
	
	
	
	
	/**
	 * This method validates the response code from the web service
	 * 
	 * @param response
	 * 
	 * @return
	 * 	true - acceptable code
	 * 	false - unacceptable code
	 */
	private boolean validateResponseCode (Response response) {
			
		// Check response code
		System.out.println("\nResponse code: " + response.getStatus());
		
		if (response.getStatus() != 200) {
			// Display error message if response code is not 200

			System.out.println("Error invoking REST web service");
			System.out.println(response.getStatusInfo().getReasonPhrase());

			// Return to main
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * This method validate a teacher using a web service
	 * 
	 * @param teacherName - a teacher's name
	 * 
	 * @throws IOException
	 */
	public void validateTeacher(String teacherName) throws IOException {
		
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();

		// Generate the service path
		WebTarget webTarget = client.target(baseURI);
		webTarget = webTarget.path("validateteacher");
		System.out.println("\n\nRetrieving data from " + webTarget.getUri());

		// Create object 
		Teacher teacher = new Teacher();
		teacher.setName(teacherName);
		
		// Parse teacher to JSON format
		ObjectMapper mapper = new ObjectMapper();
		String jsonTeacher = mapper.writeValueAsString(teacher);

		// Execute HTTP POST method
		Response response = webTarget.request().post(Entity.entity(jsonTeacher, 
				MediaType.APPLICATION_JSON));
		
		// Validate response code
		if (validateResponseCode(response)) {
			// Display result on success validation
			
			String jsonString = response.readEntity(String.class);
			System.out.println("\nIn JSON format: " +  jsonString);
			
			System.out.println("\nIs " + teacherName 
					+ " a valid teacher? " + jsonString);
			
		}
	}
	
	/**
	 * This method prints a list of teacher and assigned subject.  
	 * The data is from a web service.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void getTeachersSubject() 
			throws JsonParseException, JsonMappingException, IOException {
		
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();

		// Get WebTarget for URL
		WebTarget webTarget = client.target(baseURI);
		webTarget = webTarget.path("getteacherssubject");
		System.out.println("\n\nRetrieving data from " + webTarget.getUri());
		
		// Execute HTTP GET method
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

		// Validate response code
		if (validateResponseCode(response)) {
			// Display result on success validation
			
			// REST call is a success. Get the result
			String jsonResponse = response.readEntity(String.class);
			
			// Parse result using List
			ObjectMapper mapper = new ObjectMapper();
			List<Teacher> teachers = mapper.readValue(jsonResponse, 
					new TypeReference<List<Teacher>>() { });
			
			// Print the result
			System.out.println("\nList of Teachers in Hogwarts");
			for (Teacher teacher:teachers) {
				
				System.out.print("[" + teacher.getId() + "] " 
						+ teacher.getName());
				System.out.println( " : " + teacher.getSubject().getName());
				
			}
		}
	}
	
	/**
	 * This method print a list of teachers from a web service in several styles
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void getTeachers() 
			throws JsonParseException, JsonMappingException, IOException {
		
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();

		// Get WebTarget for URL
		WebTarget webTarget = client.target(baseURI);
		webTarget = webTarget.path("getteachers");
		System.out.println("Retriving a list of teachers from " 
				+ webTarget.getUri());
		
		// Execute HTTP GET method
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

		// Validate response 
		if (validateResponseCode(response)) {
		
			// REST call is a success. Get the result
			String jsonResponse = response.readEntity(String.class);
			
			ObjectMapper mapper = new ObjectMapper();
			
			// Method 1: Parse and print result using array
			// Source: https://mkyong.com/java/jackson-convert-json-array
			// -string-to-list/
			Teacher teachersArray[] = mapper.readValue(jsonResponse, 
					Teacher[].class);
			System.out.println("\nList of Teachers in Hogwarts (Method 1)");
			for (Teacher teacher:teachersArray)
				System.out.println(teacher.getId() + ":" +teacher.getName());
		
			// Method 2: Parse and print result using List
			List<Teacher> teacherList1 = Arrays.asList(
					mapper.readValue(jsonResponse, Teacher[].class));
			System.out.println("\nList of Teachers in Hogwarts (Method 2)");
			for (Teacher teacher:teacherList1)
				System.out.println(teacher.getId() + ":" +teacher.getName());
			
			// Method 3: Parse and print result using List
			List<Teacher> teacherList2 = mapper.readValue(
					jsonResponse, new TypeReference<List<Teacher>>() { });
			System.out.println("\nList of Teachers in Hogwarts (Method 3)");
			for (Teacher teacher:teacherList2)
				System.out.println(teacher.getId() + ":" +teacher.getName());
			
		}
	}
	
	
	/**
	 * This method adds a teacher using a JSON object.
	 * 
	 * @throws IOException
	 */
	public void addTeacher() throws IOException {
		
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();

		// Get WebTarget for URL
		String newPath = baseURI + "/addteacher";
		WebTarget webTarget = client.target(newPath);

		// Create object 
		Teacher teacher = new Teacher();
		teacher.setName("Gilderoy Lockhart");
		
		// Parse teacher to JSON format
		ObjectMapper mapper = new ObjectMapper();
		String jsonTeacher = mapper.writeValueAsString(teacher);

		// Execute HTTP POST method
		Response response = webTarget.request().post
				(Entity.entity(jsonTeacher, MediaType.APPLICATION_JSON));
		
		// Validate response
		if (validateResponseCode(response)) {
			
			System.out.println("Accessing service from " + webTarget.getUri());
			
			// REST call is a success.  
			String jsonString = response.readEntity(String.class);
			
			// Parse to Teacher object
			teacher = mapper.readValue(jsonString, Teacher.class);
			
			// Display the result
			System.out.println("\nResponse target format: " 
					+ response.getMediaType());
			System.out.print("Value from parsing:");
			System.out.println("("+ teacher.getId() +"): " + teacher.getName());
		}
	}
	
	
	/**
	 * This method displays object of a teacher retrieve from a web service.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void getFixedTeacher () throws JsonParseException, 
		JsonMappingException, IOException {
		
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();
		
		// Get WebTarget for URL
		WebTarget webTarget = client.target(baseURI);
		webTarget = webTarget.path("getteacher");
		System.out.println("\n\nInvoking: " + webTarget.getUri());
		
		// Execute HTTP GET method
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
		
		// Validate response
		if (validateResponseCode(response)) {
		
			// REST call is a success.  Extract response.
			String jsonString = response.readEntity(String.class);
			
			// Parser to Java object
			ObjectMapper mapper = new ObjectMapper();
			
			// Parse to Teacher object
			Teacher teacher = mapper.readValue(jsonString, Teacher.class);
			
			System.out.println("\nResponse target format: " + 
					response.getMediaType());
			System.out.println("JSON format: " +  jsonString);
			System.out.println("Parsed to Java object: (" 
					+ teacher.getId() +"): " + teacher.getName());
		
		}

		
	}
	
	
	/**
	 * This message gets a message from a web service on default URI
	 */
	public void getMessage () {
		
		
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();
		
		// Specify the target URI
		WebTarget webTarget = client.target(baseURI);
		System.out.println("\n\nInvoking: " + webTarget.getUri());
		
		// Execute HTTP GET method
		Response response = webTarget.request().get();
		
		// Validate response
		if (validateResponseCode(response)) {
		
			// REST call is a success.  Print the response.
			System.out.println("\nResponse target format " 
					+ response.getMediaType() + ":");
			System.out.println(response.readEntity(String.class));
		}
		
		}
	
	/**
	 * This method demonstrate the call to GET web service and the steps to
	 * count the number of teachers in Hogwarts
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void countTeachers() 
			throws JsonParseException, JsonMappingException, IOException {
			
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();
		
		// Specify the target URI
		WebTarget webTarget = client.target(baseURI);
		webTarget = webTarget.path("countteachers");
		
		System.out.println("\n\nInvoking " + webTarget.getUri());
		
		// Execute HTTP GET method
		Response response = webTarget.request().get();
		
		// Validate response
		if (response.getStatus() != 200) {
		
			// REST call is a success.  Print the response.
			System.out.println("Error invoking REST web service");
			System.out.println(response.getStatusInfo().getReasonPhrase());
			
			return;
		}
		
		System.out.println("\nResponse target format " + response.getMediaType()
		+ ":");
		
		int numberOfTeachers = response.readEntity(Integer.class);
		
		System.out.println("The number of teachers in Hogwarts is " 
		+ numberOfTeachers);
		
	}
	
	/**
	 * This method demonstrate the call to Post web service and the steps to
	 * find teachers name based on surname
	 * @param param
	 * @throws IOException
	 */
	public void searchTeachers(String param) throws IOException {
		
		// Create JAX-RS client
		Client client = ClientBuilder.newClient();
				
		// Specify the target URI
		WebTarget webTarget = client.target(baseURI);
		webTarget = webTarget.path("searchteachers");
				
		System.out.println("\n\nInvoking " + webTarget.getUri());
		
		//Wrap in object
		Teacher paramTeacher = new Teacher();
		paramTeacher.setName(param);
		
		//parse teacher to JSON format
		ObjectMapper mapper = new ObjectMapper();
		String jsonTeacher = mapper.writeValueAsString(paramTeacher);
				
		// Execute HTTP GET method
		Response response = webTarget.request()
				.post(Entity.entity(jsonTeacher, MediaType.APPLICATION_JSON));
		
		//Check response code
		System.out.println("Response code: " + response.getStatus());
				
		// Validate response
		if (response.getStatus() != 200) {
				
			// REST call is a success.  Print the response.
			System.out.println("Error invoking REST web service");
			System.out.println(response.getStatusInfo().getReasonPhrase());
					
				return;
			}
		
		//Process search result
		String jsonResponse = response.readEntity(String.class);
		List<Teacher> result =mapper.
				readValue(jsonResponse, new TypeReference<List<Teacher>>() {});
		
		//Print header
		System.out.println("\nSearch Parameter (surname): " + param);
		System.out.println("\nSearch Result");
		System.out.println("\nTotal Result of " + param);
		System.out.println(result.size());
		System.out.println("==============================");
		
		//print result
		int number = 0;
		
		for (Teacher teacher: result)
			System.out.println(++number + ". " + teacher.getName() );
		System.out.println("\n\n");

		
	}

}
