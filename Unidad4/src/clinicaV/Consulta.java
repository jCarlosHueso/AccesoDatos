package clinicaV;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	private String motivo;
	private String diagnostico;
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
	private String receta;
	
	public Consulta() {
	}
	
<<<<<<< HEAD
	public Consulta(ConsultaClave id, String motivo,  String diagnostico,
			String receta) {
		this.idConsulta = id;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.receta = receta;
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
				"\tMotivo:"+motivo+
				"\tDiagnostico:"+diagnostico+
				"\tReceta:"+receta);
	}
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
>>>>>>> d7be6f3fcc409e5f3eeb7cc9918452a8311da14c
	
	
	
}
