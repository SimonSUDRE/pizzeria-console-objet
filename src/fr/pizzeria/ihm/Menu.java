package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.pizzeria.dao.IPizzaDao;

/**
 * @author Simon SUDRE
 * class Menu
 */
public class Menu {

	/** titre : String */
	private String titre;
	
	/** actions : OptionMenu[] */
	private Map<Integer,OptionMenu> actions;
	
	/** cmp : Integer static */
	private static Integer cmp = 0;
	
	/**
	 * Constructeur 
	 * @param actions la liste des option menu
	 */
	public Menu(String titre, Map<Integer,OptionMenu> actions) {
		this.titre = titre;
		this.actions = actions;
		cmp++;
	}
	
	/**
	 * Contructeur avec valeur par default
	 */
	public Menu() {
		this("***** Pizzeria Administration *****", new LinkedHashMap<>());
	}
	
	/**
	 * Contructeur
	 * @param titre le titre du menu
	 */
	public Menu(String titre) {
		this(titre, new LinkedHashMap<>());
	}

	/**
	 * recupere la liste des option menu
	 * @return OptionMenu[] la liste des option menu
	 */
	public Map<Integer,OptionMenu> getActions() {
		return actions;
	}

	/**
	 * insert des option dans le menu
	 * @param action une option
	 */
	public void setActions(OptionMenu action) {
		this.setIdActions(action);
	}
	
	/**
	 * replace the IPizzaDao
	 * @param pizzaDaoImpl new IPizzaDao
	 */
	public void updateActionsDaoPizza(IPizzaDao pizzaDaoImpl) {
		for(OptionMenu om : this.getActions().values()) {
			om.setDaoPizza(pizzaDaoImpl);
		}
	}
	
	/**
	 * insert des option dans le menu 
	 * si l'option est quitter l'application on met l'id a 99
	 * @param action un option du menu
	 */
	private void setIdActions(OptionMenu action) {
		if(action.getClass().equals(OutAppOptionMenu.class)) {
			this.getActions().put(99, action);
		}
		else {
			this.getActions().put(cmp, action);
		}
	}

	/**
	 * retourne le titre du menu
	 * @return String le titre
	 */
	private String getTitre() {
		return titre;
	}

	/**
	 *  affiche le menu des options
	 */
	public void afficher() {
		CONSOLE.info(this.getTitre());
		for(Map.Entry<Integer, OptionMenu> entry : this.getActions().entrySet()) {
			CONSOLE.info(entry.getKey() + ". " + entry.getValue().getLibelle());
		}
	}
}
