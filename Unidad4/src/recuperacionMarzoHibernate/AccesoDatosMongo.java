package recuperacionMarzoHibernate;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;


public class AccesoDatosMongo {
	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb://localhost:27017";

	public AccesoDatosMongo() {
		try {
			// Conectar con el cluster AccesoDatos de MongoAtlas
			cluster = MongoClients.create(new ConnectionString(cadenaCNX));

			// Registrar las clase para poder trabajar
			// con objetos (POJO).
			// SOLAMENTE PARA INSERTAR Y RECUPERAR
			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();
			CodecRegistry registro = fromRegistries(getDefaultCodecRegistry(), fromProviders(proveedor));
			// Obtenemos la base de datos, si no existe se crea
			bd = cluster.getDatabase("ServiHogar").withCodecRegistry(registro);

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

	
	public boolean comprobarCliente(Cliente cliente) {
		Cliente c = null;
		boolean resultado=false;
		try {
			MongoCollection<Cliente> col = bd.getCollection("cliente",Cliente.class);
			
			Bson filtro = Filters.eq("nif",cliente.getNif());
			
			c = col.find(filtro).first();
			
			if(c!=null) {
				resultado=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;

	}

	public boolean crearCliente(Cliente c) {
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

	public boolean crearPresupuesto(PresupuestoMongo p) {
		boolean resultado = false;
		try {
			MongoCollection<PresupuestoMongo> col = bd.getCollection("presupuesto",PresupuestoMongo.class);
			
			InsertOneResult r =col.insertOne(p);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		return resultado;	}

	public String obtenerEstadisticaCliente(String nif) {
		String estadistica="";
		
		
		
		
		
		return estadistica;
	}


}
