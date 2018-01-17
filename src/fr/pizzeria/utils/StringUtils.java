package fr.pizzeria.utils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import fr.pizzeria.model.CategoriePizza;

/**
 * @author Simon SUDRE
 * class String utils
 */
public class StringUtils {
	
	/**
	 * Constructeur par default
	 */
	private StringUtils() {}

	/**
	 * recupère un string avec les attribut de la class qui ont une annotation ToString
	 * code -&gt; nom prix€ :: categorie
	 * @param o l'object dont on doit retourner un string
	 * @return un string avec les attribut de la class qui ont une annotation ToString
	 */
	public static String getStringValue(Object o) {
		StringBuilder chaine = new StringBuilder();
		String val = "";
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				if(f.isAnnotationPresent(ToString.class)) {
					ToString annotation = f.getAnnotation(ToString.class);
					Object value = f.get(o);
					if(annotation.categorie()) {
						val = CategoriePizza.valueOf(value.toString()).getValue();
					}
					else if(annotation.decimal()) {
						 val = df.format(value);
					}
					else if(annotation.upperCase()) {
						val = value.toString().toUpperCase();
					}
					else {
						val = value.toString();
					}
					chaine.append(annotation.before()).append(val).append(annotation.after()).append(annotation.separateur());
				}
			}
			return chaine.toString();
		} catch(Exception e) {
			return null;
		}
	}
}
