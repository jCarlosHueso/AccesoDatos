package JAXB;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import FicherosBinarios.Album;
import FicherosTexto.Artista;

public class AdJaxb {

	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

	public AdJaxb() {

	}

	public boolean crearXML(Artista a, ArrayList<Album> albumes) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			// Creamos el artista de JAXB que va a pasar al fichero
			ArtistaXML aXML = new ArtistaXML();
			aXML.setNombre(a.getNombre());
			aXML.setNumAlbumes(albumes.size());

			for (Album al : albumes) {
				// Crear objeto albumXML
				AlbumXML alXML = new AlbumXML(al.getId(), al.getTitulo());
				aXML.getAlbumes().add(alXML);
			}

			// Ordenamos los albumes por fecha de publcación
			albumes.sort(new Comparator<Album>() {

				@Override
				public int compare(Album o1, Album o2) {
					// TODO Auto-generated method stub
					return o1.getFechaP().compareTo(o2.getFechaP());
				}

			});
			aXML.setfPA(albumes.get(0).getFechaP());
			aXML.setfUA(albumes.get(albumes.size() - 1).getFechaP());

			// HAcemos el marshal
			Marshaller m = JAXBContext.newInstance(ArtistaXML.class).createMarshaller();
			m.marshal(aXML, new File(a.getNombre().replace(" ", "") + "_jaxb.xml"));
			resultado = true;

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public void mostrar(Artista a) {
		// TODO Auto-generated method stub
		File f = new File(a.getNombre().replace(" ", "") + "_jaxb.xml");
		if (f.exists()) {
			try {
				Unmarshaller um = JAXBContext.newInstance(ArtistaXML.class).createUnmarshaller();
				ArtistaXML art = (ArtistaXML) um.unmarshal(f);
				art.mostrar();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("No se ha generado información del artista");
		}
	}

	public boolean addAlbum(String nombre, String titulo) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		File f = new File(nombre.replace(" ", "") + "_jaxb.xml");
		if (f.exists()) {
			try {
				Unmarshaller um = JAXBContext.newInstance(ArtistaXML.class).createUnmarshaller();
				ArtistaXML art = (ArtistaXML) um.unmarshal(f);
				
				//Incrementamos el nº de álbumes
				art.setNumAlbumes(art.getNumAlbumes()+1);
				
				//Creamos el nuevo álbum
				AlbumXML al = new AlbumXML(art.getAlbumes()
						.get(art.getAlbumes().size()-1).getId()+1,
						titulo);
				art.getAlbumes().add(al);
				
				//HAcemos el marshall
				Marshaller m = JAXBContext.newInstance(ArtistaXML.class).createMarshaller();
				m.marshal(art, new File(nombre.replace(" ", "") + "_jaxb.xml"));
				resultado = true;
				
			} catch (JAXBException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 

		return resultado;
	}

	public ArrayList<AlbumXML> obtenerAlbumes(String nombre) {
		// TODO Auto-generated method stub
		ArrayList<AlbumXML> resultado = new ArrayList();
		
		File f = new File(nombre.replace(" ", "") + "_jaxb.xml");
		if (f.exists()) {
			
				try {
					Unmarshaller um = JAXBContext.newInstance(ArtistaXML.class)
							.createUnmarshaller();
					ArtistaXML art = (ArtistaXML) um.unmarshal(f);
					return art.getAlbumes();
							
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		}
		return resultado;
	}

	public boolean borrarAlbum(String nombre, int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		File f = new File(nombre.replace(" ", "") + "_jaxb.xml");
		if (f.exists()) {
			
				try {
					Unmarshaller um = JAXBContext.newInstance(ArtistaXML.class)
							.createUnmarshaller();
					ArtistaXML art = (ArtistaXML) um.unmarshal(f);
					
					//Borrar el álbum
					AlbumXML borrar = null;
					for(AlbumXML al:art.getAlbumes()) {
						if(id==al.getId()) {
							borrar=al;
							break;
						}
					}
					if(borrar!=null) {
						art.getAlbumes().remove(borrar);
						//HAcemos el marshall
						Marshaller m = JAXBContext.newInstance(ArtistaXML.class).createMarshaller();
						m.marshal(art, new File(nombre.replace(" ", "") + "_jaxb.xml"));
						resultado = true;
					}
							
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		}
		return resultado;
	}

}
