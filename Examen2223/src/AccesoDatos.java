import java.io.BufferedReader;
import java.io.EOFException;
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
	private String fTxt = "ventas.txt";
	private String fObj = "ventas.obj";

	public AccesoDatos() {

	}

	public ArrayList<Venta> obtenerVentasTxt() {
		// TODO Auto-generated method stub
		ArrayList<Venta> resultado = new ArrayList();

		BufferedReader f = null;
		try {
			f = new BufferedReader(new FileReader(fTxt));
			String linea;
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				Venta v = new Venta(Integer.parseInt(campos[0]), Integer.parseInt(campos[2]),
						Float.parseFloat(campos[3]));
				resultado.add(v);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public Venta obtenerVentaObj(Venta v) {
		// TODO Auto-generated method stub
		Venta resultado = null;

		ObjectInputStream f = null;
		try {
			f = new ObjectInputStream(new FileInputStream(fObj));
			while (true) {
				Venta vFich = (Venta) f.readObject();
				// Comprobar si es la buscada
				if (v.getIdProducto() == vFich.getIdProducto()) {
					return vFich;
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Áun no hay ventasOBJ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public boolean addVentaOBJ(Venta v) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		File f1 = new File(fObj);

		ObjectOutputStream f = null;

		try {
			if (f1.exists()) {
				f = new MiObjectOutputStream(new FileOutputStream(fObj,true));
			}
			else {
				f = new ObjectOutputStream(new FileOutputStream(fObj,true));
			}
			f.writeObject(v);
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

	public boolean modificarVentaOBJ(Venta vModificado) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		ObjectInputStream fO = null;
		ObjectOutputStream fT = null;
		
		try {
			fO = new ObjectInputStream(new FileInputStream(fObj));
			fT = new ObjectOutputStream(
					new FileOutputStream("ventas.tmp",false));
			while(true) {
				Venta vLeido = (Venta) fO.readObject();
				if(vLeido.getIdProducto()==vModificado.getIdProducto()) {
					fT.writeObject(vModificado);
				}
				else {
					fT.writeObject(vLeido);
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
			if(fT!=null) {
				try {
					fT.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//Borrar y renombrar
		File f1 = new File(fObj);
		if(f1.delete()) {
			File f2  = new File("ventas.tmp");
			if(!f2.renameTo(f1)) {
				System.out.println("Error al renombar ventas.tmp");
			}
			else {
				resultado = true;
			}
		}
		else {
			System.out.println("Error al borrar ventas.obj");
		}
		return resultado;
	}

	public ArrayList<Venta> obtenerVentasOBJ() {
		// TODO Auto-generated method stub
		ArrayList<Venta> resultado = new ArrayList();
		ObjectInputStream f = null;
		try {
			f = new ObjectInputStream(new FileInputStream(fObj));
			while (true) {
				Venta vFich = (Venta) f.readObject();
				resultado.add(vFich);
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Áun no hay ventasOBJ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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
