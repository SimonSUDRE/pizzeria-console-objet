package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import static fr.pizzeria.dao.PizzaDaoJdbc.closeConnection;
import static fr.pizzeria.dao.PizzaDaoJpa.closeEntityManagerFactory;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoJdbc;
import fr.pizzeria.dao.PizzaDaoJpa;

/**
 * @author Simon SUDRE
 * class option quitter l'aplication
 */
public class OutAppOptionMenu extends OptionMenu {

	/**
	 * Constructeur de l'option quitter l'application
	 */
	public OutAppOptionMenu() {
		super("Sortir", null);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner)
	 */
	@Override
	public boolean execute(Scanner sc) {
		CONSOLE.info("Aurevoir \n");
		if(this.pizzaDaoImpl instanceof PizzaDaoJdbc) {
			closeConnection();
		}
		if(this.pizzaDaoImpl instanceof PizzaDaoJpa) {
			closeEntityManagerFactory();
		}
		System.exit(0);
		return false;
	}
}
