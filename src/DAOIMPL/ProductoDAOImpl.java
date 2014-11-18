package DAOIMPL;

// Generated 29/09/2014 11:17:45 AM by Hibernate Tools 3.4.0.CR1

import hibernate.HibernateUtil;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import DAO.ProductoDAO;
import bean.Cotizacion;
import bean.Producto;

/**
 * Home object for domain model class Producto.
 * @see dao.impl.impl.Producto
 * @author Hibernate Tools
 */
public class ProductoDAOImpl implements ProductoDAO{

	private static final Log log = LogFactory.getLog(ProductoDAOImpl.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

/*	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
*/
	
	public void saveOrUpdate(Producto instance) {
		log.debug("Grabando Producto instance");
		Session session=sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			session.saveOrUpdate(instance);
			session.getTransaction().commit();
			log.debug("Se grabo con :exito: ");
		} catch (RuntimeException re) {
			log.error("No se pudo grabar -->", re);
			throw re;
		}
	}
	public void persist(Producto transientInstance) {
		log.debug("persisting Producto instance");
		try {
			Session session=sessionFactory.openSession();
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Producto instance) {
		log.debug("attaching dirty Producto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Producto instance) {
		Session session=sessionFactory.openSession();
		log.debug("attaching clean Producto instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Producto persistentInstance) {
		log.debug("deleting Producto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Producto merge(Producto detachedInstance) {
		log.debug("merging Producto instance");
		try {
			Producto result = (Producto) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Producto findById(int id) {
		log.debug("getting Producto instance with id: " + id);
		try {
			Session session=sessionFactory.openSession();
			Producto instance = (Producto) session
					.get("bean.Producto", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Producto instance) {
		log.debug("finding Producto instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("dao.impl.impl.Producto")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		List<Producto> productos;
		Session session=sessionFactory.openSession();		
		try {
			productos=session.createQuery("from Producto").list();
			return productos;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.info("Hola que aze se cayo o que aze? PD:_Se cayo al buscar los productos");
			e.printStackTrace();
			return null; 
		}
		
	}
}
