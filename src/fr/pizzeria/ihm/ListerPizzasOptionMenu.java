package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import java.util.List;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class Option lister pizzas
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	/**
	 * Constructeur de l'option lister pizza
	 * @param pizzaDaoImpl le dao qui gère la liste des pizzas
	 */
	public ListerPizzasOptionMenu(IPizzaDao pizzaDaoImpl) {
		super("Lister les pizzas", pizzaDaoImpl);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner, fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(Scanner sc) {
		CONSOLE.info("Liste des pizzas : \n");
		List<Pizza> p = this.pizzaDaoImpl.findAllPizzas();
		for(int i = 0; i < p.size(); i++) {
			String s = p.get(i).toString();
			CONSOLE.info(s);
		}
		return true;
	}

}
