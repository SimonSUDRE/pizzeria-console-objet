package fr.pizzeria.exception;

/**
 * @author Simon SUDRE
 * class StockageException
 */
public abstract class StockageException extends Exception {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param msg le message d'erreur
	 */
	public StockageException(String msg) { super(msg); } 
	
}
