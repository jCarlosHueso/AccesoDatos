package monglo;

import java.util.ArrayList;
import java.util.Date;

public class Mensajes {

	private int codigo;
	private String deEmpleado;
	private String paraDepartamento;
	private Date fecha;
	private String asunto;
	private String mensaje;
	private ArrayList<Destinatarios> destinatarios;
	public Mensajes() {
	}
	public Mensajes(int codigo, String deEmpleado, String paraDepartamento, Date fecha, String asunto, String mensaje,
			ArrayList<Destinatarios> destinatarios) {
		this.codigo = codigo;
		this.deEmpleado = deEmpleado;
		this.paraDepartamento = paraDepartamento;
		this.fecha = fecha;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.destinatarios = destinatarios;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDeEmpleado() {
		return deEmpleado;
	}
	public void setDeEmpleado(String deEmpleado) {
		this.deEmpleado = deEmpleado;
	}
	public String getParaDepartamento() {
		return paraDepartamento;
	}
	public void setParaDepartamento(String paraDepartamento) {
		this.paraDepartamento = paraDepartamento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	public ArrayList<Destinatarios> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(ArrayList<Destinatarios> destinatarios) {
		this.destinatarios = destinatarios;
	}
	@Override
	public String toString() {
		return "Mensajes [codigo=" + codigo + ", deEmpleado=" + deEmpleado + ", paraDepartamento=" + paraDepartamento
				+ ", fecha=" + fecha + ", asunto=" + asunto + ", mensaje=" + mensaje + ", destinatarios="
				+ destinatarios + "]";
	}
}
