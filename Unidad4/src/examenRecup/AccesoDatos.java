package examenRecup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AccesoDatos {
		private EntityManager em = null;

		public AccesoDatos() {
			try {
				em = Persistence.createEntityManagerFactory("UPExamen").createEntityManager();
				
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

		public List<Socio> obtenerSocios() {
			List<Socio> resultado = new ArrayList<Socio>();
			
			try {
				Query c = em.createQuery("from Socio");
				resultado = c.getResultList();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return resultado;
		}

		public Socio obtenerSocio(int idSocio) {
			Socio resultado=null;
			
			
			try {
				Query consulta = em.createQuery("from Socio where id = ?1");
				
				consulta.setParameter(1, idSocio);
				
				List<Socio> r = consulta.getResultList();
				if(!r.isEmpty()) {
					resultado = r.get(0);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return resultado;
		}

		public long obtenerLibrosPrestados(Socio socio) {
			long resultado = 0;
			try {
				Query c = em.createQuery("select count(*) from Prestamo where idPrestamo.socio.id = ?1 and fechaDevolReal = null");
				c.setParameter(1, socio.getId());
				List<Object> r = c.getResultList();
				if(!r.isEmpty()) {
					resultado = Long.parseLong(r.get(0).toString());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			
				e.printStackTrace();
			}
			
			return resultado;
		}

		public List<Libro> obtenerLibros() {
List<Libro> resultado = new ArrayList<Libro>();
			
			try {
				Query c = em.createQuery("from Libro");
				resultado = c.getResultList();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return resultado;
		}

		public Libro obtenerLibro(int isbn) {
return null;
		}
		
		
		
		
		
		
		
}
