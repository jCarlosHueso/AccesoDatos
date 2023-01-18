package gimnasio;

public class Cliente
{
	String usuario, dni, apellidos, nombre, telefono;
	boolean baja;
	int id;
	
	public Cliente() {}
	
	public Cliente(String usuario, String dni, String apellidos, String nombre, String telefono, boolean baja, int id)
	{
		this.usuario = usuario;
		this.dni = dni;
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.telefono = telefono;
		this.baja = baja;
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return "Cliente [usuario=" + usuario + ", dni=" + dni + ", apellidos=" + apellidos + ", nombre=" + nombre
				+ ", telefono=" + telefono + ", baja=" + baja + ", id=" + id + "]";
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isBaja() {
		return baja;
	}
	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
