package clinicaV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Principal {
	
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opci�n:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Cliente");
				System.out.println("2-Crear Mascota");
				System.out.println("3-Crear Tratamiento");
				System.out.println("4-Mostrar tratamiento");
				System.out.println("5-Borrar tratamiento");
				System.out.println("6-Modificar tratamiento");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearCliente();
					break;
				case 2:
					crearMascota();
					break;
				case 3:
					crearTratamiento();
					break;
				case 4:
					mostrarTratamiento();
					break;
				case 5:
					borrarTratamiento();
					break;
				case 6:
					modificarTratamiento();
					break;
				}
			} while (opcion != 0);
			//Cerrar conexi�n
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexi�n con Cl�nica");
		}
	}
	private static void modificarTratamiento() {
		// TODO Auto-generated method stub
		mostrarTratamientos();
		System.out.println("C�digo de tratamiento");
		int codigo = t.nextInt();t.nextLine();
		Tratamiento tr = ad.obtenerTratamiento(codigo);
		if(tr!=null) {
			System.out.println("Nueva descripci�n:");
			tr.setDescripcion(t.nextLine());
			if(!ad.modificarTratamiento(tr)) {
				System.out.println("Error al modifcar el tratamiento");
			}
			else {
				System.out.println("Tratamiento modificar");
			}
		}
		else {
			System.out.println("Error, Tratamiento no existe");
		}
	}
	private static void borrarTratamiento() {
		// TODO Auto-generated method stub
		mostrarTratamientos();
		System.out.println("C�digo de tratamiento");
		int codigo = t.nextInt();t.nextLine();
		Tratamiento tr = ad.obtenerTratamiento(codigo);
		if(tr!=null) {
			if(!ad.borrarTratamiento(tr)) {
				System.out.println("Error al borrar el tratamiento");
			}
			else {
				System.out.println("Tratamiento borrado");
			}
		}
		else {
			System.out.println("Error, Tratamiento no existe");
		}
	}
	private static void mostrarTratamiento() {
		// TODO Auto-generated method stub
		mostrarTratamientos();//Solamente c�digo, mascota y fecha
		System.out.println("C�digo de tratamiento");
		int codigo = t.nextInt();t.nextLine();
		Object[] datos=ad.obtenerInfoTratamiento(codigo);
		if(datos!=null) {
			Cliente c = (Cliente) datos[0];
			Mascota m = (Mascota) datos[1];
			Tratamiento tr = (Tratamiento) datos[2];
			System.out.print("-- CLIENTE --:");
			c.mostrar();
			System.out.print("-- MASCOTAS --:");
			m.mostrar(false);
			System.out.print("-- TRATAMIENTO --:");
			tr.mostrar();
		}
		else {
			System.out.println("Error, no existe el tratamiento");
		}
	}
	private static void mostrarTratamientos() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		ArrayList<Object[]> tratamientos = ad.obtenerTratamientos();
		for(Object[] o:tratamientos) {
			System.out.println("C�digo:"+o[0]+
					"\tMascota:"+o[1]+
					"\tFecha"+formato.format(o[2]));
		}
	}
	private static void crearTratamiento() {
		// TODO Auto-generated method stub
		mostrarMascotas();
		System.out.println("C�digo Mascota:");
		int codigo = t.nextInt();t.nextLine();
		Mascota m = ad.obtenerMascota(codigo);
		if(m!=null) {
			Tratamiento tr = new Tratamiento();
			tr.setCodigo(ad.obtenerCodigoTratamiento());
			System.out.println("Descrpci�n del tratamiento:");
			tr.setDescripcion(t.nextLine());			
			tr.setFecha(new Date());
			if(ad.crearTratamiento(m,tr)) {
				System.out.println("Tratamiento a�adido con c�digo "+tr.getCodigo());
			}
			else {
				System.out.println("Error al crear el tratamiento");
			}
		}
		else {
			System.out.println("Error, mascota no existe");
		}
	}
	private static void mostrarMascotas() {
		// TODO Auto-generated method stub
		ArrayList<Mascota> mascotas = ad.obtenerMascotas();
		for(Mascota m: mascotas) {
			m.mostrar(false);
		}
	}
	private static void crearMascota() {
		// TODO Auto-generated method stub
		mostrarClientes();
		System.out.println("C�digo de cliente:");
		int codigo = t.nextInt();t.nextLine();
		Cliente c = ad.obtenerCliente(codigo);
		if(c != null) {
			Mascota m = new Mascota();
			m.setCliente(codigo);
			System.out.println("Nombre mascota:");
			m.setNombre(t.nextLine());
			System.out.println("Tipo:");
			m.setTipo(t.nextLine());
			m.setCodigo(ad.obtenerCodigo("mascotas"));
			if(ad.crearMascota(m)) {
				System.out.println("Mascota a�adida con c�digo "+m.getCodigo());
			}
			else {
				System.out.println("Error al a�adir la mascota");
			}
			
		}
		else {
			System.out.println("Cliente no existe");
		}
		
	}
	private static void mostrarClientes() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clientes = ad.obtenerClientes();
		for(Cliente c: clientes) {
			c.mostrar();
		}
	}
	private static void crearCliente() {
		// TODO Auto-generated method stub
		System.out.println("Email:");
		String email = t.nextLine();				
		Cliente c = ad.obtenerCliente(email);
		if(c==null) {
			c = new Cliente();
			c.setEmail(email);
			System.out.println("Nombre");
			c.setNombre(t.nextLine());
			c.setCodigo(ad.obtenerCodigo("clientes"));
			if(ad.crearCliente(c)) {
				System.out.println("Cliente creado con c�digo:"+c.getCodigo());
			}
			else {
				System.out.println("Error al crear el cliente");
			}
		}
		else {
			System.out.println("Error, el cliente ya existe");
		}
		
	}
}
