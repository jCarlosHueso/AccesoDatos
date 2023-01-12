package examenRepaso2;

import java.util.ArrayList;
import java.util.Scanner;


public class principal {
static Scanner t = new Scanner(System.in);
static AccesoDatos ad = new AccesoDatos();
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
				ejr1();
				break;
			case 2:
				break;
			
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			}	

		} while (opcion != 0);

	}
	private static void ejr1() {
		ArrayList<ventas> v = ad.obtenerVentas();
		
		for (ventas ventas : v) {
			ventas vobj = ad.obtenerVentasObj(ventas);
			if(vobj==null) {
			if(ad.addVenta(ventas)) {
				
				System.out.println("venta añadida");
				
			}else {
				System.out.println("venta no añadida");
			}
				
			}else{
				vobj.setCantidad(vobj.getCantidad()+ ventas.getCantidad());
				vobj.setImporteRecaudado(vobj.getImporteRecaudado()+ventas.getImporteRecaudado());
				if(ad.modificarVenta(vobj)) {
					
				}
				
			}
			mostrarVentasObj();
		}
	}
		
	}


