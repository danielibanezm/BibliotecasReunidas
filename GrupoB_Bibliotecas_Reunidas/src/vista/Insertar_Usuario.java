package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.BaseDeDatos;
import modelo.Socios;
import modelo.Usuarios;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Insertar_Usuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JTextField txtContrasenia;
	
	private ComprobarCampos comprobar = new ComprobarCampos();
	private Usuarios nuevoUser = new Usuarios();
	private Errores err = new Errores();
	private BaseDeDatos bd = new BaseDeDatos();


	public Insertar_Usuario(String idBib, DefaultTableModel modeloTabla, Ventana ventana, boolean esAdmin) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Insertar_Usuario.class.getResource("/img/libro.png")));
		setModal(true);
		setBounds(400, 250, 646, 426);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 642, 320);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblAadir = new JLabel("Añadir");
			lblAadir.setVerticalAlignment(SwingConstants.TOP);
			lblAadir.setHorizontalAlignment(SwingConstants.LEFT);
			lblAadir.setForeground(new Color(130, 72, 172));
			lblAadir.setFont(new Font("Gabriola", Font.BOLD, 55));
			lblAadir.setBounds(224, 11, 239, 81);
			contentPanel.add(lblAadir);
		}
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setVerticalAlignment(SwingConstants.TOP);
			lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			lblUsuario.setForeground(new Color(9, 3, 62));
			lblUsuario.setFont(new Font("Gabriola", Font.BOLD, 55));
			lblUsuario.setBounds(248, 53, 239, 81);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblEmail.setBounds(131, 175, 120, 19);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblContrasea = new JLabel("Contraseña:");
			lblContrasea.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblContrasea.setBounds(131, 217, 134, 19);
			contentPanel.add(lblContrasea);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
			txtEmail.setColumns(10);
			txtEmail.setBounds(309, 174, 212, 20);
			contentPanel.add(txtEmail);
		}
		{
			txtContrasenia = new JTextField();
			txtContrasenia.setFont(new Font("Verdana", Font.PLAIN, 12));
			txtContrasenia.setColumns(10);
			txtContrasenia.setBounds(309, 216, 212, 20);
			contentPanel.add(txtContrasenia);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(191, 164, 217));
			buttonPane.setBounds(0, 320, 642, 67);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				// -- GUARDAR --
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (camposLlenos() && comprobar.validarCamposUsuarios(txtEmail, txtContrasenia)) {
							
							nuevoUser = rellenaObjeto();
							insertar(nuevoUser, idBib);
							actualizarse(nuevoUser, modeloTabla);
							
						}
					}
				});
				// ---------------------------------
				
				btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnGuardar.setBackground(Color.WHITE);
				btnGuardar.setActionCommand("OK");
				btnGuardar.setBounds(59, 11, 98, 39);
				buttonPane.add(btnGuardar);
			}
			{
				// -- CANCELAR --
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				// ---------------------------------
				
				btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setActionCommand("Cancel");
				btnCancelar.setBounds(492, 11, 111, 39);
				buttonPane.add(btnCancelar);
			}
		}
	}

	public boolean camposLlenos() {
		return !(txtEmail.getText().isEmpty() || txtContrasenia.getText().isEmpty());
	}
	
	public Usuarios rellenaObjeto() {
		Usuarios user = new Usuarios();

		user.setEmail(txtEmail.getText());
		user.setContrasenia(txtContrasenia.getText());

		return user;
	}
	
	public void insertar(Usuarios nuevoUser, String idBib) {
		int opcion = 0;
		opcion = err.preguntarInsertar();

		if (opcion == 0) {
			bd.insertarUsuario(nuevoUser, idBib);
			bd.insertarContrasenia(nuevoUser, idBib);

		} else {

		}
	}
	
	public void actualizarse(Usuarios nuevoUser, DefaultTableModel modeloTabla) {
		Object[] nuevaFila = {nuevoUser.getIdUsuario(), nuevoUser.getEmail(), nuevoUser.getContrasenia()};
		modeloTabla.addRow(nuevaFila);

		modeloTabla.fireTableDataChanged();
	}
}
