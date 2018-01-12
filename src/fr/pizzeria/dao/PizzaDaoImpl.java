package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class implémentant l'interface IPizzaDao
 */
public class PizzaDaoImpl implements IPizzaDao {

	/** pizza : List<Pizza> */
	private static List<Pizza> pizzas = new ArrayList<>();

	/**
	 * Constructeur : initialise le tableau des pizza
	 */
	public PizzaDaoImpl() {
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.valueOf("VIANDE")));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.valueOf("POISSON")));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.valueOf("POISSON")));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.valueOf("SANS_VIANDE")));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.valueOf("VIANDE")));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.valueOf("VIANDE")));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.valueOf("POISSON")));
		pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.valueOf("VIANDE")));
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		for(int i = 0; i < this.findAllPizzas().size(); i++) {
			if(this.findAllPizzas().get(i).equals(pizza)) {
				throw new SavePizzaException("\u001B[31m cette pizza existe ! \u001B[0m");
			}
		}
		this.findAllPizzas().add(pizza);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String, fr.pizzeria.model.Pizza)
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		for(int i = 0; i < this.findAllPizzas().size(); i++) {
			if(this.findAllPizzas().get(i).equals(pizza)) {
				throw new SavePizzaException("\u001B[31m cette pizza existe ! \u001B[0m");
			}
			String s = this.findAllPizzas().get(i).getCode();
			if(s.equals(codePizza)) {
				this.findAllPizzas().get(i).setCode(pizza.getCode());
				this.findAllPizzas().get(i).setNom(pizza.getNom());
				this.findAllPizzas().get(i).setPrix(pizza.getPrix());
				this.findAllPizzas().get(i).setCategorie(pizza.getCategorie());
				break;
			}
			else if(i == this.findAllPizzas().size()) {
				throw new UpdatePizzaException("\u001B[31m Le code entreé n'existe pas ! \u001B[0m");
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	@Override
	public void deletePizza(String codePizza) throws StockageException {
		for(int i = 0; i < this.findAllPizzas().size(); i++) {
			if(this.findAllPizzas().get(i).getCode().equals(codePizza)) {
				this.findAllPizzas().remove(i);
				break;
			}
			else if(i == this.findAllPizzas().size()) {
				throw new DeletePizzaException("\u001B[31m Le code entreé n'existe pas ! \u001B[0m");
			}
		}
	}
}
