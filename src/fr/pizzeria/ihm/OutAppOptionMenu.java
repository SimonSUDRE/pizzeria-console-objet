package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import java.util.Scanner;

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
		System.exit(0);
		return false;
	}
}
