package saneamiento;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class claveAS implements Serializable{
	@ManyToOne
	@JoinColumn(name = "animal",referencedColumnName = "codigo")
	private Animal animal;
	@ManyToOne
	@JoinColumn(name = "saneamiento",referencedColumnName = "id")
	private Saneamiento sanea;
	
	public claveAS() {
		
	}

	public claveAS(Animal animal, Saneamiento sanea) {
		this.animal = animal;
		this.sanea = sanea;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Saneamiento getSanea() {
		return sanea;
	}

	public void setSanea(Saneamiento sanea) {
		this.sanea = sanea;
	}
}
