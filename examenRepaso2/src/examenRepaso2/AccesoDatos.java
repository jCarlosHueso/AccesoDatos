package examenRepaso2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AccesoDatos {
String ftxt = "ventas.txt";
String fobj = "ventas.obj";
	public ArrayList<ventas> obtenerVentas() {
		 ArrayList<ventas> resultado = new ArrayList<ventas>();
		 
		 BufferedReader f =null;
		 
		 
		 try {
			new BufferedReader(new FileReader(ftxt));
			
			String linea;
			while((linea=f.readLine())!=null) {
				
				String[] campos = linea.split(";");
				
				ventas v = new ventas();
				
				v.setIdProducto(Integer.parseInt(campos[0]));
				v.setCantidad(Integer.parseInt(campos[2]));
				v.setImporteRecaudado(Integer.parseInt(campos[3]));
				resultado.add(v);				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 return resultado;
	}
	public ventas obtenerVentasObj(ventas v) {
ventas resultado = null;
		ObjectInputStream f = null;
		
		try {
			f = new ObjectInputStream(new FileInputStream(fobj));
			while(true) {
				
				ventas vFich = (ventas) f.readObject();

				
				if(v.getIdProducto()==vFich.getIdProducto()) {
					return vFich;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return resultado;
	}
	public boolean addVenta(ventas ventas) {
boolean resultado  = false;

File f1 = new File(fobj);

ObjectOutputStream f = null;
try {
if(f1.exists()) {
	
		f = new MiObjectOutputStream(new FileOutputStream(fobj,true));
	
}else {
	
	f = new ObjectOutputStream(new FileOutputStream(fobj,true));
	
}

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


		return resultado;
	}
	public boolean modificarVenta(ventas vobj) {

		boolean resultado = false;
		ObjectInputStream fO = null;
		ObjectOutputStream fT = null;
		
		try {
			fO =new ObjectInputStream( new FileInputStream(fobj));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

}
