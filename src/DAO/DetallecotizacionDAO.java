package DAO;

import java.util.List;

import bean.Detallecotizacion;

public interface DetallecotizacionDAO {
public void saveOrUpdate(Detallecotizacion detalle);
public void saveOrUpdateList(List<Detallecotizacion> detalles);
}
