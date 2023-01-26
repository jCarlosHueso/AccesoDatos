import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "nombre","stock", "vendido", "recaudado"})
public class Info {
	private int id, stock, vendido;
	private String nombre;
	private  float recaudado;
	
	public Info(int id, int stock, int vendido, String nombre, float recaudado) {
		super();
		this.id = id;
		this.stock = stock;
		this.vendido = vendido;
		this.nombre = nombre;
		this.recaudado = recaudado;
	}
	public Info() {
		super();
	}
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@XmlElement
	public int getVendido() {
		return vendido;
	}
	public void setVendido(int vendido) {
		this.vendido = vendido;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public float getRecaudado() {
		return recaudado;
	}
	public void setRecaudado(float recaudado) {
		this.recaudado = recaudado;
	}
	
	
}
