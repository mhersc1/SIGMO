package DAO;

import java.util.List;

import bean.Producto;


public interface ProductoDAO {
	public List<Producto> findAll();
	public Producto findById(int id);
	public void persist(Producto transientInstance) ;
	public void saveOrUpdate(Producto instance);

}
