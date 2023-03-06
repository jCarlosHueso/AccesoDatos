package ejercicioMongoExamen;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import clinicaV.Cliente;
import clinicaV.Mascota;

public class AccesoADatos {
	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb://localhost:27017";
	
	public AccesoADatos() {
		try {
			//Conectar con el cluster AccesoDatos de MongoAtlas
			cluster = MongoClients.create(cadenaCNX);
			
			//Registrar las clase para poder trabajar
			//con objetos (POJO). 
			//SOLAMENTE PARA INSERTAR Y RECUPERAR
			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();
			CodecRegistry registro = 
					fromRegistries(getDefaultCodecRegistry(),
							fromProviders(proveedor));
			//Obtenemos la base de datos, si no existe se crea
			bd = cluster.getDatabase("GranTeatro").withCodecRegistry(registro);
			
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
	public Espectaculos obtenerEspectaculo(String tit) {
		// TODO Auto-generated method stub
		Espectaculos resultado = null;
		
		try {
			MongoCollection<Espectaculos> col = bd.getCollection("espectaculos",Espectaculos.class);
			
			Bson filtro = Filters.eq("titulo", tit);
			
			resultado = col.find(filtro).first();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public int obtenerCodigo(String coleccion) {
		// TODO Auto-generated method stub
		int resultado = 0;
		
		try {
			MongoCollection<Document> col = bd.getCollection(coleccion);
			
			MongoCursor<Document> cursor = col.aggregate(
					Arrays.asList(
							Aggregates.group(null, Accumulators.max("codigo", "$_id"))
					 
					)).cursor();
			if(cursor.hasNext()) {
				Document d = cursor.next();
				resultado = d.getInteger("codigo");
			}
			resultado++;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public boolean crearEspectaculo(Espectaculos es) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			MongoCollection<Espectaculos> col = bd.getCollection("espectaculos",Espectaculos.class);
			
			InsertOneResult r =col.insertOne(es);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public Zonas obtenerZonas(String nombre) {
		// TODO Auto-generated method stub
		Zonas resultado = null;
		
		try {
			MongoCollection<Zonas> col = bd.getCollection("zonas",Zonas.class);
			
			Bson filtro = Filters.eq("nombre", nombre);
			
			resultado = col.find(filtro).first();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearZona(Zonas z) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			MongoCollection<Zonas> col = bd.getCollection("zonas",Zonas.class);
			
			InsertOneResult r =col.insertOne(z);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Espectaculos> obtenerEspectaculos() {
		// TODO Auto-generated method stub
		ArrayList<Espectaculos> resultado = new ArrayList<>(); 
			try {
				MongoCollection<Espectaculos> col = bd.getCollection("espectaculos",Espectaculos.class);			
				col.find().into(resultado);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return resultado;
	}
	public Espectaculos obtenerEspectaculo(int codigo) {
		// TODO Auto-generated method stub
		Espectaculos resultado = null;
		
		try {
			MongoCollection<Espectaculos> col = bd.getCollection("espectaculos",Espectaculos.class);
			
			Bson filtro = Filters.eq("_id", codigo);
			
			resultado = col.find(filtro).first();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public ArrayList<Zonas> obtenerZonas() {
		// TODO Auto-generated method stub
		ArrayList<Zonas> resultado = new ArrayList<>(); 
		try {
			MongoCollection<Zonas> col = bd.getCollection("zonas",Zonas.class);			
			col.find().into(resultado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public Zonas obtenerZona(int codZona) {
		// TODO Auto-generated method stub
		Zonas resultado = null;
		
		try {
			MongoCollection<Zonas> col = bd.getCollection("zonas",Zonas.class);
			
			Bson filtro = Filters.eq("_id", codZona);
			
			resultado = col.find(filtro).first();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public int obtenerCodigoEntrada() {
		// TODO Auto-generated method stub
		int resultado=0;
		try {
			
			MongoCollection<Document> col = bd.getCollection("zonas");
			Document d = col.aggregate(Arrays.asList(
					Aggregates.group(null, 
							Accumulators.max("codigo", 
									new Document("$max","$entradas.codigo"))))).first();
			if(d.get("codigo")!=null)
				resultado = d.getInteger("codigo");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado +1;
	}
	public boolean crearEntrada(Espectaculos es, Zonas z, Entradas en) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Zonas> col = bd.getCollection("zonas",Zonas.class);
			
			Bson filtro = Filters.eq("_id",z.getId());
			Bson modif = Updates.combine(Updates.addToSet("entradas", en));
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				resultado =true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Object[]> obtenerEntradas() {
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList<>();
		try {
			MongoCollection<Document> col = bd.getCollection("zonas");
			Bson campos = Projections.fields(
					Projections.exclude("_id"),
					Projections.include("entradas.codigo","entradas.espectaculo","codigo",
							"entradas.precio"));
			
			MongoCursor<Document> cursor = col.find().projection(campos).cursor();
			while(cursor.hasNext()) {				
				Document d = cursor.next();
				//System.out.println(d.toJson());
				ArrayList<Document> entradas = (ArrayList<Document>) d.get("entradas");
				for (Document tr: entradas) {
					resultado.add(new Object[] {tr.getInteger("codigo"),
							tr.getInteger("espectaculo"),
							d.getInteger("zonas"),tr.getDouble("precio")}); 
				}			
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean modificarZona(Zonas z) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("zonas");
			
			Bson filtro = Filters.in("_id",z.getId());
			
			Bson camposModif = Updates.combine(
					Updates.set("precioBase", z.getPrecioBase()));
			 
			/*UpdateOptions opcionesModif = new UpdateOptions().arrayFilters(
					Arrays.asList(Filters.eq("_id",z.getId())));*/
			UpdateResult r = col.updateOne(filtro, camposModif);
			if(r.getModifiedCount()==1) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean borrarEntrada(Espectaculos es) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("zonas");
			
			Bson filtro = Filters.in("entradas.espectaculo",es.getId());
			
			Bson camposModif = Updates.combine(
					Updates.pull("entradas", new Document("espectaculo",es.getId())));
			
			UpdateResult r = col.updateOne(filtro, camposModif);
			if(r.getModifiedCount()==1) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	
}
