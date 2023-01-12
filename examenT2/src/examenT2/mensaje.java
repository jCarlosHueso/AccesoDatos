package examenT2;

import java.util.Date;

public class mensaje {

	int id,deEmpleado,paraDepartamento;
	String asunto,mensaje;
	Date fechaEnvio;
	public mensaje() {
	}
	public mensaje(int id, int deEmpleado, int paraDepartamento, String asunto, String mensaje, Date fechaEnvio) {
		this.id = id;
		this.deEmpleado = deEmpleado;
		this.paraDepartamento = paraDepartamento;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fechaEnvio = fechaEnvio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDeEmpleado() {
		return deEmpleado;
	}
	public void setDeEmpleado(int deEmpleado) {
		this.deEmpleado = deEmpleado;
	}
	public int getParaDepartamento() {
		return paraDepartamento;
	}
	public void setParaDepartamento(int paraDepartamento) {
		this.paraDepartamento = paraDepartamento;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	@Override
	public String toString() {
		return "mensaje [id=" + id + ", deEmpleado=" + deEmpleado + ", paraDepartamento=" + paraDepartamento
				+ ", asunto=" + asunto + ", mensaje=" + mensaje + ", fechaEnvio=" + fechaEnvio + "]";
	}
	
	
	
}
