package Examen;

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
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				//TERMINAO
				System.out.println("1-Crear producto");
				System.out.println("2-Crear Factura");
				System.out.println("3-Anular Factura");
				System.out.println("4-Informe de facturacion");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearProducto();
					break;
				case 2:
					crearFactura();
					break;
				case 3:
					anularFactura();
					break;
				case 4:
					crearInformeProducto();
					break;

				}
			} while (opcion != 0);
			// Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error");
		}
	}

	private static void anularFactura() {

		ArrayList<Facturas>facturas=ad.obtenerFacturas();
		float totalF=0;
		for (Facturas f : facturas) {
			if(f.getFacturaAnulacion()==0) {
				System.out.println(f.toString());
				//total por producto
				
				ArrayList<Detalles>detalles=f.getDetalle();
				for (Detalles d : detalles) {
					System.out.println("Total de: "+d.getProducto().getCodigo() + " : "+(d.getPrecioUnidad()*d.getCantidad()));
					totalF=totalF+(d.getPrecioUnidad()*d.getCantidad());
					
				}
			}else {
				//factura anulada
			}System.out.println("total de la factura: " + totalF);
		}
		System.out.println("introduce un numero de factura");
		int num = t.nextInt();t.nextLine();
		
		Facturas fNegativa = new Facturas();
		
		Facturas facturaN=ad.obtenerFactura(num);
		
		fNegativa.setNumero((ad.obtenerUltimoNumero()));
		fNegativa.setFecha(new Date());
		fNegativa.setCliente(facturaN.getCliente());
		fNegativa.setFacturaAnulacion(0);
		
		ArrayList<Detalles>detalles=facturaN.getDetalle();
		ArrayList<Detalles>detallesN=new ArrayList<>();
		for (Detalles d : detalles) {
			Detalles det = new Detalles(d.getProducto(),(d.getCantidad()*-1),d.getPrecioUnidad());
			detallesN.add(det);
		}
		fNegativa.setDetalle(detallesN);
		
		if(ad.crearFactura(fNegativa)) {
			System.out.println("factura creada");
		}else {
			System.out.println("error al crear la factura");
		}
		
		

	}

	private static void crearInformeProducto() {


		ad.crearInformeFactura();
		
		
	}
	

	private static void crearFactura() {

		Facturas factura = new Facturas();
		// numero de fecha era ultimo mas uno
		
		
		
		
		factura.setNumero(ad.obtenerUltimoNumero());
		
		
		
		// la fecha es la hora actual 
		
		factura.setFecha(new Date());
		//el dni se pide por teclado
		System.out.println("Introduce el dni del cliente:");
		factura.setCliente(t.nextLine());
		//factura anulacion es por defecto 0
		factura.setFacturaAnulacion(0);
		
		// se añaden los detalles (todos los productos) 
		ArrayList<Detalles> detalle = new ArrayList<>();
		detalle=addProductos();
		factura.setDetalle(detalle);
		
		//se crea la factura
		if(ad.crearFactura(factura)) {
			System.out.println("factura creada con exito");
			
	if(actualizarStock(factura)) {
		System.out.println("stock actualizado");
	}
		}else {
			System.out.println("Error al crear factura");
		}
		
		
		
	}

	private static boolean actualizarStock(Facturas factura) {
boolean resultado=false;
		ArrayList<Detalles> d = factura.getDetalle();
		for (Detalles detalles : d) {
			//obtener el producto del detalle, restarle el stock de la cantidad del detalle y actualizar el producto
		}


		return resultado;
	}

	private static ArrayList<Detalles> addProductos() {
		
		ArrayList<Detalles> resultado = new ArrayList<>();
		
		//optar a eelegir otro producto
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Añadir un producto");
			opcion = t.nextInt();
			t.nextLine();
			
			switch (opcion) {
			case 1:
				Detalles d = crearUnDetalle(); 
				
			resultado.add(d);
			
				break;

			}
			
		} while (opcion != 0);
		
		return resultado;
	}

	private static Detalles crearUnDetalle() {
		
		Detalles resultado = new Detalles();
		
		//mostrar los productos
		ArrayList<Productos> productos = ad.obtenerProductos();
		
		for (Productos prod : productos) {
			System.out.println(prod.toString());
		}
		
		//pedir el codigo
		System.out.println("Introduce el codigo del producto a añadir");
		String codigo = t.nextLine();
		Productos p = ad.obtenerProducto(codigo);
		//chequeito de que existe
		
		if(p!=null) {
			
			resultado.setProducto(p);
		}else {
			System.out.println("error ese producto no existe");
		}
		
		//tomar presio
		resultado.setPrecioUnidad(p.getPrecio());
		
		//checkear que hay stock suficiente
		System.out.println("introduce cantidad");
		
		int cantidad = t.nextInt();
		if(cantidad<p.getStock()) {
			resultado.setCantidad(cantidad);
			
		}else {
			System.out.println("Error, no hay tanta cantidad en el stockaje");
		}

		return resultado;
	}

	private static void crearProducto() {
System.out.println("introduce el codigo del producto a crear");
String codigoP = t.nextLine();

Productos p = ad.obtenerProducto(codigoP);

if(p==null){
	
	 p = new Productos();
	
	//no hay dese producto
	p.setCodigo(codigoP);
	System.out.println("introduce el nombre del producto a crear");
	String nombreP = t.nextLine();
	p.setNombre(nombreP);
	
	System.out.println("introduce el precio del producto a crear");
	float precio = t.nextFloat();
	p.setPrecio(precio);
	
	System.out.println("introduce el Stock del producto a crear");
	int stock = t.nextInt();
	p.setStock(stock);
	
	//System.out.println(p.toString());
	
	if(ad.crearProducto(p)) {
		System.out.println("Producto creado");
	}else {
		System.out.println("Error al crear el poducto");
	}
	
}else {
	System.out.println("Error, ese codigo ya está siendo usado por otro producto");
}
		
		
	}
}
