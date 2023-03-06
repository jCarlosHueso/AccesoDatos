package Examen;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
			// Conectar con el cluster AccesoDatos de MongoAtlas
			cluster = MongoClients.create(new ConnectionString(cadenaCNX));

			// Registrar las clase para poder trabajar
			// con objetos (POJO).
			// SOLAMENTE PARA INSERTAR Y RECUPERAR
			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();
			CodecRegistry registro = fromRegistries(getDefaultCodecRegistry(), fromProviders(proveedor));
			// Obtenemos la base de datos, si no existe se crea
			bd = cluster.getDatabase("bricolaje").withCodecRegistry(registro);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public MongoDatabase getBd() {
		return bd;
	}

	public void cerrar() {
		try {
			cluster.close();

		} catch (MongoException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public Productos obtenerProducto(String codigoP) {	
Productos resultado = null;
try {
	MongoCollection<Productos> col = bd.getCollection("producto",Productos.class);
	
	Bson filtro = Filters.eq("codigo",codigoP);
	
	resultado = col.find(filtro).first();
	
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}
return resultado;

	}

	public boolean crearProducto(Productos p) {

		boolean resultado = false;
		try {
			//Recuperar la colección en la que vamos a insertar el artista
			MongoCollection<Document> coleccion = 
					bd.getCollection("producto");
			
			InsertOneResult r = coleccion.insertOne(
					new Document()
					.append("codigo", p.getCodigo())
					.append("nombre", p.getNombre())
					.append("precio", p.getPrecio())
					.append("stock", p.getStock()));
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;

	}

	public int obtenerUltimoNumero() {

		int resultado = 0;
		try {			
			MongoCollection<Document> col = bd.getCollection("facturas");
			
			MongoCursor<Document> cursor = col.aggregate(
					Arrays.asList(
							Aggregates.group(null, Accumulators.max("codigo", "$codigo")) )).cursor();
			
			if(cursor.hasNext()) {
				Document d = cursor.next();
				resultado = d.getInteger("codigo");
			}
			resultado++;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado+1;


	}
	
	public ArrayList<Productos> obtenerProductos() {
		
		
			// TODO Auto-generated method stub
			ArrayList<Productos> resultado = new ArrayList<>() ;
			try {
				MongoCollection<Productos> col = 
						bd.getCollection("producto",Productos.class);
				
				col.find().into(resultado);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return resultado;
		}

	public boolean crearFactura(Facturas factura) {

				boolean resultado = false;
				
				try {
					//Recuperar la colección en la que vamos a insertar el artista
					MongoCollection<Document> coleccion = 
							bd.getCollection("factura");
					
					InsertOneResult r = coleccion.insertOne(
							new Document()
							.append("numero", factura.getNumero())
							.append("fecha", factura.getFecha())
							.append("cliente", factura.getCliente())
							.append("detalle", factura.getDetalle())
							.append("facturaAnulacion", factura.getFacturaAnulacion()));
					
					if(r.getInsertedId()!=null) {
						resultado = true;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				return resultado;			
	}

	public void crearInformeFactura() {
	
	}

	public ArrayList<Facturas> obtenerFacturas() {
		// TODO Auto-generated method stub
		ArrayList<Facturas> resultado = new ArrayList<>() ;
		try {
			MongoCollection<Facturas> col = 
					bd.getCollection("factura",Facturas.class);
			col.find().into(resultado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Facturas obtenerFactura(int num) {
		Facturas resultado = null;
		try {
			MongoCollection<Facturas> col = bd.getCollection("factura",Facturas.class);
			
			Bson filtro = Filters.eq("numero",num);
			
			resultado = col.find(filtro).first();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	
}
