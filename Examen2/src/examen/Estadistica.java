package examen;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"fecha", "ventas", "total"})
public class Estadistica {
	
	private Date fecha;
	private ArrayList<Venta> ventas = new ArrayList<>();
	private float total;
	
	public Estadistica() {}

	public Estadistica(Date fecha, ArrayList<Venta> ventas) {
		this.fecha = fecha;
		this.ventas = ventas;
		this.total = calcularTotal(ventas);
	}
	
	private float calcularTotal(ArrayList<Venta> ventas) {
		float resultado = 0;
		
		for(Venta v: ventas) {
			resultado += v.getTotal();
		}
		
		return resultado;
	}
	
	@XmlElement
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@XmlElementWrapper
	@XmlElement(name="venta")
	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}
	
	
	@XmlElement
	public float getTotal() {
		return this.total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
}
