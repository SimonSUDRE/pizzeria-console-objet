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
	 * recupère un string avec les attribut de la class qui ont une annotation ToString
	 * code -&gt; nom prix€ :: categorie
	 * @param o l'object dont on doit retourner un string
	 * @return un string avec les attribut de la class qui ont une annotation ToString
	 */
	public static String getStringValue(Object o) {
		String chaine = "", val = "";
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
					
					chaine += annotation.before() + val + annotation.after() + annotation.separateur();
				}
			}
			return chaine;
		} catch(Exception e) {
			return null;
		}
	}
}
