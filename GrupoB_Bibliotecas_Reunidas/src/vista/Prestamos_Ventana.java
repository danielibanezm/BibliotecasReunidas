package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controlador.BaseDeDatos;
import modelo.Prestamos;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Prestamos_Ventana extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jtPrestamos;
	private int filaTabla;
	DefaultTableModel modeloTabla = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public Prestamos_Ventana(Ventana ventana, boolean esAdmin, String idBib) {
		BaseDeDatos bd = new BaseDeDatos();
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		//		--	VOLVER AL MENÚ --
		JButton btnMen = new JButton("MENÚ");
		btnMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin, idBib));
			}
		});
		//-------------------------------------------------------
		
		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(52, 30, 79, 37);
		add(btnMen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 164, 1278, 374);
		add(scrollPane);
		
		jtPrestamos = new JTable();
		scrollPane.setViewportView(jtPrestamos);
		
		// ----- CREAR TABLA ----
		modeloTabla.setColumnIdentifiers(new Object[]{"ID Préstamo", "Nombre Socio", "Apellido Socio", "ID Libro", "Titulo Libro", "Fecha Préstamo","Fecha Entrega prevista"});
		jtPrestamos.setModel(modeloTabla);
		
		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(130, 72, 172));
		lblConsultar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblConsultar.setBounds(535, 32, 239, 81);
		add(lblConsultar);
		
		JLabel lblPrstamos = new JLabel("Préstamos");
		lblPrstamos.setVerticalAlignment(SwingConstants.TOP);
		lblPrstamos.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrstamos.setForeground(new Color(9, 3, 62));
		lblPrstamos.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblPrstamos.setBounds(586, 72, 239, 81);
		add(lblPrstamos);
		
		jtPrestamos.setForeground(new Color(36, 54, 69));
		jtPrestamos.setFont(new Font("Verdana", Font.PLAIN, 13));
		jtPrestamos.setBackground(new Color(255, 255, 255));
		jtPrestamos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jtPrestamos.getTableHeader().setReorderingAllowed(false);
		jtPrestamos.setRowHeight(30);
		JButton btnRegistrarDevolucion = new JButton("Registrar devolución");
		btnRegistrarDevolucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtPrestamos.getSelectedRow();
				if (filaTabla != -1) { //Se ha seleccionado una fila
					String idPrestamo = (String) jtPrestamos.getValueAt(filaTabla, 0);
				    String idLibro = (String) jtPrestamos.getValueAt(filaTabla, 3);
				    String nombreSocio = (String) jtPrestamos.getValueAt(filaTabla, 1);
				    String apellidoSocio = (String) jtPrestamos.getValueAt(filaTabla, 2);
				    
				    String idSocio = bd.obtenerIdSocioDesdeNombre(nombreSocio, apellidoSocio, idBib);
				    
				    bd.verificarFechaDevolucion (idPrestamo, idSocio, idBib, idLibro);
				    
				    // Eliminamos la fila del modelo.
					modeloTabla.removeRow(filaTabla);
					
				    bd.actualizarStock(idLibro, idBib);

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un préstamo para proceder con la devolución", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		btnRegistrarDevolucion.setForeground(Color.WHITE);
		btnRegistrarDevolucion.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnRegistrarDevolucion.setBorder(null);
		btnRegistrarDevolucion.setBackground(new Color(130, 72, 172));
		btnRegistrarDevolucion.setBounds(52, 583, 156, 37);
		add(btnRegistrarDevolucion);

		jtPrestamos.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtPrestamos.getColumnModel().getColumn(1).setPreferredWidth(60);
		jtPrestamos.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtPrestamos.getColumnModel().getColumn(3).setPreferredWidth(60);
		jtPrestamos.getColumnModel().getColumn(4).setPreferredWidth(200);
		jtPrestamos.getColumnModel().getColumn(5).setPreferredWidth(120);
		jtPrestamos.getColumnModel().getColumn(6).setPreferredWidth(120);
		
		JTableHeader encabezado = jtPrestamos.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtPrestamos.getTableHeader().setResizingAllowed(false);
		jtPrestamos.getTableHeader().setReorderingAllowed(false);
		
		for(Prestamos recorrePrestamos : bd.cargaPrestamos(idBib)) {
			String idBiblioteca = recorrePrestamos.getId_biblioteca();
			String idLibro = recorrePrestamos.getId_libro();			
			String tituloLibro = bd.obtenerTituloLibro(idLibro, idBiblioteca);
			String nombreSocio = bd.obtenerNombreSocio(recorrePrestamos.getId_socio(), idBiblioteca);
			String apellidoSocio = bd.obtenerApellidoSocio(recorrePrestamos.getId_socio(), idBiblioteca);
			
			modeloTabla.addRow(new Object[] {
				recorrePrestamos.getId_prestamo(),
				nombreSocio,
				apellidoSocio,
				recorrePrestamos.getId_libro(),
				tituloLibro,
				recorrePrestamos.getFecha_prestamo(),
				recorrePrestamos.getFecha_entrega_prevista(),
			});
		}

	}
}
