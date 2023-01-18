package examen;

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
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class AD_Examen2 {

	private String nombreFTxt = "productosSistemaAntiguo.txt";
	private String nombreFBin = "productos.bin";
	private String nombreFObj = "ventas.obj";

	public AD_Examen2() {

	}

	public ArrayList<Producto> obtenerProductosTxt() {
		ArrayList<Producto> resultado = new ArrayList<>();

		BufferedReader f = null;

		try {
			f = new BufferedReader(new FileReader(nombreFTxt));
			String[] campos;
			String linea;

			while ((linea = f.readLine()) != null) {
				campos = linea.split(";");

				if (!Boolean.parseBoolean(campos[3])) {
					resultado.add(new Producto(Integer.parseInt(campos[0]), Float.parseFloat(campos[1]),
							Integer.parseInt(campos[2]), campos[4]));
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no se ha encontrado.");
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

	public boolean generarBin(ArrayList<Producto> productos) {
		boolean resultado = false;

		File fichero = new File(nombreFBin);

		if (fichero.exists()) {
			fichero.delete();
		}

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "rw");

			for (Producto p : productos) {
				f.writeInt(p.getCodigo());

				f.writeFloat(p.getPrecio());

				f.writeInt(p.getStock());

				StringBuffer sb = new StringBuffer(p.getNombre());
				sb.setLength(100);
				f.writeChars(sb.toString());
			}

			resultado = true;
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
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

	public ArrayList<Producto> obtenerProductosBin() {
		ArrayList<Producto> resultado = new ArrayList<>();

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "r");

			while (true) {
				Producto p = new Producto();

				p.setCodigo(f.readInt());

				p.setPrecio(f.readFloat());

				p.setStock(f.readInt());

				p.setNombre("");
				for (int i = 0; i < 100; i++) {
					p.setNombre(p.getNombre() + f.readChar());
				}
				p.setNombre(p.getNombre().trim());

				resultado.add(p);
			}

		} catch (EOFException e) {

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

	public Producto obtenerProductoBin(int codigo) {
		Producto resultado = null;

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "r");

			while (true) {

				int cod = f.readInt();
				if (cod == codigo) {
					resultado = new Producto();
					resultado.setCodigo(codigo);
					resultado.setPrecio(f.readFloat());
					resultado.setStock(f.readInt());
					resultado.setNombre("");
					for (int i = 0; i < 100; i++) {
						resultado.setNombre(resultado.getNombre() + f.readChar());
					}
					resultado.setNombre(resultado.getNombre().trim());
					return resultado;

				} else {
					f.seek(f.getFilePointer() + 208);

				}

			}

		} catch (EOFException e) {
			// TODO: handle exception

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

	public boolean generarVenta(Venta v) {
		boolean resultado = false;

		ObjectOutputStream f = null;

		try {
			File archivo = new File(nombreFObj);
			if (archivo.exists()) {
				f = new MiObjectOutputStream(new FileOutputStream(nombreFObj, true));
			} else {
				f = new ObjectOutputStream(new FileOutputStream(nombreFObj, true));
			}

			f.writeObject(v);
			resultado = true;

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

	public boolean modificarStock(Producto p, int cantidad) {
		boolean resultado = false;

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "rw");

			while (true) {

				int cod = f.readInt();
				if (cod == p.getCodigo()) {
					f.seek(f.getFilePointer()+4);
					f.writeInt(p.getStock()-cantidad);
					return true;

				} else {
					f.seek(f.getFilePointer() + 208);

				}

			}

		} catch (EOFException e) {
			// TODO: handle exception

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

	public ArrayList<Venta> obtenerVentasObj() {
		ArrayList<Venta> resultado = new ArrayList<>();
		
		// Tenemos que leer el archivo nombreFObj
		
		ObjectInputStream f = null;
		
		try {
			f = new ObjectInputStream(new FileInputStream(nombreFObj));
			
			while(true) {
				Venta v = new Venta();
				v = (Venta) f.readObject();
				resultado.add(v);
			}
			
		} catch(EOFException e) {
			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no ha sido generado aún.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f != null) {
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

	public boolean generarXML(File f, Estadistica e) {
		boolean resultado = false;
		
		Marshaller m = null;
		
		try {
			m = JAXBContext.newInstance(Estadistica.class).createMarshaller();
			
			m.marshal(e, f);
			resultado = true;
		} catch (JAXBException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return resultado;
	}

	public boolean generarArbolXMLDom(File fDom, ArrayList<Venta> ventas) {
		boolean resultado = false;
		
		Document doc = null;
		
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			doc.setXmlVersion("1.0");
			
			Element raiz = doc.createElement("estadistica");
			doc.appendChild(raiz);
			
			Element fecha = doc.createElement("fecha");
			raiz.appendChild(fecha);
			
			// Formateamos la fecha antes de añadirla
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Text tFecha = doc.createTextNode(formato.format(new Date(System.currentTimeMillis())));
			fecha.appendChild(tFecha);
			
			Element nodoVentas = doc.createElement("ventas");
			raiz.appendChild(nodoVentas);
			
			float sumaTotal = 0;
			for(Venta v: ventas) {
				// Recorrer y meter en ventas
				Element venta = doc.createElement("venta");
				nodoVentas.appendChild(venta);
				venta.setAttribute("producto", Integer.toString(v.getCodigo()));
				
					Element cantidad = doc.createElement("cantidad");
					venta.appendChild(cantidad);
				
						Text tCantidad = doc.createTextNode(Integer.toString(v.getCantidad()));
						cantidad.appendChild(tCantidad);
				
					Element precio = doc.createElement("precio");
					venta.appendChild(precio);
						
						float precioPorUnidad = obtenerProductoBin(v.getCodigo()).getPrecio();
						Text tPrecio = doc.createTextNode(Float.toString(precioPorUnidad));
						precio.appendChild(tPrecio);
						
				// VAMOS CALCULANDO LA SUMA TOTAL
				sumaTotal += precioPorUnidad * v.getCantidad();
			}
			
			Element total = doc.createElement("total");
			raiz.appendChild(total);
			
			Text tTotal = doc.createTextNode(Float.toString(sumaTotal));
			total.appendChild(tTotal);
			
			// GENERAMOS FINALMENTE EL FICHERO
			generarFichero(fDom.getName(), doc);
			resultado =true;
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
	private void generarFichero(String nombreFichero, Node doc) {
		// TODO Auto-generated method stub
		
		try {
			//Generamos la fuente que se va a llevar la fichero
			//Va a ser el árbol
			Source fuente = new DOMSource(doc);
			//Creamos el destino
			Result destino = new StreamResult(new File(nombreFichero));
			//Transformar el arbol en fichero
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(fuente, destino);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
