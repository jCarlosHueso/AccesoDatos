package claseFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	//Definimos el formato con el que vamos 
	//a pintar/pedir fechas
	static SimpleDateFormat formato = 
			new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar ruta actual");
			System.out.println("2-Info Archivo/Carpeta");
			System.out.println("3-Mostrar Carpeta");
			System.out.println("4-Crear Carpeta");
			System.out.println("5-Crear fichero");
			System.out.println("6-Renombrar fichero");
			System.out.println("7-MostrarTodo");
			System.out.println("8-Mostrar el tamaño(real) de una carpeta");
			opcion = t.nextInt(); t.nextLine();
			switch(opcion) {
			case 1:
				mostrarRutaCarpetaActual();
				break;
			case 2:
				mostrarInfo();
				break;
			case 3:
				mostrarCarpeta();
				break;
			case 4:
				crearCarpeta();
				break;
			case 5:
				crearFichero();
				break;
			case 6:
				renombrarFichero();
				break;
			case 7:
				mostrarTodo();
				break;
			}
		}while(opcion!=0);
		
	}

	private static void mostrarTodo() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre de una carpeta");
		String carpeta = t.nextLine();
		
		File f = new File(carpeta);
		if(f.exists() && f.isDirectory()) {
			mostrarCarpeta(f,0);
		}
	}

	private static void mostrarCarpeta(File f, int nivel) {
		// TODO Auto-generated method stub
		
		//Mostramos el nombre de la carpeta actual
		for(int i=0; i<nivel;i++) {
			System.out.print("\t");
		}
		System.out.println("-"+f.getName());
		//Obtenemos el contenido de la carpeta
		File[] contenido = f.listFiles();
		
		for(int i=0;i<contenido.length;i++) {
			if(contenido[i].isDirectory()) {
				mostrarCarpeta(contenido[i],nivel+1);
			}
			else {
				for(int j=0; j<nivel+1;j++) {
					System.out.print("\t");
				}
				System.out.println("-"+contenido[i].getName());
			}
		}
	}

	private static void renombrarFichero() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre del fichero a renombrar");
		String original = t.nextLine();
		
		File o = new File(original);
		//Comprobams que existe
		if(o.exists()) {
			System.out.println("Introduce el nuevo nombre");
			String nuevo = t.nextLine();
			
			File n = new File(nuevo);
			//Comprobamos que no existe
			if(!n.exists()) {
				if(o.renameTo(n)) {
					System.out.println("Fichero renombrado");
				}
				else {
					System.out.println("Error: No se puede renombrar");
				}
			}
			else {
				System.out.println("Error, ya existe un fichero " + nuevo);
			}
		}
		else {
			System.out.println("Error, el fichero no existe");
		}
	}

	private static void crearFichero() {
		// TODO Auto-generated method stub
		System.out.println("Introduce nombre de fichero");
		String nombre = t.nextLine();
		
		//Creamos el objeto File
		File f = new File(nombre);
		//Comprobamos que no existe
		if(!f.exists()) {
			//Creamos el fichero
			try {
				
				if(f.createNewFile()) {
					System.out.println("Fichero creado correctamente");
				}
				else {
					System.out.println("Error al crear el fichero");
				}
			} 
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Error: El fichero ya existe");
		}
	}

	private static void crearCarpeta() {
		// TODO Auto-generated method stub
		System.out.println("Introduce ruta de carpeta");
		String ruta = t.nextLine();
		
		File f = new File(ruta);
		if(!f.exists()) {
			if(f.mkdir()) {
				System.out.println("carpeta creada con exito");
			}
			else {
				System.out.println("No se ha podido crear la carpeta");
			}
		}
		else {
			System.out.println("La carpeta ya existe");
		}
	}

	private static void mostrarCarpeta() {
		// TODO Auto-generated method stub
		System.out.println("Introduce ruta de carpeta");
		String ruta = t.nextLine();
		
		File f = new File(ruta);
		if(f.exists() && f.isDirectory()) {
			//Obtenemos el contenido de la carpeta
			File[] contenido = f.listFiles();
			
			for(int i=0;i<contenido.length;i++) {
				String tipo;
				if(contenido[i].isDirectory()) {
					tipo = "d";
				}
				else {
					tipo = "f";
				}				
				String permisos;
				if(contenido[i].canRead()) {
					permisos = "r";
				}
				else {
					permisos = "-";
				}
				if(contenido[i].canWrite()) {
					permisos += "w";
				}
				else {
					permisos += "-";
				}
				if(contenido[i].canExecute()) {
					permisos += "x";
				}
				else {
					permisos += "-";
				}
				
				System.out.println(
						tipo + "\t" + permisos + "\t"+
						contenido[i].getName());
			}
		}
		else {
			System.out.println("La ruta no existe o no es una carpeta");
		}
	}

	private static void mostrarInfo() {
		// TODO Auto-generated method stub
		
		//Pedir ruta
		System.out.println("Introdue una ruta de archivo o carpeta");
		String ruta = t.nextLine();
		
		//Crear objeto File a la ruta
		File f = new File(ruta);
		
		//Comprobar si existe
		if(f.exists()) {
			//Comprobar si es archivo o carpeta
			if(f.isDirectory()) {
				System.out.println("La ruta corresponde a una carpeta");
			}
			else {
				System.out.println("La ruta corresponde a una fichero");
			}
			//Fecha de modificación
			//Creamos un objeto Date para trabajar con fechas
			//para representar la fecha de modicación
			Date fechaM = new Date(f.lastModified());
			
			System.out.println("Fecha de modificación:"+ 
					formato.format(fechaM) );
			
			//Tamaño
			System.out.println("Tamaño:"+f.length());
		}
		else{
			System.out.println("La ruta no existe");
		}
	}

	private static void mostrarRutaCarpetaActual() {
		// TODO Auto-generated method stub
		
		//Creamos un objeto File a la carpeta actual
		File f = new File(".");
		
		//Mostrar la ruta absoluta de la carpeta .
		System.out.println(f.getAbsolutePath());
	}

}
