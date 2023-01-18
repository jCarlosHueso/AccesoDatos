package JAXB;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"titulo"})
public class AlbumXML {
	private int id;
	private String titulo;
	
	
	
	public void mostrar() {
		System.out.println("Id:"+id + "\tTítulo:"+titulo);
	}
	public AlbumXML() {
		super();
	}
	
	public AlbumXML(int id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
