package FicherosTexto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PrincipalArtistas {
	static Scanner t = new Scanner(System.in);
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	static ADTexto fArtistas = new ADTexto();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");	
			System.out.println("1-Crear Artista");
			System.out.println("2-Mostrar Artistas");
			System.out.println("3-Modificar Artista");
			System.out.println("4-Borrar Artista");
			System.out.println("5-Mostrar algunos Artista");
			opcion = t.nextInt(); t.nextLine();
			switch(opcion) {
			case 1:
				crearArtista();
				break;
			}
		}while(opcion!=0);
	}

	private static void crearArtista() {
		// TODO Auto-generated method stub
		Artista a = new Artista();
		System.out.println("Nombre Artístico");
		a.setNombre(t.nextLine());
		System.out.println("Género");
		a.setGenero(t.nextLine());
		System.out.println("Año de Lanzamiento");
		a.setLanzamiento(t.nextInt());t.nextLine();
		//Pedimos fecha de último álbum correcta
		boolean fechaCorrecta = false;
		do {
			try {
				System.out.println("Fecha Último Álbum");	
				a.setFechaUA(formato.parse(t.nextLine()).getTime());
				fechaCorrecta= true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Error: Fecha Incorrecta");
			}
		}while(!fechaCorrecta);
		
		if(fArtistas.crearArtista(a)) {
			System.out.println("Artista creado con éxito");
		}
		else {
			System.out.println("Error al crear el artista");
		}
	}
		

}
