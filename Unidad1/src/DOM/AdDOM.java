package DOM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import FicherosBinarios.Album;
import FicherosTexto.Artista;


public class AdDOM {
	Document doc = null; //Árbol DOM
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	
	public AdDOM() {
		
	}

	public boolean crearXML(Artista a, ArrayList<Album> albumes) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		
		//Creamos el árbol vacío
		try {
			doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder()
					.newDocument();
			//Establecemos versión
			doc.setXmlVersion("1.0");
			
			//Creamos el elemento raíz
			Element raiz = doc.createElement("artista");
			//Asignamos el elmento al árbol
			doc.appendChild(raiz);
			//Rellenamos atributos
			raiz.setAttribute("nombre", a.getNombre());
			
			//Creamos nodo numAlbumes
			Element numA = doc.createElement("numAlbumes");
			//Añadimos el nodo a la raiz
			raiz.appendChild(numA);
			//Creamos el valor del nodo nº de albumes, es un texto
			Text tNumA = doc.createTextNode(Integer.toString(albumes.size()));
			numA.appendChild(tNumA);
			
			//Creamos nodo albumes
			Element nodoAlbumes = doc.createElement("albumes");
			raiz.appendChild(nodoAlbumes);
			
			
			Date fechaPA = null;
			Date fechaUA = null;
			//Recorremos los álbumes
			for(Album al:albumes) {
				Element album = doc.createElement("album");
				nodoAlbumes.appendChild(album);
				album.setAttribute("id", Integer.toString(al.getId()));
				
				Text titulo = doc.createTextNode(al.getTitulo());
				album.appendChild(titulo);
				
				if(fechaPA==null || al.getFechaP().getTime()<fechaPA.getTime()) {
					fechaPA = al.getFechaP();
				}
				if(fechaUA==null || al.getFechaP().getTime()>fechaUA.getTime()) {
					fechaUA = al.getFechaP();			
				}
			}
			
			//Crear nodos fecha
			Element fPA = doc.createElement("fechaPrimerAlbum");
			raiz.appendChild(fPA);
			Text valorFPA = doc.createTextNode(formato.format(fechaPA));
			fPA.appendChild(valorFPA);
			
			Element fUA = doc.createElement("fechaUltimoAlbum");
			raiz.appendChild(fUA);
			Text valorFUA = doc.createTextNode(formato.format(fechaUA));
			fUA.appendChild(valorFUA);
			
			//Pasar arbol a fichero
			generarFichero(a.getNombre().replace(" ", "")+".xml");
			resultado =true;

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	private void generarFichero(String nombreFichero) {
		// TODO Auto-generated method stub
		
		try {
			//Generamos la fuente que se va a llevar la fichero
			//Va a ser el árbol
			Source fuente = new DOMSource(doc);
			//Creamos el destino
			Result destino = new StreamResult(new File(nombreFichero));
			//Transformar el arbol en fichero
			Transformer t = TransformerFactory.
					newInstance().
					newTransformer();
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

	public void mostrar(Artista a) {
		// TODO Auto-generated method stu		
		try {
			//Establecemos el nombre del fichero
			String nombreF = a.getNombre().replace(" ", "")+".xml";
			//Cargamos el árbol del fichero
			doc = DocumentBuilderFactory.
					newInstance().
					newDocumentBuilder().
					parse(new File(nombreF));
			if(doc!=null) {
				Element raiz = doc.getDocumentElement();
				pintarElmento(raiz,0);
				System.out.println("");
			}
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("No hay información del artista");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pintarElmento(Node elto, int nTab) {
		// TODO Auto-generated method stub
		
		//Chequear si es elemento de texto
		if(elto.getNodeType()==Node.TEXT_NODE) {
			System.out.print(":"+elto.getNodeValue());
		}
		else {
			System.out.print("\n");
			//Pinto tabulaciones
			for(int i=0;i<nTab;i++) {
				System.out.print("\t");
			}
			System.out.print(elto.getNodeName());
			
			//Ver si hay atributos
			NamedNodeMap atri =  elto.getAttributes();
			for(int i=0; i<atri.getLength();i++) {
				Attr atributo = (Attr) atri.item(i);				
				System.out.print(" " + atributo.getName()+"="
				                   +atributo.getValue());
			}
			
			NodeList hijos =  elto.getChildNodes();
			for(int i=0; i<hijos.getLength();i++) {				
				pintarElmento(hijos.item(i), nTab+1);
			}
		}
		
	}

}
