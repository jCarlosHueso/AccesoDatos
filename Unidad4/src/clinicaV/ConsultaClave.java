package clinicaV;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ConsultaClave  implements Serializable{
	@ManyToOne
	@JoinColumn(name="mascota", referencedColumnName = "codigo")
	//referencedColumnName contiene el nombre del campo
	// de la clase Mascota que hace de pk
	private Mascota mascota;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	public ConsultaClave(Mascota mascota, Date fecha) {
		this.mascota = mascota;
		this.fecha = fecha;
	}
	public ConsultaClave() {
	}
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	

}
