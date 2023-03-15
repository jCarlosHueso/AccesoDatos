package clinicaV;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Cliente");
				System.out.println("2-Mostrar Clientes");
				System.out.println("3-Modificar Cliente");
				System.out.println("4-Borrar Cliente");
				System.out.println("5-Crear Consulta");
				System.out.println("6-Crear Consulta");
				System.out.println("7-Modificar Consulta");
				System.out.println("8-Mostrar Consulta");
				System.out.println("9-Mostrar Rankig Buenos Clientes");
				System.out.println("10-% Consultas no asistidas Clientes");
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearCliente();
					break;
				case 2:
					mostrarClientes();
					break;
				case 3:
					modificarCliente();
					break;
				case 4:
					borrarCliente();
					break;
				case 5:
					crearMascota();
					break;
				case 6:
					crearConsulta();
					break;
				case 7:
					modificarConsulta();
					break;
				case 8:
					mostrarConsultas();
					break;
				case 9:
					mostrarBuenosClientes();
					break;
				case 10:
					infoMalosClientes();
					break;
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}
	private static void infoMalosClientes() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = ad.obtenerClientes();
		for(Cliente c:clientes) {
			float numConsultasPedidas=0;
			float numConsultasFaltas=0;
			for(Mascota m:c.getMascotas()) {				
				for(Consulta cons:m.getConsultas()) {
					if(cons.getIdConsulta().getFecha().before(new Date())) {
						//Acumular las consultas de cada mascota
						numConsultasPedidas+=1;
						if(cons.getDiagnostico()==null) {
							//Acumulo consulta faltada
							numConsultasFaltas += 1;
						}
					}
				}
				
			}
			System.out.println("Cliente:"+c.getNombre()+
					"\tPedidas:"+numConsultasPedidas +
					"\tFaltas:"+numConsultasFaltas +
					"\t%:"+
					(numConsultasPedidas==0?0:
						numConsultasFaltas/numConsultasPedidas*100));
		}
	}
	private static void mostrarBuenosClientes() {
		// TODO Auto-generated method stub
		List<Object[]> datos = ad.obtenerBuenosClientes();
		for(Object[] o:datos) {
			System.out.println("Cliente:"+o[0]+
					"Nº Consultas:"+o[1]);
		}
	}
	private static void modificarConsulta() {
		// TODO Auto-generated method stub
		mostrarConsultas();
		try {
			System.out.println("Código Mascota");
			int codigo = t.nextInt(); t.nextLine();
			System.out.println("FEcha(ddmmyyhhmm)");		
			Date fecha = formato.parse(t.nextLine());
			
			//Consulta c = ad.obtenerConsulta(codigo,fecha);
			Consulta c = ad.obtenerConsulta(
					new ConsultaClave(ad.obtenerMascota(codigo),fecha));
			if(c!=null) {
				System.out.println("Modificar consulta:");
				c.mostrar();
				System.out.println("Diagnóstico");
				c.setDiagnostico(t.nextLine());
				System.out.println("Receta");
				c.setReceta(t.nextLine());
				if(ad.modificarConsulta(c)) {
					System.out.println("Consulta modificada");
				}
				else {
					System.out.println("Error al modificar la consulta");
				}
				
			}
			else {
				System.out.println("Error, consulta no existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void mostrarConsultas() {
		// TODO Auto-generated method stub
		List<Consulta> consultas = ad.obtenerConsultas();
		for(Consulta c:consultas) {
			c.mostrar();
		}
	}
	private static void crearConsulta() {
		// TODO Auto-generated method stub
		try {
			mostrarClientes();
			System.out.println("Código de cliente");
			int codigo = t.nextInt();t.nextLine();
			Cliente c = ad.obtenerCliente(codigo);
			if(c!=null) {
				//Mostrar mascotas (NO HACE FALTA OBTENERLAS)
				//HIBERNATE LAS RECUPERA CUANDO OBTIENE EL CLIENTE
				for(Mascota m:c.getMascotas()) {
					m.mostrar(false);
				}
				System.out.println("Código de mascota:");
				int codigoC = t.nextInt(); t.nextLine();
				Mascota m = ad.obtenerMascota(codigoC);
				//Chequear que mascota existe para el cliente
				if(m!=null && m.getCliente()==c) {
					Consulta consulta = new Consulta();
					System.out.println("Fecha consulta:(ddMMyyhhmm)");
					consulta.setIdConsulta(new ConsultaClave(m, 
							formato.parse(t.nextLine())));
					System.out.println("Motivo de la consulta");
					consulta.setMotivo(t.nextLine());
					if(ad.crearConsulta(consulta)) {
						System.out.println("Consulta creada");
						consulta.mostrar();
					}
					else {
						System.out.println("Error, al crear la consulta");
					}
					
				}
				else {
					System.out.println("Error, la mascota no existe para el cliente");
				}
			}
			else {
				System.out.println("Error, no existe cliente");
			}
		}
		catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Error, fecha con formato incorrecto");
		}
	}
	private static void crearMascota() {
		// TODO Auto-generated method stub
		mostrarClientes();
		System.out.println("Código de cliente");
		int codigo = t.nextInt();t.nextLine();
		Cliente c = ad.obtenerCliente(codigo);
		if(c!=null) {
			Mascota m = new Mascota();
			m.setCliente(c);
			System.out.println("Nombre mascota");
			m.setNombre(t.nextLine());
			System.out.println("Tipo mascota");
			m.setTipo(t.nextLine());
			if(ad.crearMascota(m)) {
				System.out.println("Mascota creada con código:"+m.getCodigo());
			}
			else {
				System.out.println("Error al crear la mascota");
			}
		}
		else {
			System.out.println("Error, no existe cliente");
		}
	}
	private static void borrarCliente() {
		// TODO Auto-generated method stub
		mostrarClientes();
		System.out.println("Código");
		int codigo = t.nextInt();t.nextLine();		
		Cliente c = ad.obtenerCliente(codigo);
		if(c!=null) {	
			if(c.getMascotas().isEmpty()) {
				if(ad.borrarCliente(c)) {
					System.out.println("Cliente borrad");
				}
				else {
					System.out.println("Error al borrar el cliente");
				}
			}
			else {
				if(ad.borradoPeligroso(c)){
					System.out.println("Cliente, mascotas y consultas borradas");
				}
				else {
					System.out.println("Error al borrar el cliente");
				}
			}
		}
		else {
			System.out.println("Error, cliente no existe");
		}
	}
	private static void modificarCliente() {
		// TODO Auto-generated method stub
		mostrarClientes();
		System.out.println("Código");
		int codigo = t.nextInt();t.nextLine();		
		Cliente c = ad.obtenerCliente(codigo);
		if(c!=null) {
			System.out.println("Nuevo Nombre:");
			c.setNombre(t.nextLine());
			System.out.println("Nuevo teléfono:");
			c.setTelefono(t.nextLine());
			if(ad.modificarCliente(c)) {
				System.out.println("Cliente modificado");
			}
			else {
				System.out.println("Error al modificar el cliente");
			}
		}
		else {
			System.out.println("Error, cliente no existe");
		}
	}
	private static void mostrarClientes() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = ad.obtenerClientes();
		for(Cliente c:clientes) {
			c.mostrar(true);
		}
	}
	private static void crearCliente() {
		// TODO Auto-generated method stub
		System.out.println("Introduce email:");
		String email = t.nextLine();
		Cliente c = ad.obtenerCliente(email);
		if(c==null) {
			c =new Cliente();
			c.setEmail(email);
			System.out.println("Nombre:");
			c.setNombre(t.nextLine());
			System.out.println("Teléfono:");
			c.setTelefono(t.nextLine());
			if(ad.crearCliente(c)) {
				System.out.println("Cliente creado con código:" + c.getCodigo());
			}
			else {
				System.out.println("Error al crear el cliente");
			}
		}
		else {
			System.out.println("Error, cliente no existe");
		}
	}
	
}
