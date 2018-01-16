package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Simon SUDRE
 * enumeration Categorie pizza
 */
@Entity
public enum CategoriePizza {
	
	VIANDE ("Viande"),
	POISSON ("Poisson"),
	SANS_VIANDE ("Sans Viande");
		   
	/** String : value */
	@Column(name="value", length=25, nullable= false)
	private final String value;
		  
	/**
	 * Constructeur
	 * @param value la valeur de la catégorie
	 */
	CategoriePizza(String value){
		this.value = value;
	}

	/**
	 * recuperation de la categorie
	 * @return String la valeur de la categorie
	 */
	public String getValue() {
		return this.value;
	}
}
