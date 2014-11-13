package extras;

import hibernate.HibernateUtil;

import org.junit.Test;

public class UtilTest {

	//@Test
	public void test() {		
		System.out.println("Retorna ..."+Util.getProperties("parameters.properties"));		
	}
	@Test
	public void testHibernate() {
		HibernateUtil.getSessionFactory();				
	}
}
