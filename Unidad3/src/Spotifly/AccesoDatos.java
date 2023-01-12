package Spotifly;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
//import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;


public class AccesoDatos {
	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb+srv://root:root@accesodatos.lxwgnek.mongodb.net/?retryWrites=true&w=majority";
	public AccesoDatos() {
		try {
			//Conectar con el cluster AccesoDatos de MongoAtlas
			cluster = MongoClients.create(
					new ConnectionString(cadenaCNX));
			
			//Registrar las clase para poder trabajar
			//con objetos (POJO). 
			//SOLAMENTE PARA INSERTAR Y RECUPERAR
			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();
			CodecRegistry registro = 
					fromRegistries(getDefaultCodecRegistry(),
							fromProviders(proveedor));
			//Obtenemos la base de datos, si no existe se crea
			bd = cluster.getDatabase("spotifly").withCodecRegistry(registro);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public MongoDatabase getBd() {
		return bd;
	}

	public void setBd(MongoDatabase bd) {
		this.bd = bd;
	}
	
	public void cerrar() {
		try {
			cluster.close();
			
		} catch (MongoException e) {
			System.out.println(e.getMessage());
		}
	}

	public Artista obtenerArtista(String nombre) {
		Artista resultado = null;
		try {
			MongoCollection<Document> c = bd.getCollection("artista");	
			
			Document d = c.find(Filters.eq("nombre",nombre)).first();
			
			if(d!=null) {
				System.out.println(d.toJson());				
				resultado = new Artista(
						d.getString("nombre"),
						(ArrayList<String>)d.getList("genero",String.class),
						d.getDate("fechaC"),
						d.getBoolean("seguir"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearArtista(Artista a) {
		boolean resultado = false;
		try {
			//Recuperar la colección en la que vamos a insertar el artista
			MongoCollection<Document> coleccion = 
					bd.getCollection("artista");
			
			InsertOneResult r = coleccion.insertOne(
					new Document().append("nombre", a.getNombre())
					.append("genero", a.getGenero())
					.append("fechaC", a.getFechaC())
					.append("seguir", a.isSeguir()));
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	public ArrayList<Artista> obtenerArtistas() {
		ArrayList<Artista> resultado = new ArrayList<Artista>();
		try {
			MongoCollection<Document> col = bd.getCollection("artista");
			
			MongoCursor<Document> cursor = 
					col.find().sort(Sorts.ascending("nombre")).cursor();
			//Recorremos el cursor para generar el resultado
			while(cursor.hasNext()) {
				Document d = cursor.next();
				
				resultado.add(new Artista(
						d.getString("nombre"),
						(ArrayList<String>)d.getList("genero",String.class),
						d.getDate("fechaC"),
						d.getBoolean("seguir")));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<String> obtenerArtistas(String genero) {	
		ArrayList<String> resultado = new ArrayList<String>();
		try {
			MongoCollection<Document> col = bd.getCollection("artista");
			
			//Establecer los campos que queremos recuperar
			Bson campos = Projections.fields(Projections.include("nombre"),
					Projections.exclude("_id"));
			Bson filtro = Filters.and(
					Filters.eq("seguir",true),Filters.in("genero", genero),
					Filters.eq("nombre","sss"));
					
			MongoCursor<Document> cursor = 
					col.find(filtro)
					.projection(campos)
					.sort(Sorts.ascending("nombre")).cursor();
			//Recorremos el cursor para generar el resultado
			while(cursor.hasNext()) {
				Document d = cursor.next();
				
				resultado.add(d.getString("nombre"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modicarNombreArtista(Artista a, String nombre) {
		boolean resultado = false;
		try {
			
			MongoCollection<Document> col = bd.getCollection("artista");
			
			Bson filtro = Filters.eq("nombre",a.getNombre());
			Bson modificaciones = Updates.combine(Updates.set("nombre", nombre));
			UpdateResult r = col.updateOne(filtro, modificaciones);			
			if(r.getModifiedCount()==1) {				
				resultado=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean dejarSeguir(String genero) {
		boolean resultado = false;
		try {
			
			MongoCollection<Document> col = bd.getCollection("artista");
			
			// where seguir= true and genero in arraygenero
			Bson filtro = Filters.and(Filters.eq("seguir",true),
					Filters.in("genero",genero));
			Bson modificaciones = 
					Updates.combine(Updates.set("seguir",false));
			UpdateResult r = col.updateMany(filtro, modificaciones);
			if(r.getModifiedCount()>0) {
				resultado=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public long borrarArtistasNoSeguidos() {
		long resultado = -1;
		try {		
			MongoCollection<Document> col = bd.getCollection("artista");
			
			Bson filtro = Filters.eq("seguir",false);
			DeleteResult r =col.deleteMany(filtro);
			resultado = r.getDeletedCount();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Album obtenerAlbum(String nombre, String titulo) {
		Album resultado = null;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Album> col = bd.getCollection("album",Album.class);
			
			ArrayList<Album> albumes =new ArrayList<Album>();
			
			Bson filtro = Filters.and(Filters.eq("artista",nombre),
					                  Filters.eq("titulo",titulo));
			
			col.find(filtro).into(albumes);
			//Obtenemos el álbum si es que existe
			if(!albumes.isEmpty()) {
				resultado = albumes.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearAlbum(Album al) {
		boolean resultado = false;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Album> col = bd.getCollection("album",Album.class);
			
			InsertOneResult r =col.insertOne(al);
			
			if(r.getInsertedId()!=null) {
				resultado=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes() {
		ArrayList<Album> resultado = new ArrayList<Album>();
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Album> col = bd.getCollection("album",Album.class);
			
			col.find().into(resultado);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	

}
