package examenRecup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
	
@Id
private String isbn;
@Column(nullable = false)
private int numEjemplares;
@Column(nullable = false)
private String titulo;
@Column(nullable = false)
private String autor;

public Libro() {
}
public Libro(String isbn, int numEjemplares, String titulo, String autor) {
	this.isbn = isbn;
	this.numEjemplares = numEjemplares;
	this.titulo = titulo;
	this.autor = autor;
}
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
public int getNumEjemplares() {
	return numEjemplares;
}
public void setNumEjemplares(int numEjemplares) {
	this.numEjemplares = numEjemplares;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getAutor() {
	return autor;
}
public void setAutor(String autor) {
	this.autor = autor;
}




}

