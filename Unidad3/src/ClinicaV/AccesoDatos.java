package ClinicaV;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.print.Doc;

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
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

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
	public ArrayList<Mascota> obtenerMascotas() {
		// TODO Auto-generated method stub
		ArrayList<Mascota> resultado = new ArrayList<>() ;
		try {
			MongoCollection<Mascota> col = bd.getCollection("mascotas",Mascota.class);
			
			col.find().into(resultado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public Mascota obtenerMascota(int codigo) {
		// TODO Auto-generated method stub
		Mascota resultado = null;
		try {
			MongoCollection<Mascota> col = bd.getCollection("mascotas",Mascota.class);
			
			Bson filtro = Filters.eq("codigo",codigo);
			
			resultado = col.find(filtro).first();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public int obtenerCodigoTratamiento() {
		// TODO Auto-generated method stub
		int resultado=0;
		try {
			
			MongoCollection<Document> col = bd.getCollection("mascotas");
			Document d = col.aggregate(Arrays.asList(
					Aggregates.group(null, 
							Accumulators.max("codigo", 
									new Document("$max","$tratamientos.codigo"))))).first();
			if(d.get("codigo")!=null)
				resultado = d.getInteger("codigo");
						
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado +1;
				
	}
	public boolean crearTratamiento(Mascota m, Tratamiento tr) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Mascota> col = bd.getCollection("mascotas",Mascota.class);
			
			Bson filtro = Filters.eq("codigo",m.getCodigo());
			Bson modif = Updates.combine(Updates.addToSet("tratamientos", tr));
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
	public ArrayList<Object[]> obtenerTratamientos() {
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList<>();
		try {
			MongoCollection<Document> col = bd.getCollection("mascotas");
			Bson campos = Projections.fields(
					Projections.exclude("_id"),
					Projections.include("tratamientos.codigo","nombre",
							"tratamientos.fecha"));
			
			MongoCursor<Document> cursor = col.find().projection(campos).cursor();
			while(cursor.hasNext()) {				
				Document d = cursor.next();
				//System.out.println(d.toJson());
				ArrayList<Document> tratamientos= (ArrayList<Document>) d.get("tratamientos");
				for (Document tr : tratamientos) {
					resultado.add(new Object[] {tr.getInteger("codigo"),
							d.getString("nombre"),tr.getDate("fecha")});
				}
								
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public Object[] obtenerInfoTratamiento(int codigo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Object[] resultado = null;
		try {
			MongoCollection<Document> col = bd.getCollection("mascotas");
			Bson filtro = Filters.eq("tratamientos.codigo",codigo);
			Bson salida = Projections.fields(
					Projections.exclude("_id"),
					Projections.include("nombre","codigo","tipo","tratamientos","datosCliente"));
			Document d = col.aggregate(Arrays.asList(
					Aggregates.match(filtro),
					Aggregates.unwind("$tratamientos"),
					Aggregates.match(filtro),
					//ColeccionPrincipal, clave externa, clave primaria, alias
					Aggregates.lookup("clientes", "cliente", "codigo","datosCliente"),
					Aggregates.project(salida)
					)).first();
			//System.out.println(d.toJson());
			ArrayList<Document> clientes = (ArrayList<Document>) d.get("datosCliente");
			Cliente c = new Cliente(clientes.get(0).getInteger("codigo"), 
					clientes.get(0).getString("nombre"), clientes.get(0).getString("email"));
			Mascota m = new Mascota(d.getInteger("codigo"),c.getCodigo(), 
					d.getString("nombre"), d.getString("tipo"), new ArrayList<>());
			Document dTratamiento = (Document) d.get("tratamientos");
			Tratamiento tr =  new Tratamiento(dTratamiento.getInteger("codigo"), 
					dTratamiento.getDate("fecha"), dTratamiento.getString("descripcion"));
			
			resultado = new Object[] {c,m,tr};
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public Tratamiento obtenerTratamiento(int codigo) {
		// TODO Auto-generated method stub
		Tratamiento resultado = null;
		try {
			MongoCollection<Document> col = bd.getCollection("mascotas");
			
			Bson filtro = Filters.in("tratamientos.codigo",codigo);
			Bson campos = Projections.fields(
					Projections.excludeId(),
					Projections.elemMatch("tratamientos",Filters.eq("codigo",codigo)));
			Document d = col.find(filtro).projection(campos).first();
			
			if(d!=null) {
				System.out.println(d.toJson());
				ArrayList<Document> dTrat = (ArrayList<Document>) d.get("tratamientos");
				resultado = new Tratamiento(dTrat.get(0).getInteger("codigo"), 
						dTrat.get(0).getDate("fecha"), dTrat.get(0).getString("descripcion"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean borrarTratamiento(Tratamiento tr) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("mascotas");
			
			Bson filtro = Filters.in("tratamientos.codigo",tr.getCodigo());
			
			Bson camposModif = Updates.combine(
					Updates.pull("tratamientos", new Document("codigo",tr.getCodigo())));
			
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
	public boolean modificarTratamiento(Tratamiento tr) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Document> col = bd.getCollection("mascotas");
			
			Bson filtro = Filters.in("tratamientos.codigo",tr.getCodigo());
			
			Bson camposModif = Updates.combine(
					Updates.set("tratamientos.$[elem].descripcion", tr.getDescripcion()),
					Updates.set("tratamientos.$[elem].fecha", new Date())
					);
			 
			UpdateOptions opcionesModif = new UpdateOptions().arrayFilters(
					Arrays.asList(Filters.eq("elem.codigo",tr.getCodigo())));
			UpdateResult r = col.updateOne(filtro, camposModif,opcionesModif);
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
