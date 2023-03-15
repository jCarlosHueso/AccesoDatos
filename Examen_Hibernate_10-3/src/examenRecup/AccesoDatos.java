package examenRecup;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

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
		
		
		
		
		
		
		
}
