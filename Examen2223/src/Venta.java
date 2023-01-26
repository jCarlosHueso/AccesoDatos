import java.io.Serializable;

public class Venta implements Serializable{
	private int idProducto;
	private int cantidadV;
	private float importeR;
	
	
	public Venta(){
		
	}
	public Venta(int idProducto, int cantidadV, float importeR) {
		this.idProducto = idProducto;
		this.cantidadV = cantidadV;
		this.importeR = importeR;
	}
	
	public void mostrar() {
		System.out.println("Id:" + idProducto + 
				"\tCantidad:" + cantidadV + 
				"\tImporte:" + importeR);
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidadV() {
		return cantidadV;
	}
	public void setCantidadV(int cantidadV) {
		this.cantidadV = cantidadV;
	}
	public float getImporteR() {
		return importeR;
	}
	public void setImporteR(float importeR) {
		this.importeR = importeR;
	}
	
	

}
