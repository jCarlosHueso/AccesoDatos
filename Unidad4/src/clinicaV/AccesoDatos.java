package clinicaV;

import java.util.ArrayList;
import java.util.Date;
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
			em = Persistence.createEntityManagerFactory("UPClinicaV").createEntityManager();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void cerrar() {
		try {
			if(em!=null) {
				em.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Cliente obtenerCliente(String email) {
		// TODO Auto-generated method stub
		Cliente resultado = null;
		try {
			Query consulta = em.createQuery("from Cliente where email = ?1");
			
			consulta.setParameter(1, email);
			
			List<Cliente> r = consulta.getResultList();
			if(!r.isEmpty()) {
				resultado = r.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			//Siempre que hagamos una modificación en los datos
			//hay que hacerlo dentro de una transacción
			t = em.getTransaction();
			t.begin();
			//Hacemos el insert
			em.persist(c);
			t.commit();
			em.clear();
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		List<Cliente> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("from Cliente");
			resultado = c.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Cliente obtenerCliente(int codigo) {
		// TODO Auto-generated method stub
		Cliente resultado =null;
		try {
			resultado = em.find(Cliente.class, codigo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		
		EntityTransaction t  = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.persist(c);
			t.commit();
			em.clear();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		
		EntityTransaction t  = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.remove(c);
			t.commit();
			em.clear();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearMascota(Mascota m) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		
		EntityTransaction t  = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.persist(m);
			t.commit();
			em.clear();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public Mascota obtenerMascota(int codigoC) {
		// TODO Auto-generated method stub
		Mascota resultado =null;		
		try {
			return em.find(Mascota.class, codigoC);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearConsulta(Consulta consulta) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		
		EntityTransaction t  = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.persist(consulta);
			t.commit();
			em.clear();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Consulta> obtenerConsultas() {
		// TODO Auto-generated method stub
		List<Consulta> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("from Consulta "
					+ "order by idConsulta.fecha desc");
			resultado = c.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Consulta obtenerConsulta(int codigo, Date fecha) {
		// TODO Auto-generated method stub
		Consulta resultado = null;
		try {
			Query c = em.createQuery("from Consulta "
					+ "where idConsulta.mascota.codigo = ?1 and "
					+ "idConsulta.fecha = ?2");
			c.setParameter(1, codigo);
			c.setParameter(2, fecha);
			
			List<Consulta> r = c.getResultList();
			if(r.size()==1) {
				resultado = r.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarConsulta(Consulta consulta) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t=em.getTransaction();
			t.begin();
			Query c = em.createQuery("update Consulta set "
					+ "diagnostico = ?1, receta = ?2 "
					+ "where idConsulta=?3");
			c.setParameter(1, consulta.getDiagnostico());
			c.setParameter(2, consulta.getReceta());
			c.setParameter(3, consulta.getIdConsulta());
			int r = c.executeUpdate();
			if(r==1) {
				resultado = true;
				t.commit();
				//Refrescar caché
				em.clear();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public Consulta obtenerConsulta(ConsultaClave consultaClave) {
		// TODO Auto-generated method stub
		Consulta resultado = null;
		try {
			resultado = em.find(Consulta.class, consultaClave);			
			
		} catch (Exception e) {
			// TODO: handle exception
		
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Object[]> obtenerBuenosClientes() {
		// TODO Auto-generated method stub
		List<Object[]> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("select "
					+ "c.idConsulta.mascota.cliente.nombre, count(*) as numero "
					+ "from Consulta as c "
					+ "where diagnostico != null and c.idConsulta.fecha<?1 "
					+ "group by c.idConsulta.mascota.cliente.codigo "
					+ "order by numero desc");
			c.setParameter(1, new Date());
			resultado = c.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borradoPeligroso(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t=em.getTransaction();
			t.begin();
			Query consulta =null;
			int r=0;
			//Borrar las consultas por mascota 
			//ya que no nos permite por cliente
			for(Mascota m:c.getMascotas()) {
				//Borrar conultas
				consulta = em.createQuery("delete from Consulta "
						+ "where idConsulta.mascota = ?1");
				consulta.setParameter(1, m);
				r = consulta.executeUpdate();
				//No chequeamos error ya que r puede ser 0
				//y eso no significa que haya error
			}		
			
			//Borrar mascotas
			consulta = em.createQuery("delete from Mascota "
					+ "where cliente = ?1");
			consulta.setParameter(1, c);
			r = consulta.executeUpdate();
			if(r>=1) {
				consulta = em.createQuery("delete from Cliente "
						+ "where codigo = ?1");
				consulta.setParameter(1, c.getCodigo());
				r = consulta.executeUpdate();
				if(r==1) {
					t.commit();
					em.clear();
					resultado=true;
				}
				else {
					t.rollback();
				}
			}
			else {
				t.rollback();
			}					
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}
	
	
}
