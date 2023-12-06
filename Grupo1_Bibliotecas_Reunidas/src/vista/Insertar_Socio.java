package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.BaseDeDatos;
import modelo.Libros;
import modelo.Socios;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Insertar_Socio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtNacimiento;
	private JTextField txtEmail;
	private JTextField txtTelf;
	private JTextField txtDireccion;
	private ComprobarCampos comprobar = new ComprobarCampos();
	private Socios nuevoSocio = new Socios();
	private Errores err = new Errores();
	private BaseDeDatos bd = new BaseDeDatos();

	public Insertar_Socio(String idBib, DefaultTableModel modeloTabla, Ventana ventana, boolean esAdmin) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Insertar_Socio.class.getResource("/img/libro.png")));
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(new Color(191, 164, 217));
		setBounds(100, 100, 1005, 491);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 989, 374);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblAadir = new JLabel("Añadir");
		lblAadir.setVerticalAlignment(SwingConstants.TOP);
		lblAadir.setHorizontalAlignment(SwingConstants.LEFT);
		lblAadir.setForeground(new Color(130, 72, 172));
		lblAadir.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblAadir.setBounds(395, 21, 239, 81);
		contentPanel.add(lblAadir);
		
		JLabel lblSocio = new JLabel("Socio");
		lblSocio.setVerticalAlignment(SwingConstants.TOP);
		lblSocio.setHorizontalAlignment(SwingConstants.LEFT);
		lblSocio.setForeground(new Color(9, 3, 62));
		lblSocio.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblSocio.setBounds(419, 63, 239, 81);
		contentPanel.add(lblSocio);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombre.setBounds(66, 169, 120, 19);
		contentPanel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblApellido.setBounds(66, 211, 134, 19);
		contentPanel.add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDni.setBounds(66, 252, 73, 19);
		contentPanel.add(lblDni);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(66, 297, 134, 19);
		contentPanel.add(lblFechaDeNacimiento);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(244, 168, 212, 20);
		contentPanel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtApellido.setColumns(10);
		txtApellido.setBounds(244, 210, 212, 20);
		contentPanel.add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDni.setColumns(10);
		txtDni.setBounds(244, 251, 212, 20);
		contentPanel.add(txtDni);
		
		txtNacimiento = new JTextField();
		txtNacimiento.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNacimiento.setColumns(10);
		txtNacimiento.setBounds(244, 296, 212, 20);
		contentPanel.add(txtNacimiento);
		
		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDireccin.setBounds(561, 169, 73, 19);
		contentPanel.add(lblDireccin);
		
		JLabel lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTelfono.setBounds(561, 211, 73, 19);
		contentPanel.add(lblTelfono);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEmail.setBounds(561, 252, 50, 19);
		contentPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(680, 251, 212, 20);
		contentPanel.add(txtEmail);
		
		txtTelf = new JTextField();
		txtTelf.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTelf.setColumns(10);
		txtTelf.setBounds(680, 210, 212, 20);
		contentPanel.add(txtTelf);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(680, 168, 212, 20);
		contentPanel.add(txtDireccion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 512, 989, -138);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
		}
		
		//-- BOTÓN GUARDAR --
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (camposLlenos() && comprobar.validarCamposSocios(txtNombre, txtApellido, txtDni, txtDireccion, txtEmail,
						txtTelf, txtNacimiento)) {
					
					nuevoSocio = rellenaObjeto();
					insertar(nuevoSocio, idBib);
					actualizarse(nuevoSocio, modeloTabla);
					
				}
			}
					
		});
		//-----------------------------------------
		
		btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setActionCommand("OK");
		btnGuardar.setBounds(245, 396, 98, 39);
		getContentPane().add(btnGuardar);
		
		//-- BOTÓN CANCELAR --
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		//-------------------------------
		
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setActionCommand("Cancel");
		btnCancelar.setBounds(678, 396, 111, 39);
		getContentPane().add(btnCancelar);
	}
	
	public boolean camposLlenos() {
		return !(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDni.getText().isEmpty()
				|| txtDireccion.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelf.getText().isEmpty()
				|| txtNacimiento.getText().isEmpty());
	}
	
	public Socios rellenaObjeto() {
		Socios socio = new Socios();

		socio.setNombre(txtNombre.getText());
		socio.setApellido(txtApellido.getText());
		socio.setDni(txtDni.getText());
		socio.setDireccion(txtDireccion.getText());
		socio.setEmail(txtEmail.getText());
		socio.setTelefono(txtTelf.getText());
		socio.setFechaNacimiento(txtNacimiento.getText());
		socio.setListaNegra(false);

		return socio;
	}
	
	public void insertar(Socios nuevoSocio, String idBib) {
		int opcion = 0;
		opcion = err.preguntarInsertar();

		if (opcion == 0) {
			bd.insertarSocio(nuevoSocio, idBib);

		} else {

		}
	}
	
	public void actualizarse(Socios nuevoSocio, DefaultTableModel modeloTabla) {
		Object[] nuevaFila = {nuevoSocio.getNombre(), nuevoSocio.getApellido(), nuevoSocio.getDni(), nuevoSocio.getDireccion(),
				nuevoSocio.getTelefono(), nuevoSocio.getEmail(), nuevoSocio.getFechaNacimiento(), "No"};
		modeloTabla.addRow(nuevaFila);

		modeloTabla.fireTableDataChanged();
	}
}
