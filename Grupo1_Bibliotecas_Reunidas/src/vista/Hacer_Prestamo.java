package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.BaseDeDatos;
import modelo.Libros;
import modelo.Prestamos;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class Hacer_Prestamo extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreSocio;
	private JTextField txtApellidoSocio;
	private JTextField txtCorreoSocio;
	private String idSocio;
	private String idLibro;
	private String idBiblioteca;
	private String nombreSocio;
	private String apellidoSocio;
	private String correoSocio;
	
	private Prestamos prestamo = new Prestamos();
	private BaseDeDatos bd = new BaseDeDatos();
	private Errores err = new Errores();
	private JTextField textField;
	private boolean insertRealizado = false;
	
	//Con el boolean esAdmin no tienes que hacer nada, es para que al retornar al menú sepa qué tipo de usuario está en la app.
	//Librito tiene toda la info del libro que se ha seleccionado en la tabla. A través del getter se obtienen sus datos.
	public Hacer_Prestamo(Libros librito, String idBib) {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar_Libro.class.getResource("/img/libro.png")));
		setBounds(180, 130, 625, 528);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 609, 489);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNombreSocio = new JLabel("NOMBRE DEL SOCIO");
		lblNombreSocio.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNombreSocio.setBounds(49, 149, 138, 24);
		contentPanel.add(lblNombreSocio);
		
		JLabel lblApellidoSocio = new JLabel("APELLIDO DEL SOCIO");
		lblApellidoSocio.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblApellidoSocio.setBounds(221, 149, 142, 24);
		contentPanel.add(lblApellidoSocio);
		
		JLabel lblCorreoSocio = new JLabel("CORREO DEL SOCIO");
		lblCorreoSocio.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCorreoSocio.setBounds(415, 149, 142, 24);
		contentPanel.add(lblCorreoSocio);
		
		txtNombreSocio = new JTextField();
		txtNombreSocio.setColumns(10);
		txtNombreSocio.setBounds(38, 200, 138, 37);
		contentPanel.add(txtNombreSocio);
		
		txtApellidoSocio = new JTextField();
		txtApellidoSocio.setColumns(10);
		txtApellidoSocio.setBounds(38, 200, 138, 37);
		contentPanel.add(txtApellidoSocio);
		
		txtCorreoSocio = new JTextField();
		txtCorreoSocio.setColumns(10);
		txtCorreoSocio.setBounds(391, 200, 178, 37);
		contentPanel.add(txtCorreoSocio);
		
		JButton btnInsertar = new JButton("SOLICITAR NUEVO PRÉSTAMO");
		btnInsertar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreSocio = txtNombreSocio.getText();
				apellidoSocio = txtApellidoSocio.getText();
				correoSocio = txtCorreoSocio.getText();
				
				if (!bd.comprobarDatosSocio(nombreSocio, apellidoSocio, correoSocio)) {
					JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos y no se encuentran en la Base de Datos. Por favor, inténtelo de nuevo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else{
					idSocio = bd.obtenerIdSocio(librito, nombreSocio, apellidoSocio, correoSocio, idBib);
					idLibro = bd.obtenerIdLibro(librito, idBib);

					insertRealizado = bd.insertarPrestamo(idSocio, idLibro, idBib);
					
					if (insertRealizado) {
						bd.borrarUnaUnidadStock(idLibro, idBib);
					}
				}								
			}
		});
		btnInsertar.setBounds(193, 277, 216, 51);
		contentPanel.add(btnInsertar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnVolver.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnVolver.setBackground(new Color(130, 72, 172));
		btnVolver.setActionCommand("Cancel");
		btnVolver.setBounds(38, 414, 111, 39);
		contentPanel.add(btnVolver);
		
		JLabel lblPrstamo = new JLabel("Préstamo");
		lblPrstamo.setVerticalAlignment(SwingConstants.TOP);
		lblPrstamo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrstamo.setForeground(new Color(9, 3, 62));
		lblPrstamo.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblPrstamo.setBounds(221, 57, 239, 81);
		contentPanel.add(lblPrstamo);
		
		JLabel lblRealizar = new JLabel("Realizar");
		lblRealizar.setVerticalAlignment(SwingConstants.TOP);
		lblRealizar.setHorizontalAlignment(SwingConstants.LEFT);
		lblRealizar.setForeground(new Color(130, 72, 172));
		lblRealizar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblRealizar.setBounds(170, 17, 239, 81);
		contentPanel.add(lblRealizar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(213, 200, 138, 37);
		contentPanel.add(textField);

	}
}
