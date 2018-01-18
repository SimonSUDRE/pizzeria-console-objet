package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import com.mysql.jdbc.Messages;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * @author Simon SUDRE
 * class pizza DAO JPA
 */
public class PizzaDaoJpa implements IPizzaDao {

	/** ENTYTIMANAGERFACTORY : EntityManagerFactory */
	public static final EntityManagerFactory ENTYTIMANAGERFACTORY;
	
	/**
	 * initialise l' entity manager factory
	 */
	static {
		ENTYTIMANAGERFACTORY = Persistence.createEntityManagerFactory("pizzeria");
	}
	
	/**
	 * ferme entity manager factory
	 */
	public static void closeEntityManagerFactory() {
		ENTYTIMANAGERFACTORY.close();
	}
	
	/** em : EntityManager */
	private EntityManager em;
		
	/**
	 * GET entity manager
	 * @return EntityManager 
	 */
	private EntityManager getEntityManager() {
		em = ENTYTIMANAGERFACTORY.createEntityManager();
		return em;
	}
	
	/**
	 * ferme l'entity manager
	 */
	private void closeEntityManager() {
		em.close();
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> pizzas = getEntityManager().createQuery("From Pizza p", Pizza.class).getResultList();
		closeEntityManager();
		return pizzas;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		EntityTransaction entityTransaction = getEntityManager().getTransaction();
		entityTransaction.begin();
		try {
			getEntityManager().persist(pizza);
			entityTransaction.commit();
		} catch(PersistenceException e) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new SavePizzaException(Messages.getString("CodeAlreadyUsed"));
		} finally {
			if(getEntityManager().isOpen()) {
				closeEntityManager();
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String, fr.pizzeria.model.Pizza)
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		EntityTransaction entityTransaction = getEntityManager().getTransaction();
		entityTransaction.begin();
		try {
			Pizza pizzaTarget = getEntityManager().find(Pizza.class, codePizza);
			pizzaTarget.setCategorie(pizza.getCategorie());
			pizzaTarget.setCode(pizza.getCode());
			pizzaTarget.setNom(pizza.getNom());
			pizzaTarget.setPrix(pizza.getPrix());
			entityTransaction.commit();
		} catch(NoResultException e) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new UpdatePizzaException(Messages.getString("UnfoundPizza"));
		} catch(PersistenceException e) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new UpdatePizzaException(Messages.getString("CodeAlreadyUsed"));
		} finally {
			if(getEntityManager().isOpen()) {
				closeEntityManager();
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	@Override
	public void deletePizza(String codePizza) throws StockageException {
		EntityTransaction entityTransaction = getEntityManager().getTransaction();
		entityTransaction.begin();
		try {
			getEntityManager().remove(getEntityManager().find(Pizza.class, codePizza));
			entityTransaction.commit();
		}catch(NoResultException e) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw new DeletePizzaException(Messages.getString("UnfoundPizza"));
		}finally {
			if(getEntityManager().isOpen()) {
				closeEntityManager();
			}
		}
	}
}