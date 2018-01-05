package fr.pizzeria.ihm;

import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Simon SUDRE
 * class Menu
 */
public class Menu {

	/** titre : String */
	private String titre;
	
	/** actions : OptionMenu[] */
	private Map<Integer,OptionMenu> actions;
	
	/** cmp : int static */
	private static int cmp = 0;
	
	/**
	 * Constructeur 
	 * @param actions la liste des option menu
	 */
	public Menu(Map<Integer,OptionMenu> actions) {
		this.titre = "***** Pizzeria Administration *****";
		this.actions = actions;
	}
	
	/**
	 * Contructeur avec valeur par default
	 */
	public Menu() {
		this.titre = "***** Pizzeria Administration *****";
		this.actions = new LinkedHashMap<Integer,OptionMenu>();
	}
	
	/**
	 * Contructeur
	 * @param titre le titre du menu
	 */
	public Menu(String titre) {
		this.titre = titre;
		this.actions = new LinkedHashMap<Integer,OptionMenu>();
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
	 * insert des option dans le menu 
	 * si l'option est quitter l'application on met l'id a 99
	 * @param action un option du menu
	 */
	private void setIdActions(OptionMenu action) {
		if(action.getClass().equals(OutAppOptionMenu.class)) {
			this.getActions().put(99, action);
		}
		else {
			cmp++;
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
