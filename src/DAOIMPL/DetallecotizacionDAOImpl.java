package DAOIMPL;

// Generated 29/09/2014 11:17:45 AM by Hibernate Tools 3.4.0.CR1

import hibernate.HibernateUtil;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import DAO.DetallecotizacionDAO;
import bean.Detallecotizacion;

/**
 * Home object for domain model class Detallecotizacion.
 * @see dao.impl.impl.Detallecotizacion
 * @author Hibernate Tools
 */
public class DetallecotizacionDAOImpl implements DetallecotizacionDAO{

	private static final Log log = LogFactory
			.getLog(DetallecotizacionDAOImpl.class);

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

	public void persist(Detallecotizacion transientInstance) {
		log.debug("persisting Detallecotizacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Detallecotizacion instance) {
		log.debug("Grabando Detallecotizacion instance");
		Session session=sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			session.saveOrUpdate(instance);
			session.getTransaction().commit();
			log.debug("Grabado successful");
		} catch (RuntimeException re) {
			log.error("Grabado failed", re);
			throw re;
		}
	}

	public void attachClean(Detallecotizacion instance) {
		log.debug("attaching clean Detallecotizacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Detallecotizacion persistentInstance) {
		log.debug("deleting Detallecotizacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Detallecotizacion merge(Detallecotizacion detachedInstance) {
		log.debug("merging Detallecotizacion instance");
		try {
			Detallecotizacion result = (Detallecotizacion) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Detallecotizacion findById(java.lang.Integer id) {
		log.debug("getting Detallecotizacion instance with id: " + id);
		try {
			Detallecotizacion instance = (Detallecotizacion) sessionFactory
					.getCurrentSession().get("dao.impl.impl.Detallecotizacion",
							id);
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

	public List findByExample(Detallecotizacion instance) {
		log.debug("finding Detallecotizacion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("dao.impl.impl.Detallecotizacion")
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
	public void saveOrUpdateList(List<Detallecotizacion> detalles) {
		// TODO Auto-generated method stub
		log.debug("Grabando Detallecotizacion instance");
		Session session=sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			for(Detallecotizacion det:detalles){
			session.saveOrUpdate(det);
			}
			session.getTransaction().commit();
			log.debug("Grabado successful");
		} catch (RuntimeException re) {
			log.error("Grabado failed", re);
			session.getTransaction().rollback();
			throw re;
		}		
	}
}
