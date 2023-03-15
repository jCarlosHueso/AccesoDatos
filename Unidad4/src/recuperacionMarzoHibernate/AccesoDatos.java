package recuperacionMarzoHibernate;

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
				em = Persistence.createEntityManagerFactory("UPservihogar")
						.createEntityManager();
				
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

		public Cliente obtenerClienteNif(String nif) {
			Cliente resultado = null;
			try {
				Query consulta = em.createQuery("from Cliente where nif = ?1");
				
				consulta.setParameter(1, nif);
				
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

		public boolean addPresupuesto(Presupuesto p) {
			
			
			boolean resultado = false;
			EntityTransaction t = null;
			try {
				//Siempre que hagamos una modificación en los datos
				//hay que hacerlo dentro de una transacción
				t = em.getTransaction();
				t.begin();
				//Hacemos el insert
				em.persist(p);
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

		public Presupuesto obtenerIdPresupuesto(Presupuesto p) {
			Presupuesto resultado = null;
			try {
				Query consulta = em.createQuery("from Presupuesto where cliente = ?1 and fecha = ?2");
				
				consulta.setParameter(1, p.getCliente());
				consulta.setParameter(2, p.getFecha());
				
				List<Presupuesto> r = consulta.getResultList();
				if(!r.isEmpty()) {
					resultado = r.get(0);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return resultado;
		}

		public List<Servicio> obtenerServicios() {
			// TODO Auto-generated method stub
			List<Servicio> resultado = new ArrayList<>();
			try {
				Query c = em.createQuery("from Servicio");
				resultado = c.getResultList();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return resultado;

		}

		public Servicio obtenerServicio(String codigo) {
			
			Servicio resultado = null;
			try {
				Query consulta = em.createQuery("from Servicio where codigo = ?1");
				
				consulta.setParameter(1, codigo);
				
				List<Servicio> r = consulta.getResultList();
				if(!r.isEmpty()) {
					resultado = r.get(0);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return resultado;
		}

		public boolean crearDealle(Detalle_Presupuesto d) {
			boolean resultado = false;
			EntityTransaction t = null;
			try {
				//Siempre que hagamos una modificación en los datos
				//hay que hacerlo dentro de una transacción
				t = em.getTransaction();
				t.begin();
				//Hacemos el insert
				em.persist(d);
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

		public List<Detalle_Presupuesto> obtenerDetalles(Presupuesto presupuesto) {
			List<Detalle_Presupuesto> resultado = new ArrayList<>();
			try {
				Query c = em.createQuery("from Detalle_Presupuesto where Presupuesto =?1");
				c.setParameter(1, presupuesto);

				resultado = c.getResultList();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return resultado;
		}

		public List<Presupuesto> obtenerPresupuestos() {
			List<Presupuesto> resultado = new ArrayList<>();
			try {
				Query c = em.createQuery("from Presupuesto");
				
				resultado = c.getResultList();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return resultado;
		}
	}
