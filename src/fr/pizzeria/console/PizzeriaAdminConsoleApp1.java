package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class application de la pizzeria
 */
public class PizzeriaAdminConsoleApp1 {

	/** pizza : Pizza[] */
	private static Pizza[] pizza = {
			new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.valueOf("VIANDE")),
			new Pizza("MAR", "Margherita", 14.00, CategoriePizza.valueOf("POISSON")),
			new Pizza("REIN", "La Reine", 11.50, CategoriePizza.valueOf("POISSON")),
			new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.valueOf("SANS_VIANDE")),
			new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.valueOf("VIANDE")),
			new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.valueOf("VIANDE")),
			new Pizza("ORI", "L’orientale", 13.50, CategoriePizza.valueOf("POISSON")),
			new Pizza("IND", "L’indienne", 14.00, CategoriePizza.valueOf("VIANDE"))
		};
	
	/** scanner : Scanner */
	private static Scanner scanner;

	/**
	 * affiche la liste des pizza
	 */
	private static void afficheListePizza() {
		for(int i = 0; i < pizza.length; i++) {
			System.out.println(pizza[i].toString());
		}
	}
	
	/**
	 * creer un object de type pizza avec les entréer clavier utilisateur
	 * @param scanner le lecteur des entréer clavier
	 * @return une pizza
	 */
	private static Pizza creerPizza(Scanner scanner) {
		String valeurString, valeurString1, vdScanner;
		System.out.println("Veuillez saisir le code ");
		valeurString = scanner.next();
		System.out.println("Veuillez saisir le nom (sans espace)  ");
		valeurString1 = scanner.next();
		System.out.println("Veuillez saisir le prix ");
		vdScanner = scanner.next();
		Pizza p = new Pizza(valeurString, valeurString1, Double.parseDouble(vdScanner), null);
		return p;
	}
	
	/**
	 * verifie si un String est un entier
	 * @param s le String à verifier
	 * @return true c'est un entier false c'en n'est pas un
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	/**
	 * point d'entréer
	 * @param args non utilisés dans cette application
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		int viScanner;
		String vsScanner;
		do {
			System.out.println("\n***** Pizzeria Administration ***** \n" + 
				"1. Lister les pizzas  \n" + 
				"2. Ajouter une nouvelle pizza  \n" + 
				"3. Mettre à jour une pizza \n" + 
				"4. Supprimer une pizza \n" + 
				"99. Sortir ");
			viScanner = scanner.nextInt();
			
			if(viScanner == 1) {
				System.out.println("Liste des pizzas : \n");
				afficheListePizza();
				viScanner = 0;
			}
			
			if(viScanner == 2) {
				System.out.println("Ajout d’une nouvelle pizza");
				Pizza[] pizza1 = new Pizza[pizza.length + 1];
				for(int i = 0; i < pizza.length; i++) {
					pizza1[i] = pizza[i];
				}
				pizza1[pizza1.length-1] = creerPizza(scanner);
				pizza = pizza1;
				viScanner = 0;
			}
			
			if(viScanner == 3) {
				int out = 0;
				do {
					System.out.println(" Mise à jour d’une pizza \n");
					afficheListePizza();
					System.out.println("Veuillez choisir la pizza à modifier. \n (99 pour abandonner)\n");
					vsScanner = scanner.next();
					if(isInteger(vsScanner)) {
						out = Integer.parseInt(vsScanner);
					}
					else {
						for(int i = 0; i < pizza.length; i++) {
							if(pizza[i].getCode().equals(vsScanner)) {
								pizza[i] = creerPizza(scanner);
							}
						}
					}
					vsScanner = null;
					viScanner = 0;
				} while(out != 99);
			}
			
			if(viScanner == 4) {
				int out = 0;
				do {
					System.out.println("Suppression d’une pizza \n");
					afficheListePizza();
					System.out.println("Veuillez choisir la pizza à supprimer. \n (99 pour abandonner)\n");
					vsScanner = scanner.next();
					if(isInteger(vsScanner)) {
						out = Integer.parseInt(vsScanner);
					}
					else {
						Pizza[] pizza1 = new Pizza[pizza.length-1];
						for(int i = 0; i < pizza.length; i++) {
							if(!pizza[i].getCode().equals(vsScanner)) {
								if(i == pizza.length-1) {
									pizza1[0] = pizza[i];
								}
								else {
									pizza1[i] = pizza[i];
								}
							}
						}
						pizza = pizza1;
						break;
					}
					vsScanner = null;
					viScanner = 0;
				} while(out != 99);
			}
			if(viScanner == 99) {
				System.out.println("Aurevoir \n");
			}
		} while(viScanner != 99);
	}
}
