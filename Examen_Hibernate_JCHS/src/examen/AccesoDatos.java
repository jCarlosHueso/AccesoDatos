package examen;

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
			em = Persistence.createEntityManagerFactory("UPTenis").createEntityManager();
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

	public boolean existeJugador(int cod1) {
		jugador resul = null;
		boolean resultado = false;

		try {
			resul = em.find(jugador.class, cod1);
			if (resul != null) {
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public List<jugador> obtenerJugadores() {
		List<jugador> resultado = new ArrayList<>();

		try {
			Query consulta = em.createQuery("from jugador");
			resultado = consulta.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean crearPartido(partido p) {

		boolean resultado = false;

		EntityTransaction t = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.persist(p);
			t.commit();
			em.clear();
			resultado = true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}

		return resultado;

	}

	public jugador obtenerJugadores(int cod1) {
		jugador resultado = null;

		try {
			resultado = em.find(jugador.class, cod1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

}
