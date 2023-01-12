package examen;

public class mensaje {

	private int de;
	private int para;
	private long fecha;
	private String mensaje;
	private boolean borrado = false;

	public mensaje() {
		super();
	}

	public mensaje(int de, int para, long fecha, String mensaje, boolean borrado) {
		super();
		this.de = de;
		this.para = para;
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.borrado = borrado;
	}

	public int getDe() {
		return de;
	}

	public void setDe(int de) {
		this.de = de;
	}

	public int getPara() {
		return para;
	}

	public void setPara(int para) {
		this.para = para;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return "mensaje [de=" + de + ", para=" + para + ", fecha=" + fecha + ", mensaje=" + mensaje + ", borrado="
				+ borrado + "]";
	}

}
