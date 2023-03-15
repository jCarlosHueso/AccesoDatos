package clinicaV;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Mascota  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@ManyToOne
	@JoinColumn(name="cliente", referencedColumnName = "codigo")
	private Cliente cliente;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String tipo;	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "idConsulta.mascota" )
	private List<Consulta> consultas=new ArrayList();
	
	public Mascota() {
	}
	public Mascota(int codigo, Cliente cliente, String nombre, String tipo, List<Consulta> tratamientos) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.nombre = nombre;
		this.tipo = tipo;
		this.consultas = tratamientos;
	}
	
	public void mostrar(boolean mosrtarTrat) {
		System.out.println("Codigo:"+codigo + 
				"\tCliente:"+ cliente.getNombre() +
				"\tNombre:" + nombre +
				"\ttipo:"+tipo);
		if(mosrtarTrat) {
			for(Consulta t: consultas) {
				t.mostrar();
			}
		}
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	
}
