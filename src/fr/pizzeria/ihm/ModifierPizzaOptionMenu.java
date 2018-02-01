package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import java.util.List;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class option modifier pizza
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

	/**
	 * Constructeur de l'option modifier pizza
	 * @param pizzaDaoImpl le dao qui gère la liste des pizzas
	 */
	public ModifierPizzaOptionMenu(IPizzaDao pizzaDaoImpl) {
		super("Mettre Ã  jour une pizza", pizzaDaoImpl);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner, fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(Scanner sc) {
		int out = 0;
		String vsScanner;
		String valeurString;
		String valeurString1;
		String vdScanner = null;
		String valeurCategorie;
		boolean b = false;
		double valueDouble = 0.0;
		do {
			CONSOLE.info(" Mise a jour d'une pizza \n");
			List<Pizza> p = this.pizzaDaoImpl.findAllPizzas();
			for(int i = 0; i < p.size(); i++) {
				String s = p.get(i).toString();
				CONSOLE.info(s);
			}
			CONSOLE.info("Veuillez choisir la pizza a modifier. \n (99 pour abandonner)\n");
			vsScanner = sc.next();
			if(isInteger(vsScanner)) {
				out = Integer.parseInt(vsScanner);
			}
			else {
				CONSOLE.info("Veuillez saisir le code ");
				valeurString = sc.next();
				CONSOLE.info("Veuillez saisir le nom (sans espace)  ");
				valeurString1 = sc.next();
				CONSOLE.info("Veuillez saisir le prix ");
				vdScanner = sc.next();
				try {
					valueDouble = Double.parseDouble(vdScanner);
				}
				catch(NumberFormatException e) {
					CONSOLE.error("erreur critique", "\u001B[31m ce n'est pas un prix ! \u001B[0m");
				}
				for(int i = 0; i < CategoriePizza.values().length; i++) {
					String s = CategoriePizza.values()[i].toString();
					CONSOLE.info(s);
				}
				do {
					CONSOLE.info("Veuillez saisir la categorie : ");
					valeurCategorie = sc.next();
					for(int i = 0; i < CategoriePizza.values().length; i++) {
						String s = valeurCategorie.toUpperCase();
						if(CategoriePizza.values()[i].name().equals(s)) {
							b = true;
						}
					}
					if(!b) {
						CONSOLE.info("\u001B[31m ce n'est pas une categorie ! \u001B[0m");
						return b;
					}
					else {
						break;
					}
				} while(true);
				Pizza pizza = new Pizza(valeurString, valeurString1, valueDouble, CategoriePizza.valueOf(valeurCategorie.toUpperCase()));
				try {
					this.pizzaDaoImpl.updatePizza(vsScanner, pizza);
				} catch (StockageException e) {
					CONSOLE.error("erreur critique", e.getMessage());
				}
			}
		} while(out != 99);
		return b;
	}
}
