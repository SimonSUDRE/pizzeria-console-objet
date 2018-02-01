package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import java.util.List;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class Option supprimer pizza
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	/**
	 * Contructeur de l'option suppimer pizza
	 * @param pizzaDaoImpl le dao qui g�re la liste des pizzas
	 */
	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDaoImpl) {
		super("Supprimer une pizza", pizzaDaoImpl);
	}
	
	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner, fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(Scanner sc) {
		int out = 0;
		String vsScanner;
		do {
			CONSOLE.info("Suppression d’une pizza \n");
			List<Pizza> p = this.pizzaDaoImpl.findAllPizzas();
			for(int i = 0; i < p.size(); i++) {
				String s = p.get(i).toString();
				CONSOLE.info(s);
			}
			CONSOLE.info("Veuillez choisir la pizza à supprimer. \n (99 pour abandonner)\n");
			vsScanner = sc.next();
			if(isInteger(vsScanner)) {
				out = Integer.parseInt(vsScanner);
			}
			else {
				try {
					this.pizzaDaoImpl.deletePizza(vsScanner);
				} catch (StockageException e) {
					CONSOLE.error("erreur critique", e.getMessage());
				}
				break;
			}
		} while(out != 99);
		return true;
	}

}
