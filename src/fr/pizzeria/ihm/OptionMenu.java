package fr.pizzeria.ihm;

import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;

/**
 * @author Simon SUDRE
 * class OptionMenu
 */
public abstract class OptionMenu {
	
	/** libelle : String */
	protected String libelle;
	
	/** pizzaDaoImpl : IPizzaDao */
	protected IPizzaDao pizzaDaoImpl;
	
	/** constructeur : increment static de l'id
	 * @param libelle le titre de l'option
	 * @param pizzaDaoImpl le dao qui gère la liste des pizzas
	 */
	public OptionMenu(String libelle, IPizzaDao pizzaDaoImpl) {
		this.libelle = libelle;
		this.pizzaDaoImpl = pizzaDaoImpl;
	}

	/**
	 * recuperation du libelle
	 * @return le libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * execute le code de l'option selectionne a partir du menu
	 * @param sc le lecteur d'entrer clavier
	 * @return retourne si l'execution a reussi
	 */
	public abstract boolean execute(Scanner sc);
	
}
