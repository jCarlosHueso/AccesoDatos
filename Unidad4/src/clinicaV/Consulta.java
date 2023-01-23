package clinicaV;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta {
	private int codigo;
	private Date fecha;
	private String motivo;
	private String diagnostico;
	private String receta;
	
	public Consulta() {
	}
	
	public Consulta(int codigo,  Date fecha, String descripcion) {
		this.codigo = codigo;
	
		this.fecha = fecha;
		this.motivo = descripcion;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		System.out.println("Codigo:"+codigo + 
			
				"\tFecha:" + formato.format(fecha) +
				"\tMotivo:"+motivo+
				"\tDiagnostico:"+diagnostico+
				"\tReceta:"+receta);
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return motivo;
	}

	public void setDescripcion(String descripcion) {
		this.motivo = descripcion;
	}
	
	
	
}
