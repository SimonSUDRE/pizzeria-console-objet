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
	 * @param pizzaDaoImpl le dao qui g�re la liste des pizzas
	 */
	public ModifierPizzaOptionMenu(IPizzaDao pizzaDaoImpl) {
		super("Mettre à jour une pizza", pizzaDaoImpl);
	}
	
	/**
	 * verifie si un String est un entier
	 * @param s le String à verifier
	 * @return true c'est un entier false c'en n'est pas un
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner, fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(Scanner sc) {
		int out = 0;
		String vsScanner, valeurString, valeurString1, vdScanner = null, valeurCategorie;
		boolean b = false;
		double valueDouble = 0.0;
		do {
			CONSOLE.info(" Mise à jour d’une pizza \n");
			List<Pizza> p = this.pizzaDaoImpl.findAllPizzas();
			for(int i = 0; i < p.size(); i++) {
				CONSOLE.info(p.get(i).toString());
			}
			CONSOLE.info("Veuillez choisir la pizza à modifier. \n (99 pour abandonner)\n");
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
					if(b == false) {
						CONSOLE.info("\u001B[31m ce n'est pas une categorie ! \u001B[0m");
						return b;
					}
				} while(b == false);
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
