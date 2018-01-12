package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import static fr.pizzeria.console.PizzeriaAdminConsoleApp.CONSOLE;

/**
 * @author Simon SUDRE
 * class pizza dao JDBC
 */
public class PizzaDaoJdbc implements IPizzaDao {

	/** pizza : List<Pizza> */
	private static List<Pizza> pizzas = new ArrayList<>();
	
	/** connection : Connection */
	private static Connection connection = null;
	
	/**
	 * Constructeur : initialise le tableau des pizza dans la bdd
	 */
	public PizzaDaoJdbc() {
		initConnection();
		this.initDatabase();
	}
	
	/**
	 * init list for database
	 * @return List<Pizza>
	 */
	private static List<Pizza> initPizzasList() {
		List<Pizza> p = new ArrayList<>();
		p.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.valueOf("VIANDE")));
		p.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.valueOf("POISSON")));
		p.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.valueOf("POISSON")));
		p.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.valueOf("SANS_VIANDE")));
		p.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.valueOf("VIANDE")));
		p.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.valueOf("VIANDE")));
		p.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.valueOf("POISSON")));
		p.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.valueOf("VIANDE")));
		return p;
	}
	
	/**
	 * initialise la connection à la base de données
	 */
	public static void initConnection() {
		ResourceBundle props = ResourceBundle.getBundle("jdbc");
		
		String driver = props.getString("jdbc.driver");
		String url = props.getString("jdbc.url");
		String username = props.getString("jdbc.username");
		String password = props.getString("jdbc.password");
		
		if (driver != null) {
		    try {
				Class.forName(driver) ;
				connection = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				CONSOLE.error("La class Driver n'existe pas ! ", e.getMessage());
			} catch (SQLException e) {
				CONSOLE.error("ERREUR SQL de connection ! ", e.getMessage());
				closeConnection();
			}
		}
	}
	
	/**
	 * initialize la base de données avec la liste des pizzas dans la liste
	 */
	private void initDatabase() {
		List<Pizza> p = initPizzasList();
		for(Pizza pizza : p) {
			try {
				this.saveNewPizza(pizza);
			} catch (StockageException e) {
				CONSOLE.error("erreur critique ", e.getMessage());
			}
		}
	}
	
	/**
	 * retourne la liste des pizzas dans la liste
	 * @return List<Pizza>
	 */
	private List<Pizza> getPizzas(){
		return pizzas;
	}
	
	/**
	 * met fin a la connection a la base de données
	 */
	public static void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				CONSOLE.error("ERREUR SQL de fermeture de connection ! ", e.getMessage());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> piz = new ArrayList<>();
		ResultSet allpizza = null;
		PreparedStatement preparedSelectPizza = null;
		try {
			preparedSelectPizza = 
					connection.prepareStatement("SELECT * from Pizza;");
			allpizza = preparedSelectPizza.executeQuery();
			while(allpizza.next()) {
				String pizzaId = allpizza.getString("Pizza_id");
				String pizzaName = allpizza.getString("Pizza_name");
				Double pizzaPrice = allpizza.getDouble("Pizza_price");
				CategoriePizza pizzaCategory = CategoriePizza.valueOf(allpizza.getString("Pizza_category"));
				piz.add(new Pizza(pizzaId, pizzaName, pizzaPrice, pizzaCategory));
			}
			allpizza.close();
		} catch (SQLException e) {
			if(connection == null) {
				CONSOLE.error("ERREUR SQL Connection non ouverte", e.getMessage());
			}
			CONSOLE.error("ERREUR SQL de requete Select ! ", e.getMessage());
		} finally{
			if(allpizza != null) {
				try {
					allpizza.close();	
				} catch (SQLException e) {
					CONSOLE.error("ERREUR SQL de fermeture de requete Select ! ", e.getMessage());
				}
			}
			
			if(preparedSelectPizza != null) {
				try {
					preparedSelectPizza.close();
				} catch (SQLException e) {
					CONSOLE.error("ERREUR SQL de fermeture de requete Select ! ", e.getMessage());
				}
			}
		}
		return piz;
	}
	
	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		PreparedStatement preparedInsertPizza = null;
		try {
			preparedInsertPizza =
					connection.prepareStatement(
							  "INSERT "
							+ "INTO Pizza(Pizza_id, Pizza_name, Pizza_price, Pizza_category) "
							+ "VALUES (?, ?, ?, ?);");
			preparedInsertPizza.setString(1, pizza.getCode());
			preparedInsertPizza.setString(2, pizza.getNom());
			preparedInsertPizza.setDouble(3, pizza.getPrix());
			preparedInsertPizza.setString(4, pizza.getCategorie().name());
			preparedInsertPizza.executeUpdate();
			preparedInsertPizza.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			CONSOLE.error("ERREUR SQL Cette clé pizza existe déjà !", e.getMessage());
			throw new SavePizzaException("ERREUR SQL Cette clé pizza existe déjà !");
		} catch (SQLException e) {
			if(connection == null) {
				CONSOLE.error("ERREUR SQL Connection non ouverte", e.getMessage());
			}
			CONSOLE.error("ERREUR SQL de requete Insert ! ", e.getMessage());
		} finally{
			if(preparedInsertPizza != null) {
				try {
					preparedInsertPizza.close();
				} catch (SQLException e) {
					CONSOLE.error("ERREUR SQL de fermeture de requete Insert ! ", e.getMessage());
				}
			}
		}
		this.getPizzas().add(pizza);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String, fr.pizzeria.model.Pizza)
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		int result;		
		PreparedStatement preparedUpdatePizza = null;
		try {
			preparedUpdatePizza = 
					connection.prepareStatement(
							  "UPDATE Pizza "
							+ "SET Pizza_id=?, "
								+ "Pizza_name=?, "
								+ "Pizza_price=?, "
								+ "Pizza_category=? "
							+"WHERE Pizza_id=?;");
			preparedUpdatePizza.setString(1, pizza.getCode());
			preparedUpdatePizza.setString(2, pizza.getNom());
			preparedUpdatePizza.setDouble(3, pizza.getPrix());
			preparedUpdatePizza.setString(4, pizza.getCategorie().name());
			preparedUpdatePizza.setString(5, codePizza);
			result = preparedUpdatePizza.executeUpdate();
			if(result == 0) {
				throw new UpdatePizzaException("ERREUR SQL Cette clé pizza n'existe pas !");
			}
			preparedUpdatePizza.close();
		} catch (SQLIntegrityConstraintViolationException e) { 
			CONSOLE.error("ERREUR SQL Cette clé pizza existe déjà !", e.getMessage());
			throw new SavePizzaException("ERREUR SQL Cette clé pizza existe déjà !");
		} catch (SQLException e) {
			if(connection == null) {
				CONSOLE.error("ERREUR SQL Connection non ouverte", e.getMessage());
			}
			CONSOLE.error("ERREUR SQL de requete Update ! ", e.getMessage());
		} finally{
			if(preparedUpdatePizza != null) {
				try {
					preparedUpdatePizza.close();
				} catch (SQLException e) {
					CONSOLE.error("ERREUR SQL de fermeture de requete Update ! ", e.getMessage());
				}
			}
		}
		for(int i = 0; i < this.getPizzas().size(); i++) {
			if(this.getPizzas().get(i).getCode().equals(codePizza)) {
				this.findAllPizzas().get(i).setCode(pizza.getCode());
				this.findAllPizzas().get(i).setNom(pizza.getNom());
				this.findAllPizzas().get(i).setPrix(pizza.getPrix());
				this.findAllPizzas().get(i).setCategorie(pizza.getCategorie());
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	@Override
	public void deletePizza(String codePizza) throws StockageException {
		int result;
		PreparedStatement preparedDeletePizza = null;
		try {
			preparedDeletePizza = 
					connection.prepareStatement(
							  "DELETE "
							+ "FROM Pizza "
							+ "WHERE Pizza_id=?;");
			preparedDeletePizza.setString(1, codePizza);
			result = preparedDeletePizza.executeUpdate();
			if(result == 0) {
				throw new DeletePizzaException("ERREUR SQL Cette clé pizza n'existe pas !");
			}
			preparedDeletePizza.close();
		} catch (SQLException e) {
			if(connection == null) {
				CONSOLE.error("ERREUR SQL Connection non ouverte", e.getMessage());
			}
			CONSOLE.error("ERREUR SQL de requete Delete : erreur d'access ! ", e.getMessage());
		} finally{
			if(preparedDeletePizza != null) {
				try {
					preparedDeletePizza.close();
				} catch (SQLException e) {
					CONSOLE.error("ERREUR SQL de fermeture de requete Delete ! ", e.getMessage());
				}
			}
		}
		for(int i = 0; i < this.getPizzas().size(); i++) {
			if(this.getPizzas().get(i).getCode().equals(codePizza)) {
				this.getPizzas().remove(i);
			}
		}
	}
}