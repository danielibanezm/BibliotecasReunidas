package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.mysql.jdbc.CommunicationsException;

import modelo.Libros;
import modelo.Prestamos;
import modelo.InformacionRecibo;
import modelo.Socios;
import modelo.Usuarios;
import vista.Errores;

public class BaseDeDatos {
	private Errores err = new Errores();

	// -- MÉTODOS PRÉSTAMOS --
	public ArrayList<Prestamos> cargaPrestamos(String idBiblioteca) {
		ArrayList<Prestamos> arrlPrestamos = new ArrayList<>();

		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery(
					"select id_prestamo, id_biblioteca, id_socio, id_libro, fecha_prestamo, fecha_entrega_prevista"
							+ " FROM prestamos" + " WHERE id_biblioteca = '" + idBiblioteca + "' order by id_prestamo");

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
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
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
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
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
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
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
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return apellido;
	}

	public int comprobarNumeroPrestamos(String idBiblioteca, String idSocio, String idLibro) {
		int numPrestamos = 0;
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta
					.executeQuery("SELECT id_socio, id_biblioteca, COUNT(*) as cantidad_prestamos FROM prestamos"
							+ " WHERE id_socio = '" + idSocio + "' AND id_biblioteca = '" + idBiblioteca + "'"
							+ " GROUP BY id_socio, id_biblioteca");

			if (registro.next()) {
				numPrestamos = Integer.parseInt(registro.getString("cantidad_prestamos"));
			}

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return numPrestamos;

	}

	public boolean insertarPrestamo(String id_socio, String id_libro, String id_biblioteca) {
		Connection conexion = null;
		Statement consulta = null;
		String fechaFormateada = null;
		boolean insertRealizado = false;

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
						"INSERT INTO prestamos (id_biblioteca, id_socio, id_libro, fecha_prestamo, fecha_entrega_prevista) "
								+ "VALUES ('" + id_biblioteca + "', '" + id_socio + "', '" + id_libro + "', '"
								+ fechaFormateada + "', '" + fechaEntregaPrevista + "')");

				JOptionPane.showMessageDialog(null,
						"Se han insertado correctamente la fecha de prestamo y la fecha de entrega prevista en la tabla.",
						"Información", JOptionPane.INFORMATION_MESSAGE);

				err.confirmarInsert();
				insertRealizado = true;
			} else {
				JOptionPane.showMessageDialog(null,
						"No queda stock de este libro en la biblioteca. Por favor, escoja otro libro o espere a que otro socio devuelva un ejemplar.",
						"Aviso", JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException error) {
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			}
		}

		return insertRealizado;
	}

	public String calcularFechaEntregaPrevista(String fechaPrestamo) {
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

		} catch (java.text.ParseException e) {
			return null;
		}
	}

	public void verificarFechaDevolucion(String idPrestamo, String idSocio, String idBiblioteca, String idLibro) {
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();

			registro = consulta.executeQuery("SELECT fecha_entrega_prevista FROM prestamos WHERE id_prestamo ='"
					+ idPrestamo + "' AND id_biblioteca ='" + idBiblioteca + "' AND id_libro = '" + idLibro + "'");

			if (registro.next()) {
				String fechaEntregaPrevista = registro.getString("fecha_entrega_prevista");

				// Obtener la fecha actual
				Calendar calendar = Calendar.getInstance();
				java.util.Date fechaActual = calendar.getTime();

				// Convertir la fecha de entrega prevista a Date
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date fechaEntrega = dateFormat.parse(fechaEntregaPrevista);

				// Comparar las fechas
				if (fechaActual.after(fechaEntrega)) {
					System.out.println("Hola");
					JOptionPane.showMessageDialog(null,
							"Este ejemplar se ha devuelto fuera de plazo. El socio tendrá un recargo del doble de mensualidad en la próxima cuota.",
							"Aviso", JOptionPane.WARNING_MESSAGE);

					insertarMulta(idSocio, idBiblioteca);
				} else {
					JOptionPane.showMessageDialog(null, "El libro se ha devuelto correctamente dentro del plazo.",
							"Información", JOptionPane.INFORMATION_MESSAGE);
				}

				borrarPrestamo(idPrestamo, idSocio, idBiblioteca, idLibro);
			}
		} catch (SQLException e) {
		} catch (java.text.ParseException e) {
		} finally {
			try {
				if (registro != null) {
					registro.close();
				}
				if (consulta != null) {
					consulta.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public void borrarPrestamo(String idPrestamo, String idSocio, String idBiblioteca, String idLibro) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("DELETE FROM prestamos WHERE id_prestamo = '" + idPrestamo
					+ "' AND id_biblioteca = '" + idBiblioteca + "' AND id_libro = '" + idLibro + "'");

			err.confirmarDeletePrestamos();
		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public void insertarMulta(String idSocio, String idBiblioteca) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("UPDATE multas SET multa_obtenida = '1' " + "WHERE id_socio = '" + idSocio
					+ "' AND id_biblioteca = '" + idBiblioteca + "'");

			err.confirmarInsert();
		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public String obtenerIdSocioDesdeNombre(String nombreSocio, String apellidoSocio, String idBib) {
		String id = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_socio" + " FROM socios" + " WHERE id_biblioteca = '" + idBib
					+ "' AND nombre_socio = '" + nombreSocio + "' AND apellido_socio = '" + apellidoSocio + "'");

			if (registro.next()) {
				id = registro.getString("id_socio");
			}

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return id;
	}

	public String obtenerIdSocioDesdeLibro(Libros libro, String nombreSocio, String apellidoSocio, String correoSocio,
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
					+ "' AND email_socio = '" + correoSocio + "'");

			if (registro.next()) {
				id = registro.getString("id_socio");
			}

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
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
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
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

			resultadoNombre = consulta
					.executeQuery("SELECT nombre_socio FROM socios WHERE nombre_socio = '" + nombre + "'");
			nombreExiste = resultadoNombre.next();

			resultadoApellido = consulta
					.executeQuery("SELECT apellido_socio FROM socios WHERE apellido_socio = '" + apellido + "'");
			apellidoExiste = resultadoApellido.next();

			resultadoCorreo = consulta
					.executeQuery("SELECT email_socio FROM socios WHERE email_socio = '" + correo + "'");
			correoExiste = resultadoCorreo.next();

		} catch (SQLException e) {
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
			consulta.executeUpdate("UPDATE libros SET stock_total = stock_total - 1 WHERE id_libro = '" + idLibro
					+ "' AND id_biblioteca = '" + idBib + "'");

		} catch (SQLException e) {
		} finally {
			try {
				if (consulta != null) {
					consulta.close();
				}
				if (conexion != null) {
					conexion.close();
				}
				JOptionPane.showMessageDialog(null, "Se ha modificado correctamente el stock de tomos de este libro.",
						"Información", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
			}
		}
	}

	public void actualizarStock(String idLibro, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();
			consulta.executeUpdate("UPDATE libros SET stock_total = stock_total + 1 WHERE id_libro = '" + idLibro
					+ "' AND id_biblioteca = '" + idBib + "'");

		} catch (SQLException e) {
		} finally {
			try {
				if (consulta != null) {
					consulta.close();
				}
				if (conexion != null) {
					conexion.close();
				}
				JOptionPane.showMessageDialog(null, "Se ha modificado correctamente el stock de tomos de este libro.",
						"Información", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
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
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
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
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
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
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return idBib;
	}

	// ------------------------------------------------------------------------------------------

	// -- MÉTODOS LIBROS --
	public ArrayList<Libros> cargaLibros(String idBib) {
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
							+ "numPaginas_libro, SUM(stock_total) AS stock_total " + "FROM libros "
							+ "WHERE id_biblioteca = " + idBib + " GROUP BY titulo_libro ORDER BY titulo_libro");

			if (!registro.next()) {
				// err.baseDatosVacia();
			}
			// While porque podría devolver más de una fila.
			while (registro.next()) {

				Libros nuevoLibro = new Libros();
				nuevoLibro.setIdLibro(registro.getString("id_libro"));
				nuevoLibro.setIdBiblioteca(registro.getString("id_biblioteca"));
				nuevoLibro.setTitulo(registro.getString("titulo_libro"));
				nuevoLibro.setAutores(registro.getString("autores_libro"));
				nuevoLibro.setIsbn(registro.getString("isbn_libro"));
				nuevoLibro.setEditorial(registro.getString("editorial_libro"));
				nuevoLibro.setGenero(registro.getString("genero_libro"));
				nuevoLibro.setIdioma(registro.getString("idioma_libro"));
				nuevoLibro.setEdicion(registro.getString("edicion_libro"));
				nuevoLibro.setUbicacion(registro.getString("ubicacion_libro"));
				nuevoLibro.setPublicacion(registro.getString("publicacion_libro"));
				nuevoLibro.setPais(registro.getString("pais_libro"));
				nuevoLibro.setPaginas(registro.getString("numPaginas_libro"));
				nuevoLibro.setStockTotal(registro.getInt("stock_total"));

				arrlLibros.add(nuevoLibro);
			}

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return arrlLibros;
	}

	public void editarLibro(Libros libro, String id, String idBib) {
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
					+ "WHERE id_libro='" + id + "'" + " AND id_biblioteca = '" + idBib + "' ");

			err.confirmarUpdate();

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public void eliminarLibro(String id, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate(
					"DELETE FROM libros WHERE id_libro = '" + id + "'" + " AND id_biblioteca = '" + idBib + "' ");

			err.confirmarEliminar();

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
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
							+ "pais_libro, numPaginas_libro, stock_total) values ('" + idBib + "', '" + libro.getIsbn()
							+ "', '" + libro.getTitulo() + "', '" + libro.getAutores() + "', '" + libro.getEditorial()
							+ "', '" + libro.getGenero() + "', '" + libro.getIdioma() + "', '" + libro.getEdicion()
							+ "', '" + libro.getUbicacion() + "', '" + libro.getPublicacion() + "', '" + libro.getPais()
							+ "', '" + libro.getPaginas() + "', 1)");

			err.confirmarInsert();

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
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
							+ ", ubicacion_libro, stock_total " + "FROM	libros " + "WHERE id_biblioteca = '" + idBib
							+ "' ");

			if (!registro.next()) {
				err.baseDatosVacia();
			}
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
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {

			}
		}

		return arrlLibros;
	}
	// ------------------------------------------------------------------------------------------

	// -- MÉTODOS SOCIOS --
	public ArrayList<Socios> cargaSocios(String idBib) {
		ArrayList<Socios> arrlSocios = new ArrayList<>();

		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consultita = conexion.createStatement();

			registro = consultita.executeQuery(
					"SELECT id_socio, id_biblioteca, nombre_socio, apellido_socio, dni_socio, direccion_socio,"
							+ "tlf_socio, email_socio, lista_negra, fechaNad_socio" + " FROM socios " + "WHERE "
							+ "id_biblioteca = '" + idBib + "' " + "ORDER BY nombre_socio");

			if (!registro.next()) {
				err.baseDatosVacia();
			}

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
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return arrlSocios;
	}

	public String obtenerIdSocio(Socios socio, String idBib) {
		String id = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_socio" + " FROM socios" + " WHERE nombre_socio = '"
					+ socio.getNombre() + "'" + " AND apellido_socio = '" + socio.getApellido() + "'"
					+ " AND dni_socio = '" + socio.getDni() + "'" + " AND id_biblioteca = '" + idBib + "' ");

			if (registro.next()) {
				id = registro.getString("id_socio");
			}

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return id;
	}

	public void eliminarSocio(String id, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate(
					"DELETE FROM socios WHERE id_socio = '" + id + "'" + " AND id_biblioteca = '" + idBib + "' ");

			err.confirmarEliminar();

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	public void editarSocio(Socios socio, String id, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("UPDATE socios SET " + "nombre_socio='" + socio.getNombre() + "', "
					+ "apellido_socio='" + socio.getApellido() + "', " + "dni_socio='" + socio.getDni() + "', "
					+ "direccion_socio='" + socio.getDireccion() + "', " + "tlf_socio='" + socio.getTelefono() + "', "
					+ "email_socio='" + socio.getEmail() + "', " + "lista_negra=" + socio.isListaNegra() + ", "
					+ "fechaNad_socio='" + socio.getFechaNacimiento() + "' " + "WHERE id_socio='" + id + "'"
					+ " AND id_biblioteca = '" + idBib + "' ");

			err.confirmarUpdate();

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public void insertarSocio(Socios socio, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate(
					"INSERT INTO SOCIOS (id_biblioteca, nombre_socio, apellido_socio, dni_socio, direccion_socio, "
							+ "tlf_socio, email_socio, lista_negra, fechaNad_socio) VALUES ('" + idBib + "', '"
							+ socio.getNombre() + "', '" + socio.getApellido() + "', '" + socio.getDni() + "', '"
							+ socio.getDireccion() + "', '" + socio.getTelefono() + "', '" + socio.getEmail() + "', "
							+ socio.isListaNegra() + ", '" + socio.getFechaNacimiento() + "')");

			err.confirmarInsert();

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}
	// ------------------------------------------------------------------------------------------

	// -- USUARIOS --
	public ArrayList<Usuarios> cargaUsuarios(String idBib) {
		ArrayList<Usuarios> arrlUsers = new ArrayList<>();

		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consultita = conexion.createStatement();
			// Lanzamos la consulta:
			registro = consultita.executeQuery(
					"SELECT u.id_usuario, u.id_biblioteca, u.email_usuario, o.material " + "FROM usuarios u, otros o "
							+ "WHERE u.id_usuario = o.id_otro AND u.id_biblioteca = o.id_biblioteca "
							+ "AND u.id_biblioteca = " + idBib);

			if (!registro.next()) {
				err.baseDatosVacia();
			}
			// While porque podría devolver más de una fila.
			while (registro.next()) {
				Usuarios nuevoUser = new Usuarios();

				nuevoUser.setIdUsuario(registro.getString("u.id_usuario"));
				nuevoUser.setIdBib(registro.getString("u.id_biblioteca"));
				nuevoUser.setEmail(registro.getString("u.email_usuario"));
				nuevoUser.setContrasenia(registro.getString("o.material"));

				arrlUsers.add(nuevoUser);
			}

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return arrlUsers;
	}

	public String obtenerIdUser(Usuarios user, String idBib) {
		String id = null; // Inicializar el ID como nulo
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_usuario FROM usuarios WHERE email_usuario = '" + user.getEmail()
					+ "' AND id_biblioteca = '" + idBib + "' ");

			if (registro.next()) {
				id = registro.getString("id_usuario");
			}

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return id;
	}

	public void insertarUsuario(Usuarios nuevoUser, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("INSERT INTO USUARIOS (id_biblioteca, email_usuario) VALUES ('" + idBib + "', '"
					+ nuevoUser.getEmail() + "')");

			err.confirmarInsert();

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	public void insertarContrasenia(Usuarios nuevoUser, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("INSERT INTO OTROS (id_biblioteca, material) VALUES ('" + idBib + "', '"
					+ nuevoUser.getContrasenia() + "')");

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public void editarEmail(Usuarios user, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("UPDATE usuarios SET " + "email_usuario='" + user.getEmail() + "' WHERE id_usuario='"
					+ user.getIdUsuario() + "' AND id_biblioteca = '" + idBib + "'");

			err.confirmarUpdate();

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	public void editarContrasenia(Usuarios user, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("UPDATE otros SET material='" + user.getContrasenia() + "' WHERE id_otro='"
					+ user.getIdUsuario() + "' AND id_biblioteca = '" + idBib + "'");

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	public void eliminarUsuario(String id, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate(
					"DELETE FROM usuarios WHERE id_usuario = '" + id + "'" + " AND id_biblioteca = '" + idBib + "' ");

			err.confirmarEliminar();

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	public void eliminarContrasenia(String id, String idBib) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate(
					"DELETE FROM otros WHERE id_otro = '" + id + "'" + " AND id_biblioteca = '" + idBib + "' ");

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	// ------------------------------------------------------------------------------------------

	// -- MÉTODOS RECIBOS --
	public ArrayList<InformacionRecibo> cargaInfoRecibos(String idBiblioteca) {
		ArrayList<InformacionRecibo> arrlRecibos = new ArrayList<>();

		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT r.id_recibo, s.nombre_socio, s.apellido_socio, s.dni_socio, "
					+ "b.calle_biblioteca, b.provincia_biblioteca, b.codigoPostal_biblioteca, "
					+ "b.tlf_biblioteca, r.pagado, m.multa_obtenida "
					+ "FROM recibos r, bibliotecas b, socios s, multas m " + "WHERE r.id_biblioteca = '" + idBiblioteca
					+ "' " + "AND r.id_biblioteca = b.id_biblioteca " + "AND r.id_biblioteca = m.id_biblioteca "
					+ "AND r.id_socio = s.id_socio " + "AND r.id_socio = m.id_socio "
					+ "AND r.id_biblioteca = s.id_biblioteca " + "ORDER BY r.id_recibo");

			if (registro == null) {
				err.baseDatosVacia();
			}
			// While se pone porque podría devolver más de una fila.
			while (registro.next()) {
				InformacionRecibo nuevoRecibo = new InformacionRecibo();
				nuevoRecibo.setId_recibo(registro.getString("id_recibo"));
				nuevoRecibo.setNombre_socio(registro.getString("nombre_socio"));
				nuevoRecibo.setApellido_socio(registro.getString("apellido_socio"));
				nuevoRecibo.setDni_socio(registro.getString("dni_socio"));
				nuevoRecibo.setCalle_biblioteca(registro.getString("calle_biblioteca"));
				nuevoRecibo.setProvincia_biblioteca(registro.getString("provincia_biblioteca"));
				nuevoRecibo.setCodigo_postal_biblioteca(registro.getString("codigoPostal_biblioteca"));
				nuevoRecibo.setTelefono_biblioteca(registro.getString("tlf_biblioteca"));
				nuevoRecibo.setPagado(registro.getBoolean("pagado"));
				nuevoRecibo.setMulta_obtenida(registro.getBoolean("multa_obtenida"));

				arrlRecibos.add(nuevoRecibo);
			}

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {

			}
		}

		return arrlRecibos;
	}

	public void generarRecibo(InformacionRecibo recibo) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("INSERT INTO recibos (id_biblioteca, id_socio, pagado) VALUES (" + recibo.getId_bib()
					+ ", " + recibo.getId_socio() + ", " + recibo.isPagado() + ")");

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					String titulo = "Aviso";
					JOptionPane.showMessageDialog(null, "Recibo insertado correctamente.", titulo,
							JOptionPane.INFORMATION_MESSAGE);
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	public String obtenerIdRecibo(InformacionRecibo recibo) {
		String idRecibo = null;

		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_recibo" + " FROM recibos" + " WHERE id_socio = "
					+ recibo.getId_socio() + " AND id_biblioteca = " + recibo.getId_bib());

			if (registro.next()) {
				idRecibo = registro.getString("id_recibo");
			} else {
				return idRecibo;
			}

		} catch (SQLException e) {
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return idRecibo;
	}

	public void editarRecibo(InformacionRecibo recibo, String id) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("UPDATE recibos SET pagado= " + recibo.isPagado() + " WHERE id_recibo= " + id
					+ " AND id_biblioteca = " + recibo.getId_bib());

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		} finally {
			try {
				if (conexion != null) {
					String titulo = "Aviso";
					JOptionPane.showMessageDialog(null, "Recibo editado correctamente.", titulo,
							JOptionPane.INFORMATION_MESSAGE);
					conexion.close();
				}
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	public void confirmarPagoRecibo(String idRecibo) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();

			consulta.executeUpdate("UPDATE recibos SET pagado = 1 WHERE id_recibo = '" + idRecibo + "'");
		} catch (SQLException e) {
			err.baseDatosVacia();
		} finally {
			try {
				if (conexion != null) {
					String titulo = "Aviso";
					JOptionPane.showMessageDialog(null, "Recibo editado correctamente.", titulo,
							JOptionPane.INFORMATION_MESSAGE);
					conexion.close();
				}
			} catch (SQLException e) {
			}
		}
	}
	// ------------------------------------------------------------------------------------------
}
