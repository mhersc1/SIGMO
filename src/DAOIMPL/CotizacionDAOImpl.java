package DAOIMPL;

// Generated 29/09/2014 11:17:45 AM by Hibernate Tools 3.4.0.CR1

import hibernate.HibernateUtil;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import DAO.CotizacionDAO;
import bean.Cotizacion;
import extras.Util;

/**
 * Home object for domain model class Cotizacion.
 * @see dao.impl.impl.Cotizacion
 * @author Hibernate Tools
 */
public class CotizacionDAOImpl implements CotizacionDAO {

	private static final Log log = LogFactory.getLog(CotizacionDAOImpl.class);	
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

	public void persist(Cotizacion transientInstance) {
		log.debug("persisting Cotizacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Cotizacion instance) {
		log.debug("Grabando Cotizacion instance");
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
	@Override
	public List<Cotizacion> obtenerListaCotizacionesPorFecha(Date fechaDesde,
			Date fechaHasta) {
		java.sql.Date inicio;
		java.sql.Date fin;
		try {
			inicio=new java.sql.Date(fechaDesde.getTime());
			fin=new java.sql.Date(fechaHasta.getTime());
			Session session=this.sessionFactory.openSession();
			String hql= "from Cotizacion where fecharegistro >= '"+inicio+"' and fecharegistro <= '"+ fin+"'";
			return (List<Cotizacion>)session.createQuery(hql).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public void attachClean(Cotizacion instance) {
		log.debug("attaching clean Cotizacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Cotizacion persistentInstance) {
		log.debug("deleting Cotizacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cotizacion merge(Cotizacion detachedInstance) {
		log.debug("merging Cotizacion instance");
		try {
			Cotizacion result = (Cotizacion) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	@Override
	public Cotizacion findById(int id) {
		Session session=sessionFactory.openSession();
		log.debug("getting Cotizacion instance with id: " + id);
		try {
			Cotizacion instance = (Cotizacion) session.get("bean.Cotizacion", id);
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

	public List findByExample(Cotizacion instance) {
		log.debug("finding Cotizacion instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("dao.impl.impl.Cotizacion")
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
	public String generarCorrelativoNumeroCotizacion() {//Correlativo formateado
		// TODO Auto-generated method stub
		Integer correl;
		Session session=this.sessionFactory.openSession();
		String numCont="000001";
		try {
			String query="select max(codigo)+1 from Cotizacion ";
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
}
