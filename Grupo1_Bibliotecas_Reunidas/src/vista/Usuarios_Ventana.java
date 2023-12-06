package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controlador.BaseDeDatos;
import modelo.Socios;
import modelo.Usuarios;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Usuarios_Ventana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private static final long serialVersionUID = 1L;
	private JButton btnanadir;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JScrollPane scrollPane;
	private JTable jtResultados;
	private Usuarios user = new Usuarios();
	private BaseDeDatos bd = new BaseDeDatos();
	private Errores err = new Errores();
	private JButton btnCargar;
	private int filaTabla;
	private Editar_Usuario editarUsuario;


	/**
	 * Create the panel.
	 */
	public Usuarios_Ventana(Ventana ventana, boolean esAdmin, String idBib) {
		Insertar_Usuario insertarUsuario = new Insertar_Usuario(idBib, modeloTabla, ventana, esAdmin);
		
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
		// --------------------------------------------------------
		
		btnMen.setToolTipText("");
		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(25, 25, 79, 37);
		add(btnMen);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setVerticalAlignment(SwingConstants.TOP);
		lblUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuarios.setForeground(new Color(9, 3, 62));
		lblUsuarios.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblUsuarios.setBounds(606, 80, 239, 81);
		add(lblUsuarios);

		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(130, 72, 172));
		lblConsultar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblConsultar.setBounds(562, 36, 239, 81);
		add(lblConsultar);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(25, 186, 1318, 312);
		add(scrollPane);

		// -- AÑADIR USUARIO --
		btnanadir = new JButton("Añadir usuario");
		btnanadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (insertarUsuario != null) {
					insertarUsuario.setVisible(true);
				}
			}
		});
		//--------------------------------------
		
		btnanadir.setForeground(new Color(9, 3, 62));
		btnanadir.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnanadir.setBorder(null);
		btnanadir.setBackground(new Color(233, 210, 255));
		btnanadir.setBounds(579, 580, 106, 37);
		add(btnanadir);

		// -- EDITAR USUARIO --
		btnEditar = new JButton("Editar usuario");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila
					editarUsuario = new Editar_Usuario(user, idBib, modeloTabla, filaTabla);
					editarUsuario.setVisible(true);

				} else {
					// No se ha seleccionado ningún socio por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un usuario para editarlo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//-------------------------------------------
		
		btnEditar.setForeground(new Color(9, 3, 62));
		btnEditar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditar.setBorder(null);
		btnEditar.setBackground(new Color(233, 210, 255));
		btnEditar.setBounds(796, 580, 106, 37);
		add(btnEditar);

		//-- BORRAR USUARIO --
		btnBorrar = new JButton("Borrar usuario");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					eliminar(filaTabla, idBib);

				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un usuario para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//---------------------------------------
		
		btnBorrar.setForeground(new Color(9, 3, 62));
		btnBorrar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrar.setBorder(null);
		btnBorrar.setBackground(new Color(233, 210, 255));
		btnBorrar.setBounds(1016, 580, 106, 37);
		add(btnBorrar);

		// -------------------------- JTABLE --------------------------------------

		// -- SELECCIONAR SOCIO DE LA TABLA --
		jtResultados = new JTable();
		jtResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 0) != null) {
					user.setIdUsuario(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 0).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 1) != null) {
					user.setEmail(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 1).toString());
				}
				if (modeloTabla.getValueAt(jtResultados.getSelectedRow(), 2) != null) {
					user.setContrasenia(modeloTabla.getValueAt(jtResultados.getSelectedRow(), 2).toString());
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

		modeloTabla.setColumnIdentifiers(new Object[] { "ID", "Email", "Contraseña"});

		jtResultados.setModel(modeloTabla);
		
		rellenaTabla(idBib);

		jtResultados.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(2).setPreferredWidth(100);

		JTableHeader encabezado = jtResultados.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtResultados.getTableHeader().setResizingAllowed(false);
		jtResultados.getTableHeader().setReorderingAllowed(false);

		// -------------------------------------------------------------

	}
	
	public void  rellenaTabla(String idBib) {

		for (Usuarios recorreUsers : bd.cargaUsuarios(idBib)) {
			modeloTabla.addRow(new Object[] { recorreUsers.getIdUsuario(), recorreUsers.getEmail(), recorreUsers.getContrasenia() });
		}
	}

	public void eliminar(int filaTabla, String idBib) {
		int opcion = 0;
		String id;

		opcion = err.preguntarEliminar();

		if (opcion == 0) {
			id = bd.obtenerIdUser(user, idBib);
			bd.eliminarSocio(id, idBib);

			// Eliminamos la fila del modelo.
			modeloTabla.removeRow(filaTabla);
		}
	}

}
