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
	
	//Con el boolean esAdmin no tienes que hacer nada, es para que al retornar al menú sepa qué tipo de usuario está en la app.
	//Librito tiene toda la info del libro que se ha seleccionado en la tabla. A través del getter se obtienen sus datos.
	public Hacer_Prestamo(Libros librito) {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar_Libro.class.getResource("/img/libro.png")));
		setBounds(180, 130, 1147, 698);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1131, 577);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNombreSocio = new JLabel("NOMBRE DEL SOCIO");
		lblNombreSocio.setBounds(94, 149, 102, 24);
		contentPanel.add(lblNombreSocio);
		
		JLabel lblApellidoSocio = new JLabel("APELLIDO DEL SOCIO");
		lblApellidoSocio.setBounds(248, 149, 112, 24);
		contentPanel.add(lblApellidoSocio);
		
		JLabel lblCorreoSocio = new JLabel("CORREO DEL SOCIO");
		lblCorreoSocio.setBounds(430, 149, 102, 24);
		contentPanel.add(lblCorreoSocio);
		
		txtNombreSocio = new JTextField();
		txtNombreSocio.setColumns(10);
		txtNombreSocio.setBounds(75, 200, 138, 37);
		contentPanel.add(txtNombreSocio);
		
		txtApellidoSocio = new JTextField();
		txtApellidoSocio.setColumns(10);
		txtApellidoSocio.setBounds(233, 200, 138, 37);
		contentPanel.add(txtApellidoSocio);
		
		txtCorreoSocio = new JTextField();
		txtCorreoSocio.setColumns(10);
		txtCorreoSocio.setBounds(391, 200, 178, 37);
		contentPanel.add(txtCorreoSocio);
		
		JButton btnInsertar = new JButton("SOLICITAR NUEVO PRÉSTAMO");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreSocio = txtNombreSocio.getText();
				System.out.println(nombreSocio);
				apellidoSocio = txtApellidoSocio.getText();
				System.out.println(apellidoSocio);
				correoSocio = txtCorreoSocio.getText();
				System.out.println(correoSocio);
				
				if (!bd.comprobarDatosSocio(nombreSocio, apellidoSocio, correoSocio)) {
					JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos y no se encuentran en la Base de Datos. Por favor, inténtelo de nuevo.");
				} else{
					idSocio = bd.obtenerIdSocio(librito, nombreSocio, apellidoSocio, correoSocio);
					idLibro = bd.obtenerIdLibro(librito);
					idBiblioteca = bd.obtenerIdBiblioteca(librito);

					bd.insertarPrestamo(idSocio, idLibro, idBiblioteca);
				}								
			}
		});
		btnInsertar.setBounds(193, 277, 216, 51);
		contentPanel.add(btnInsertar);
		
		JLabel lblTitulo = new JLabel("SOLICITUD DE PRÉSTAMO");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(172, 24, 247, 73);
		contentPanel.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnVolver.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setActionCommand("Cancel");
		btnVolver.setBounds(75, 412, 111, 39);
		contentPanel.add(btnVolver);

	}
}
