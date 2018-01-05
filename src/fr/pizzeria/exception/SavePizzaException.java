package fr.pizzeria.exception;

/**
 * @author Simon SUDRE
 * class Save New Pizza Exception
 */
public class SavePizzaException extends StockageException {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur 
	 * @param msg le message d'erreur
	 */
	public SavePizzaException(String msg) { super(msg); } 
	
}
