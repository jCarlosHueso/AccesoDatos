package Spotifly;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.ObjectCallbacks;

public class AccesoDatos {

private ObjectContainer bd= null;

public AccesoDatos() {
	
	bd= Db4oEmbedded.openFile("spotifly.db4o");
	
	}

public ObjectContainer getBd() {
	return bd;
}

public void setBd(ObjectContainer bd) {
	this.bd = bd;
}

public void cerrar() {
	bd.close();
}

public boolean crearAlbum(Album a) {
boolean resultado=false;

try {
	bd.store(a);
	resultado=true;
} catch (Exception e) {
	
	System.out.println("error al guardar");
	e.printStackTrace();
}

return resultado;
}

public Album obtenerAlbum(String artista, String titulo) {
Album resultado=null;
Album a = new Album();
a.setArtista(artista);
a.setTitulo(titulo);
try {
	ObjectSet<Album>r=bd.queryByExample(a);
	if(r.hasNext()) {
		resultado=r.next();
	}
} catch (Exception e) {
	
	System.out.println("Error al recuperar el album");
	e.printStackTrace();
}

return resultado;

}
}


