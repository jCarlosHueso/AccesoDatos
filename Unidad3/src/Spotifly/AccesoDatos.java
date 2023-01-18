package Spotifly;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
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
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import ClinicaV.Cliente;


public class AccesoDatos {
	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb+srv://root:root@accesodatos.c38r0xl.mongodb.net/?retryWrites=true&w=majority";
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
			// TODO: handle exception
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
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public Artista obtenerArtista(String nombre) {
		// TODO Auto-generated method stub
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearArtista(Artista a) {
		// TODO Auto-generated method stub
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
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	public ArrayList<Artista> obtenerArtistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> resultado = new ArrayList();
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<String> obtenerArtistas(String genero) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<String> resultado = new ArrayList();
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modicarNombreArtista(Artista a, String nombre) {
		// TODO Auto-generated method stub
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean dejarSeguir(String genero) {
		// TODO Auto-generated method stub
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public long borrarArtistasNoSeguidos() {
		// TODO Auto-generated method stub
		long resultado = -1;
		try {		
			MongoCollection<Document> col = bd.getCollection("artista");
			
			Bson filtro = Filters.eq("seguir",false);
			DeleteResult r =col.deleteMany(filtro);
			resultado = r.getDeletedCount();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Album obtenerAlbum(String nombre, String titulo) {
		// TODO Auto-generated method stub
		Album resultado = null;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Album> col = bd.getCollection("album",Album.class);
			
			ArrayList<Album> albumes =new ArrayList();
			
			Bson filtro = Filters.and(Filters.eq("artista",nombre),
					                  Filters.eq("titulo",titulo));
			
			col.find(filtro).into(albumes);
			//Obtenemos el álbum si es que existe
			if(!albumes.isEmpty()) {
				resultado = albumes.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Album> col = bd.getCollection("album",Album.class);
			
			InsertOneResult r =col.insertOne(al);
			
			if(r.getInsertedId()!=null) {
				resultado=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> resultado = new ArrayList();
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Album> col = bd.getCollection("album",Album.class);
			
			col.find().into(resultado);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes(String nombre) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Album> resultado = new ArrayList();
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Album> col = bd.getCollection("album",Album.class);
			
			Bson filtro = Filters.eq("artista",nombre);
			
			col.find(filtro).into(resultado);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Cancion obtenerCancion(Album al, String tituloC) {
		// TODO Auto-generated method stub
		Cancion resultado = null;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("album");
			
			Bson filtro = Filters.and(Filters.eq("artista",al.getArtista()),
					Filters.eq("titulo",al.getTitulo()),
					Filters.in("canciones.titulo", tituloC));
			Bson campos = Projections.fields(Projections.exclude("_id"),
					Projections.elemMatch("canciones",Filters.eq("titulo", tituloC)));
			
			Document doc = col.find(filtro).projection(campos).first();			
			if(doc!=null) {
				System.out.println(doc.toJson());
				ArrayList<Document> reg = (ArrayList<Document>) doc.get("canciones");
				resultado = new Cancion(reg.get(0).getString("titulo"),
						reg.get(0).getDouble("valoracion"));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean addCancion(Album al, Cancion c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("album");
			
			Bson filtro = Filters.and(Filters.eq("artista",al.getArtista()),
									  Filters.eq("titulo",al.getTitulo()));
			
			Document cMongo = new Document().append("titulo", c.getTitulo())
					.append("valoracion", c.getValoracion());
			
			Bson camposModif = Updates.combine(
					Updates.addToSet("canciones", cMongo));
			
			UpdateResult r = col.updateOne(filtro, camposModif);
			if(r.getModifiedCount()==1) {
				resultado=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean existeCancion(Album al, String tituloC) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("album");
			
			Bson filtro = Filters.and(Filters.eq("artista",al.getArtista()),
					Filters.eq("titulo",al.getTitulo()),
					Filters.in("canciones.titulo", tituloC));
			Bson campos = Projections.fields(Projections.exclude("_id"),
					Projections.include("canciones"));
			
			Document doc = col.find(filtro).projection(campos).first();			
			if(doc!=null) {
				return true;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarCancion(Album al, Cancion c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("album");
			
			Bson filtro = Filters.and(Filters.eq("artista",al.getArtista()),
					Filters.eq("titulo",al.getTitulo()),
					Filters.in("canciones.titulo", c.getTitulo()));
			//Creamos el objeto a borrar
			Document cBorrar = new Document().append("titulo",c.getTitulo());
			
			Bson camposModif = Updates.pull("canciones", cBorrar);
			
			UpdateResult r = col.updateOne(filtro, camposModif);
			if(r.getModifiedCount()==1) {
				resultado=true;
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean valorarCancion(Album al, Cancion c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("album");
			
			Bson filtro = Filters.and(Filters.eq("artista",al.getArtista()),
					Filters.eq("titulo",al.getTitulo()),
					Filters.in("canciones.titulo", c.getTitulo()));			
			//Modificar un atributo de un objeto del array
			Bson camposModif = Updates.set("canciones.$[elem].valoracion", c.getValoracion());
			UpdateOptions objetosAModificar = new UpdateOptions().arrayFilters(
					Arrays.asList(Filters.eq("elem.titulo",c.getTitulo())));
					 
			UpdateResult r = col.updateOne(filtro, camposModif,objetosAModificar);
			if(r.getModifiedCount()==1) {
				resultado=true;
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Object[]> obtenerCancionesPorValoracion(double val) {
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList();
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("album");
			
			MongoCursor<Document> cursor = col.aggregate(Arrays.asList(
					Aggregates.unwind("$canciones"),
					Aggregates.match(Filters.eq("canciones.valoracion",val)),
					Aggregates.project(Projections.fields(Projections.exclude("_id"),
							Projections.include("artista","titulo","canciones"))))).cursor();
			
			while(cursor.hasNext()) {
				Document album = cursor.next();
				Document cancion = (Document) album.get("canciones");
				Object[] o = {album.getString("artista"),album.getString("titulo"),
						cancion.getString("titulo"),cancion.getDouble("valoracion")};
				resultado.add(o);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public void infoArtistas() {
		// TODO Auto-generated method stub
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("album");
			
			col.aggregate(Arrays.asList(
					Aggregates.group("$artista", 
							Accumulators.sum("numAlbumes", 1),
							Accumulators.sum("numCanciones", new Document("$size","$canciones")),
							Accumulators.min("AñoPrimerAlbum", "$anio"),
							Accumulators.max("AñoUltimoAlbum", "$anio"),
							Accumulators.avg("MediaValoracion", new Document("$avg","$canciones.valoracion"))  
					),
					Aggregates.addFields(new Field("artista","$_id"),new Field("val2",  new Document("$round","$MediaValoracion"))),
					Aggregates.project(Projections.fields(
							Projections.exclude("_id")
							)),
					Aggregates.sort(Sorts.ascending("artista"))
					))
			.forEach(doc->System.out.println(doc.toJson()));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void totalArtistaSeguidos() {
		// TODO Auto-generated method stub
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("artista");
			
			col.aggregate(Arrays.asList(
					Aggregates.match(Filters.eq("seguir",true)),
					Aggregates.count("ArtistasSeguidos"),
					Aggregates.project(Projections.exclude("_id"))
					))
			.forEach(doc->System.out.println(doc.toJson()));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void infoAlbumes() {
		// TODO Auto-generated method stub
		try {
			//Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("album");
			
			col.aggregate(Arrays.asList(
					Aggregates.lookup("artista", "artista", "nombre","datosArtista"),
					Aggregates.project(Projections.fields(
							Projections.exclude("_id"),
							Projections.include("titulo","anio",
									             "artista","datosArtista.genero")))
					))
			.forEach(doc->System.out.println(doc.toJson()));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Cliente obtenerCliente(String email) {
		// TODO Auto-generated method stub
		Cliente resultado = null;
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}
}
