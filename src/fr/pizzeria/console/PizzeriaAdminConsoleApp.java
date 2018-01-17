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
	private static Menu menu;
	
	/**
	 * GET Menu 
	 * @return Menu menu
	 */
	public static Menu getMenu() {
		return menu;
	}
	
	/**
	 * SET Menu 
	 * @param m Menu
	 */
	public static void setMenu(Menu m) {
		menu = m;
	}
	
	/**
	 * GET Scanner
	 * @return Scanner scanner
	 */
	public static Scanner getScanner() {
		return scanner;
	}
	
	/**
	 * SET Scanner
	 * @param sc Scanner
	 */
	public static void setScanner(Scanner sc) {
		scanner = sc;
	}
	
	/**
	 * point d'entréer
	 * @param args non utilisés dans cette application
	 */
	public static void main(String[] args) {
		setScanner(new Scanner(System.in));
		IPizzaDao pizzaDaoImple = new PizzaDaoImpl();
		int viScanner;
		setMenu(new Menu());
		getMenu().setActions(new DataBaseOptionMenu(pizzaDaoImple));
		getMenu().setActions(new ListerPizzasOptionMenu(pizzaDaoImple));
		getMenu().setActions(new AjouterPizzaOptionMenu(pizzaDaoImple));
		getMenu().setActions(new ModifierPizzaOptionMenu(pizzaDaoImple));
		getMenu().setActions(new SupprimerPizzaOptionMenu(pizzaDaoImple));
		getMenu().setActions(new OutAppOptionMenu());
		do {
			getMenu().afficher();
			viScanner = getScanner().nextInt();
			if(getMenu().getActions().get(viScanner) != null) {
				getMenu().getActions().get(viScanner).execute(getScanner());
			}
			else {
				CONSOLE.info("ce n'est pas une option \n");
			}
		} while(true);
	}
}
