package fr.pizzeria.ihm;

import java.util.Scanner;
import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import static fr.pizzeria.console.PizzeriaAdminConsoleApp.getMenu;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.dao.PizzaDaoJdbc;

/**
 * @author Simon SUDRE
 * class data base option menu
 */
public class DataBaseOptionMenu extends OptionMenu {
	
	/**
	 * Constructeur
	 * @param pizzaDaoImpl IPizzaDao
	 */
	public DataBaseOptionMenu(IPizzaDao pizzaDaoImpl) {
		super("Utiliser la base de données", pizzaDaoImpl);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner)
	 */
	@Override
	public boolean execute(Scanner sc) {
		String vsScanner = null;
		int out = 0;
		while(true) {
			CONSOLE.info("Mise a jour du DAO ");
			CONSOLE.info("1. Sans base de données");
			CONSOLE.info("2. Avec base de données");
			vsScanner = sc.next();
			if(isInteger(vsScanner)) {
				out = Integer.parseInt(vsScanner);
				if(out == 1) {
					if(this.pizzaDaoImpl instanceof PizzaDaoJdbc) {
						getMenu().updateActionsDaoPizza(new PizzaDaoImpl());
						break;
					}
					else {
						CONSOLE.info("Vous êtes déjà sans la base de données !");
						break;
					}
				}
				else if(out == 2) {
					if(this.pizzaDaoImpl instanceof PizzaDaoImpl) {
						getMenu().updateActionsDaoPizza(new PizzaDaoJdbc());
						break;
					}
					else {
						CONSOLE.info("Vous êtes déjà avec la base de données !");
						break;
					}
				}
				else {
					vsScanner = null;
					CONSOLE.info("\u001B[31m ce n'est pas une option ! \u001B[0m");
				}
				
			}
			else {
				vsScanner = null;
				CONSOLE.info("\u001B[31m ce n'est pas une option ! \u001B[0m");
			}
		}		
		return true;
	}
}