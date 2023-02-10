package clinicaV;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

<<<<<<< HEAD
<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Consulta {
	@EmbeddedId	
	private ConsultaClave idConsulta;	
	@Column(nullable = false)
	private String motivo;
	@Column(nullable = true)
	private String diagnostico;
	@Column(nullable = true)
=======
public class Consulta {
	private int codigo;
	private Date fecha;
=======
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
>>>>>>> 3b8ff0e (prueba saneamientos)
	private String motivo;
	@Column(nullable = true)
	private String diagnostico;
<<<<<<< HEAD
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
=======
	@Column(nullable = true)
>>>>>>> 3b8ff0e (prueba saneamientos)
	private String receta;
	
	public Consulta() {
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 3b8ff0e (prueba saneamientos)
	public Consulta(ConsultaClave id, String motivo,  String diagnostico,
			String receta) {
		this.idConsulta = id;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.receta = receta;
<<<<<<< HEAD
=======
	public Consulta(int codigo,  Date fecha, String descripcion) {
		this.codigo = codigo;
	
		this.fecha = fecha;
		this.motivo = descripcion;
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
<<<<<<< HEAD
		System.out.println("Mascota:"+idConsulta.getMascota().getNombre() + 			
				"\tFecha:" + formato.format(idConsulta.getFecha()) +
=======
		System.out.println("Codigo:"+codigo + 
			
				"\tFecha:" + formato.format(fecha) +
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
=======
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		System.out.println("Mascota:"+idConsulta.getMascota().getCodigo()+ "-"+
		idConsulta.getMascota().getNombre() + 			
				"\tFecha:" + formato.format(idConsulta.getFecha()) +
>>>>>>> 3b8ff0e (prueba saneamientos)
				"\tMotivo:"+motivo+
				"\tDiagnostico:"+diagnostico+
				"\tReceta:"+receta);
	}
<<<<<<< HEAD
<<<<<<< HEAD
	



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

	
=======
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

=======
>>>>>>> 3b8ff0e (prueba saneamientos)
	



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
<<<<<<< HEAD
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
=======

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

	
>>>>>>> 3b8ff0e (prueba saneamientos)
	
	
	
}
