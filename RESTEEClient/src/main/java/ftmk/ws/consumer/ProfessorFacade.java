package ftmk.ws.consumer;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ftmk.exception.JSONException;
import ftmk.model.Professor;


public class ProfessorFacade {
	
	private Client client;
	private WebTarget webTarget;
	
	public ProfessorFacade () {
		
		// Create JAX-RS client
		client = ClientBuilder.newClient();

		// Get WebTarget for URL
		webTarget = client.
				target("http://localhost:8080/RESTProvider/services/teacher");
		
	}
	
	
	public boolean validateProfesstor (String queryName) 
			throws JSONException, JsonProcessingException {
		
		
		webTarget = webTarget.path("validateteacher");

		// Create object 
		Professor professor = new Professor();
		professor.setName(queryName);
		
		// Parse teacher to JSON format
		ObjectMapper mapper = new ObjectMapper();
		String jsonProfessor = mapper.writeValueAsString(professor);

		// Execute HTTP POST method
		Response response = webTarget.request().
				post(Entity.entity(jsonProfessor, MediaType.APPLICATION_JSON));
		
		// Check response code
		if (response.getStatus() != 200) {
			// Display error message if response code is not 200

			String error = "Error invoking REST web service";
			error += "\n" + response.getStatusInfo().getReasonPhrase();
			
			throw new JSONException(error);
		}
		
		// REST call is a success. Retrieve the output from Response.
		String jsonString = response.readEntity(String.class);
		
		// Parse to boolean
		return Boolean.parseBoolean(jsonString);
		
		
	}
	
	
	/**
	 * Get a fixed professor from Hogwarts
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public String getFixedProfessor () 
			throws JsonMappingException, JsonProcessingException {
		
		// Add path
		webTarget = webTarget.path("getteacher");
		
		// Execute HTTP GET method
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();


		// Check response code
		if (response.getStatus() != 200) {
			// Display error message if response code is not 200

			String error = "Error invoking REST web service";
			error += "\n" + response.getStatusInfo().getReasonPhrase();
			
			return error;

		}

		// REST call is a success.  
		String jsonString = response.readEntity(String.class);

		// Parse to Java object
		ObjectMapper mapper = new ObjectMapper();

		// Parse to Teacher object
		Map<String, Object> jsonMap = mapper.readValue(jsonString, 
				new TypeReference<Map<String,Object>>(){});
		
		// Stringified the response
		String parsedProfessor = jsonMap.get("name").toString();
		
		return parsedProfessor;

	}
	
	
	/**
	 * Get default message from the web service
	 * @return
	 */
	public String getDefaultMessage () {
		
		// Execute HTTP GET method
		Response response = webTarget.request().get();
		
		// Check response code
		if (response.getStatus() != 200) {
			// Display error message if response code is not 200
			
			String error = "Error invoking REST web service";
			error += "\n" + response.getStatusInfo().getReasonPhrase();
			
			return error;
		}
		
		// REST call is a success.  Print the response
		String jsonResponse = response.readEntity(String.class);
		
		return jsonResponse;
		
	}
	
	public List<Professor> searchProfessors(String param)
			throws JSONException, JsonProcessingException {
		
		webTarget = webTarget.path("searchteachers");
		
		// Wrap in object
		Professor paramTeacher = new Professor();
		paramTeacher.setName(param);
			
		// Parse teacher to JSON format
		ObjectMapper mapper = new ObjectMapper();
		String jsonTeacher = mapper.writeValueAsString(paramTeacher);
			
		// Execute HTTP POST method
		Response response = webTarget.request().
				post(Entity.entity(jsonTeacher, MediaType.APPLICATION_JSON));
			
		// Check response code
		if (response.getStatus() != 200) {
			
			// Display error message if response code is not 200
			String error = "Error invoking REST web service";
			error += "\n" + response.getStatusInfo().getReasonPhrase();
			
			// Return to main
			throw new JSONException(error);
			
		}
			
		// Process search result
		String jsonResponse = response.readEntity(String.class);
			
		List<Professor> results = mapper.readValue(jsonResponse,
				new TypeReference<List<Professor>>() { });
			
		return results;
			
	}
			
	/**
	*This method count number of teachers is Hogwarts
	* @return
	* @throws JSONException
    */
			
	public int countProfessors () throws JSONException {
			
		webTarget = webTarget.path("countteachers");
			
		// Execute HTTP GET method
		Response response = webTarget.request().get();
		
		// Check response code
		if (response.getStatus() != 200) {
			
			// Display error message if response code is not 200
			String error = "Error invoking REST web service";
			error += "\n" + response.getStatusInfo().getReasonPhrase();
			
			// Return to main
			throw new JSONException(error);
			}
			
		// REST call is a success. Print the response.
		int numberOfTeachers = 
				Integer.parseInt(response.readEntity(String.class));
			
		return numberOfTeachers;
		}
	
		
	

}
