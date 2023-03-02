package saneamiento;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AccesoDatos {
	private EntityManager em = null;

	public AccesoDatos() {
		try {
			em = Persistence.createEntityManagerFactory("UPSaneamiento")
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

	public List<Veterinario> obtenerVeterinarios() {
		// TODO Auto-generated method stub
		List<Veterinario>resultado = new ArrayList<>();
		try {
			Query consulta = em.createQuery("from Veterinario");
			resultado = consulta.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Veterinario obtenerVeterinario(int colegiado) {
		// TODO Auto-generated method stub
		Veterinario resultado = null;
		try {
			resultado = em.find(Veterinario.class, colegiado);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Saneamiento> obtenerSaneamientos() {
		// TODO Auto-generated method stub
		List<Saneamiento>resultado = new ArrayList<>();
		try {
			Query consulta = em.createQuery("from Saneamiento");
			resultado = consulta.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Saneamiento obtenerSaneamiento(String codigoSan) {
		// TODO Auto-generated method stub
		Saneamiento resultado = null;
		try {
			resultado = em.find(Saneamiento.class, codigoSan);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearSaneamiento(Saneamiento s) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		EntityTransaction t = null;
		try {
			t= em.getTransaction();
			t.begin();
			em.persist(s);
			t.commit();
			em.clear();
			resultado = true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Animal> obtenerAnimales() {
		// TODO Auto-generated method stub
		List<Animal>resultado = new ArrayList<>();
		try {
			Query consulta = em.createQuery("from Animal");
			resultado = consulta.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Animal obtenerAnimal(int codAnim) {
		// TODO Auto-generated method stub
		Animal resultado = null;
		try {
			resultado = em.find(Animal.class, codAnim);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Object[]> obtenerEstadisticaPorAnimales() {
		// TODO Auto-generated method stub
		List<Object[]>resultado = new ArrayList<>();
		try {
			Query consulta = em.createQuery("select rs.clave.animal.codigo, "
					+ "count(*) as NumSaneamientos,min(s.fecha), max(s.fecha) "
					+ "from ResultadoSaneamiento as rs inner join Saneamiento as s on rs.clave.sanea = s.id"
					+ " group by rs.clave.animal");
			resultado = consulta.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}


	public boolean crearDatosSaneamiento(Saneamiento s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t = em.getTransaction();
			t.begin();
			
			em.merge(s);
			
			t.commit();
			em.clear();
			resultado = true;
		}catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarSaneamiento(Saneamiento s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t = em.getTransaction();
			t.begin();
			// BORRAR PRIMERO LOS RESULTADOS DEL SANEAMIENTO
			Query c = em.createQuery("delete from ResultadoSaneamiento where clave.saneamiento.id = ?1");
			c.setParameter(1, s.getId());
			int r = c.executeUpdate();
			
			if(r >= 1) {
				c = em.createQuery("delete from Saneamiento where id = ?1");
				c.setParameter(1, s.getId());
				r = c.executeUpdate();
		
				if(r == 1) {
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

		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		}
		return resultado;
	}

}
