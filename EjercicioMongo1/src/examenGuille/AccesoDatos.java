package examenGuille;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import java.util.ArrayList;
import java.util.Arrays;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
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

public class AccesoDatos {

	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String nombreDB = "empresa";
	// OBTENER EL CONNECTION STRING DEL LOCAL
	private String cadenaCNX = "mongodb://localhost:27017";

	public AccesoDatos() {

		try {
			cluster = MongoClients.create(new ConnectionString(cadenaCNX));
			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();
			CodecRegistry registro = fromRegistries(getDefaultCodecRegistry(), fromProviders(proveedor));

			// Obtenemos la base de datos, si no existe se crea
			bd = cluster.getDatabase(nombreDB).withCodecRegistry(registro);
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

	public Empleado obtenerEmpleado(String dni) {
		Empleado resultado = null;
		try {
			MongoCollection<Empleado> col = bd.getCollection("empleado", Empleado.class);
			
			resultado = col.find(Filters.eq("dni", dni)).first();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultado;
	}

	public boolean crearEmpleado(Empleado em) {
		boolean resultado = false;

		try {

			MongoCollection<Empleado> col = bd.getCollection("empleado", Empleado.class);

			InsertOneResult r = col.insertOne(em);

			if (r.getInsertedId() != null) {
				resultado = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resultado;
	}

	private int obtenerCodigoMensaje() {
		int resultado = 0;

		try {

			MongoCollection<Mensaje> col = bd.getCollection("mensaje", Mensaje.class);

			Mensaje m = col.aggregate(Arrays.asList(Aggregates.group(null, Accumulators.max("codigo", "$codigo"))))
					.first();

			if (m != null) {
				resultado = m.getCodigo();
			}

			resultado++;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Destinatario> obtenerDestinatariosDep(String dep) {
		ArrayList<Destinatario> resultado = new ArrayList<>();

		try {

			MongoCollection<Empleado> col = bd.getCollection("empleado", Empleado.class);

			MongoCursor<Empleado> cursor = col.find(Filters.eq("departamento", dep)).cursor();

			while (cursor.hasNext()) {
				Empleado e = cursor.next();
				resultado.add(new Destinatario(e.getDni(), false));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resultado;
	}

	public boolean enviarMensaje(Mensaje m) {
		
		boolean resultado = false;

		m.setCodigo(this.obtenerCodigoMensaje());
		if (m.getCodigo() != 0) {
			try {
				MongoCollection<Mensaje> col = bd.getCollection("mensaje", Mensaje.class);
				InsertOneResult r = col.insertOne(m);
				if (r.getInsertedId() != null) {
					resultado = true;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return resultado;
	}

	public ArrayList<Object[]> obtenerMensajesEmp(Empleado empIdentificado) {
		ArrayList<Object[]> resultado = new ArrayList<>();

		try {

			MongoCollection<Document> col = bd.getCollection("mensaje");

			MongoCursor<Document> cursor = col.aggregate(
					Arrays.asList(Aggregates.match(Filters.eq("destinatarios.empleado", empIdentificado.getDni())),
							Aggregates.unwind("$destinatarios"),
							Aggregates.match(Filters.eq("destinatarios.empleado", empIdentificado.getDni())),
							Aggregates.lookup("empleado", "deEmpleado", "dni", "datosEmisor")))
					.cursor();

			while (cursor.hasNext()) {
				Document m = cursor.next();
				String emisor = (((ArrayList<Document>) m.get("datosEmisor")).get(0)).getString("nombre");
				// resultado.add(new Destinatario(e.getDni(), false));
				resultado.add(new Object[] { emisor, m.getString("asunto"), m.getString("mensaje") });
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resultado;
	}

	public boolean borrarMensajes(Empleado em) {
		boolean resultado = false;

		try {

			MongoCollection<Document> col = bd.getCollection("mensaje");
			
			Bson filtro = Filters.in("destinatarios.empleado", em.getDni());

			Bson camposModif = Updates.pull("destinatarios", new Document("empleado", em.getDni()));

			UpdateResult r = col.updateMany(filtro, camposModif);

			if (r.getModifiedCount() >= 1) {
				resultado = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Object[]> obtenerMensajesEnviados(Empleado em) {
		
		ArrayList<Object[]> resultado = new ArrayList<>();
		
		try {
			MongoCollection<Document> col = bd.getCollection("mensaje");

			MongoCursor<Document> cursor = col.aggregate(Arrays.asList(
					Aggregates.match(Filters.eq("deEmpleado", em.getDni())),
					Aggregates.group("$paraDepartamento", 
								Accumulators.sum("numMensajes", 1),
								Accumulators.min("primerMens", "$fecha"),
								Accumulators.max("ultimoMens", "$fecha")
								)
					)).cursor();

			while (cursor.hasNext()) {
				Document d = cursor.next();
				//String emisor = (((ArrayList<Document>) m.get("datosEmisor")).get(0)).getString("nombre");
				// resultado.add(new Destinatario(e.getDni(), false));
				resultado.add(new Object[] {
											d.getString("_id"),
											d.getInteger("numMensajes"),
											d.getDate("primerMens"),
											d.getDate("ultimoMens")
											});
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultado;
	}

}
