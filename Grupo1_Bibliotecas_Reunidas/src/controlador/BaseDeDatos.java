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
import vista.Errores;


public class BaseDeDatos {
	private Errores err = new Errores();
	
	// -- MÉTODOS PRÉSTAMOS --
	public ArrayList<Prestamos> cargaPrestamos(){
		ArrayList<Prestamos> arrlPrestamos = new ArrayList <>();
		
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecas_reunidas","root","");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select * from prestamos");
			
			if(registro==null) {
				err.baseDatosVacia();
			}
			//While se pone porque podría devolver más de una fila.
			while(registro.next()){
				Prestamos nuevoPrestamo = new Prestamos();
				
				nuevoPrestamo.setId_prestamo(registro.getString("id_prestamo"));
				nuevoPrestamo.setId_socio(registro.getString("id_socio"));
				nuevoPrestamo.setId_libro(registro.getString("id_libro"));
				nuevoPrestamo.setId_biblioteca(registro.getString("id_biblioteca"));
				nuevoPrestamo.setFecha_prestamo(registro.getString("fecha_prestamo"));
				nuevoPrestamo.setFecha_entrega_prevista(registro.getString("fecha_entrega_prevista"));
				nuevoPrestamo.setFecha_entrega(registro.getString("fecha_entrega"));
				nuevoPrestamo.setComentarios(registro.getString("comentarios"));

				arrlPrestamos.add(nuevoPrestamo);
			}
		
		} catch (SQLException e) {
			err.baseDatosNoConexion();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
			}catch(NullPointerException e) {
				
			}
		}
		
		return arrlPrestamos;
	}
	
	public String insertarPrestamo(String id_socio, String id_libro, String id_biblioteca, String comentarios){
		Connection conexion=null;
		Statement consulta=null;
		String fechaFormateada = null;
		
		try{
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta=conexion.createStatement();
			
			boolean esResultado = false;
			
			esResultado = consulta.execute("SELECT id_prestamo FROM prestamos WHERE id_libro = " + id_libro);
			
			if (!esResultado) {
				 // Obtener la fecha actual con Calendar
	            Calendar calendar = Calendar.getInstance();
	            java.util.Date fechaActual = calendar.getTime();
	            
	            // Formatear la fecha actual como 'YYYY-MM-DD'
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            fechaFormateada = dateFormat.format(fechaActual);
	            
	            String fechaEntregaPrevista = calcularFechaEntregaPrevista(fechaFormateada);            

	            // Insertar la fecha actual en la base de datos
	            consulta.executeUpdate("INSERT INTO prestamos (id_socio, id_libro, id_biblioteca, fecha_prestamo, fecha_entrega_prevista, fecha_entrega, comentarios) "
	            		+ "VALUES ('" + id_socio + "', '" + id_libro + "', '" + id_biblioteca + "', '" + fechaFormateada + "', '" + fechaEntregaPrevista + 
	            		"', NULL, '" + comentarios + "')");

				JOptionPane.showMessageDialog(null, "Se ha insertado correctamente la fecha de prestamo y la fecha de entrega prevista en la tabla.");
				
			} else {
				JOptionPane.showMessageDialog(null, "Este ejemplar ya está prestado. Por favor, espere a que el otro usuario lo devuelva o escoja otro libro.");
			}
			
			
		}catch(SQLException error) {
			err.baseDatosNoConexion();
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
			}catch(NullPointerException e) {
				
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
            e.printStackTrace();
            return null;
        }
    }
	
	public ArrayList<String> cargaComboSocios() {
		Connection conexion=null;
		Statement consulta=null;
		ResultSet registro=null;
		ArrayList<String> arrLCombo = new ArrayList<>();
		String auxiliar = null;
		
		try{
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta=conexion.createStatement();			
			registro=consulta.executeQuery("SELECT id_socio FROM prestamos");			
			
			if(registro==null) {
				err.baseDatosVacia();
			}
			while(registro.next()) {							
				auxiliar = registro.getString("id_socio");				
				arrLCombo.add(auxiliar);				
			}
			
		}catch(SQLException error) {
			err.baseDatosNoConexion();
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
			}catch(NullPointerException e) {
				
			}
		}
		return arrLCombo;
	}
	
	public ArrayList<String> cargaComboLibros() {
		Connection conexion=null;
		Statement consulta=null;
		ResultSet registro=null;
		ArrayList<String> arrLCombo = new ArrayList<>();
		String auxiliar = null;
		
		try{
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta=conexion.createStatement();			
			registro=consulta.executeQuery("SELECT id_libro FROM prestamos");			
			
			if(registro==null) {
				System.out.println("La base de datos está vacia.");
			}
			while(registro.next()) {				
				auxiliar = registro.getString("id_libro");				
				arrLCombo.add(auxiliar);				
			}
			
		}catch(SQLException error) {
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
			}catch(NullPointerException e) {
				
			}
		}
		return arrLCombo;
	}
	
	public ArrayList<String> cargaComboBibliotecas() {
		Connection conexion=null;
		Statement consulta=null;
		ResultSet registro=null;
		ArrayList<String> arrLCombo = new ArrayList<>();
		String auxiliar = null;
		
		try{
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta=conexion.createStatement();			
			registro=consulta.executeQuery("SELECT id_biblioteca FROM prestamos");			
			
			if(registro==null) {
				System.out.println("La base de datos está vacia.");
			}
			while(registro.next()) {				
				auxiliar = registro.getString("id_biblioteca");				
				arrLCombo.add(auxiliar);				
			}
			
		}catch(SQLException error) {
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
			}catch(NullPointerException e) {
				
			}
		}
		return arrLCombo;
	}
	//------------------------------------------------------------------------------------------
	
	// -- MÉTODOS LOGIN --
	
	public boolean compruebaUsuario(String usuario, String contrasenia) {
		String id = "";
		boolean correcto = false;
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas","root","");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_usuario"
					+ " FROM usuarios"
					+ " WHERE email_usuario = '" + usuario + "'");

			if (registro.next()) {
				id = registro.getString("id_usuario");
				
				correcto = compruebaContrasenia(id, contrasenia);
				
			}
			
		} catch (SQLException e) {
			err.baseDatosNoConexion();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
				
			}catch(NullPointerException e) {
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
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas","root","");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT material"
					+ " FROM otros"
					+ " WHERE id_otro = '" + id + "'");
			
			if (registro.next()) {
				contra = registro.getString("material");
				
				if(contra.equals(contrasenia)) {
					correcto = true;
				}
			}

		} catch (SQLException e) {
			err.baseDatosNoConexion();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
			}catch(NullPointerException e) {
			}
		}
		
		return correcto;
	}
	//------------------------------------------------------------------------------------------
	
	// -- MÉTODOS LIBROS --
	
	public ArrayList<Libros> cargaLibros(String consulta, String aux){
		ArrayList<Libros> arrlLibros = new ArrayList <>();
		
		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas","root","");
			consultita = conexion.createStatement();
			//Lanzamos la consulta:
			registro = consultita.executeQuery("SELECT id_libro, id_biblioteca, titulo_libro, autores_libro, isbn_libro, editorial_libro,"
				+ "genero_libro, idioma_libro, edicion_libro, ubicacion_libro, publicacion_libro, pais_libro,"
				+ "numPaginas_libro, COUNT(*) AS stock_total "
				+ "FROM	libros "
				+ "WHERE " + consulta + " LIKE '%" + aux + "%' "
				+ "GROUP BY id_biblioteca, titulo_libro, autores_libro, isbn_libro, editorial_libro, genero_libro, idioma_libro, edicion_libro, "
				+ "ubicacion_libro, publicacion_libro, pais_libro, numPaginas_libro "
				+ "ORDER BY " + consulta);
			
			if(!registro.next()) {
				err.baseDatosVacia();
			}
			//While porque podría devolver más de una fila.
			while(registro.next()){
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
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
			err.baseDatosNoConexion();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
			}catch(NullPointerException e) {
				
			}
		}
		
		return arrlLibros;
	}
	
	public String obtenerIdLibro(Libros libro) {
	    String id = "";
	    Connection conexion = null;
	    Statement consulta = null;
	    ResultSet registro = null;

	    try {
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");

	        consulta = conexion.createStatement();
	        registro = consulta.executeQuery("SELECT id_libro"
	                + " FROM libros"
	                + " WHERE isbn_libro = '" + libro.getIsbn() + "'"
	                + " AND titulo_libro = '" + libro.getTitulo() + "'"
	                + " AND autores_libro = '" + libro.getAutores() + "'");

	        if (registro.next()) {
	            id = registro.getString("id_libro");
	        }

	    } catch (SQLException e) {
	    	err.baseDatosNoConexion();
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	        	err.baseDatosNoConexion();
	        }catch(NullPointerException e) {
	        }
	    }

	    return id;
	}

	public void editarLibro(Libros libro, String id) {
	    Connection conexion = null;
	    Statement consulta = null;

	    try {
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
	        consulta = conexion.createStatement();

	        consulta.executeUpdate("UPDATE libros SET "
                    + "isbn_libro='" + libro.getIsbn() + "', "
                    + "titulo_libro='" + libro.getTitulo() + "', "
                    + "autores_libro='" + libro.getAutores() + "', "
                    + "editorial_libro='" + libro.getEditorial() + "', "
                    + "genero_libro='" + libro.getGenero() + "', "
                    + "idioma_libro='" + libro.getIdioma() + "', "
                    + "edicion_libro='" + libro.getEdicion() + "', "
                    + "ubicacion_libro='" + libro.getUbicacion() + "', "
                    + "publicacion_libro='" + libro.getPublicacion() + "', "
                    + "pais_libro='" + libro.getPais() + "', "
                    + "numPaginas_libro='" + libro.getPaginas() + "' "
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
	        }catch(NullPointerException e) {
	        }
	    }
	}
	
	public void eliminarLibro(String id) {
		Connection conexion = null;
		Statement consulta = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();
			
			consulta.executeUpdate("delete from libros where id=" + id);
			
			err.confirmarEliminar();
			
		} catch (CommunicationsException e) {
			err.baseDatosNoConexion();
		} catch (SQLException e) {
			err.baseDatosNoConexion();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
			}catch(NullPointerException e) {
			}
		}
		
	}
	
	public String obtenerUltimoIdLibro() {
	    String ultimoId = "";
	    Connection conexion = null;
	    Statement consulta = null;
	    ResultSet resultado = null;

	    try {
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
	        consulta = conexion.createStatement();

	        resultado = consulta.executeQuery("SELECT MAX(id_libro) AS ultimoId FROM libros");

	        if (resultado.next()) {
	        	ultimoId = resultado.getString("ultimoId");
	        }

	    } catch (SQLException e) {
	    	  e.printStackTrace();
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	        	  e.printStackTrace();
	        } catch (NullPointerException e) {
	        	  e.printStackTrace();
	        }
	    }

	    return ultimoId;
	}
	
	public void insertarLibro(String id, Libros libro) {
		Connection conexion = null;
		Statement consulta = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas", "root", "");
			consulta = conexion.createStatement();
			
			consulta.executeUpdate("INSERT INTO LIBROS (id_libro, isbn_libro, titulo_libro, autores_libro, editorial_libro, "
	                + "genero_libro, idioma_libro, edicion_libro, ubicacion_libro, publicacion_libro, "
	                + "pais_libro, numPaginas_libro) values ('" + id + "', '" + libro.getIsbn() + "', '" + libro.getTitulo()
	                + "', '" + libro.getAutores() + "', '" + libro.getEditorial() + "', '" + libro.getGenero() + "', '" + libro.getIdioma()
	                + "', '" + libro.getEdicion() + "', '" + libro.getUbicacion() + "', '" + libro.getPublicacion()
	                + "', '" + libro.getPais() + "', '" + libro.getPaginas() + "')");

			err.confirmarInsert();
				
		} catch (SQLException e) {
			e.printStackTrace();
			err.baseDatosNoConexion();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				err.baseDatosNoConexion();
			}catch(NullPointerException e) {	
			}
		}
		
	}
	
	//------------------------------------------------------------------------------------------

}
