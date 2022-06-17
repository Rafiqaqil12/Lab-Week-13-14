package ftmk.exception;

public class JSONException extends Exception {
	
	private String message;
	
	public JSONException (String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		
		return this.message;
	}

}
