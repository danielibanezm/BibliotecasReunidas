package vista;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controlador.BaseDeDatos;
import modelo.Libros;
import modelo.Socios;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Socios_Ventana extends JPanel {
	// Creamos un objeto de la clase ButtonGroup para poder agrupar nuestros radio
	// buttons:
	private ButtonGroup radioButton = new ButtonGroup();
	// Creamos un objeto para el modelo de nuestra tabla:

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String consulta = "";

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnNuevoSocio;
	private JButton btnEditarSocio;
	private JButton btnBorrarSocio;
	private JTable jtResultados;
	private Socios socio = new Socios();
	private int filaTabla;

	private BaseDeDatos bd = new BaseDeDatos();
	private Errores err = new Errores();
	private Editar_Socio editarSocio;

	public Socios_Ventana(Ventana ventana, boolean esAdmin, String idBib) {
		Insertar_Socio insertarSocio = new Insertar_Socio(idBib, modeloTabla, ventana, esAdmin);
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		// -- VOLVER AL MENÚ --
		JButton btnMen = new JButton("MENÚ");
		btnMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin, idBib));
			}
		});
		// ------------------------------------------------------------

		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(32, 24, 79, 37);
		add(btnMen);

		JLabel lblLibros = new JLabel("Socios");
		lblLibros.setVerticalAlignment(SwingConstants.TOP);
		lblLibros.setHorizontalAlignment(SwingConstants.LEFT);
		lblLibros.setForeground(new Color(9, 3, 62));
		lblLibros.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblLibros.setBounds(593, 68, 239, 81);
		add(lblLibros);

		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(130, 72, 172));
		lblConsultar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblConsultar.setBounds(549, 24, 239, 81);
		add(lblConsultar);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(32, 160, 1318, 463);
		add(scrollPane);

		// -- AÑADIR SOCIO --
		btnNuevoSocio = new JButton("Añadir socio");
		btnNuevoSocio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (insertarSocio != null) {
					insertarSocio.setVisible(true);
				}
			}
		});
		// ----------------------------------------------

		btnNuevoSocio.setForeground(new Color(9, 3, 62));
		btnNuevoSocio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevoSocio.setBorder(null);
		btnNuevoSocio.setBackground(new Color(233, 210, 255));
		btnNuevoSocio.setBounds(387, 655, 87, 37);
		add(btnNuevoSocio);

		// -- EDITAR SOCIO --
		btnEditarSocio = new JButton("Editar socio");
		btnEditarSocio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila
					editarSocio = new Editar_Socio(socio, modeloTabla, filaTabla, idBib);
					editarSocio.setVisible(true);

				} else {
					// No se ha seleccionado ningún socio por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un socio para editarlo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ---------------------------------------

		btnEditarSocio.setForeground(new Color(9, 3, 62));
		btnEditarSocio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarSocio.setBorder(null);
		btnEditarSocio.setBackground(new Color(233, 210, 255));
		btnEditarSocio.setBounds(667, 655, 87, 37);
		add(btnEditarSocio);

		// -- BOTÓN ELIMINAR SOCIO --
		btnBorrarSocio = new JButton("Borrar socio");
		btnBorrarSocio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					eliminar(filaTabla, idBib);

				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un socio para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ----------------------------------------------------

		btnBorrarSocio.setForeground(new Color(9, 3, 62));
		btnBorrarSocio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarSocio.setBorder(null);
		btnBorrarSocio.setBackground(new Color(233, 210, 255));
		btnBorrarSocio.setBounds(977, 655, 87, 37);
		add(btnBorrarSocio);

		// -------------------------- JTABLE --------------------------------------

		// -- SELECCIONAR SOCIO DE LA TABLA --
		jtResultados = new JTable();
		jtResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 0) != null) {
					socio.setNombre(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 0).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 1) != null) {
					socio.setApellido(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 1).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 2) != null) {
					socio.setDni(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 2).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 3) != null) {
					socio.setDireccion(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 3).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 4) != null) {
					socio.setTelefono(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 4).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 5) != null) {
					socio.setEmail(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 5).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 6) != null) {
					socio.setFechaNacimiento(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 6).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 7) != null) {
					if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 7).equals("Sí")) {
						socio.setListaNegra(true);
					} else {
						socio.setListaNegra(false);
					}

				}

			}
		});
		// ------------------

		jtResultados.setForeground(new Color(36, 54, 69));
		jtResultados.setFont(new Font("Verdana", Font.PLAIN, 13));
		jtResultados.setBackground(new Color(255, 255, 255));
		jtResultados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jtResultados.getTableHeader().setReorderingAllowed(false);

		jtResultados.setRowHeight(30);
		scrollPane.setViewportView(jtResultados);

		modeloTabla.setColumnIdentifiers(new Object[] { "Nombre", "Apellidos", "DNI", "Dirección", "Teléfono", "Email",
				"Fecha nacimiento", "Lista negra" });

		jtResultados.setModel(modeloTabla);

		jtResultados.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(3).setPreferredWidth(120);
		jtResultados.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtResultados.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(6).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(7).setPreferredWidth(10);

		JTableHeader encabezado = jtResultados.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtResultados.getTableHeader().setResizingAllowed(false);
		jtResultados.getTableHeader().setReorderingAllowed(false);
		
		rellenaTabla(idBib);

		// -------------------------------------------------------------
	}

	public void rellenaTabla(String idBib) {
		modeloTabla.setRowCount(0);
		String lNegra = "";

		// Recorremos los objetos del ArrayList que nos retorna el método de la clase
		// BaseDeDatos:
		for (Socios recorreSocios : bd.cargaSocios(idBib)) {

			if (recorreSocios.isListaNegra()) {
				lNegra = "Sí";
			} else {
				lNegra = "No";
			}

			// Object puede coger todo tipo de datos, hasta imágenes.
			modeloTabla.addRow(new Object[] { recorreSocios.getNombre(), recorreSocios.getApellido(),
					recorreSocios.getDni(), recorreSocios.getDireccion(), recorreSocios.getTelefono(),
					recorreSocios.getEmail(), recorreSocios.getFechaNacimiento(), lNegra });
		}
	}

	public void eliminar(int filaTabla, String idBib) {
		BaseDeDatos bd = new BaseDeDatos();
		int opcion = 0;
		String id;

		opcion = err.preguntarEliminar();

		if (opcion == 0) {
			id = bd.obtenerIdSocio(socio, idBib);
			bd.eliminarSocio(id, idBib);

			// Eliminamos la fila del modelo.
			modeloTabla.removeRow(filaTabla);
		}
	}

	
}
