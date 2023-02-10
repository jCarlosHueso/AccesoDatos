package taller;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AccesoDatos {
	private EntityManager em = null;

	public AccesoDatos() {
		try {
			em = Persistence.createEntityManagerFactory("UPTaller")
					.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void cerrar() {
		try {
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Vehiculo> obtenerVehiculos() {
		// TODO Auto-generated method stub
		List<Vehiculo> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("from Vehiculo order by matricula");
			resultado = c.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Vehiculo obtenerVehiculo(int codigo) {
		// TODO Auto-generated method stub
		Vehiculo resultado=null;
		try {
			resultado = em.find(Vehiculo.class, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearReparacion(Reparacion r) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t= em.getTransaction();
			t.begin();
			em.persist(r);
			t.commit();
			em.clear();
			resultado = true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Reparacion> obtenerReparaciones() {
		// TODO Auto-generated method stub
		List<Reparacion> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("from Reparacion order by fecha desc");
			resultado = c.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Pieza> obtenerPiezas() {
		// TODO Auto-generated method stub
		List<Pieza> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("from Pieza order by codigo");
			resultado = c.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Reparacion obtenerReparacion(int id) {
		// TODO Auto-generated method stub
		Reparacion resultado=null;
		try {
			resultado = em.find(Reparacion.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Pieza obtenerPieza(String codigo) {
		// TODO Auto-generated method stub
		Pieza resultado=null;
		try {
			resultado = em.find(Pieza.class, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public PiezaReparacion obtenerPR(clavePR codigo) {
		// TODO Auto-generated method stub
		PiezaReparacion resultado=null;
		try {
			resultado = em.find(PiezaReparacion.class, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean guardarPR(PiezaReparacion pr) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t= em.getTransaction();
			t.begin();
			em.persist(pr);
			//em.persist(pr.getClave().getPieza());
			t.commit();
			em.clear();
			resultado = true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarPr(PiezaReparacion pr) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			t= em.getTransaction();
			t.begin();
			Query c = em.createQuery("delete from PiezaReparacion "
					+ "where clave = ?1");
			c.setParameter(1, pr.getClave());
			int r = c.executeUpdate();
			if(r==1) {
				//Actualizar el stock de la pieza
				c= em.createQuery("update Pieza set stock = stock + ?1 "
						+ "where codigo = ?2");
				c.setParameter(1, pr.getCantidad());
				c.setParameter(2, pr.getClave().getPieza().getCodigo());
				r = c.executeUpdate();
				if(r==1) {
					t.commit();
					em.clear();
					resultado = true;
				}
				else {
					t.rollback();
				}
				
			}
			else {
				t.rollback();
			}
			
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Object[]> obtenerEstadistica() {
		// TODO Auto-generated method stub
		List<Object[]> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("select count(*),"
					+ "sum(stock*precio), min(precio), max(precio) "
					+ "from Pieza");
			resultado = c.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
}
