package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import static fr.pizzeria.console.PizzeriaAdminConsoleApp.getMenu;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.dao.PizzaDaoJdbc;
import fr.pizzeria.dao.PizzaDaoJpa;

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
	
	
	/**
	 * Met IPizzaDao avec impl
	 */
	private void pizzaDaoImpl() {
		if(this.pizzaDaoImpl instanceof PizzaDaoJdbc || this.pizzaDaoImpl instanceof PizzaDaoJpa) {
			getMenu().updateActionsDaoPizza(new PizzaDaoImpl());
		}
		else {
			CONSOLE.info("Vous êtes déjà sans la base de données !");
		}
	}
	
	/**
	 * Met IPizzaDao avec jdbc
	 */
	private void pizzaDaoJdbc() {
		if(this.pizzaDaoImpl instanceof PizzaDaoImpl || this.pizzaDaoImpl instanceof PizzaDaoJpa) {
			getMenu().updateActionsDaoPizza(new PizzaDaoJdbc());
		}
		else {
			CONSOLE.info("Vous êtes déjà avec la base de données !");
		}
	}
	
	/**
	 * Met IPizzaDao avec jpa
	 */
	private void pizzaDaoJpa() {
		if(this.pizzaDaoImpl instanceof PizzaDaoImpl || this.pizzaDaoImpl instanceof PizzaDaoJdbc) {
			getMenu().updateActionsDaoPizza(new PizzaDaoJpa());
		}
		else {
			CONSOLE.info("Vous êtes déjà avec la base de données !");
		}
	}
	
	/** 
	 * Met a jour IPizzaDao en fonction du choix
	 * @param rScanner Scanner
	 * @return boolean 
	 */
	private boolean scannerChoiceDao(String rScanner) {
		int out = Integer.parseInt(rScanner);
		if(out == 1) {
			this.pizzaDaoImpl();
			return true;
		}
		else if(out == 2) {
			this.pizzaDaoJdbc();
			return true;
		}
		else if(out == 3) {
			this.pizzaDaoJpa();
			return true;
		}
		else {
			CONSOLE.info("\u001B[31m ce n'est pas une option ! \u001B[0m");
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner)
	 */
	@Override
	public boolean execute(Scanner sc) {
		String vsScanner = null;
		while(true) {
			CONSOLE.info("Mise a jour du DAO ");
			CONSOLE.info("1. Sans base de données");
			CONSOLE.info("2. Avec base de données");
			vsScanner = sc.next();
			if(isInteger(vsScanner)) {
				if(this.scannerChoiceDao(vsScanner)) {
					break;
				}
			}
			else {
				CONSOLE.info("\u001B[31m ce n'est pas une option ! \u001B[0m");
			}
		}		
		return true;
	}
}