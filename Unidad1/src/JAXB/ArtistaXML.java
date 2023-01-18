package JAXB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"numAlbumes","fPA","fUA","albumes"})
public class ArtistaXML {
		private String nombre;
		private int numAlbumes;
		private Date fPA;
		private Date fUA;
		private ArrayList<AlbumXML> albumes = new ArrayList();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		
		public void mostrar() {
			System.out.println("Nombre:"+nombre+
					"\tNºAlbumes:" + numAlbumes + 
					"\tFPA:"+formato.format(fPA)+
					"\tFUA:"+formato.format(fUA));
			for(AlbumXML al:albumes) {
				al.mostrar();
			}
		}
		public ArtistaXML() {
			super();
		}
		
		
		

		
		public ArtistaXML(String nombre, int numAlbumes, Date fPA, Date fUA, ArrayList<AlbumXML> albumes,
				SimpleDateFormat formato) {
			super();
			this.nombre = nombre;
			this.numAlbumes = numAlbumes;
			this.fPA = fPA;
			this.fUA = fUA;
			this.albumes = albumes;
			this.formato = formato;
		}
		
		@XmlElement(name="NumeroAlbumes")
		public int getNumAlbumes() {
			return numAlbumes;
		}
		public void setNumAlbumes(int numAlbumes) {
			this.numAlbumes = numAlbumes;
		}
		@XmlAttribute
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		@XmlElement
		public Date getfPA() {
			return fPA;
		}
		public void setfPA(Date fPA) {
			this.fPA = fPA;
		}
		@XmlElement
		public Date getfUA() {
			return fUA;
		}
		public void setfUA(Date fUA) {
			this.fUA = fUA;
		}
		@XmlElementWrapper
		@XmlElement(name="album")
		public ArrayList<AlbumXML> getAlbumes() {
			return albumes;
		}
		public void setAlbumes(ArrayList<AlbumXML> albumes) {
			this.albumes = albumes;
		}
		
		
		

}
