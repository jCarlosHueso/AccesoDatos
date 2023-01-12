package FicherosTexto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ADTexto {
	
	String nombreF =  "artistas.txt";
	
	

	public ADTexto() {
		
	}



	public boolean crearArtista(Artista a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		//Declaramos el fichero para abrirlo en modo escritura
		BufferedWriter f = null;
				
		try {
			//Abrimos fichero
			//Pasamos el nombre del fichero y el parámetro append con valor true
			//El parámetro append indica si añadimos la línea al fichero 
			//manteniendo los datos anteriores o si creamos de nuevo el fichero
			f = new BufferedWriter(new FileWriter(nombreF, true));
			//Escribimos el artista
			f.write(a.getNombre()+";"+
					a.getGenero()+";"+
					a.getFechaUA()+";"+
					a.getLanzamiento()+";"+
					a.isSeguir()+"\n");
			resultado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

}
