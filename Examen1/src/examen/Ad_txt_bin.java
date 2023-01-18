package examen;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Ad_txt_bin {
	private String nombreFB="cuentas.bin";
	private String nombreFT="cuentas.txt";
	private String nombreFO="cuentas.obj";
	
	public Ad_txt_bin() {
		
		
	}

	public ArrayList<Cuenta> obtenerCuentasTxt() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> resultado = new ArrayList<>();
		
		BufferedReader fichero = null;
		try {
			fichero = new BufferedReader(new FileReader(nombreFT));
			String linea;
			while((linea=fichero.readLine())!=null) {
				String[] campos = linea.split(";");
				Cuenta c = new Cuenta(Integer.parseInt(campos[0]), campos[1], 
						              campos[2], Float.parseFloat(campos[3]),
						              false);
				resultado.add(c);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No existe el fichero de cuentas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean crearBianario(ArrayList<Cuenta> cuentas) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		
		//borramos  el fichero si existe
		File fich = new File(nombreFB);
		if(fich.exists()) {
			fich.delete();
		}
		
		try {
			f= new RandomAccessFile(nombreFB, "rw");
			for(Cuenta c:cuentas) {
				f.writeInt(c.getCodigo());
				
				StringBuffer texto = new StringBuffer(c.getApellidos());
				texto.setLength(20);
				f.writeChars(texto.toString());
				
				texto = new StringBuffer(c.getNombre());
				texto.setLength(10);
				f.writeChars(texto.toString());
				
				f.writeFloat(c.getSaldo());
				
				f.writeBoolean(c.isCancelada());
			}
			resultado = true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public ArrayList<Cuenta> obtenerCuentasBin() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> resultado = new ArrayList<>();
		
		DataInputStream f = null;
		
		try {
			f= new DataInputStream(new FileInputStream(nombreFB));
			while(true) {
				Cuenta c = new Cuenta();
				c.setCodigo(f.readInt());
				
				c.setApellidos("");
				for(int i=0;i<20;i++) {
					c.setApellidos(c.getApellidos()+f.readChar());
				}
				c.setApellidos(c.getApellidos().trim());
				
				c.setNombre("");
				for(int i=0;i<10;i++) {
					c.setNombre(c.getNombre()+f.readChar());
				}
				c.setNombre(c.getNombre().trim());
				
				c.setSaldo(f.readFloat());
				
				c.setCancelada(f.readBoolean());
				
				resultado.add(c);
				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay cuentas");
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

	public Cuenta obtenerCuentaBin(int codigo) {
		// TODO Auto-generated method stub
		Cuenta resultado = null;
		
		RandomAccessFile f = null;
		try {
			f= new RandomAccessFile(nombreFB, "r");
			while(true) {				
				//Leemos el código
				int codigoF = f.readInt();
				
				//Comprobar si es el buscado
				if(codigoF==codigo) {
					resultado = new Cuenta();
					resultado.setCodigo(codigoF);
					//Leer Apellidos
					resultado.setApellidos("");
					for(int i=0; i<20;i++) {
						resultado.setApellidos(resultado.getApellidos()+
								f.readChar());
					}
					resultado.setApellidos(resultado.getApellidos().trim());
					//Leer Nombre
					resultado.setNombre("");
					for(int i=0; i<10;i++) {
						resultado.setNombre(resultado.getNombre()+
								f.readChar());
					}
					resultado.setNombre(resultado.getNombre().trim());
					
					//Leer saldo
					resultado.setSaldo(f.readFloat());
					//Leer cancelada
					resultado.setCancelada(f.readBoolean());
					return resultado;
					
				}
				else {
					//Desplazomos el apuntado al siguiente código
					//Desplazamos 65B=> 40(ape)+20(nombre)+4(saldo)+1(cancelado)
					f.seek(f.getFilePointer()+65);
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public boolean actualizarSaldo(Cuenta c, float cantidad) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		
		RandomAccessFile f= null;
		try {
			f = new RandomAccessFile(nombreFB, "rw");
			while(true) {				
				int codigo = f.readInt();
				if(codigo==c.getCodigo()) {
					//Situarnos justo delante del saldo
					f.seek(f.getFilePointer()+60);
					//Escribimos el nuevo saldo
					f.writeFloat(c.getSaldo()+cantidad);
					return true;
				}
				else {
					f.seek(f.getFilePointer()+65);
				}				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
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

	public boolean crearCuentasOBJ(ArrayList<Cuenta> cuentas) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		ObjectOutputStream f = null;
		try {
			f = new ObjectOutputStream(new FileOutputStream(nombreFO,false));
			for(Cuenta c:cuentas) {
				f.writeObject(c);
			}
			resultado=true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public ArrayList<Cuenta> obtenerCuentasObj() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> resultado = new ArrayList<>();
		
		ObjectInputStream f = null;
		try {
			f= new ObjectInputStream(new FileInputStream(nombreFO));
			while(true) {
				Cuenta c = (Cuenta) f.readObject();
				resultado.add(c);
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	public boolean cancelarCuenta(int codigo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		ObjectInputStream fO = null;
		ObjectOutputStream fTmp = null;
		
		try {
			fO = new ObjectInputStream(new FileInputStream(nombreFO));
			fTmp = new ObjectOutputStream(new FileOutputStream("cuentas.tmp"));
			while(true) {
				Cuenta c = (Cuenta) fO.readObject();
				if(c.getCodigo()==codigo && !c.isCancelada()) {
					c.setCancelada(true);
					resultado = true;
				}
				fTmp.writeObject(c);
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fO!=null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fTmp!=null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		File ficheroO = new File(nombreFO);
		if(ficheroO.delete()) {
			File ficheroTmp = new File("cuentas.tmp");
			if(!ficheroTmp.renameTo(ficheroO)) {
				resultado = false;
			}
		}
		else {
			resultado = false;
		}
		return resultado;
	}

	public Movimientos hacerUnmarshal(File f) {
		// TODO Auto-generated method stub
		Movimientos resultado = null;
		
		try {
			Unmarshaller um = JAXBContext.newInstance(Movimientos.class)
					                     .createUnmarshaller();
			resultado =  (Movimientos) um.unmarshal(f);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean hacerMarshal(File f, Movimientos movs) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			Marshaller m = JAXBContext.newInstance(Movimientos.class)
					                  .createMarshaller();
			m.marshal(movs, f);
			resultado=true;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	
}
