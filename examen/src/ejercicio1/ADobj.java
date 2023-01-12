package ejercicio1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ADobj {
	String nombreObj = "ventas.obj";
	String nombreTmp = "ventas.tmp";

	public ADobj() {
	}

	public boolean actualizarVenta(ventas ventas2) {
		boolean resultado = false;
		ObjectInputStream fO = null;
		ObjectOutputStream fTmp = null;

		try {
			fO = new ObjectInputStream(new FileInputStream(nombreObj));
			fTmp = new ObjectOutputStream(new FileOutputStream(nombreTmp, false));
			while (true) {
				ventas v = (ventas) fO.readObject();
				if (v.getIdProducto() != ventas2.getIdProducto()) {
					fTmp.writeObject(ventas2);
				} else {

					v.setCantidad(v.getCantidad() + ventas2.getCantidad());
					v.setImporte(v.getImporte() + ventas2.getImporte());
					fTmp.writeObject(v);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fO != null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fTmp != null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		File fOriginal = new File(nombreObj);
		if (fOriginal.delete()) {
			File fTemp = new File(nombreTmp);
			if (fTemp.renameTo(fOriginal)) {
				resultado = true;
			} else {
				System.out.println("Error al renombrar " + nombreTmp);
			}
		} else {
			System.out.println("Error al borrar " + nombreObj);
		}
		return resultado;
	}

	public boolean añadirVenta(ventas ventas) {

		boolean resultado = false;
		resultado = true;

		ObjectOutputStream f = null;

		try {
			File fichero = new File(nombreObj);
			if (fichero.exists()) {

				f = new MiObjectOutputStream(new FileOutputStream(nombreObj, true));

			} else {

				// fichero.createNewFile();

				f = new ObjectOutputStream(new FileOutputStream(nombreObj, true));
			}

			f.writeObject(ventas);
			resultado = true;

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

	public ventas obtenerventa(int idProducto) {
		ventas resultado = new ventas();

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream(nombreObj));
			while (true) {
				ventas v = (ventas) f.readObject();

				if (v.getIdProducto() == idProducto) {

					resultado.setIdProducto(idProducto);
					resultado.setCantidad(v.getCantidad());
					resultado.setImporte(v.getImporte());
				}
			}
		} catch (EOFException e) {

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
