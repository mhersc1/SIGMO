package DAOIMPL;

// Generated 29/09/2014 11:17:45 AM by Hibernate Tools 3.4.0.CR1

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import DAO.DocumentosclienteDAO;
import bean.Cliente;
import bean.Documentoscliente;

/**
 * Home object for domain model class Documentoscliente.
 * @see dao.impl.impl.Documentoscliente
 * @author Hibernate Tools
 */
public class DocumentosclienteDAOImpl implements DocumentosclienteDAO{

	private static final Log log = LogFactory
			.getLog(DocumentosclienteDAOImpl.class);

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

	public void persist(Documentoscliente transientInstance) {
		log.debug("persisting Documentoscliente instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Documentoscliente instance) {
		log.debug("attaching dirty Documentoscliente instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Documentoscliente instance) {
		log.debug("attaching clean Documentoscliente instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Documentoscliente persistentInstance) {
		log.debug("deleting Documentoscliente instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Documentoscliente merge(Documentoscliente detachedInstance) {
		log.debug("merging Documentoscliente instance");
		try {
			Documentoscliente result = (Documentoscliente) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Documentoscliente findById(bean.DocumentosclienteId id) {
		log.debug("getting Documentoscliente instance with id: " + id);
		try {
			Documentoscliente instance = (Documentoscliente) sessionFactory
					.getCurrentSession().get("dao.impl.impl.Documentoscliente",
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

	public List findByExample(Documentoscliente instance) {
		log.debug("finding Documentoscliente instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("dao.impl.impl.Documentoscliente")
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
	public List<Documentoscliente> cargarDocumentosCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		List<Documentoscliente> documentos=new ArrayList<Documentoscliente>();
		Session session=sessionFactory.openSession();
		try {
			String hql="from Documentoscliente where id.idcliente = '"+cliente.getId()+"' order by id.iddocumento asc";
			documentos=session.createQuery(hql).list();
			return documentos;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}		
	}
}
