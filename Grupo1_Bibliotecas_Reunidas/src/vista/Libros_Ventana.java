package vista;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.jdbc.CommunicationsException;


import modelo.Libros;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Libros_Ventana extends JPanel {
	//Creamos un objeto de la clase ButtonGroup para poder agrupar nuestros radio buttons:
	private ButtonGroup radioButton = new ButtonGroup();
	
	private String consulta = "";
	
	//Creamos un objeto para el modelo de nuestra tabla:
	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JRadioButton rdbtIsbn;
	private JRadioButton rdbtAutor;
	private JLabel lblBuscar;
	private JTextField textField;
	private JLabel lblConsultar;
	private JLabel lblLibros;
	private JLabel lblResultados;
	private JTable jtResultados;
	private JRadioButton rdbtTitulo;
	private JButton btnEditarLibro;
	private JButton btnPrestamo_2;
	private JButton btnMen;
	
	public Libros_Ventana(Ventana ventana, boolean esAdmin) {

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		lblConsultar = new JLabel("Consultar");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(130, 72, 172));
		lblConsultar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblConsultar.setBounds(540, 21, 239, 81);
		add(lblConsultar);
		
		lblLibros = new JLabel("Libros");
		lblLibros.setVerticalAlignment(SwingConstants.TOP);
		lblLibros.setHorizontalAlignment(SwingConstants.LEFT);
		lblLibros.setForeground(new Color(9, 3, 62));
		lblLibros.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblLibros.setBounds(584, 65, 239, 81);
		add(lblLibros);
		
		
		
		//	-----------------	Radio Buttons	------------------------
		rdbtTitulo = new JRadioButton("Título");
		rdbtTitulo.setSelected(true);
		rdbtTitulo.setContentAreaFilled(false);
		rdbtTitulo.setBackground(new Color(237, 227, 244));
		rdbtTitulo.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtTitulo.setBounds(469, 146, 109, 23);
		add(rdbtTitulo);
		
		rdbtIsbn = new JRadioButton("ISBN");
		rdbtIsbn.setContentAreaFilled(false);
		rdbtIsbn.setBackground(new Color(230, 217, 240));
		rdbtIsbn.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtIsbn.setBounds(604, 146, 109, 23);
		add(rdbtIsbn);
		
		rdbtAutor = new JRadioButton("Autor");
		rdbtAutor.setContentAreaFilled(false);
		rdbtAutor.setBorder(null);
		rdbtAutor.setBackground(new Color(230, 217, 240));
		rdbtAutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtAutor.setBounds(738, 146, 109, 23);
		add(rdbtAutor);
		
		//Agrupamos nuestros radio buttons:
		radioButton.add(rdbtTitulo);
		radioButton.add(rdbtIsbn);
		radioButton.add(rdbtAutor);
		
		//-------------------------------------------------------------
		
		lblBuscar = new JLabel("Buscar por:");
		lblBuscar.setForeground(new Color(9, 3, 62));
		lblBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		lblBuscar.setBounds(290, 150, 109, 14);
		add(lblBuscar);
		
		// -- EVENTO TEXTFIELD --
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(rdbtTitulo.isSelected()) {
					consulta = "titulo_libro";
				}else if(rdbtIsbn.isSelected()) {
					consulta = "isbn_libro";
				}else if(rdbtAutor.isSelected()){
					consulta = "autores_libro";
				}
				
				rellenaTabla(consulta);
			}
		});
		//--------------------------------------
		
		textField.setBounds(469, 190, 428, 29);
		add(textField);
		textField.setColumns(10);
		
		lblResultados = new JLabel("Resultados:");
		lblResultados.setForeground(new Color(9, 3, 62));
		lblResultados.setFont(new Font("Verdana", Font.BOLD, 12));
		lblResultados.setBounds(290, 251, 109, 14);
		add(lblResultados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(23, 293, 1271, 327);
		add(scrollPane);
		
		
		// -- REALIZAR PRÉSTAMO --
		JButton btnPrestamo = new JButton("Realizar préstamo");
		btnPrestamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Hacer_Prestamo_Ventana(ventana, esAdmin));
			}
		});
		//-----------------------------------------------------
		
		btnPrestamo.setForeground(Color.WHITE);
		btnPrestamo.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPrestamo.setBorder(null);
		btnPrestamo.setBackground(new Color(130, 72, 172));
		btnPrestamo.setBounds(23, 652, 131, 37);
		add(btnPrestamo);
		
		// -- RESTABLECER BÚSQUEDA --
		JButton btnBorrar = new JButton("Limpiar búsqueda");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modeloTabla.setRowCount(0);
				textField.setText("");
				rdbtTitulo.setSelected(true);
			}
		});
		//------------------------------------------------------
		
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrar.setBorder(null);
		btnBorrar.setBackground(new Color(130, 72, 172));
		btnBorrar.setBounds(1163, 652, 131, 37);
		add(btnBorrar);
		
		btnEditarLibro = new JButton("Editar libro");
		btnEditarLibro.setForeground(new Color(9, 3, 62));
		btnEditarLibro.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarLibro.setBorder(null);
		btnEditarLibro.setBackground(new Color(233, 210, 255));
		btnEditarLibro.setBounds(529, 652, 87, 37);
		add(btnEditarLibro);
		
		btnPrestamo_2 = new JButton("Borrar libro");
		btnPrestamo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrestamo_2.setForeground(new Color(9, 3, 62));
		btnPrestamo_2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPrestamo_2.setBorder(null);
		btnPrestamo_2.setBackground(new Color(233, 210, 255));
		btnPrestamo_2.setBounds(681, 652, 87, 37);
		add(btnPrestamo_2);
		
		//	--	VOLVER AL MENÚ --
		btnMen = new JButton("MENÚ");
		btnMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin));
			}
		});
		//------------------------------------------------------
		
		btnMen.setToolTipText("");
		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(23, 30, 79, 37);
		add(btnMen);
		
		//--------------------------	JTABLE --------------------------------------
		jtResultados = new JTable();
		jtResultados.setForeground(new Color(36, 54, 69));
		jtResultados.setFont(new Font("Verdana", Font.PLAIN, 13));
		jtResultados.setBackground(new Color(255, 255, 255));
		jtResultados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jtResultados.getTableHeader().setReorderingAllowed(false);
		
		//Cambiar la altura de las filas:
		jtResultados.setRowHeight(30);
		scrollPane.setViewportView(jtResultados);
		
		//Le añadimos a nuestra tabla las columnas que va a tener:
		modeloTabla.setColumnIdentifiers(new Object[]{"ISBN", "Título", "Autores", "Editorial",
				"Género", "Idioma", "Edición", "Publicación", "Pais", "Nº Páginas", "Ubicación"});
		//Le decimos que le establezca el modelo que hemos creado a nuestra tabla:
		jtResultados.setModel(modeloTabla);
		
		//Establecer el ancho de las columnas:
		jtResultados.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtResultados.getColumnModel().getColumn(1).setPreferredWidth(110);
		jtResultados.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtResultados.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtResultados.getColumnModel().getColumn(4).setPreferredWidth(20);
		jtResultados.getColumnModel().getColumn(5).setPreferredWidth(20);
		jtResultados.getColumnModel().getColumn(6).setPreferredWidth(10);
		jtResultados.getColumnModel().getColumn(7).setPreferredWidth(30);
		jtResultados.getColumnModel().getColumn(8).setPreferredWidth(10);
		jtResultados.getColumnModel().getColumn(9).setPreferredWidth(10);
		jtResultados.getColumnModel().getColumn(10).setPreferredWidth(80);
		
		JTableHeader encabezado = jtResultados.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));
		
		//Que no se cambie el tamaño de las columnas.
		jtResultados.getTableHeader().setResizingAllowed(false);
		//Que no se cambie el orden de las columnas.
		jtResultados.getTableHeader().setReorderingAllowed(false);
			
		//	-------------------------------------------------------------

	}
	
	public void rellenaTabla(String consulta) {
		//Creamos una variable donde vamos a guardar lo que se haya escrito en el textField:
		String aux = textField.getText().toString();
		
		modeloTabla.setRowCount(0);
		
		//Recorremos los objetos del ArrayList que nos retorna el método de la clase BaseDeDatos:
		for(Libros recorreLibros : cargaLibros(consulta, aux)) {
			
			//Object puede coger todo tipo de datos, hasta imágenes.
			modeloTabla.addRow(new Object[] {
				recorreLibros.getIsbn(),
				recorreLibros.getTitulo(),
				recorreLibros.getAutores(),
				recorreLibros.getEditorial(),
				recorreLibros.getGenero(),
				recorreLibros.getIdioma(),
				recorreLibros.getEdicion(),
				recorreLibros.getPublicacion(),
				recorreLibros.getPais(),
				recorreLibros.getPaginas(),
				recorreLibros.getUbicacion(),
			});
		}
			
	}
	
	public ArrayList<Libros> cargaLibros(String consulta, String aux){
		ArrayList<Libros> arrlLibros = new ArrayList <>();
		
		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas","root","");
			consultita = conexion.createStatement();
			//Lanzamos la consulta:
			registro = consultita.executeQuery("SELECT isbn_libro, titulo_libro, autores_libro,"
					+ " editorial_libro, genero_libro, idioma_libro, edicion_libro, publicacion_libro,"
					+ " pais_libro, numPaginas_libro, ubicacion_libro"
					+ " FROM libros"
					+ " WHERE " + consulta + " LIKE '%" + aux + "%'"
					+ " ORDER BY " + consulta);
			
			if(registro==null) {
			System.out.println("Base de datos vacía");
			}
			//While porque podría devolver más de una fila.
			while(registro.next()){
				Libros nuevoLibro = new Libros();
				
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
				
				arrlLibros.add(nuevoLibro);
			}
		
		} catch (CommunicationsException e) {

		} catch (SQLException e) {

		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {

			}catch(NullPointerException e) {
				
			}
		}
		
		return arrlLibros;
	}
}
