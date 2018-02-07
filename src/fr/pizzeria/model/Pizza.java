package fr.pizzeria.model;

import static fr.pizzeria.utils.StringUtils.getStringValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import fr.pizzeria.utils.ToString;

/**
 * @author Simon SUDRE
 * class Pizza
 */
@Entity
public class Pizza {
	
	/** id : Integer */
	@Id
	private static Integer id = -1;
	
	/** code : String */
	@Column(name="Pizza_id", length=5, nullable=false)
	@ToString(upperCase = true, separateur = "	->	")
	private String code;
	
	/** nom : String */
	@Column(name="Pizza_name", length=50, nullable=false)
	@ToString()
	private String nom;
	
	/** prix : Double */
	@Column(name="Pizza_price", nullable=false)
	@ToString(decimal = true, separateur = " :: ", after = " €)", before = "(")
	private Double prix;
	
	/** categorie : CategoriePizza */
	@Column
	@Enumerated(EnumType.STRING)
	@ToString(categorie = true)
	private CategoriePizza categorie;
	
	
	/**
	 * Constructeur par default
	 */
	public Pizza() {}
	
	/**
	 * le constructeur d'une pizza :  l'id est générer avec un compteur
	 * @param code code identifiant une pizza
	 * @param nom nom de la pizza
	 * @param prix prix de la pizza
	 * @param categori la categorie de la pizza
	 */
	public Pizza(String code, String nom, Double prix, CategoriePizza categori) {
		Pizza.id++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		categorie = categori;
	}

	/**
	 * recuperation de l'identifiant de la pizza
	 * @return Integer l'identifiant de la pizza
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * recuperation du code de la pizza
	 * @return String le code de la pizza
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * mise a jour du code de la pizza
	 * @param code le nouveau code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * recuperation du nom de la pizza
	 * @return String le nom de la pizza
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * mise a jour du nom d ela pizza 
	 * @param nom le nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * recuperation du prix de la pizza
	 * @return Double le prix de la pizza
	 */
	public Double getPrix() {
		return prix;
	}
	
	/**
	 * mise a jour du prix de la pizza
	 * @param prix le nouveau prix de la pizza
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	/**
	 * recupere la categorie de la pizza
	 * @return CategoriePizza la categorie de la pizza
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/**
	 * met a jour la categorie de la pizza
	 * @param categori la categorie
	 */
	public void setCategorie(CategoriePizza categori) {
		categorie = categori;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prix == null) ? 0 : prix.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (categorie != other.categorie)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * code -&gt; nom(prix€) :: categorie
	 */
	@Override
	public String toString() {
		return getStringValue(this);
	}

	/**
	 * generate a string of pizza
	 * @return String the pizza
	 */
	public String generateSting() {
		return code + ", " + nom + ", " + prix + ", " + categorie.name();
	}
}
