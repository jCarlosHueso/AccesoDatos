package ejercicio1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ADtxt {
	String nombreTxt="ventas.txt";
public ADtxt() {
	
}

public ArrayList<ventas> obtenerVentas() {
	
	ArrayList<ventas> resultado = new ArrayList<ventas>();	


BufferedReader f = null;


try {
	f = new BufferedReader(new FileReader(nombreTxt));
	String linea ="";
	
	while((linea=f.readLine())!=null) {
		
		String datos[] = linea.split(";");
		ventas v2 = new ventas();
		v2.setIdProducto(Integer.parseInt(datos[0]));
		v2.setCantidad(Integer.parseInt(datos[2]));
		v2.setImporte(Double.parseDouble(datos[3]));
		
		resultado.add(v2);
		
	}
	
	
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	
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
