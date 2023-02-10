package monglo;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

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
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;

public class AccesoDatos {
	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb://localhost:27017";
	static Scanner t = new Scanner(System.in);
	static private String empRegistrado;

	public AccesoDatos() {
		try {
			cluster = MongoClients.create(cadenaCNX);

			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();
			CodecRegistry registro = fromRegistries(getDefaultCodecRegistry(), fromProviders(proveedor));
			bd = cluster.getDatabase("EnvioCorreos").withCodecRegistry(registro);

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

	public boolean encontrarEmpleado(String dni) {

		boolean resultado = false;
		try {
			MongoCollection<Document> emp = bd.getCollection("Empleado");

			Bson filtro = Filters.eq("DNI", dni);

			Document d = emp.find(Filters.eq("DNI", dni)).first();
			// System.out.println(emple.toString());
			if (d == null) {
				resultado = true;
			} else {
				resultado = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;

	}

	public boolean crearEmpleado(String dni) {
		boolean resultado = false;

		System.out.println("Introduce el nombre completo del empleado: ");
		String nombre = t.nextLine();
		System.out.println("Introduce el departamento al que pertenece este empleado:");
		String departamento = t.nextLine();

		try {
			MongoCollection<Document> coleccion = bd.getCollection("Empleado");

			InsertOneResult r = coleccion.insertOne(
					new Document().append("DNI", dni).append("nombre", nombre).append("departamento", departamento));
			if (r.getInsertedId() != null) {
				resultado = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	public ArrayList<Destinatarios> obtenerEmpleadosDpto(String dpto) {

		ArrayList<Destinatarios> resultado = new ArrayList<Destinatarios>();

		try {
			MongoCollection<Document> col = bd.getCollection("Empleado");

			// Establecer los campos que queremos recuperar
			Bson campos = Projections.fields(Projections.include("DNI"), Projections.exclude("_id"));

			Bson filtro = Filters.and(Filters.eq("departamento", dpto));

			MongoCursor<Document> cursor = col.find(filtro).projection(campos).sort(Sorts.ascending("DNI")).cursor();
			// Recorremos el cursor para generar el resultado

			while (cursor.hasNext()) {
				Document d = cursor.next();
				Destinatarios dest = new Destinatarios(d.getString("DNI"), true);
				resultado.add(dest);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean crearMensaje(String empleadoRegistrado, String dpto, ArrayList<Destinatarios> destinatarios) {
		boolean resultado = false;

		try {
			MongoCollection<Document> coleccion = bd.getCollection("Mensaje");
			int codigo = obtenerCodigo();// calcular despues el codigo obteniendo el ultimo
			System.out.println("Introduce el asunto del mensaje");
			String asunto = t.nextLine();
			System.out.println("Introduce el cuerpo del mensaje");
			String mensaje = t.nextLine();
			Date fecha = new Date();
			InsertOneResult r = coleccion.insertOne(new Document().append("Codigo", codigo)
					.append("Emisor", empleadoRegistrado).append("DepartamentoReceptor", dpto).append("Fecha", fecha)
					.append("Asunto", asunto).append("Mensaje", mensaje).append("Destinatarios", destinatarios));
			if (r.getInsertedId() != null) {
				resultado = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return resultado;
	}

	private int obtenerCodigo() {
		int resultado = 0;
		try {
			MongoCollection<Document> col = bd.getCollection("Mensaje");

			MongoCursor<Document> cursor = col
					.aggregate(Arrays.asList(Aggregates.group(null, Accumulators.max("Codigo", "$Codigo"))

					)).cursor();
			if (cursor.hasNext()) {
				Document d = cursor.next();
				resultado = d.getInteger("Codigo");
			}

			resultado++;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return resultado;
	}

	public boolean registrarEmpleado() {
		Boolean resultado = false;
		System.out.println("Introduce tu DNI");
		String empIntroducido = t.nextLine();

		try {
			MongoCollection<Document> col = bd.getCollection("Empleado");

			Bson campos = Projections.fields(Projections.include("DNI"), Projections.exclude("_id"));

			Bson filtro = Filters.and(Filters.eq("DNI", empIntroducido));

			MongoCursor<Document> cursor = col.find(filtro).projection(campos).sort(Sorts.ascending("DNI")).cursor();

			while (cursor.hasNext()) {
				Document d = cursor.next();
				if (d.getString("DNI") != null) {
					resultado = true;
					System.out.println("Registrado con exito con el usuario: " + d.getString("DNI"));
					empRegistrado = d.getString("DNI");

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public String recuperarEmpleadoRegistrado() {
		return empRegistrado;
	}

	public void mostrarInfo(String empleadoRegistrado) {

		try {
			// Nos conectamos a la colección con POJO
			MongoCollection<Document> col = bd.getCollection("Mensaje");

			col.aggregate(Arrays.asList(
					Aggregates.group("$Emisor",
							Accumulators.sum("numMensajesEnviados",1),
							Accumulators.min("FechaPrimerMensaje", "$Fecha"),
							Accumulators.max("FechaUltimoMensaje", "$Fecha")),
					Aggregates.project(Projections.fields(
							Projections.exclude("_id"))
							))
					)
			.forEach(doc->System.out.println(doc.toString()));
			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
