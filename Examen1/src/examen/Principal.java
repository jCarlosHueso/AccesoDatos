package examen;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static Ad_txt_bin fCuentas = new Ad_txt_bin();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Ejercicio1");
			System.out.println("2-Ejercicio2");
			System.out.println("3-Ejercicio3");
			System.out.println("4-Ejercicio4");
			System.out.println("5-Ejercicio5");
			System.out.println("6-Ejercicio6");
			System.out.println("7-Ejercicio7");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				ejercicio1();
				break;
			case 2:
				ejercicio2();
				break;
			
			case 3:
				ejercicio3();
				break;
			case 4:
				ejercicio4();
				break;
			case 5:
				ejercicio5();
				break;
			case 6:
				ejercicio6();
				break;
			case 7:
				ejercicio7();
				break;
			}	

		} while (opcion != 0);

	}


	private static void ejercicio7() {
		// TODO Auto-generated method stub
		String nombreXML="movimientos.xml";
		Movimientos movs = null;
		
		//Chequear si es la 1ª vez  para pedir la sucursal
		File f = new File(nombreXML);
		if(f.exists()) {
			movs = fCuentas.hacerUnmarshal(f);
		}
		else {
			//Pedir sucursal
			System.out.println("Sucursal:");
			String sucursal = t.nextLine();
			movs = new Movimientos();
			movs.setSucursal(sucursal);
		}
		
		//Pedir datos de nuevo movimieto
		ejercicio2();
		System.out.println("Introduce código de cuenta");
		int codigo = t.nextInt();t.nextLine();
		Cuenta c = fCuentas.obtenerCuentaBin(codigo);
		if(c!=null && !c.isCancelada()) {
			System.out.println("Tipo de movimieto (I/R)");
			String tipo=t.nextLine();
			System.out.println("Cantidad:");
			float cantidad = t.nextFloat(); t.nextLine();			
			if(tipo.equalsIgnoreCase("I") || 
					(tipo.equalsIgnoreCase("R") && c.getSaldo()>=cantidad)) {
				//Creamos el movimiento
				Movimiento m = new Movimiento(tipo,c.getCodigo(),
						c.getNombre(),c.getApellidos(),cantidad);
				//Actualizar saldo en binario
				if(tipo.equalsIgnoreCase("R")) {
					cantidad = cantidad*-1;
				}
				fCuentas.actualizarSaldo(c,cantidad);
				//Añadimos el moviento a movs
				movs.getMovimientos().add(m);
				//Hacer marshal
				if(fCuentas.hacerMarshal(f,movs)) {
					System.out.println("Fichero XML generado");
				}
				else {
					System.out.println("Error al generar XML");
				}
			}
			else {
				System.out.println("Error en tipo de movimiento o no hay saldo");
			}
			
		}
		else {
			System.out.println("Error, la cuenta no existe o está cancelada");
		}
	}


	private static void ejercicio6() {
		// TODO Auto-generated method stub
		ejercicio5();
		System.out.println("Introduce código de cuenta");
		int codigo = t.nextInt(); t.nextLine();
		if(fCuentas.cancelarCuenta(codigo)) {
			System.out.println("Cuenta cancelada");
		}
		else {
			System.out.println("Error al cancelar la cuenta");
		}
	}


	private static void ejercicio5() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> cuentas = fCuentas.obtenerCuentasObj();
		for(Cuenta c:cuentas) {
			c.mostrar();
		}
	}


	private static void ejercicio4() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> cuentas = fCuentas.obtenerCuentasBin();
		if(cuentas.size()>0) {
			if(fCuentas.crearCuentasOBJ(cuentas)) {
				System.out.println("Fichero de objetos creado");
			}
			else {
				System.out.println("Error al generar el fichero de objetos");
			}
		}
	}


	private static void ejercicio3() {
		// TODO Auto-generated method stub
		ejercicio2();
		System.out.println("Introduce el nº de cuenta");
		int codigo = t.nextInt(); t.nextLine();
		Cuenta c = fCuentas.obtenerCuentaBin(codigo);
		if(c!=null && !c.isCancelada()) {
			System.out.println("Tipo de movimient (I/R)");
			String tipo = t.nextLine();
			System.out.println("Cantidad:");
			float cantidad = t.nextFloat(); t.nextLine();
			if(tipo.equalsIgnoreCase("I") || tipo.equalsIgnoreCase("R")) {
				//Comprobar saldo
				if(c.getSaldo()<cantidad && tipo.equalsIgnoreCase("R")) {
					System.out.println("No hay saldo suficiente");
				}
				else {
					if(tipo.equalsIgnoreCase("R")) {					
						cantidad = cantidad * -1;
					}
					if(fCuentas.actualizarSaldo(c,cantidad)) {
						System.out.println("Saldo actualizado");
					}
					else {
						System.out.println("Error al acutalizar el saldo");
					}
				}
								
			}
			else {
				System.out.println("No has seleccionado correctamente el tipo..");
			}
			
		}
		else {
			System.out.println("La cuenta no existe o está cancelada");
		}
	}


	private static void ejercicio2() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> cuentas = fCuentas.obtenerCuentasBin();
		for(Cuenta c:cuentas) {
			c.mostrar();
		}
	}


	private static void ejercicio1() {
		// TODO Auto-generated method stub
		//Obtener las cuentas del fichero de texto
		ArrayList<Cuenta> cuentas = fCuentas.obtenerCuentasTxt();
		//Generar el fichero binario
		if(fCuentas.crearBianario(cuentas)) {
			System.out.println("Cuentas creadas");
		}
		else {
			System.out.println("Error al crear las cuentas");
		}
	}


}
