package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.mysql.jdbc.CommunicationsException;

import modelo.Libros;
import modelo.Prestamos;
import modelo.Socios;
import vista.Errores;

public class BaseDeDatos {
	private Errores err = new Errores();

	// -- MÉTODOS PRÉSTAMOS --
	public ArrayList<Prestamos> cargaPrestamos() {
		ArrayList<Prestamos> arrlPrestamos = new ArrayList<>();

		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select * from prestamos");

			if (registro == null) {
				err.baseDatosVacia();
			}
			// While se pone porque podría devolver más de una fila.
			while (registro.next()) {
				Prestamos nuevoPrestamo = new Prestamos();
				nuevoPrestamo.setId_prestamo(registro.getString("id_prestamo"));
				nuevoPrestamo.setId_biblioteca(registro.getString("id_biblioteca"));
				nuevoPrestamo.setId_socio(registro.getString("id_socio"));
				nuevoPrestamo.setId_libro(registro.getString("id_libro"));
				nuevoPrestamo.setFecha_prestamo(registro.getString("fecha_prestamo"));
				nuevoPrestamo.setFecha_entrega_prevista(registro.getString("fecha_entrega_prevista"));

				arrlPrestamos.add(nuevoPrestamo);
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {

			}
		}

		return arrlPrestamos;
	}
	
	public String obtenerTituloLibro(String idLibro, String idBiblioteca) {
		String titulo = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT titulo_libro FROM libros WHERE id_libro = '" + idLibro + "' AND"
					+ " id_biblioteca = '" + idBiblioteca + "'");

			if (registro.next()) {
				titulo = registro.getString("titulo_libro");
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

		return titulo;
	}
	
	public String obtenerNombreSocio(String idSocio, String idBiblioteca) {
		String nombre = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT nombre_socio FROM socios WHERE id_socio = '" + idSocio + "' AND"
					+ " id_biblioteca = '" + idBiblioteca + "'");

			if (registro.next()) {
				nombre = registro.getString("nombre_socio");
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

		return nombre;
	}
	
	public String obtenerApellidoSocio(String idSocio, String idBiblioteca) {
		String apellido = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;


		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT apellido_socio FROM socios WHERE id_socio = '" + idSocio + "' AND"
					+ " id_biblioteca = '" + idBiblioteca + "'");

			if (registro.next()) {
				apellido = registro.getString("apellido_socio");
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

		return apellido;
	}

	public String insertarPrestamo(String id_socio, String id_libro, String id_biblioteca) {
		Connection conexion = null;
		Statement consulta = null;
		String fechaFormateada = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			boolean prestamoExiste = false;

			ResultSet resultSet = consulta
					.executeQuery("SELECT id_prestamo FROM prestamos WHERE id_libro = '" + id_libro + "'");
			prestamoExiste = resultSet.next();

			if (!prestamoExiste) {
				// Obtener la fecha actual con Calendar
				Calendar calendar = Calendar.getInstance();
				java.util.Date fechaActual = calendar.getTime();

				// Formatear la fecha actual como 'YYYY-MM-DD'
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fechaFormateada = dateFormat.format(fechaActual);

				String fechaEntregaPrevista = calcularFechaEntregaPrevista(fechaFormateada);

				// Insertar la fecha actual en la base de datos
				consulta.executeUpdate(
				        "INSERT INTO prestamos (id_biblioteca, id_socio, id_libro, fecha_prestamo, fecha_entrega_prevista, fecha_entrega) "
				                + "VALUES ('" + id_biblioteca + "', '" + id_socio + "', '" + id_libro + "', '"
				                + fechaFormateada + "', '" + fechaEntregaPrevista + "', NULL)");	

				JOptionPane.showMessageDialog(null,
						"Se han insertado correctamente la fecha de prestamo y la fecha de entrega prevista en la tabla.", "Información", 
							JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null,
						"Este ejemplar ya está prestado. Por favor, espere a que el otro usuario lo devuelva o escoja otro libro.", "Aviso", 
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException error) {
			//error.printStackTrace();
			err.baseDatosNoConexion();
		}finally {
		    try {
		        if (conexion != null) {
		            conexion.close();
		        }
		    } catch (SQLException e) {
		       // e.printStackTrace();
		        err.baseDatosNoConexion();
		    }
		}


		return fechaFormateada;
	}

	public static String calcularFechaEntregaPrevista(String fechaPrestamo) {
		try {
			// Convertir la fecha de préstamo a Date usando SimpleDateFormat
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fecha = dateFormat.parse(fechaPrestamo);

			// Convertir la fecha de préstamo a Calendar
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);

			// Sumar 2 semanas a la fecha de préstamo
			calendar.add(Calendar.WEEK_OF_YEAR, 2);

			// Obtener la nueva fecha
			java.util.Date fechaEntregaPrevista = calendar.getTime();

			// Formatear la fecha como una cadena
			return dateFormat.format(fechaEntregaPrevista);

		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public String obtenerIdSocio(Libros libro, String nombreSocio, String apellidoSocio, String correoSocio,
			String idBib) {
		String id = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_socio" + " FROM socios" + " WHERE id_biblioteca = '" + idBib
					+ "' " + " AND nombre_socio = '" + nombreSocio + "'" + " AND apellido_socio = '" + apellidoSocio
					+ "'" + " AND email_socio = '" + correoSocio + "'");

			if (registro.next()) {
				id = registro.getString("id_socio");
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

		return id;
	}

	public String obtenerIdLibro(Libros libro, String idBib) {
		String id = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_libro" + " FROM libros" + " WHERE isbn_libro = '"
					+ libro.getIsbn() + "'" + " AND titulo_libro = '" + libro.getTitulo() + "'"
					+ " AND autores_libro = '" + libro.getAutores() + "'" + " AND id_biblioteca = '" + idBib + "' ");

			if (registro.next()) {
				id = registro.getString("id_libro");
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

		return id;
	}

	public boolean comprobarDatosSocio(String nombre, String apellido, String correo) {
		Connection conexion = null;
		Statement consulta = null;
		ResultSet resultadoNombre = null;
		ResultSet resultadoApellido = null;
		ResultSet resultadoCorreo = null;
		boolean nombreExiste = false;
		boolean apellidoExiste = false;
		boolean correoExiste = false;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();

			resultadoNombre = consulta.executeQuery("SELECT nombre_socio FROM socios WHERE nombre_socio = '" + nombre + "'");
			nombreExiste = resultadoNombre.next();

			resultadoApellido = consulta.executeQuery("SELECT apellido_socio FROM socios WHERE apellido_socio = '" + apellido + "'");
			apellidoExiste = resultadoApellido.next();

			resultadoCorreo = consulta.executeQuery("SELECT email_socio FROM socios WHERE email_socio = '" + correo + "'");
			correoExiste = resultadoCorreo.next();

		} catch (SQLException e) {
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				if (resultadoNombre != null) {
					resultadoNombre.close();
				}
				if (resultadoApellido != null) {
					resultadoApellido.close();
				}
				if (resultadoCorreo != null) {
					resultadoCorreo.close();
				}
				if (consulta != null) {
					consulta.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			}
		}

		// Devolver true si todos los campos existen en la base de datos
		if (nombreExiste && apellidoExiste && correoExiste) {
			return true;
		}
		return false;
	}
	
	public void borrarUnaUnidadStock(String idLibro, String idBib) {
	    Connection conexion = null;
	    Statement consulta = null;
	    
	    try {
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
	        consulta = conexion.createStatement();
	        consulta.executeUpdate("UPDATE libros SET stock_total = stock_total - 1 WHERE id_libro = '" + idLibro + "' "
	                        + "AND id_biblioteca = '" + idBib + "'");
	        
	    } catch (SQLException e) {
	        err.baseDatosNoConexion();
	    } finally {
	        try {
	            if (consulta != null) {
	                consulta.close();
	            }
	            if (conexion != null) {
	                conexion.close();
	            }
	            JOptionPane.showMessageDialog(null,
						"Se ha modificado correctamente el stock de tomos de este libro.", "Información", 
							JOptionPane.INFORMATION_MESSAGE);
	        } catch (SQLException e) {
	            err.baseDatosNoConexion();
	        }
	    }
	}

	// ------------------------------------------------------------------------------------------

	// -- MÉTODOS LOGIN --

	public boolean compruebaUsuario(String usuario, String contrasenia) {
		String id = "";
		boolean correcto = false;
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta
					.executeQuery("SELECT id_usuario" + " FROM usuarios" + " WHERE email_usuario = '" + usuario + "'");

			if (registro.next()) {
				id = registro.getString("id_usuario");

				correcto = compruebaContrasenia(id, contrasenia);

			}

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				err.baseDatosNoConexion();

			} catch (NullPointerException e) {
			}
		}

		return correcto;

	}

	public boolean compruebaContrasenia(String id, String contrasenia) {
		boolean correcto = false;
		String contra = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT material" + " FROM otros" + " WHERE id_otro = '" + id + "'");

			if (registro.next()) {
				contra = registro.getString("material");

				if (contra.equals(contrasenia)) {
					correcto = true;
				}
			}

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

		return correcto;
	}

	public String obtenBiblioteca(String usuario) {
		String idBib = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery(
					"SELECT id_biblioteca" + " FROM usuarios" + " WHERE email_usuario = '" + usuario + "'");

			if (registro.next()) {
				idBib = registro.getString("id_biblioteca");

			}

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

		return idBib;
	}
	
	
	// ------------------------------------------------------------------------------------------

	// -- MÉTODOS LIBROS --

	public ArrayList<Libros> cargaLibros(String consulta, String aux, String idBib) {
		ArrayList<Libros> arrlLibros = new ArrayList<>();

		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consultita = conexion.createStatement();
			// Lanzamos la consulta:
			registro = consultita.executeQuery(
					"SELECT id_libro, id_biblioteca, titulo_libro, autores_libro, isbn_libro, editorial_libro,"
							+ "genero_libro, idioma_libro, edicion_libro, ubicacion_libro, publicacion_libro, pais_libro,"
							+ "numPaginas_libro, SUM(stock_total) AS stock_total " + "FROM	libros " + "WHERE " + consulta
							+ " LIKE '%" + aux + "%' " + "AND id_biblioteca = '" + idBib + "' "
							+ "GROUP BY id_biblioteca, titulo_libro, autores_libro, isbn_libro, editorial_libro, genero_libro, idioma_libro, edicion_libro, "
							+ "ubicacion_libro, publicacion_libro, pais_libro, numPaginas_libro " + "ORDER BY "
							+ consulta);

			if (!registro.next()) {
				err.baseDatosVacia();
			}
			// While porque podría devolver más de una fila.
			while (registro.next()) {
				Libros nuevoLibro = new Libros();
				nuevoLibro.setIdLibro(registro.getString("id_libro"));
				nuevoLibro.setIdBiblioteca(registro.getString("id_biblioteca"));
				nuevoLibro.setIsbn(registro.getString("isbn_libro"));
				nuevoLibro.setTitulo(registro.getString("titulo_libro"));
				nuevoLibro.setAutores(registro.getString("autores_libro"));
				nuevoLibro.setEditorial(registro.getString("editorial_libro"));
				nuevoLibro.setGenero(registro.getString("genero_libro"));
				nuevoLibro.setIdioma(registro.getString("idioma_libro"));
				nuevoLibro.setEdicion(registro.getString("edicion_libro"));
				nuevoLibro.setPublicacion(registro.getString("publicacion_libro"));
				nuevoLibro.setPais(registro.getString("pais_libro"));
				nuevoLibro.setPaginas(registro.getString("numPaginas_libro"));
				nuevoLibro.setUbicacion(registro.getString("ubicacion_libro"));
				nuevoLibro.setStockTotal(registro.getInt("stock_total"));

				arrlLibros.add(nuevoLibro);
			}

		} catch (SQLException e) {			
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {

			}
		}

		return arrlLibros;
	}

	public void editarLibro(Libros libro, String id) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("UPDATE libros SET " + "isbn_libro='" + libro.getIsbn() + "', " + "titulo_libro='"
					+ libro.getTitulo() + "', " + "autores_libro='" + libro.getAutores() + "', " + "editorial_libro='"
					+ libro.getEditorial() + "', " + "genero_libro='" + libro.getGenero() + "', " + "idioma_libro='"
					+ libro.getIdioma() + "', " + "edicion_libro='" + libro.getEdicion() + "', " + "ubicacion_libro='"
					+ libro.getUbicacion() + "', " + "publicacion_libro='" + libro.getPublicacion() + "', "
					+ "pais_libro='" + libro.getPais() + "', " + "numPaginas_libro='" + libro.getPaginas() + "' "
					+ "WHERE id_libro='" + id + "'");

			err.confirmarUpdate();

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}
	}

	public void eliminarLibro(String id) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("DELETE FROM libros WHERE id_libro = '" + id + "'");

			err.confirmarEliminar();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

	}

	public void insertarLibro(Libros libro, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate(
					"INSERT INTO LIBROS (id_biblioteca, isbn_libro, titulo_libro, autores_libro, editorial_libro, "
							+ "genero_libro, idioma_libro, edicion_libro, ubicacion_libro, publicacion_libro, "
							+ "pais_libro, numPaginas_libro, stock_total) values ('" + idBib + "', '" + libro.getIsbn() + "', '"
							+ libro.getTitulo() + "', '" + libro.getAutores() + "', '" + libro.getEditorial() + "', '"
							+ libro.getGenero() + "', '" + libro.getIdioma() + "', '" + libro.getEdicion() + "', '"
							+ libro.getUbicacion() + "', '" + libro.getPublicacion() + "', '" + libro.getPais() + "', '"
							+ libro.getPaginas() + "', 1)");

			err.confirmarInsert();

		} catch (SQLException e) {
			e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {
			}
		}

	}
	
	public ArrayList<Libros> obtenerTodosLosLibros(String idBib) {
		ArrayList<Libros> arrlLibros = new ArrayList<>();

		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consultita = conexion.createStatement();
			// Lanzamos la consulta:
			registro = consultita.executeQuery(
					"SELECT id_libro, id_biblioteca, isbn_libro, titulo_libro, autores_libro, editorial_libro,"
							+ "genero_libro, idioma_libro, edicion_libro, publicacion_libro, pais_libro, numPaginas_libro"
							+ ", ubicacion_libro, stock_total " + "FROM	libros " + "WHERE id_biblioteca = '" + idBib + "' ");

			if (!registro.next()) {
				err.baseDatosVacia();
			}
			// While porque podría devolver más de una fila.
			while (registro.next()) {
				Libros nuevoLibro = new Libros();
				nuevoLibro.setIdLibro(registro.getString("id_libro"));
				nuevoLibro.setIdBiblioteca(registro.getString("id_biblioteca"));
				nuevoLibro.setIsbn(registro.getString("isbn_libro"));
				nuevoLibro.setTitulo(registro.getString("titulo_libro"));
				nuevoLibro.setAutores(registro.getString("autores_libro"));
				nuevoLibro.setEditorial(registro.getString("editorial_libro"));
				nuevoLibro.setGenero(registro.getString("genero_libro"));
				nuevoLibro.setIdioma(registro.getString("idioma_libro"));
				nuevoLibro.setEdicion(registro.getString("edicion_libro"));
				nuevoLibro.setPublicacion(registro.getString("publicacion_libro"));
				nuevoLibro.setPais(registro.getString("pais_libro"));
				nuevoLibro.setPaginas(registro.getString("numPaginas_libro"));
				nuevoLibro.setUbicacion(registro.getString("ubicacion_libro"));
				nuevoLibro.setStockTotal(registro.getInt("stock_total"));

				arrlLibros.add(nuevoLibro);
			}

		} catch (SQLException e) {			
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {

			}
		}

		return arrlLibros;
	}

	// ------------------------------------------------------------------------------------------

	// -- MÉTODOS SOCIOS --
	
	public ArrayList<Socios> cargaSocios(String consulta, String aux, String idBib) {
		ArrayList<Socios> arrlSocios = new ArrayList<>();

		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consultita = conexion.createStatement();
			// Lanzamos la consulta:
			registro = consultita.executeQuery(
					"SELECT id_socio, id_biblioteca, nombre_socio, apellido_socio, dni_socio, direccion_socio,"
							+ "tlf_socio, email_socio, lista_negra, fechaNad_socio"
							+ " FROM socios " + "WHERE " + consulta
							+ " LIKE '%" + aux + "%' " + "AND id_biblioteca = '" + idBib + "' "
							+ "ORDER BY " + consulta);

			if (!registro.next()) {
				err.baseDatosVacia();
			}
			// While porque podría devolver más de una fila.
			while (registro.next()) {
				Socios nuevoSocio = new Socios();
				
				nuevoSocio.setIdSocio(registro.getString("id_socio"));
				nuevoSocio.setIdBiblioteca(registro.getString("id_biblioteca"));
				nuevoSocio.setNombre(registro.getString("nombre_socio"));
				nuevoSocio.setApellido(registro.getString("apellido_socio"));
				nuevoSocio.setDni(registro.getString("dni_socio"));
				nuevoSocio.setDireccion(registro.getString("direccion_socio"));
				nuevoSocio.setTelefono(registro.getString("tlf_socio"));
				nuevoSocio.setEmail(registro.getString("email_socio"));
				nuevoSocio.setListaNegra(registro.getBoolean("lista_negra"));
				nuevoSocio.setFechaNacimiento(registro.getString("fechaNad_socio"));
				
				arrlSocios.add(nuevoSocio);
			}

		} catch (SQLException e) {			
			//e.printStackTrace();
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				err.baseDatosNoConexion();
			} catch (NullPointerException e) {

			}
		}

		return arrlSocios;
	}
	
	
	// ------------------------------------------------------------------------------------------
}
