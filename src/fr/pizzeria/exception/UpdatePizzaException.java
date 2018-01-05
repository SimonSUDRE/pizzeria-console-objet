package fr.pizzeria.exception;

/**
 * @author Simon SUDRE
 * class Update Pizza Exception
 */
public class UpdatePizzaException extends StockageException {

	 /** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur 
	 * @param msg le message d'erreur
	 */
	public UpdatePizzaException(String msg) { super(msg); } 
	
}
