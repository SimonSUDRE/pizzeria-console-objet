package fr.pizzeria.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.ihm.AjouterPizzaOptionMenu;
import fr.pizzeria.ihm.DataBaseOptionMenu;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.Menu;
import fr.pizzeria.ihm.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.OutAppOptionMenu;
import fr.pizzeria.ihm.SupprimerPizzaOptionMenu;

/**
 * @author Simon SUDRE
 * class application
 */
public class PizzeriaAdminConsoleApp {
	
	/** scanner : Scanner */
	private static Scanner scanner;
	
	/** LOG : Logger */
	public static final Logger CONSOLE = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);
		
	/** menu : Menu */
	public static Menu menu;
	
	/**
	 * point d'entréer
	 * @param args non utilisés dans cette application
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		IPizzaDao pizzaDaoImple = new PizzaDaoImpl();
		int viScanner;
		menu = new Menu();
		menu.setActions(new DataBaseOptionMenu(pizzaDaoImple));
		menu.setActions(new ListerPizzasOptionMenu(pizzaDaoImple));
		menu.setActions(new AjouterPizzaOptionMenu(pizzaDaoImple));
		menu.setActions(new ModifierPizzaOptionMenu(pizzaDaoImple));
		menu.setActions(new SupprimerPizzaOptionMenu(pizzaDaoImple));
		menu.setActions(new OutAppOptionMenu());
		do {
			menu.afficher();
			viScanner = scanner.nextInt();
			if(!menu.getActions().get(viScanner).equals(null)) {
				menu.getActions().get(viScanner).execute(scanner);
				viScanner = 0;
			}
			else {
				CONSOLE.info("ce n'est pas une option \n");
			}
		} while(true);
	}
}
