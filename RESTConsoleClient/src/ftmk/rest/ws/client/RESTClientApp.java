

package ftmk.rest.ws.client;


/**
 * This code demonstrate the call made to web services host in 
 * http://localhost:8080/RESTProvider/ and process JSON response.
 * 
 * How to use this code?
 * Remove the comment that call the static method in the main method
 * 
 * @author aqil
 *
 */
public class RESTClientApp {

	/**
	 * Main entry point of the application.
	 * 
	 * @param args
	 */
	public static void main (String args[]) {
		
		System.out.println("\nThis application demonstrate consuming "
				+ "web service data");
		
		
		RESTClientManager client = new RESTClientManager();
		
		String names[] = {"Flitwick", "Severus Snape", "albus dumbledore", 
			"Albus Dumbledore"};
		
		try {
			
			//client.getMessage(); 
			
			for (String name:names)
				client.validateTeacher(name); 
						
			client.getTeachersSubject();
			
			client.getTeachers(); 
			
			//client.addTeacher();  // Error
			
			client.getFixedTeacher(); 
			
			client.countTeachers();
			
			client.searchTeachers("s");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("\n\nEnd of execution for RESTClientApp");
	}

	
	
}
