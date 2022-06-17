package hogwarts.rest.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Reference: Java EE 8 Development with Eclipse (Kindle)
 * Topic: Creating RESTful web services using Jersey

 * We need to tell the Jersey Framework what packages it need to scan to look 
 * for the REST resources.  There are two approach to do this,
 * 
 * 1. Configure the Jersey servlet in web xml 
 * 2. Create a subclass of javax.ws.rs.core.Application.
 * 
 * This application is demonstrating the second approach.
 * 
 * This class tells the framework when the URI is 
 * http://localhost:8080/RESTProvider/services/
 * it is referring to all the services in hogwarts.rest.services.
 * 
 * @author emalianakasmuri
 *
 */




@ApplicationPath("services")
public class RESTProvider extends ResourceConfig {
	
	public RESTProvider () {
		
		System.out.println("Deploying hogwarts.rest.services on RESTProvider");
		
		packages("hogwarts.rest.services");
	}

}
