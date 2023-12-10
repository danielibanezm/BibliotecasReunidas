package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controlador.BaseDeDatos;
import modelo.Prestamos;
import modelo.InformacionRecibo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Recibos_Ventana extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jtRecibos;
	private int filaTabla;
	DefaultTableModel modeloTabla = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public Recibos_Ventana(Ventana ventana, boolean esAdmin, String idBib) {
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
		
		jtRecibos = new JTable();
		scrollPane.setViewportView(jtRecibos);
		
		// ----- CREAR TABLA ----
		modeloTabla.setColumnIdentifiers(new Object[]{"ID Recibo", "Nombre Socio", "Apellido Socio", "DNI Socio", "Multado/a", "Recibo Pagado"});
		jtRecibos.setModel(modeloTabla);
		
		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(130, 72, 172));
		lblConsultar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblConsultar.setBounds(535, 32, 239, 81);
		add(lblConsultar);
		
		JLabel lblPrstamos = new JLabel("Recibos");
		lblPrstamos.setVerticalAlignment(SwingConstants.TOP);
		lblPrstamos.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrstamos.setForeground(new Color(9, 3, 62));
		lblPrstamos.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblPrstamos.setBounds(586, 72, 239, 81);
		add(lblPrstamos);
		
		jtRecibos.setForeground(new Color(36, 54, 69));
		jtRecibos.setFont(new Font("Verdana", Font.PLAIN, 13));
		jtRecibos.setBackground(new Color(255, 255, 255));
		jtRecibos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jtRecibos.getTableHeader().setReorderingAllowed(false);
		
		//Cambiar la altura de las filas:
		jtRecibos.setRowHeight(30);
		
		JButton btnRegistrarDevolucion = new JButton("Imprimir recibo seleccionado");
		btnRegistrarDevolucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        imprimirCuotas(true, idBib); // true indica que es solo un usuario específico
			}
		});
		btnRegistrarDevolucion.setForeground(new Color(9, 3, 62));
		btnRegistrarDevolucion.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnRegistrarDevolucion.setBorder(null);
		btnRegistrarDevolucion.setBackground(new Color(233, 210, 255));
		btnRegistrarDevolucion.setBounds(52, 583, 186, 37);
		add(btnRegistrarDevolucion);
		
		JButton btnImprimirTodosLos = new JButton("Imprimir todos los recibos");
		btnImprimirTodosLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        imprimirCuotas(false, idBib); // false indica que no es solo un usuario específico
			}
		});
		btnImprimirTodosLos.setForeground(new Color(9, 3, 62));
		btnImprimirTodosLos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnImprimirTodosLos.setBorder(null);
		btnImprimirTodosLos.setBackground(new Color(233, 210, 255));
		btnImprimirTodosLos.setBounds(619, 583, 170, 37);
		add(btnImprimirTodosLos);
		
		JButton btnConfirmarPago = new JButton("Confirmar pago recibo");
		btnConfirmarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = jtRecibos.getSelectedRow();

		        if (filaSeleccionada != -1) {
		            String idRecibo = (String) jtRecibos.getValueAt(filaSeleccionada, 0);

		            bd.confirmarPagoRecibo(idRecibo);

		            modeloTabla.setValueAt("Sí", filaSeleccionada, 4);

		            JOptionPane.showMessageDialog(null, "Pago del recibo confirmado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Seleccione un recibo para confirmar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnConfirmarPago.setForeground(Color.WHITE);
		btnConfirmarPago.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConfirmarPago.setBorder(null);
		btnConfirmarPago.setBackground(new Color(130, 72, 172));
		btnConfirmarPago.setBounds(1174, 583, 156, 37);
		add(btnConfirmarPago);
	
		jtRecibos.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtRecibos.getColumnModel().getColumn(1).setPreferredWidth(60);
		jtRecibos.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtRecibos.getColumnModel().getColumn(3).setPreferredWidth(60);
		jtRecibos.getColumnModel().getColumn(4).setPreferredWidth(60);
		jtRecibos.getColumnModel().getColumn(4).setPreferredWidth(60);
		
		JTableHeader encabezado = jtRecibos.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtRecibos.getTableHeader().setResizingAllowed(false);
		jtRecibos.getTableHeader().setReorderingAllowed(false);
		
		String pagado = "";
		String multado = "";

		// -- Cargar información en la tabla --
		for(InformacionRecibo recorreRecibos : bd.cargaInfoRecibos(idBib)) {	
			
			if (recorreRecibos.isPagado()) {
				pagado = "Sí";
			} else {
				pagado = "No";
			}
			
			if (recorreRecibos.isMulta_obtenida()) {
				multado = "Sí";
			} else {
				multado = "No";
			}

			modeloTabla.addRow(new Object[] {
				recorreRecibos.getId_recibo(),
				recorreRecibos.getNombre_socio(),
				recorreRecibos.getApellido_socio(),
				recorreRecibos.getDni_socio(),
				multado,
				pagado
			});
		}
	}
	
	private void imprimirCuotas(boolean esUsuarioEspecifico, String idBib) {
	    BaseDeDatos bd = new BaseDeDatos();
	    Errores err = new Errores();
	    
	    int filaSeleccionada = jtRecibos.getSelectedRow();

	    if (esUsuarioEspecifico && filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione un socio para imprimir la cuota.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    ArrayList<InformacionRecibo> listaRecibos = new ArrayList<>();
	    if (esUsuarioEspecifico) {
	        // Obtener información solo del socio seleccionado
	        InformacionRecibo reciboUsuario = bd.cargaInfoRecibos(idBib).get(filaSeleccionada);
	        listaRecibos.add(reciboUsuario);
	    } else {
	        // Obtener información de todos los socios
	        listaRecibos = bd.cargaInfoRecibos(idBib);
	    }

	    String nombreFichero;
	    
	    if (esUsuarioEspecifico) {
	        nombreFichero = "Recibo_Usuario.txt";
	    } else {
	        nombreFichero = "Recibo_Todos.txt";
	    }
	    
	    try {
	    	PrintWriter writer = new PrintWriter(new FileWriter(nombreFichero));
	        for (InformacionRecibo recibo : listaRecibos) {
	            int cuota;
	            if (recibo.isMulta_obtenida()) {
	                cuota = 10;
	            } else {
	                cuota = 5;
	            }

	            writer.println("ID Recibo: " + recibo.getId_recibo());
	            writer.println("Nombre Socio: " + recibo.getNombre_socio());
	            writer.println("Apellido Socio: " + recibo.getApellido_socio());
	            writer.println("DNI Socio: " + recibo.getDni_socio());
	            writer.println("Calle Biblioteca: " + recibo.getCalle_biblioteca());
	            writer.println("Provincia Biblioteca: " + recibo.getProvincia_biblioteca());
	            writer.println("Código Postal Biblioteca: " + recibo.getCodigo_postal_biblioteca());
	            writer.println("Teléfono Biblioteca: " + recibo.getTelefono_biblioteca());
	            writer.println("Cuota Mensual: " + cuota + "€");
	            writer.println("======================================");
	        }

	        JOptionPane.showMessageDialog(null, "Recibos insertados correctamente en el archivo: " + nombreFichero, "Información", JOptionPane.INFORMATION_MESSAGE);
	    
	        writer.close();
	    } catch (FileNotFoundException e) {
	        JOptionPane.showMessageDialog(null, "Error al crear el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (IOException e) {
			e.printStackTrace();
			err.baseDatosNoConexion();
	    }
	}
}
