package vista;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.jdbc.CommunicationsException;

import controlador.BaseDeDatos;
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
	// Creamos un objeto de la clase ButtonGroup para poder agrupar nuestros radio
	// buttons:
	private ButtonGroup radioButton = new ButtonGroup();

	private String consulta = "";

	// Creamos un objeto para el modelo de nuestra tabla:
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
	private JButton btnEliminar;
	private JButton btnMen;
	private BaseDeDatos bd = new BaseDeDatos();
	private Editar_Libro libros;
	private Hacer_Prestamo prestamo;
	private Insertar_Libro insertar;
	private Libros librito = new Libros();
	private int filaTabla;
	private String id;
	private Errores err = new Errores();

	public Libros_Ventana(Ventana ventana, boolean esAdmin, String idBib) {
		
		Insertar_Libro insertar = new Insertar_Libro(idBib, modeloTabla, ventana, esAdmin);
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

		// ----------------- Radio Buttons ------------------------
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

		// Agrupamos nuestros radio buttons:
		radioButton.add(rdbtTitulo);
		radioButton.add(rdbtIsbn);
		radioButton.add(rdbtAutor);

		// -------------------------------------------------------------

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
				
				if (textField.getText().isEmpty()) {
		            modeloTabla.setRowCount(0);
		            
				}else {
					if (rdbtTitulo.isSelected()) {
						consulta = "titulo_libro";
					} else if (rdbtIsbn.isSelected()) {
						consulta = "isbn_libro";
					} else if (rdbtAutor.isSelected()) {
						consulta = "autores_libro";
					}
					
					rellenaTabla(consulta, idBib);
				}	
			}
		});
		// --------------------------------------

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
		scrollPane.setBounds(23, 293, 1318, 327);
		add(scrollPane);

		// -- REALIZAR PRÉSTAMO --
		JButton btnPrestamo = new JButton("Realizar préstamo");
		btnPrestamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila
					prestamo = new Hacer_Prestamo(librito, idBib);
					prestamo.setVisible(true);

				} else {
					//No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un libro para realizar un préstamo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		// -----------------------------------------------------

		btnPrestamo.setForeground(Color.WHITE);
		btnPrestamo.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPrestamo.setBorder(null);
		btnPrestamo.setBackground(new Color(130, 72, 172));
		btnPrestamo.setBounds(23, 652, 131, 37);
		add(btnPrestamo);

		// -- RESTABLECER BÚSQUEDA --
		JButton btnBorrar = new JButton("Limpiar búsqueda");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modeloTabla.setRowCount(0);
				textField.setText("");
				rdbtTitulo.setSelected(true);
			}
		});
		// ------------------------------------------------------

		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrar.setBorder(null);
		btnBorrar.setBackground(new Color(130, 72, 172));
		btnBorrar.setBounds(1212, 652, 131, 37);
		add(btnBorrar);

		// -- EDITAR LIBRO --
		btnEditarLibro = new JButton("Editar libro");
		btnEditarLibro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila
					libros = new Editar_Libro(librito, modeloTabla, filaTabla, idBib);
					libros.setVisible(true);

				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un libro para editarlo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		// --------------------------------------------

		btnEditarLibro.setForeground(new Color(9, 3, 62));
		btnEditarLibro.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarLibro.setBorder(null);
		btnEditarLibro.setBackground(new Color(233, 210, 255));
		btnEditarLibro.setBounds(679, 652, 87, 37);
		add(btnEditarLibro);

		btnEliminar = new JButton("Borrar libro");

		// -- ELIMINAR LIBRO --
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					eliminar(filaTabla, idBib);

				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un libro para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// --------------------------------------------

		btnEliminar.setForeground(new Color(9, 3, 62));
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(233, 210, 255));
		btnEliminar.setBounds(831, 652, 87, 37);
		add(btnEliminar);

		// -- AÑADIR LIBRO --
		JButton btnNuevoLibro = new JButton("Añadir libro");
		btnNuevoLibro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (insertar != null) {
					insertar.setVisible(true);
				}
			}
		});
		// -------------------------------------------------

		btnNuevoLibro.setForeground(new Color(9, 3, 62));
		btnNuevoLibro.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevoLibro.setBorder(null);
		btnNuevoLibro.setBackground(new Color(233, 210, 255));
		btnNuevoLibro.setBounds(537, 652, 87, 37);
		add(btnNuevoLibro);

		// -- VOLVER AL MENÚ --
		btnMen = new JButton("MENÚ");
		btnMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin, idBib));
			}
		});
		// ------------------------------------------------------

		btnMen.setToolTipText("");
		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(23, 30, 79, 37);
		add(btnMen);

		// -------------------------- JTABLE --------------------------------------
		jtResultados = new JTable();

		// -- SELECCIONAR LIBRO DE LA TABLA --
		jtResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 0) != null) {
					librito.setIsbn(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 0).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 1) != null) {
					librito.setTitulo(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 1).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 2) != null) {
					librito.setAutores(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 2).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 3) != null) {
					librito.setEditorial(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 3).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 4) != null) {
					librito.setGenero(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 4).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 5) != null) {
					librito.setIdioma(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 5).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 6) != null) {
					librito.setEdicion(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 6).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 7) != null) {
					librito.setPublicacion(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 7).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 8) != null) {
					librito.setPais(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 8).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 9) != null) {
					librito.setPaginas(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 9).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 10) != null) {
					librito.setUbicacion(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 10).toString());
				}
			}
		});
		// ----------------------------------------------------------------------------------------------------

		jtResultados.setForeground(new Color(36, 54, 69));
		jtResultados.setFont(new Font("Verdana", Font.PLAIN, 13));
		jtResultados.setBackground(new Color(255, 255, 255));
		jtResultados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jtResultados.getTableHeader().setReorderingAllowed(false);

		// Cambiar la altura de las filas:
		jtResultados.setRowHeight(30);
		scrollPane.setViewportView(jtResultados);

		// Le añadimos a nuestra tabla las columnas que va a tener:
		modeloTabla.setColumnIdentifiers(new Object[] {"ISBN", "Título", "Autores", "Editorial",
				"Género", "Idioma", "Edición", "Publicación", "Pais", "Nº Páginas", "Ubicación", "Stock" });
		// Le decimos que le establezca el modelo que hemos creado a nuestra tabla:
		jtResultados.setModel(modeloTabla);

		// Establecer el ancho de las columnas:
		jtResultados.getColumnModel().getColumn(0).setPreferredWidth(110);
		jtResultados.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtResultados.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtResultados.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtResultados.getColumnModel().getColumn(4).setPreferredWidth(30);
		jtResultados.getColumnModel().getColumn(5).setPreferredWidth(30);
		jtResultados.getColumnModel().getColumn(6).setPreferredWidth(30);
		jtResultados.getColumnModel().getColumn(7).setPreferredWidth(50);
		jtResultados.getColumnModel().getColumn(8).setPreferredWidth(20);
		jtResultados.getColumnModel().getColumn(9).setPreferredWidth(10);
		jtResultados.getColumnModel().getColumn(10).setPreferredWidth(90);
		jtResultados.getColumnModel().getColumn(11).setPreferredWidth(5);


		JTableHeader encabezado = jtResultados.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		// Que no se cambie el tamaño de las columnas.
		jtResultados.getTableHeader().setResizingAllowed(false);
		// Que no se cambie el orden de las columnas.
		jtResultados.getTableHeader().setReorderingAllowed(false);

		// -------------------------------------------------------------

	}

	public void rellenaTabla(String consulta, String idBib) {
	    String aux = textField.getText().toString();
	    modeloTabla.setRowCount(0);

	    for (Libros recorreLibros : bd.cargaLibros(consulta, aux, idBib)) {
	        modeloTabla.addRow(new Object[] {recorreLibros.getIsbn(), recorreLibros.getTitulo(), recorreLibros.getAutores(),
	                recorreLibros.getEditorial(), recorreLibros.getGenero(), recorreLibros.getIdioma(),
	                recorreLibros.getEdicion(), recorreLibros.getPublicacion(), recorreLibros.getPais(),
	                recorreLibros.getPaginas(), recorreLibros.getUbicacion(), recorreLibros.getStockTotal() });
	    }

	    modeloTabla.fireTableDataChanged();
	}

	public void eliminar(int filaTabla, String idBib) {
		BaseDeDatos bd = new BaseDeDatos();
		int opcion = 0;
		String id;

		opcion = err.preguntarEliminar();

		if (opcion == 0) {
			id = bd.obtenerIdLibro(librito, idBib);
			bd.eliminarLibro(id, idBib);

			// Eliminamos la fila del modelo.
			modeloTabla.removeRow(filaTabla);
		}
	}
	
	public void actualizarse(Libros nuevoLib, DefaultTableModel modeloTabla) {
		Object[] nuevaFila = {nuevoLib.getTitulo(), nuevoLib.getAutores(), nuevoLib.getIsbn(),
				nuevoLib.getEditorial(), nuevoLib.getGenero(), nuevoLib.getIdioma(), nuevoLib.getEdicion(),
				nuevoLib.getUbicacion(), nuevoLib.getPublicacion(), nuevoLib.getPais(), nuevoLib.getPaginas(), 1};
		modeloTabla.addRow(nuevaFila);

		modeloTabla.fireTableDataChanged();
	}
}
