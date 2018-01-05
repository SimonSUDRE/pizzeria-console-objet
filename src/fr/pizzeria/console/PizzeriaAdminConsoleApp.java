package fr.pizzeria.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.ihm.AjouterPizzaOptionMenu;
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
		
	/**
	 * point d'entréer
	 * @param args non utilisés dans cette application
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		IPizzaDao pizzaDaoImpl = new PizzaDaoImpl();
		int viScanner;
		Menu m = new Menu();
		m.setActions(new ListerPizzasOptionMenu(pizzaDaoImpl));
		m.setActions(new AjouterPizzaOptionMenu(pizzaDaoImpl));
		m.setActions(new ModifierPizzaOptionMenu(pizzaDaoImpl));
		m.setActions(new SupprimerPizzaOptionMenu(pizzaDaoImpl));
		m.setActions(new OutAppOptionMenu());
		do {
			m.afficher();
			viScanner = scanner.nextInt();
			if(!m.getActions().get(viScanner).equals(null)) {
				m.getActions().get(viScanner).execute(scanner);
				viScanner = 0;
			}
			else {
				CONSOLE.info("ce n'est pas une option \n");
			}
		} while(true);
	}
}
