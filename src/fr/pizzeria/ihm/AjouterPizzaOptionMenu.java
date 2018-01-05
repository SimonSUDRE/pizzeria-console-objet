package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class option Ajouter pizza
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	/**
	 * constructeur de l'option ajouter pizza
	 * @param pizzaDaoImpl le dao qui g�re la liste des pizzas
	 */
	public AjouterPizzaOptionMenu(IPizzaDao pizzaDaoImpl) {
		super("Ajouter une nouvelle pizza", pizzaDaoImpl);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner, fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(Scanner sc) {
		String valeurString, valeurString1, vdScanner, valeurCategorie = null;
		boolean b = false;
		double valueDouble = 0.0;
		CONSOLE.info("Ajout d’une nouvelle pizza");
		CONSOLE.info("Veuillez saisir le code ");
		valeurString = sc.next();
		CONSOLE.info("Veuillez saisir le nom (sans espace)  ");
		valeurString1 = sc.next();
		CONSOLE.info("Veuillez saisir le prix ");
		vdScanner = sc.next();
		try {
			valueDouble  = Double.parseDouble(vdScanner);
		}
		catch(NumberFormatException e) {
			CONSOLE.error("erreur critique" ,"\u001B[31m ce n'est pas un prix ! \u001B[0m");
		}
		for(int i = 0; i < CategoriePizza.values().length; i++) {
			CONSOLE.info(CategoriePizza.values()[i].name() + " -> " + CategoriePizza.values()[i].getValue());
		}
		do {
			CONSOLE.info("Veuillez saisir la categorie : ");
			valeurCategorie = sc.next();
			for(int i = 0; i < CategoriePizza.values().length; i++) {
				if(CategoriePizza.values()[i].name().equals(valeurCategorie.toUpperCase())) {
					b = true;
				}
			}
			if(!b) {
				CONSOLE.info("\u001B[31m ce n'est pas une categorie ! \u001B[0m");
				return b;
			}
		} while(!b);
		Pizza pizza = new Pizza(valeurString, valeurString1, valueDouble, CategoriePizza.valueOf(valeurCategorie.toUpperCase()));
		try {
			this.pizzaDaoImpl.saveNewPizza(pizza);
		} catch (StockageException e) {
			CONSOLE.error("erreur critique" ,e.getMessage());
		}
		CONSOLE.info("Ajout d’une nouvelle pizza");
		return b;
	}

}
