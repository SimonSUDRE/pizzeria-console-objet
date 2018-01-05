package fr.pizzeria.dao;

import java.util.List;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * interface Pizza DAO
 */
public interface IPizzaDao {
	
	/**
	 * recupere la liste des pizza
	 * @return la liste des pizza
	 */
	List<Pizza> findAllPizzas();
	
	/**
	 * ajoute une nouvelle pizza
	 * @param pizza la nouvelle pizza
	 * @throws StockageException la pizza existe deja
	 */
	void saveNewPizza(Pizza pizza) throws StockageException;
	
	/**
	 * met a jour une pizza
	 * @param codePizza le code de la pizza a modifier	
	 * @param pizza la nouvelle version de la pizza
	 * @throws StockageException codePizza inexistant  | la pizza existe deja
	 */
	void updatePizza(String codePizza, Pizza pizza) throws StockageException;
	
	/**
	 * supprime une pizza
	 * @param codePizza le code de la pizza a supprimer
	 * @throws StockageException codePizza inexistant
	 */
	void deletePizza(String codePizza) throws StockageException; 
}
