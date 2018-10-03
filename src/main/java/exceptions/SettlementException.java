package exceptions;

public class SettlementException extends RuntimeException{

	/**
	 * Exception Class
	 */
	private static final long serialVersionUID = -8238498241194669832L;
	
	public  SettlementException (String message) {
		super(message);
	}
}
