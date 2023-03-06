package examenGuille;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Mensaje {
	
	private int codigo;
	private String deEmpleado;
	private String paraDepartamento;
	private Date fecha;
	private String asunto;
	private String mensaje;
	private ArrayList<Destinatario> destinatarios = new ArrayList<>();
	
	public Mensaje() {}

	public Mensaje(int codigo, String deEmpleado, String paraDepartamento, Date fecha, String asunto, String mensaje,
			ArrayList<Destinatario> destinatarios) {
		this.codigo = codigo;
		this.deEmpleado = deEmpleado;
		this.paraDepartamento = paraDepartamento;
		this.fecha = fecha;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.destinatarios = destinatarios;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código: " + codigo +
						   "\tDe empleado: " + deEmpleado + 
						   "\tPara dpt: " + paraDepartamento + 
						   "\tFecha: " + formato.format(fecha) + 
						   "\tAsunto: " + asunto + 
						   "\tMensaje: " + mensaje);
		
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

	public ArrayList<Destinatario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(ArrayList<Destinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}
	
}
