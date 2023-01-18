package FicherosBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import FicherosTexto.ADTexto;
import FicherosTexto.Artista;

public class ADBinario {
	String nombreF = "albumes.bin";
	String nombreTmp = "albumes.tmp";
	
	ADTexto fArtista = new ADTexto();
	
	public ADBinario() {
		
	}

	public ArrayList<Album> obtenerAlbumes() {
		ArrayList<Album> resultado = new ArrayList<>();
		
		
		DataInputStream f = null;
		
		try {
			f = new DataInputStream(new FileInputStream(nombreF));
			
			//Bucle infinito => Parará cuando se produzca la
			//excepción de fin de fichero EOF_EXCEPTION
			while(true) {
				Album a= new Album();
				//Leemos dato a dato
				//Id
				a.setId(f.readInt());
				
				//Título - 50 caracteres
				String titulo="";
				for(int i=0;i<50;i++) {
					titulo+=f.readChar();
				}
				//Limpiamos el título para quitar espacions en blanco
				a.setTitulo(titulo.trim());
				
				//FEcha Publicación
				a.setFechaP(new Date(f.readLong()));
				
				//Artista
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=f.readChar();
				}
				Artista artista = fArtista.obtenerArtista(nombre.trim());
				a.setArtista(artista);
				
				//Activo
				a.setActivo(f.readBoolean());
				
				//Añadir el álbum al resultado
				resultado.add(a);
				
			}
		} 
		
		catch (EOFException e) {
			// No hacemos nada porque es lo mejor que nos puede pasar
		}
		catch (FileNotFoundException e) {
			
			System.out.println("Aún no hay álbumes");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public Album obtenerAlbum(String titulo, String nombreA) {
		Album resultado = null;
		
		DataInputStream f = null;
		try {
			f= new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				Album a = new Album();
				//Leemos el id
				a.setId(f.readInt());
				String tit="";
				for(int i=0;i<50;i++) {
					tit+=f.readChar();
				}
				a.setTitulo(tit.trim());
				a.setFechaP(new Date(f.readLong()));
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=f.readChar();
				}
				Artista art = fArtista.obtenerArtista(nombre.trim());
				a.setArtista(art);
				a.setActivo(f.readBoolean());
				//Si el álbum es el buscado terminamos
				if(titulo.equalsIgnoreCase(a.getTitulo()) &&
				   nombreA.equalsIgnoreCase(a.getArtista().getNombre())) {
					//Álbum encontrado					
					return a;
				}
			}
		} 
		catch (EOFException e) {
		}
		catch (FileNotFoundException e) {
			System.out.println("Aún no hay álbumes");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public int obtenerUltimoId() {
		int resultado = 0;
		
		DataInputStream f = null;
		try {
			f= new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				
				//Leemos el id				
				resultado=f.readInt();
				
				//Leemos título
				for(int i=0;i<50;i++) {
					f.readChar();
				}
				//Leemos la fecha
				f.readLong();
				
				//leemos el nombre
				for(int i=0;i<50;i++) {
					f.readChar();
				}
				//Leemos activo
				f.readBoolean();
				
			}
		} 
		catch (EOFException e) {
		}
		catch (FileNotFoundException e) {
			System.out.println("Aún no hay álbumes");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;

	}

	public boolean crearAlbum(Album al) {
		boolean resultado = false;
		
		DataOutputStream f = null;
		
		try {
			f= new DataOutputStream(new FileOutputStream(nombreF, true));
			//Escribo el álbum
			f.writeInt(al.getId());
			
			//Hacemos que el título tenga 50 caracteres
			StringBuffer texto50 = new StringBuffer(al.getTitulo());
			texto50.setLength(50);
			f.writeChars(texto50.toString());
			
			f.writeLong(al.getFechaP().getTime());
			
			texto50 = new StringBuffer(al.getArtista().getNombre());
			texto50.setLength(50);
			f.writeChars(texto50.toString());
			
			f.writeBoolean(al.isActivo());
			resultado = true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public Album obtenerAlbum(int id) {
		Album resultado=null;
		DataInputStream f = null;
		try {
			f= new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				Album a = new Album();
				//Leemos el id
				a.setId(f.readInt());
				String tit="";
				for(int i=0;i<50;i++) {
					tit+=f.readChar();
				}
				a.setTitulo(tit.trim());
				a.setFechaP(new Date(f.readLong()));
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=f.readChar();
				}
				Artista art = fArtista.obtenerArtista(nombre.trim());
				a.setArtista(art);
				a.setActivo(f.readBoolean());
				//Si el álbum es el buscado terminamos
				if(a.getId()==id) {
					//Álbum encontrado					
					return a;
				}
			}
		} 
		catch (EOFException e) {
		}
		catch (FileNotFoundException e) {
			System.out.println("Aún no hay álbumes");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		return resultado;
	}

	public boolean modificarArtista(Album al, Artista ar) {
		boolean resultado=false;
		
		//Declaramos ficheros
		DataInputStream fO = null;
		DataOutputStream FTmp = null;
				
		try {
			//Abrimos ficheros
			fO = new DataInputStream(new FileInputStream(nombreF));
			FTmp = new DataOutputStream(new FileOutputStream(nombreTmp,false));
			
			while(true) {
				//Leer id
				int id = fO.readInt();
				//Escribir id
				FTmp.writeInt(id);
				//Leer y escribir el título
				for(int i=0;i<50;i++) {
					FTmp.writeChar(fO.readChar());
				}
				//Leer y escribir la fecha publicación
				FTmp.writeLong(fO.readLong());
				
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=fO.readChar();
				}
				//Comprobamos si es el que hay que modificar
				if(id==al.getId()) {
					//Escribo el nombre del nuevo artista CON 50 CARACTERES!!!
					StringBuffer texto = new StringBuffer(ar.getNombre());
					texto.setLength(50);
					FTmp.writeChars(texto.toString());
				}
				else {
					FTmp.writeChars(nombre);
				}
				//Leer y escribir activo
				FTmp.writeBoolean(fO.readBoolean());
			}
			
		} 
		catch (EOFException e) {
		}
		catch (FileNotFoundException e) {
			System.out.println("Aún no hay datos");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fO!=null) {
				try {
					fO.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(FTmp!=null) {
				try {
					FTmp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		File fOriginal = new File(nombreF);
		if(fOriginal.delete()) {
			File fTemporal = new File(nombreTmp);
			if(fTemporal.renameTo(fOriginal)) {
				resultado=true;
			}
			else {
				System.out.println("Error al renombrar el fichero temporal");
			}
		}
		else {
			System.out.println("Error al borrar el fichero original");
		}
		return resultado;
	}

	public boolean borrarAlbum(Album al) {
		boolean resultado=false;
		
		//Declaramos ficheros
		DataInputStream fO = null;
		DataOutputStream FTmp = null;
				
		try {
			//Abrimos ficheros
			fO = new DataInputStream(new FileInputStream(nombreF));
			FTmp = new DataOutputStream(new FileOutputStream(nombreTmp,false));
			
			while(true) {
				//Leer id
				int id = fO.readInt();
				if(id==al.getId()) {
					//Tengo que borrar. Leo pero no escribo
					for(int i=0;i<50;i++) {
						fO.readChar();
					}
					fO.readLong();
					for(int i=0;i<50;i++) {
						fO.readChar();
					}
					fO.readBoolean();
				}
				else {
					//Hay que leer y escribir el resto de datos
					FTmp.writeInt(id);
					for(int i=0;i<50;i++) {
						FTmp.writeChar(fO.readChar());
					}
					FTmp.writeLong(fO.readLong());;
					for(int i=0;i<50;i++) {
						FTmp.writeChar(fO.readChar());
					}
					FTmp.writeBoolean(fO.readBoolean());
				}
			}
			
		} 
		catch (EOFException e) {
		}
		catch (FileNotFoundException e) {
			System.out.println("Aún no hay datos");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fO!=null) {
				try {
					fO.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(FTmp!=null) {
				try {
					FTmp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		File fOriginal = new File(nombreF);
		if(fOriginal.delete()) {
			File fTemporal = new File(nombreTmp);
			if(fTemporal.renameTo(fOriginal)) {
				resultado=true;
			}
			else {
				System.out.println("Error al renombrar el fichero temporal");
			}
		}
		else {
			System.out.println("Error al borrar el fichero original");
		}
		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes(Artista a) {
		ArrayList<Album> resultado = new ArrayList<>();
		
		DataInputStream f = null;
		try {
			f=new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				Album al = new Album();
				al.setId(f.readInt());
				al.setTitulo("");
				for(int i=0;i<50;i++) {
					al.setTitulo(al.getTitulo()+f.readChar());
				}
				al.setTitulo(al.getTitulo().trim());
				
				al.setFechaP(new Date(f.readLong()));
				
				String nombreA = "";
				for(int i=0;i<50;i++) {
					nombreA+=f.readChar();
				}
				
				al.setArtista(fArtista.obtenerArtista(nombreA.trim()));
				al.setActivo(f.readBoolean());
				//Comprobamos si el álbum es del artista elegido
				//!!!EXPLICAR POR QUÉ NO FUCNIONA SI COMPARO OBJETOS
				if(al.getArtista().getNombre().equalsIgnoreCase(a.getNombre())) {
					resultado.add(al);
				}
				
			}
		} 
		
		catch (EOFException e) {
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}

	public boolean desactivarAlbum(Album al) {
		boolean resultado=false;
		
		//Declaramos ficheros
		DataInputStream fO = null;
		DataOutputStream FTmp = null;
				
		try {
			//Abrimos ficheros
			fO = new DataInputStream(new FileInputStream(nombreF));
			FTmp = new DataOutputStream(new FileOutputStream(nombreTmp,false));
			
			while(true) {
				//Leer id
				int id = fO.readInt();
				//Escribir id
				FTmp.writeInt(id);
				//Leer y escribir el título
				for(int i=0;i<50;i++) {
					FTmp.writeChar(fO.readChar());
				}
				//Leer y escribir la fecha publicación
				FTmp.writeLong(fO.readLong());				
				//Artista
				for(int i=0;i<50;i++) {
					FTmp.writeChar(fO.readChar());
				}
								
				boolean activo = fO.readBoolean();
				//Comprobamos si es el que hay que desactivar
				if(id==al.getId()) {					
					FTmp.writeBoolean(false);
				}
				else {
					//Leer y escribir activo
					FTmp.writeBoolean(activo);
				}
				
			}
			
		} 
		catch (EOFException e) {
		}
		catch (FileNotFoundException e) {
			System.out.println("Aún no hay datos");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fO!=null) {
				try {
					fO.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(FTmp!=null) {
				try {
					FTmp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		File fOriginal = new File(nombreF);
		if(fOriginal.delete()) {
			File fTemporal = new File(nombreTmp);
			if(fTemporal.renameTo(fOriginal)) {
				resultado=true;
			}
			else {
				System.out.println("Error al renombrar el fichero temporal");
			}
		}
		else {
			System.out.println("Error al borrar el fichero original");
		}
		return resultado;
	}
	
	
	
}
