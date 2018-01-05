package fr.pizzeria.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Simon SUDRE
 * annotation to string
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ToString {
	
	/**
	 * mettre ou non un string en majuscule
	 * @return boolean
	 */
	boolean upperCase() default false;
	
	/**
	 * mettre ou non 2 decimal a un chiffre
	 * @return boolean 
	 */
	boolean decimal() default false;
	
	/**
	 * l'attribut est une categorie de pizza
	 * @return boolean
	 */
	boolean categorie() default false;
	
	/**
	 * string avant un attribut
	 * @return string
	 */
	String before() default "";
	
	/**
	 * string apres un attribut
	 * @return string
	 */
	String after() default "";
	
	/**
	 * separateur entre deux attributs
	 * @return string
	 */
	String separateur() default "	";
}
