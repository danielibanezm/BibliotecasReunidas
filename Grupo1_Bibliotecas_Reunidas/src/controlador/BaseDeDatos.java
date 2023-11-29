package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import modelo.Prestamos;


public class BaseDeDatos {
	public ArrayList<Prestamos> cargaPrestamos(){
		ArrayList<Prestamos> arrlPrestamos = new ArrayList <>();
		
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecas_reunidas","root","");
			//Generamos la consulta:
			consulta = conexion.createStatement();
			//Lanzamos la consulta:
			registro = consulta.executeQuery("select * from prestamos");
			
			if(registro==null) {
				System.out.println("La base de datos está vacia.");
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
			System.out.println("No se ha podido establecer la conexión.");
			//e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				//e.printStackTrace();
				System.out.println("No se ha podido cerrar la base de datos.");
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
			
		}catch(SQLException error) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos.");
			error.printStackTrace();
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "No se ha podido cerrar la base de datos");
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
				System.out.println("La base de datos está vacia.");
			}
			//Rellenamos todos los paises uno por uno si hay siguiente pais. Si no hay más paises, sale del bucle.
			//While se pone porque podria devolver ás de una fila.
			while(registro.next()) {				
				//Tomamos los datos de la columna name del objeto registro, convirtiendolo en String, para guardarlo en un objeto de tipo Paises				
				auxiliar = registro.getString("id_socio");				
				arrLCombo.add(auxiliar);				
			}
			
		}catch(SQLException error) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos.");
			error.printStackTrace();
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "No se ha podido cerrar la base de datos");
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
			//Rellenamos todos los paises uno por uno si hay siguiente pais. Si no hay más paises, sale del bucle.
			//While se pone porque podria devolver ás de una fila.
			while(registro.next()) {				
				//Tomamos los datos de la columna name del objeto registro, convirtiendolo en String, para guardarlo en un objeto de tipo Paises				
				auxiliar = registro.getString("id_libro");				
				arrLCombo.add(auxiliar);				
			}
			
		}catch(SQLException error) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos.");
			error.printStackTrace();
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "No se ha podido cerrar la base de datos");
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
			//Rellenamos todos los paises uno por uno si hay siguiente pais. Si no hay más paises, sale del bucle.
			//While se pone porque podria devolver ás de una fila.
			while(registro.next()) {				
				//Tomamos los datos de la columna name del objeto registro, convirtiendolo en String, para guardarlo en un objeto de tipo Paises				
				auxiliar = registro.getString("id_biblioteca");				
				arrLCombo.add(auxiliar);				
			}
			
		}catch(SQLException error) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos.");
			error.printStackTrace();
		}finally{
			try {
				conexion.close();
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "No se ha podido cerrar la base de datos");
			}catch(NullPointerException e) {
				
			}
		}
		return arrLCombo;
	}
}
