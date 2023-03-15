package clinicaV;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Consulta  implements Serializable{
	@EmbeddedId	
	private ConsultaClave idConsulta;	
	@Column(nullable = false)
	private String motivo;
	@Column(nullable = true)
	private String diagnostico;
	@Column(nullable = true)
	private String receta;
	
	public Consulta() {
	}
	
	public Consulta(ConsultaClave id, String motivo,  String diagnostico,
			String receta) {
		this.idConsulta = id;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.receta = receta;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		System.out.println("Mascota:"+idConsulta.getMascota().getCodigo()+ "-"+
		idConsulta.getMascota().getNombre() + 			
				"\tFecha:" + formato.format(idConsulta.getFecha()) +
				"\tMotivo:"+motivo+
				"\tDiagnostico:"+diagnostico+
				"\tReceta:"+receta);
	}
	



	public ConsultaClave getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(ConsultaClave idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getReceta() {
		return receta;
	}

	public void setReceta(String receta) {
		this.receta = receta;
	}

	
	
	
	
}
