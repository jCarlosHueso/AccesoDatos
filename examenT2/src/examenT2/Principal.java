package examenT2;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	static Scanner sc = new Scanner(System.in);

	static AccesoDatos ad = new AccesoDatos();

	static int usLogueado;
	public static void main(String[] args) {
		
		
		if (ad.getConexion() != null) {
			int valorLogueo=0;
			do {
				valorLogueo=loguear();
			switch (valorLogueo) {
			case 0:
				System.out.println("usuario y contraseña incorrectos");
				break;
			case 1:
				int opcion;
				do {
					System.out.println("Introduce opción: ");
					System.out.println("0-Salir");
					System.out.println("1- Enviar Mensajes ");
					System.out.println("2- Leer mensajes ");
					System.out.println("3- Estadistica Mensajes ");

					opcion = sc.nextInt();
					sc.nextLine();
					switch (opcion) {
					case 1:
						enviarMensajes();
						break;
					case 2:
						leerMensajes();
						break;
					case 3:
						break;
					}
				} while (opcion != 0);
				
				break;
			}
			}while(valorLogueo==0);
		} else {
			System.out.println("¡conexion no establecida!");
		}
	}

	private static void leerMensajes() {
		ArrayList<mensaje> mensajes = new ArrayList<mensaje>();
		
		mensajes=ad.leerMensajes(usLogueado);
	
	if(ad.marcarMensajesLeidos(usLogueado)) {
		System.out.println("mensajes leidos");
	}else {
		System.out.println("mensajes no leidos");
	}
		
		for (mensaje msg : mensajes) {
			System.out.println(msg.toString());	
		}
	}

	private static void enviarMensajes() {
		
		ArrayList<departamento> listaDepartamentos = ad.mostarDepartamentos(); 
		
		for (departamento departamento : listaDepartamentos) {
			System.out.println(departamento.toString());
		}
		
		System.out.println("introduce el id del departamento al que le vas a enviar el mensaje");
		int idDept= sc.nextInt();sc.nextLine();
		System.out.println("introdue el asunto del mensaje: ");
		String asunto = sc.nextLine();
		System.out.println("introdue el cuerpo del mensaje: ");
		String cuerpo = sc.nextLine();
		
		if(ad.enviarMensaje(idDept,asunto,cuerpo)) {
			
			//implementar
			
		}
		
	}

	private static int loguear() {
		int accion = 0;
		System.out.println("introduce tu usuario: ");
		int us = sc.nextInt();
		sc.nextLine();
		System.out.println("introduce tu contraseña: ");
		String ps = sc.nextLine();

		int salida = ad.login(us, ps);

		
		
		switch (salida) {
		case 0:
			accion = 0;
			
			break;
		case 1:
			accion = 1;
			usLogueado=us;

			break;
		case 2:
			System.out.println("Primer logueo.\nIntroduce tu nueva contraseña");
			String nuevaContra=sc.nextLine();
			if(ad.cambiarPassword(nuevaContra,us)) {
				System.out.println("contraseña cambiada con éxito");
			}else {
				System.out.println("contraseña no cambiada");
			}
			usLogueado=us;

			break;
			
		}
		return accion;
	}

}
