package examen;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensaje {
	
	private int id;
	private Empleado deEmpleado;
	private Departamento paraDepartamento;
	private String asunto;
	private Date fechaEnvio;
	private String mensaje;
	
	public Mensaje() {}

	public Mensaje(int id, Empleado deEmpleado, Departamento paraDepartamento, String asunto, Date fechaEnvio,
			String mensaje) {
		this.id = id;
		this.deEmpleado = deEmpleado;
		this.paraDepartamento = paraDepartamento;
		this.asunto = asunto;
		this.fechaEnvio = fechaEnvio;
		this.mensaje = mensaje;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("ID: " + id + 
				"\tEmpleado: " + deEmpleado.getCodigo() + 
				"\tDepartamento: " + paraDepartamento.getNumero() + 
				"\tAsunto: " + asunto + 
				"\tFecha de envio: " + formato.format(fechaEnvio) + 
				"\tMensaje: " + mensaje);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empleado getDeEmpleado() {
		return deEmpleado;
	}

	public void setDeEmpleado(Empleado deEmpleado) {
		this.deEmpleado = deEmpleado;
	}

	public Departamento getParaDepartamento() {
		return paraDepartamento;
	}

	public void setParaDepartamento(Departamento paraDepartamento) {
		this.paraDepartamento = paraDepartamento;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
