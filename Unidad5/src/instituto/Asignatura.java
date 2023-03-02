package instituto;

public class Asignatura {
	private String codigo;
	private String nombre;
	
	public Asignatura(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Asignatura() {
	}
	
	public void mostrar() {
		System.out.println("Código Asignatura:"+codigo+
				"\tNombre:"+ nombre);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}