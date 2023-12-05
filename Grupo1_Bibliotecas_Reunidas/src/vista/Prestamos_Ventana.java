package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	private JTable table;
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
		
		btnMen.setToolTipText("");
		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(52, 30, 79, 37);
		add(btnMen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 147, 1215, 374);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		// ----- CREAR TABLA ----
		
		//Le añadimos a nuestra tabla las columnas que va a tener:
		modeloTabla.setColumnIdentifiers(new Object[]{"ID Prestamo", "Nombre Socio", "Apellido Socio", "ID Libro", "Titulo Libro", "Fecha Préstamo","Fecha Entrega prevista"});
		//Le decimos que le establezca el modelo que hemos creado a nuestra tabla:
		table.setModel(modeloTabla);
		
		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(130, 72, 172));
		lblConsultar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblConsultar.setBounds(513, 15, 239, 81);
		add(lblConsultar);
		
		JLabel lblPrstamos = new JLabel("Préstamos");
		lblPrstamos.setVerticalAlignment(SwingConstants.TOP);
		lblPrstamos.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrstamos.setForeground(new Color(9, 3, 62));
		lblPrstamos.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblPrstamos.setBounds(564, 55, 239, 81);
		add(lblPrstamos);
		
		table.setForeground(new Color(36, 54, 69));
		table.setFont(new Font("Verdana", Font.PLAIN, 13));
		table.setBackground(new Color(255, 255, 255));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.getTableHeader().setReorderingAllowed(false);
		
		//Cambiar la altura de las filas:
		table.setRowHeight(30);
		
		JButton btnRegistrarDevolucion = new JButton("Registrar devolución");
		btnRegistrarDevolucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnRegistrarDevolucion.setForeground(Color.WHITE);
		btnRegistrarDevolucion.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnRegistrarDevolucion.setBorder(null);
		btnRegistrarDevolucion.setBackground(new Color(130, 72, 172));
		btnRegistrarDevolucion.setBounds(52, 551, 156, 37);
		add(btnRegistrarDevolucion);
	
		
		//Establecer el ancho de las columnas:
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		
		JTableHeader encabezado = table.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));
		
		//Que no se cambie el tamaño de las columnas.
		table.getTableHeader().setResizingAllowed(false);
		//Que no se cambie el orden de las columnas.
		table.getTableHeader().setReorderingAllowed(false);
		
		//Variable para hacer el if de población.
		//Recorremos los objetos del ArrayList que nos retorna el método de la clase BaseDatos:
		for(Prestamos recorrePrestamos : bd.cargaPrestamos()) {
			String idBiblioteca = recorrePrestamos.getId_biblioteca();
			String idLibro = recorrePrestamos.getId_libro();			
			String tituloLibro = bd.obtenerTituloLibro(idLibro, idBiblioteca);
			String nombreSocio = bd.obtenerNombreSocio(recorrePrestamos.getId_socio(), idBiblioteca);
			String apellidoSocio = bd.obtenerApellidoSocio(recorrePrestamos.getId_socio(), idBiblioteca);
			
			//Object puede coger todo tipo de datos, hasta imágenes.
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
