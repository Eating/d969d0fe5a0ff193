package asgn2Exceptions;
/**
 * @author Yiting Zhang  
 * 
 * @studentNumber 8779210
 * 
 * @version 1.0
 */


public class TrainException extends Exception{

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = -8463596790935842790L;

	/**
	 * Creates a new instance of TrainException.
	 * 
	 * @param message - an informative message about the problem found
	 */
	public TrainException(String message) {
		super("Train Exception: " + message);
	}

}
