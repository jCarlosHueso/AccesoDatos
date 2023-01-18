import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				int opcion;
				do {
					System.out.println("Introduce opción:");
					System.out.println("0-Salir");
					System.out.println("1-Ejer1");
					System.out.println("2-Ejer2");
					System.out.println("3-Ejer3");
					System.out.println("4-Ejer4");
					opcion = t.nextInt();
					t.nextLine();
					switch (opcion) {
					case 1:
						ejer1();
						break;
					case 2:
						ejer2();
						break;
					case 3:
						ejer3();
						break;
					case 4:
						ejer4();
						break;
					}
					
				} while (opcion != 0);
	}
	private static void ejer4() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Producto:");
		Producto p = ad.obtenerProducto(t.nextInt());
		if(p!=null) {
			Venta v = ad.obtenerVentaObj(p.getId());
			if(v!=null){
				Info i = new Info(p.getId(), 
						p.getStock(), v.getCantidadV(), 
						p.getNombre(), v.getImporteR());
				ad.marshal(i);
			}
			else {
				System.out.println("Error, no hay ventas");
			}
		}
		else {
			System.out.println("Error, no existe el producto");
		}
	}
	private static void ejer3() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Id:");
		Producto p = ad.obtenerProducto(t.nextInt());
		t.nextLine();
		if(p!=null) {
			System.out.println("Nuevo stock:");
			p.setStock(t.nextInt());t.nextLine();
			if(!ad.modificarProducto(p)) {
				System.out.println("Error al modificar");
			}
			else {
				mostrarProductos();
			}
		}
		else {
			System.out.println("Error no existe producto");
		}
	}
	private static void ejer2() {
		// TODO Auto-generated method stub
		ArrayList<Venta> ventas = ad.obtenerVentasOBJ();
		for(Venta v: ventas) {
			Producto p = new Producto();
			p.setId(v.getIdProducto());
			System.out.println("Nombre producto " + v.getIdProducto());
			p.setNombre(t.nextLine());
			System.out.println("Stock para " + v.getIdProducto());
			p.setStock(t.nextInt()); t.nextLine();
			if(ad.addBinario(p)) {
				System.out.println("Producto añadido:" + p.getId());
			}
			else {
				System.out.println("Error al añadir " + p.getId());
			}
			
			mostrarProductos();
		}
	}
	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> prods = ad.obtenerProductos();
		for(Producto p:prods) {
			p.mostrar();
		}
	}
	private static void ejer1() {
		// TODO Auto-generated method stub
		//Obtener ventas txt
		ArrayList<Venta> ventas = ad.obtenerVentasTxt();

		//Procesar ventas de txt
		for(Venta v: ventas) {
			//Comprobar si existe la venta en el fOBJ
			Venta vOBJ = ad.obtenerVentaObj(v.getIdProducto());
			if(vOBJ==null) {
				//No existe=> se añade al fOBJ
				if(ad.addVentaOBJ(v)) {
					System.out.println("Venta añadida para producto "+ v.getIdProducto());
				}
				else {
					System.out.println("Error al añadir venta del producto "+ v.getIdProducto());
				}
			}
			else {
				//Existe=> se modifica
				//Nueva cantidad
				vOBJ.setCantidadV(vOBJ.getCantidadV()+v.getCantidadV());
				//Nuevo Importe 
				vOBJ.setImporteR(vOBJ.getImporteR()+v.getImporteR());
				if(ad.modificarVentaOBJ(vOBJ)) {
					System.out.println("Venta modificada para producto "+ v.getIdProducto());
				}
				else {
					System.out.println("Error al modificar venta del producto "+ v.getIdProducto());
				}
			}
		}
		//Mostrar ventas obj
		mostrarVentasOBJ();
	}
	private static void mostrarVentasOBJ() {
		// TODO Auto-generated method stub
		ArrayList<Venta> ventas = ad.obtenerVentasOBJ();
		for(Venta v:ventas) {
			v.mostrar();
		}
	}

}
