package examen19_12;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();

	public static void main(String[] args) {

		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Contratar servicio");
			System.out.println("2-Crear facturas");
			System.out.println("3-Borrar cliente");
			System.out.println("4-Informe de facturacion");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				contratarServicio();
				break;
			case 2:
				System.out.println("por terminar...");
				break;
			case 3:
				borrarCliente();
				break;
			case 4:
				informeFacturacion();
				break;
			
			}
		} while (opcion != 0);
		
	}

	private static void informeFacturacion() {

		System.out.println("introduce el mes del que se va a informar ");
		
		int mes=t.nextInt();t.nextLine();
		

		
		
		
		//   :(
		}

	private static void borrarCliente() {

		System.out.println("Clientes:");
		
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		clientes=ad.obtenerClientes();
		
		for (Cliente cliente : clientes) {
			System.out.println(cliente.toString());
			
		}
			
		System.out.println("introduce el id de cliente que va a borrar");
		
		int idCliente=t.nextInt();t.nextLine();
		if(ad.servicioContratado(idCliente)) {
		if(ad.borrarCliente(idCliente)) {
			System.out.println("cliente borrado con exito");
		}
		}else{
			System.out.println("el cliente ha contratado servicios");
		}
	}

	private static void contratarServicio() {

	System.out.println("Clientes:");
	
	ArrayList<Cliente> clientes = new ArrayList<>();
	
	clientes=ad.obtenerClientes();
	
	for (Cliente cliente : clientes) {
		System.out.println(cliente.toString());
		
	}
		
	System.out.println("introduce el id de cliente que va a contratar un servicio");
	
	int idCliente=t.nextInt();t.nextLine();
	
	ArrayList<Servicio> servicios = new ArrayList<>();
	
	
		servicios=ad.obtenerServiciosCliente(idCliente);
	
	
	
	for (Servicio servicio : servicios) {
		System.out.println(servicio.toString());
	}
	
System.out.println("intoduce el id del servicio a contratar");
int idServicio=t.nextInt();t.nextLine();

if(ad.contratarServicio(idServicio,idCliente)) {
	System.out.println("servicio contratado con exito");
}else {
	System.out.println("error servicio no contratado");
}

		
	}

}
