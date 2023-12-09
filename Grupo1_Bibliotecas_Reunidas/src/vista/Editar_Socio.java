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
import modelo.InformacionRecibo;
import modelo.Socios;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Editar_Socio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtDireccion;
	private JTextField txtTelf;
	private JTextField txtEmail;
	private JTextField txtNacimiento;

	private BaseDeDatos bd = new BaseDeDatos();
	private Errores err = new Errores();
	private ComprobarCampos comprobar = new ComprobarCampos();
	private JCheckBox chckListaNegra;

	public Editar_Socio(Socios socio, DefaultTableModel modeloTabla, int filaTabla, String idBib) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar_Socio.class.getResource("/img/libro.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 1146, 587);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 1125, 467);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblEditor = new JLabel("Editar");
		lblEditor.setVerticalAlignment(SwingConstants.TOP);
		lblEditor.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditor.setForeground(new Color(130, 72, 172));
		lblEditor.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblEditor.setBounds(479, 22, 239, 81);
		contentPanel.add(lblEditor);

		JLabel lblSocio = new JLabel("Socio");
		lblSocio.setVerticalAlignment(SwingConstants.TOP);
		lblSocio.setHorizontalAlignment(SwingConstants.LEFT);
		lblSocio.setForeground(new Color(9, 3, 62));
		lblSocio.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblSocio.setBounds(503, 64, 239, 81);
		contentPanel.add(lblSocio);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombre.setBounds(167, 169, 120, 19);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(345, 168, 212, 20);
		contentPanel.add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblApellido.setBounds(167, 211, 134, 19);
		contentPanel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtApellido.setColumns(10);
		txtApellido.setBounds(345, 210, 212, 20);
		contentPanel.add(txtApellido);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDni.setBounds(167, 252, 73, 19);
		contentPanel.add(lblDni);

		txtDni = new JTextField();
		txtDni.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDni.setColumns(10);
		txtDni.setBounds(345, 251, 212, 20);
		contentPanel.add(txtDni);

		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDireccin.setBounds(662, 169, 73, 19);
		contentPanel.add(lblDireccin);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(788, 169, 212, 20);
		contentPanel.add(txtDireccion);

		JLabel lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTelfono.setBounds(662, 211, 73, 19);
		contentPanel.add(lblTelfono);

		txtTelf = new JTextField();
		txtTelf.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTelf.setColumns(10);
		txtTelf.setBounds(788, 211, 212, 20);
		contentPanel.add(txtTelf);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEmail.setBounds(662, 252, 50, 19);
		contentPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(788, 252, 212, 20);
		contentPanel.add(txtEmail);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(167, 297, 134, 19);
		contentPanel.add(lblFechaDeNacimiento);

		txtNacimiento = new JTextField();
		txtNacimiento.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNacimiento.setColumns(10);
		txtNacimiento.setBounds(345, 296, 212, 20);
		contentPanel.add(txtNacimiento);

		JLabel lblListaNegra = new JLabel("Lista negra:");
		lblListaNegra.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblListaNegra.setBounds(167, 393, 134, 19);
		contentPanel.add(lblListaNegra);

		chckListaNegra = new JCheckBox("");
		chckListaNegra.setBackground(new Color(255, 255, 255));
		chckListaNegra.setBounds(323, 389, 97, 23);
		contentPanel.add(chckListaNegra);

		// -- RELLENAMOS LOS TEXTFIELD --
		if (socio != null) {
			txtNombre.setText(socio.getNombre());
			txtApellido.setText(socio.getApellido());
			txtDni.setText(socio.getDni());
			txtDireccion.setText(socio.getDireccion());
			txtEmail.setText(socio.getEmail());
			txtTelf.setText(socio.getTelefono());
			txtNacimiento.setText(socio.getFechaNacimiento());
			if (socio.isListaNegra()) {
				chckListaNegra.setSelected(true);
			} else {
				chckListaNegra.setSelected(false);
			}
		}
		// -----------------------------------------

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(194, 164, 217));
			buttonPane.setBounds(0, 467, 1125, 87);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);

			// -- BOTÓN GUARDAR --
			btnGuardar = new JButton("Guardar");
			btnGuardar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (camposLlenos() && comprobar.validarCamposSocios(txtNombre, txtApellido, txtDni, txtDireccion, txtEmail,
							txtTelf, txtNacimiento)) {
			            
						editar(modeloTabla, filaTabla, idBib);
			        } else {
			            JOptionPane.showMessageDialog(null, "Verifica los campos antes de guardar.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
			// ----------------------------------------

			btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
			btnGuardar.setBackground(Color.WHITE);
			btnGuardar.setActionCommand("OK");
			btnGuardar.setBounds(333, 21, 98, 39);
			buttonPane.add(btnGuardar);

			// -- BOTÓN CANCELAR --
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			// -------------------------------------------

			btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setBounds(747, 21, 111, 39);
			buttonPane.add(btnCancelar);
		}
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

		if (chckListaNegra.isSelected()) {
			socio.setListaNegra(true);
		} else {
			socio.setListaNegra(false);
		}

		return socio;
	}
	
	public boolean camposLlenos() {
		return !(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDni.getText().isEmpty()
				|| txtDireccion.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelf.getText().isEmpty()
				|| txtNacimiento.getText().isEmpty());
	}

	public void editar(DefaultTableModel modeloTabla, int filaTabla, String idBib) {
		Socios socioEditado = new Socios();
		socioEditado = rellenaObjeto();
		int opcion = 0;
		String idSocio = "", idRecibo ="";
		String listaNegra = "";
		InformacionRecibo recibo = new InformacionRecibo();

		opcion = err.preguntarEditar();

		if (opcion == 0) {
			idSocio = bd.obtenerIdSocio(socioEditado, idBib);
			bd.editarSocio(socioEditado, idSocio, idBib);

			modeloTabla.setValueAt(socioEditado.getNombre(), filaTabla, 0);
			modeloTabla.setValueAt(socioEditado.getApellido(), filaTabla, 1);
			modeloTabla.setValueAt(socioEditado.getDni(), filaTabla, 2);
			modeloTabla.setValueAt(socioEditado.getDireccion(), filaTabla, 3);
			modeloTabla.setValueAt(socioEditado.getTelefono(), filaTabla, 4);
			modeloTabla.setValueAt(socioEditado.getEmail(), filaTabla, 5);
			modeloTabla.setValueAt(socioEditado.getFechaNacimiento(), filaTabla, 6);
			
			if(socioEditado.isListaNegra()) {
				listaNegra = "Sí";
			}else {
				listaNegra = "No";
			}
			
			modeloTabla.setValueAt(listaNegra, filaTabla, 7);
			
			//--NO HA PAGADO: MOROSO --
			if(socioEditado.isListaNegra()) {
				recibo.setId_bib(idBib);
				recibo.setId_socio(idSocio);
				recibo.setPagado(false);
				
				// -- SÍ HA PAGADO: NO MOROSO --
			}else {
				recibo.setId_bib(idBib);
				recibo.setId_socio(idSocio);
				recibo.setPagado(true);	
			}
			
			idRecibo = bd.obtenerIdRecibo(recibo);
			
			if(idRecibo == null) {
				bd.generarRecibo(recibo);
				
			}else {
				bd.editarRecibo(recibo, idRecibo);
			}
			
			
			dispose();
		}
	}
	

}
