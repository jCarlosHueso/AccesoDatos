package ClinicaV;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
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
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

public class AccesoDatos {
	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb://localhost:27017";
	
	public AccesoDatos() {
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
			bd = cluster.getDatabase("clinicaV").withCodecRegistry(registro);
			
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
	public Cliente obtenerCliente(String email) {
		// TODO Auto-generated method stub
		Cliente resultado = null;
		try {
			MongoCollection<Cliente> col = bd.getCollection("clientes",Cliente.class);
			
			Bson filtro = Filters.eq("email", email);
			
			resultado = col.find(filtro).first();
			
		} catch (Exception e) {
			// TODO: handle exception
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
							Aggregates.group(null, Accumulators.max("codigo", "$codigo"))
					 
					)).cursor();
			if(cursor.hasNext()) {
				Document d = cursor.next();
				resultado = d.getInteger("codigo");
			}
			
			resultado++;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Cliente> col = bd.getCollection("clientes",Cliente.class);
			
			InsertOneResult r =col.insertOne(c);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> resultado = new ArrayList<>();
		try {
			MongoCollection<Cliente> col = bd.getCollection("clientes",Cliente.class);			
			col.find().into(resultado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public Cliente obtenerCliente(int codigo) {
		// TODO Auto-generated method stub
		Cliente resultado = null;
		try {
			MongoCollection<Cliente> col = bd.getCollection("clientes",Cliente.class);
			
			Bson filtro = Filters.eq("codigo",codigo);
			
			resultado = col.find(filtro).first();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearMascota(Mascota m) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Mascota> col = bd.getCollection("mascotas",Mascota.class);
			
			InsertOneResult r = col.insertOne(m);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
}
