package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

	public static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
	}
	
	private EntityManager em;
	
	public PizzaDaoJpa() {
		em = entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return null;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		
	}

	@Override
	public void deletePizza(String codePizza) throws StockageException {
		
	}
}