package fr.pizzeria.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class application de la pizzeria
 */
public class PizzeriaAdminConsoleApp1 {

	/** LOG : Logger */
	public static final Logger LOG = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);
		
	/** v : String representant les enumerations */
	private static String v = "VIANDE";
	
	/** p : String representant les enumerations */
	private static String p = "POISSON";
	
	/** sv : String representant les enumerations */
	private static String sv = "SANS_VIANDE";
	
	/** pizza : Pizza[] */
	private static Pizza[] pizza = {
			new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.valueOf(v)),
			new Pizza("MAR", "Margherita", 14.00, CategoriePizza.valueOf(p)),
			new Pizza("REIN", "La Reine", 11.50, CategoriePizza.valueOf(p)),
			new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.valueOf(sv)),
			new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.valueOf(v)),
			new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.valueOf(v)),
			new Pizza("ORI", "L’orientale", 13.50, CategoriePizza.valueOf(p)),
			new Pizza("IND", "L’indienne", 14.00, CategoriePizza.valueOf(v))
		};
	
	/** scanner : Scanner */
	private static Scanner scanner;
	
	/**
	 * GET  scanner of app
	 * @return Scanner scanner
	 */ 
	private static Scanner getScanner() {
		return scanner;
	}
	
	/**
	 * SET scanner of app
	 * @param sc Scanner
	 */
	private static void setScanner(Scanner sc) {
		scanner = sc;
	}

	/**
	 * affiche la liste des pizza
	 */
	private static void afficheListePizza() {
		for(int i = 0; i < pizza.length; i++) {
			Pizza p = pizza[i];
			LOG.info(p.toString());
		}
	}
	
	/**
	 * creer un object de type pizza avec les entréer clavier utilisateur
	 * @param scanner le lecteur des entréer clavier
	 * @return une pizza
	 */
	private static Pizza creerPizza(Scanner scanner) {
		String valeurString;
		String valeurString1;
		String vdScanner;
		LOG.info("Veuillez saisir le code ");
		valeurString = scanner.next();
		LOG.info("Veuillez saisir le nom (sans espace)  ");
		valeurString1 = scanner.next();
		LOG.info("Veuillez saisir le prix ");
		vdScanner = scanner.next();
		return new Pizza(valeurString, valeurString1, Double.parseDouble(vdScanner), null);
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
	    }
	    return true;
	}
	
	/**
	 * point d'entréer
	 * @param args non utilisés dans cette application
	 */
	public static void main(String[] args) {
		setScanner(new Scanner(System.in));
		int viScanner;
		String vsScanner;
		do {
			LOG.info("\n***** Pizzeria Administration ***** \n" + 
				"1. Lister les pizzas  \n" + 
				"2. Ajouter une nouvelle pizza  \n" + 
				"3. Mettre à jour une pizza \n" + 
				"4. Supprimer une pizza \n" + 
				"99. Sortir ");
			viScanner = getScanner().nextInt();
			
			if(viScanner == 1) {
				LOG.info("Liste des pizzas : \n");
				afficheListePizza();
				viScanner = 0;
			}
			
			if(viScanner == 2) {
				LOG.info("Ajout d’une nouvelle pizza");
				Pizza[] pizza1 = new Pizza[pizza.length + 1];
				for(int i = 0; i < pizza.length; i++) {
					pizza1[i] = pizza[i];
				}
				pizza1[pizza1.length-1] = creerPizza(getScanner());
				pizza = pizza1;
				viScanner = 0;
			}
			
			if(viScanner == 3) {
				int out = 0;
				do {
					LOG.info(" Mise à jour d’une pizza \n");
					afficheListePizza();
					LOG.info("Veuillez choisir la pizza à modifier. \n (99 pour abandonner)\n");
					vsScanner = getScanner().next();
					if(isInteger(vsScanner)) {
						out = Integer.parseInt(vsScanner);
					}
					else {
						for(int i = 0; i < pizza.length; i++) {
							if(pizza[i].getCode().equals(vsScanner)) {
								pizza[i] = creerPizza(getScanner());
							}
						}
					}
					viScanner = 0;
				} while(out != 99);
			}
			
			if(viScanner == 4) {
				int out = 0;
				do {
					LOG.info("Suppression d’une pizza \n");
					afficheListePizza();
					LOG.info("Veuillez choisir la pizza à supprimer. \n (99 pour abandonner)\n");
					vsScanner = getScanner().next();
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
					viScanner = 0;
				} while(out != 99);
			}
			if(viScanner == 99) {
				LOG.info("Aurevoir \n");
			}
		} while(viScanner != 99);
	}
}
