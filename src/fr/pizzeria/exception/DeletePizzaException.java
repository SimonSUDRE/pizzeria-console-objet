package fr.pizzeria.exception;

/**
 * @author Simon SUDRE
 * class Delete Pizza Exception
 */
public class DeletePizzaException extends StockageException {
	
	/** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur 
	 * @param msg le message d'erreur
	 */
	public DeletePizzaException(String msg) { super(msg); } 
	
}
