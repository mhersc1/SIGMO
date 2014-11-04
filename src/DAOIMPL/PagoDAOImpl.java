package DAOIMPL;

import hibernate.HibernateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.Pago;

import DAO.PagoDAO;

public class PagoDAOImpl implements PagoDAO{
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final Log log = LogFactory.getLog(PagoDAOImpl.class);

	
	
	@Override
	public String generarCorrelativoCodigoPago() {//Correlativo formateado
		// TODO Auto-generated method stub
		Integer correl;
		Session session=this.sessionFactory.openSession();
		String numCont="000001";
		try {
			String query="select max(codigo)+1 from Pago ";
			correl=(Integer) session.createQuery(query).uniqueResult();
			if(correl!=null){
				numCont=formatNumber(String.valueOf(correl));
			}
			session.close();
			return numCont;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}	
		
	}
	
	private String formatNumber(String correlativo){
		/**
		 * @formatNumber 	<RUC><FechaActual><correlativo>
		 * 					10471113321814121991000001
		 */
		/**
		 * @formatNumber 	<correlativo> //Hasta 6 digitos
		 * 					000001
		 */
		String correl="";
		int tam=(""+correlativo).length();
		for (int i = 0; i < (6-tam); i++) {
			correl += "0";
		}
		return correl+correlativo;
	}

	@Override
	public void registrarPago(Pago pago) {
		// TODO Auto-generated method stub
		try{
		Session session=this.sessionFactory.openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(pago);
		session.getTransaction().commit(); 
		session.close();
		} catch (RuntimeException re) {
			log.error("No se pudo grabar -->", re);
		throw re;
		}
	}
	

}
