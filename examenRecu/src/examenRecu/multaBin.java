package examenRecu;

import java.util.Date;

public class multaBin {
String matricula;
int nMultas;
float importe;
Date fechaUltAct;
public multaBin() {
	super();
}
public multaBin(String matricula, int nMultas, float importe, Date fechaUltAct) {
	super();
	this.matricula = matricula;
	this.nMultas = nMultas;
	this.importe = importe;
	this.fechaUltAct = fechaUltAct;
}
public String getMatricula() {
	return matricula;
}
public void setMatricula(String matricula) {
	this.matricula = matricula;
}
public int getnMultas() {
	return nMultas;
}
public void setnMultas(int nMultas) {
	this.nMultas = nMultas;
}
public float getImporte() {
	return importe;
}
public void setImporte(float importe) {
	this.importe = importe;
}
public Date getFechaUltAct() {
	return fechaUltAct;
}
public void setFechaUltAct(Date fechaUltAct) {
	this.fechaUltAct = fechaUltAct;
}


}
