package exceptions;

/**
 * Custom exception regarding to text-based user inputs.
 * Thrown when user input does not contain specified "amount" of characters.
 * 
 * 
 * @author Aykut Bir
 * @since 30/04/2024
 * 
 */
public class LengthException extends InputException{

	public LengthException() {
		super();
	}

}
