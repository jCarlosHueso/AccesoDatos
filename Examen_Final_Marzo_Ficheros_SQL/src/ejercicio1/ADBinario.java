package ejercicio1;

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


public class ADBinario {
	String nombreF  =  "presupuestado.bin";
	String nombreTmp = "presupuestado.tmp";
		
	public ADBinario() {
		
	}

	public boolean guardardatos(Fichero fichero) {

		
	boolean resultado = false;
		
		DataOutputStream f = null;
		
		try {
			f= new DataOutputStream(new FileOutputStream(nombreF, true));
			
			
			
			//escribir el codigodel servicio
			StringBuffer texto3 = new StringBuffer(fichero.getServicio());
			texto3.setLength(3);
			f.writeChars(texto3.toString());
			
			
			f.writeLong(fichero.getHoras());
			
			f.writeFloat(fichero.getTotal());
			
			f.writeBoolean(fichero.isBaja());
			
			
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

	public ArrayList<Fichero> obtenerInfoFichero() {
		ArrayList<Fichero> resultado = new ArrayList<>();
		
		DataInputStream f = null;
		try {
			f=new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				Fichero fichero = new Fichero();
				
				fichero.setServicio("");
				for(int i=0;i<3;i++) {
					fichero.setServicio(fichero.getServicio()+f.readChar());
				}
				
				fichero.setServicio(fichero.getServicio().trim());
				
				fichero.setHoras(f.readLong());
				
				fichero.setTotal(f.readFloat());
				
				fichero.setBaja(f.readBoolean());
				resultado.add(fichero);
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
	
	public boolean borrarServicio(String codigoS) {
		boolean resultado=false;
		
		//Declaramos ficheros
				DataInputStream fO = null;
				DataOutputStream FTmp = null;
						
				try {
					//Abrimos ficheros
					fO = new DataInputStream(new FileInputStream(nombreF));
					FTmp = new DataOutputStream(new FileOutputStream(nombreTmp,false));
					
					while(true) {
						
						String codSer=("");
						for(int i=0;i<3;i++) {
							codSer=codSer+fO.readChar();
						} 
						
						
						if(codSer.equals(codigoS)) {
							//escribir el boolean a false	
							StringBuffer texto3 = new StringBuffer(codigoS);
							texto3.setLength(3);
							FTmp.writeChars(texto3.toString());
							
							FTmp.writeLong(fO.readLong());
							
							FTmp.writeFloat(fO.readFloat());;
							
							FTmp.writeBoolean(true);
							fO.readBoolean();
						}
						else {
							//Hay que leer y escribir el resto de datos
							
							StringBuffer texto3 = new StringBuffer(codSer);
							
							texto3.setLength(3);
							
							FTmp.writeChars(texto3.toString());
							
							
							FTmp.writeLong(fO.readLong());
							
							FTmp.writeFloat(fO.readFloat());;
							
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
}
