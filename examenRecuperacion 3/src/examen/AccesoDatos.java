package examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AccesoDatos {

	public AccesoDatos() {

	}

	public ArrayList<empleado> obtenerEmpleados() {
		ArrayList<empleado> resultado = new ArrayList<>();

		BufferedReader f = null;

		try {
			f = new BufferedReader(new FileReader("empleados.txt"));

			String linea;
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				empleado emp = new empleado(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3]);
				resultado.add(emp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay empleados");
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

	public empleado obtenerEmpleado(int numEmp) {

		empleado resultado = null;

		ObjectInputStream f = null;

		try {

			f = new ObjectInputStream(new FileInputStream("empleados.obj"));

			while (true) {

				empleado emp = (empleado) f.readObject();

				if (emp.getNumEmp() == numEmp) {

					resultado = new empleado();

					resultado.setNumEmp(numEmp);
					resultado.setDni(emp.getDni());
					resultado.setNombre(emp.getNombre());
					resultado.setDepartamento(emp.getDepartamento());
				}
			}
		} catch (EOFException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("El fchero aún no existe\ncreando fichero..");
			// e.printStackTrace();

		} catch (ClassNotFoundException e) {
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

	public boolean addEmp(empleado emp) {
		boolean resultado = false;

		ObjectOutputStream f = null;

		try {
			File fichero = new File("empleados.obj");
			if (fichero.exists()) {

				f = new MiObjectOutputStream(new FileOutputStream("empleados.obj", true));

			} else {

				// fichero.createNewFile();

				f = new ObjectOutputStream(new FileOutputStream("empleados.obj", true));
			}

			f.writeObject(emp);

			resultado = true;

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

	public boolean actualizarDept(empleado emp) {

		boolean resultado = false;

		ObjectInputStream fO = null;
		ObjectOutputStream fTmp = null;

		try {
			fO = new ObjectInputStream(new FileInputStream("empleados.obj"));
			fTmp = new ObjectOutputStream(new FileOutputStream("empleados.tmp", false));

			while (true) {
				empleado emple = (empleado) fO.readObject();
				if (emple.getNumEmp() != emp.getNumEmp()) {
					fTmp.writeObject(emple);
				} else {

					emple.setDepartamento(emp.getDepartamento());

					fTmp.writeObject(emple);

				}
			}

		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fO != null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fTmp != null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		File fOriginal = new File("empleados.obj");
		if (fOriginal.delete()) {
			File fTemp = new File("empleados.tmp");
			if (fTemp.renameTo(fOriginal)) {
				resultado = true;
			} else {
				System.out.println("Error al renombrar " + "empleados.tmp");
			}
		} else {
			System.out.println("Error al borrar " + "empleados.obj");
		}

		return resultado;

	}

	public ArrayList<empleado> obtenerEmpleadosObj() {
		ArrayList<empleado> resultado = new ArrayList<>();

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream("empleados.obj"));

			while (true) {

				empleado emp = (empleado) f.readObject();
				empleado emp2 = new empleado();
				emp2.setNumEmp(emp.getNumEmp());
				emp2.setDni(emp.getDni());
				emp2.setNombre(emp.getNombre());
				emp2.setDepartamento(emp.getDepartamento());

				resultado.add(emp2);

			}
		} catch (EOFException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
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

	public boolean comprobarUsuario(int id, String pwd) {
		boolean resultado = false;

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream("empleados.obj"));
			while (true) {
				empleado emp = (empleado) f.readObject();

				if (emp.getNumEmp() == id) {
					if (emp.getDni().equalsIgnoreCase(pwd)) {
						resultado = true;
					} else {
						System.out.println("contraseña incorrecta");
					}

				}
			}

		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<empleado> getEmpleadosDpto(String dpto) {
		ArrayList<empleado> resultado = new ArrayList<empleado>();

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream("empleados.obj"));
			while (true) {
				empleado emp = (empleado) f.readObject();

				if (emp.getDepartamento().equalsIgnoreCase(dpto)) {

					resultado.add(emp);

				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean enviarMensaje(mensaje msg) {
		boolean resultado = false;

		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile("empleados.bin", "rw");

			f.seek(f.length());

			f.writeInt(msg.getDe());
			
			f.writeInt(msg.getPara());
			
			f.writeLong(msg.getFecha());

			StringBuffer texto200 = new StringBuffer(msg.getMensaje());
			texto200.setLength(200);
			f.writeChars(texto200.toString());

			f.writeBoolean(msg.isBorrado());
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

	public boolean borrarMensajesrevibidos(int usLogueado) {

		boolean resultado=false;
		
		
		
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile("empleados.bin", "rw");

			while (true) {
				int numEmp = f.readInt();
				if (numEmp == usLogueado) {
					f.seek(f.getFilePointer() + 412);
					f.writeBoolean(false);
					return true;
				} else {
					f.seek(f.getFilePointer() + 413);
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