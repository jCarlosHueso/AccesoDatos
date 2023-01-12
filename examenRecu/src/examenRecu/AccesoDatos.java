package examenRecu;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class AccesoDatos {
	String fBin = "multas.bin";

	public ArrayList<radar> obtenerMultas(String nombreArchivo) {
		ArrayList<radar> resultado = new ArrayList<radar>();
		File f = new File(nombreArchivo.replace(" ", "").toLowerCase() + ".xml");

		if (f.exists()) {
			try {
				Unmarshaller um = JAXBContext.newInstance(radar.class).createUnmarshaller();
				radar radar = (radar) um.unmarshal(f);
				resultado.add(radar);

			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("no existe el archivo");
		}

		return resultado;
	}

	public multaBin obtenerMultasBin(String string) {

		multaBin resultado = null;

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(fBin, "rw");
			// 7 caracteres = 14 bytes
			// n multas int = 4
			// importe float 4 bytes
			// fecha 8
			while (true) {
				String matric = "";
				for (int i = 0; i < 7; i++) {
					matric = matric + f.readChar();
				}
				if (matric.equalsIgnoreCase(string)) {
					resultado.setMatricula(matric);
					resultado.setnMultas(f.readInt());
					resultado.setImporte(f.readFloat());
					resultado.setFechaUltAct(new Date(f.readLong()));

				} else {
					f.seek(f.getFilePointer() + 16);
				}
			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated catch block

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean addMulta(String matricula, int vel) {
		boolean resultado = false;
		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(fBin, "rw");

			f.seek(f.length());

			StringBuffer texto7 = new StringBuffer(matricula);
			texto7.setLength(7);
			f.writeChars(texto7.toString());
			f.writeInt(1);
			if (vel < 130) {
				f.writeFloat(100);
			} else if (vel > 130 && vel < 150) {
				f.writeFloat(300);
			} else if (vel > 150) {
				f.writeFloat(500);
			}

			f.writeLong(new Date().getTime());
			resultado = true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean actualizarMulta(String matricula, int vel) {
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(fBin, "rw");

			while (true) {
				String matric = "";
for (int i = 0; i < 7; i++) {
	matric = matric + f.readChar();
	
}
				if (matric == matricula) {

					// 7 caracteres = 14 bytes
					// n multas int = 4
					// importe float 4 bytes
					// fecha 8
					
					int nmultas = f.readInt() +1;
					
					f.seek(f.getFilePointer()-4);
					f.writeInt(nmultas);
					float gasto = f.readFloat();
					f.seek(f.getFilePointer()-4);
					if (vel < 130) {
						f.writeFloat(gasto +100);
					} else if (vel > 130 && vel < 150) {
						f.writeFloat(gasto +300);
					} else if (vel > 150) {
						f.writeFloat(gasto +500);
					}	
					return true;
				} else {
					// SAltamos al siguiente id
					f.seek(f.getFilePointer() + 209);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

}
