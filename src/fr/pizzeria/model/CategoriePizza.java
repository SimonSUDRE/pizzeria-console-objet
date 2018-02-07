package fr.pizzeria.model;

/**
 * @author Simon SUDRE
 * enumeration Categorie pizza
 */

public enum CategoriePizza {
	
	VIANDE ("Viande"),
	POISSON ("Poisson"),
	SANS_VIANDE ("Sans Viande");
		   
	/** String : value */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name() + " -> " + this.getValue();
	}
}
