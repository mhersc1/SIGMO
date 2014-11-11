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

import extras.Util;
import DAO.PedidoDAO;
import bean.Pedido;

/**
 * Home object for domain model class Pedido.
 * @see dao.impl.impl.Pedido
 * @author Hibernate Tools
 */
public class PedidoDAOImpl implements PedidoDAO {

	private static final Log log = LogFactory.getLog(PedidoDAOImpl.class);

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
	}*/

	public void persist(Pedido transientInstance) {
		log.debug("persisting Pedido instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Pedido instance) {
		log.debug("Grabando Pedido instance");
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

	public void attachClean(Pedido instance) {
		log.debug("attaching clean Pedido instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Pedido persistentInstance) {
		log.debug("deleting Pedido instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pedido merge(Pedido detachedInstance) {
		log.debug("merging Pedido instance");
		try {
			Pedido result = (Pedido) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Pedido findById(int id) {
		log.debug("getting Pedido instance with id: " + id);
		try {
			Pedido instance = (Pedido) sessionFactory
					.getCurrentSession().get("dao.impl.impl.Pedido", id);
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

	public List findByExample(Pedido instance) {
		log.debug("finding Pedido instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("dao.impl.impl.Pedido")
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
	public String generarCorrelativoNumeroPedido() {//Correlativo formateado
		// TODO Auto-generated method stub
		Integer correl;
		Session session=this.sessionFactory.openSession();
		String numCont="000001";
		try {
			String query="select max(codigo)+1 from Pedido ";
			correl=(Integer) session.createQuery(query).uniqueResult();
			if(correl!=null){
				numCont=Util.darFormatoNroCotizacion(String.valueOf(correl));
			}
			return numCont;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}	
		
	}

	@Override
	public Pedido findByNro(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		try {
			Pedido cot=(Pedido)session.get(Pedido.class, id);
			return cot;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
