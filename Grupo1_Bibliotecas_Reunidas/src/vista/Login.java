package vista;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.CommunicationsException;

import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel{
	private JLabel lblTitulo;
	private JLabel img;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	
	private String usuario = "";
	private String contrasenia = "";
	private JLabel lblerror;
	private boolean correcto;
	private JButton btnAceptar;
	private Ventana ventana;
	private boolean esAdmin = true;

	
	//Modificamos el constructor para recibir una referencia de la instancia de "Ventana".
	public Login(Ventana ventana) {
		this.ventana = ventana;
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		btnAceptar = new JButton("Entrar");
		
		img = new JLabel("");
		img.setIcon(new ImageIcon(Login.class.getResource("/img/Bib.png")));
		img.setBounds(426, 80, 171, 177);
		add(img);
		
		lblTitulo = new JLabel("Librarium");
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblTitulo.setBounds(602, 162, 239, 81);
		add(lblTitulo);
		
		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				lblerror.setText("");
			}
		});
		
		txtUsuario.setBorder(new LineBorder(new Color(130, 72, 172), 3));
		txtUsuario.setBounds(455, 331, 342, 37);
		add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				lblerror.setText("");
			}
		});
		
		txtContrasenia.setBorder(new LineBorder(new Color(130, 72, 172), 3));
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(455, 428, 342, 37);
		add(txtContrasenia);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(9, 3, 62));
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblUsuario.setBounds(455, 306, 117, 14);
		add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setForeground(new Color(9, 3, 62));
		lblContrasenia.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContrasenia.setBounds(455, 403, 153, 14);
		add(lblContrasenia);
		
		//-- ACCIÓN ACEPTAR --
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				usuario = txtUsuario.getText();
				contrasenia = new String(txtContrasenia.getPassword());
				
				correcto = compruebaUsuario(usuario, contrasenia);
				if(correcto) {
					
					//Comprobamos si el usuario es admin o no para habilitar el botón de usuarios en el menú.
					if(!(usuario.contains("admin"))){
						esAdmin = false;
					}
					
					//Utilizamos la referencia a Ventana para cambiar al panel Menu
		            ventana.nuevoPanel(new Menu(ventana, esAdmin));
					
				}else {
					lblerror.setText("Usuario o contraseña incorrecto.");
				}
	
			}
		});
		//--------------------------------------------------
		
		btnAceptar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAceptar.setBorder(null);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(130, 72, 172));
		btnAceptar.setBounds(455, 516, 117, 37);
		add(btnAceptar);
		
		lblerror = new JLabel("");
		lblerror.setForeground(new Color(64, 0, 0));
		lblerror.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblerror.setBounds(575, 478, 239, 14);
		add(lblerror);

	}
	
	public boolean compruebaUsuario(String usuario, String contrasenia) {
		String id = "";
		boolean correcto = false;
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas","root","");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT id_usuario"
					+ " FROM usuarios"
					+ " WHERE email_usuario = '" + usuario + "'");

			if (registro.next()) {
				id = registro.getString("id_usuario");
				
				correcto = compruebaContrasenia(id, contrasenia);
				
			}
			
		} catch (CommunicationsException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				System.out.println("SQLException");
				
			}catch(NullPointerException e) {
				System.out.println("NullPointerException");
			}
		}

		return correcto;
	}
	
	public boolean compruebaContrasenia(String id, String contrasenia) {
		boolean correcto = false;
		String contra = "";
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecas_reunidas","root","");

			consulta = conexion.createStatement();
			registro = consulta.executeQuery("SELECT material"
					+ " FROM otros"
					+ " WHERE id_otro = '" + id + "'");
			
			if (registro.next()) {
				contra = registro.getString("material");
				
				if(contra.equals(contrasenia)) {
					correcto = true;
				}
			}
	
		} catch (CommunicationsException e) {
			
		} catch (SQLException e) {
			
		}finally {
			try {
				conexion.close();
			}catch(SQLException e) {
				System.out.println("SQLException");
			}catch(NullPointerException e) {
				System.out.println("NullPointerException");
			}
		}
		
		return correcto;
	}
	
}
