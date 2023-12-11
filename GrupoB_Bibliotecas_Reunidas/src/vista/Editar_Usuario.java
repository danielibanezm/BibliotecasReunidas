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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Editar_Usuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JTextField txtContrasenia;

	private BaseDeDatos bd = new BaseDeDatos();
	private Errores err = new Errores();
	private ComprobarCampos comprobar = new ComprobarCampos();


	public Editar_Usuario(Usuarios user, String idBib, DefaultTableModel modeloTabla, int filaTabla) {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar_Usuario.class.getResource("/img/libro.png")));
		setBounds(400, 250, 646, 426);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 661, 323);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEmail.setBounds(147, 170, 120, 19);
		contentPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(325, 169, 212, 20);
		contentPanel.add(txtEmail);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContrasea.setBounds(147, 212, 134, 19);
		contentPanel.add(lblContrasea);

		txtContrasenia = new JTextField();
		txtContrasenia.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(325, 211, 212, 20);
		contentPanel.add(txtContrasenia);

		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setVerticalAlignment(SwingConstants.TOP);
		lblEditar.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditar.setForeground(new Color(130, 72, 172));
		lblEditar.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblEditar.setBounds(233, 11, 239, 81);
		contentPanel.add(lblEditar);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setVerticalAlignment(SwingConstants.TOP);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setForeground(new Color(9, 3, 62));
		lblUsuario.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblUsuario.setBounds(257, 53, 239, 81);
		contentPanel.add(lblUsuario);

		// -- RELLENAMOS LOS TEXTFIELD --
		if (user != null) {
			txtEmail.setText(user.getEmail());
			txtContrasenia.setText(user.getContrasenia());
		}
		// -----------------------------------------

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(191, 164, 217));
			buttonPane.setBounds(0, 322, 630, 65);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);

			// -- GUARDAR --
			JButton btnGuardar = new JButton("Guardar");
			btnGuardar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (camposLlenos() && comprobar.validarCamposUsuarios(txtEmail, txtContrasenia)) {
			            
						editar(modeloTabla, filaTabla, idBib, user);
			        } else {
			            JOptionPane.showMessageDialog(null, "Verifica los campos antes de guardar.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
			// -------------------------------------

			btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
			btnGuardar.setBackground(Color.WHITE);
			btnGuardar.setActionCommand("OK");
			btnGuardar.setBounds(76, 11, 98, 39);
			buttonPane.add(btnGuardar);

			// -- CANCELAR --
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			// -------------------------------------

			btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setBounds(493, 11, 111, 39);
			buttonPane.add(btnCancelar);
		}
	}

	public boolean camposLlenos() {
		return !(txtEmail.getText().isEmpty() || txtContrasenia.getText().isEmpty());
	}

	public Usuarios rellenaObjeto(Usuarios user) {
		Usuarios userEdit = new Usuarios();

		userEdit.setIdUsuario(user.getIdUsuario());
		userEdit.setEmail(txtEmail.getText());
		userEdit.setContrasenia(txtContrasenia.getText());

		return userEdit;
	}

	public void editar(DefaultTableModel modeloTabla, int filaTabla, String idBib, Usuarios user) {
		Usuarios userEdit = new Usuarios();
		userEdit = rellenaObjeto(user);
		int opcion = 0;
		String id = "";

		opcion = err.preguntarEditar();

		if (opcion == 0) {
			bd.editarEmail(userEdit, idBib);
			bd.editarContrasenia(userEdit, idBib);

			modeloTabla.setValueAt(userEdit.getIdUsuario(), filaTabla, 0);
			modeloTabla.setValueAt(userEdit.getEmail(), filaTabla, 1);
			modeloTabla.setValueAt(userEdit.getContrasenia(), filaTabla, 2);

			dispose();
		}
	}
}
