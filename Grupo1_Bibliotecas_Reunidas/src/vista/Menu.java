package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
	private Ventana ventana;

	/**
	 * Create the panel.
	 */
	public Menu(Ventana ventana, boolean esAdmin) {
		this.ventana = ventana;
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Librarium");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setForeground(new Color(130, 72, 172));
		lblTitulo.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblTitulo.setBounds(601, 69, 239, 81);
		add(lblTitulo);
		
		JLabel lblMen = new JLabel("Menú");
		lblMen.setVerticalAlignment(SwingConstants.TOP);
		lblMen.setHorizontalAlignment(SwingConstants.LEFT);
		lblMen.setForeground(new Color(9, 3, 62));
		lblMen.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblMen.setBounds(601, 111, 239, 81);
		add(lblMen);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/img/lilLib.png")));
		lblNewLabel.setBounds(479, 43, 181, 135);
		add(lblNewLabel);
		
		JButton btnLibros = new JButton("Libros");
		
		//-- ACCIÓN BOTÓN LIBROS --
		btnLibros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Libros_Ventana(ventana, esAdmin));
			}
		});
		//--------------------------------------------
		
		btnLibros.setBorder(null);
		btnLibros.setForeground(new Color(255, 255, 255));
		btnLibros.setBackground(new Color(160, 113, 196));
		btnLibros.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnLibros.setBounds(479, 328, 315, 37);
		add(btnLibros);
		
		//-- ACCIÓN BOTÓN SOCIOS --
		JButton btnSocios = new JButton("Socios");
		btnSocios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Socios_Ventana(ventana, esAdmin));
			}
		});
		//------------------------------------------------
		
		btnSocios.setBackground(new Color(230, 217, 240));
		btnSocios.setBorder(null);
		btnSocios.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSocios.setBounds(479, 244, 315, 37);
		add(btnSocios);
		
		//-- ACCIÓN BOTÓN PRÉSTAMOS --
		JButton btnPrestamos = new JButton("Préstamos");
		btnPrestamos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Prestamos_Ventana(ventana, esAdmin));
			}
		});
		//---------------------------------------------------
		
		btnPrestamos.setBackground(new Color(217, 198, 232));
		btnPrestamos.setBorder(null);
		btnPrestamos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPrestamos.setToolTipText("");
		btnPrestamos.setBounds(479, 413, 315, 37);
		add(btnPrestamos);
		
		//-- ACCIÓN BOTÓN MULTAS --
		JButton btnReciMul = new JButton("Recibos y Multas");
		btnReciMul.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Multas_Ventana(ventana, esAdmin));
			}
		});
		//-------------------------------------------------------
		
		btnReciMul.setForeground(new Color(255, 255, 255));
		btnReciMul.setBackground(new Color(160, 113, 196));
		btnReciMul.setBorder(null);
		btnReciMul.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnReciMul.setToolTipText("");
		btnReciMul.setBounds(479, 497, 315, 37);
		add(btnReciMul);
		
		//-- ACCIÓN BOTÓN usuarios --
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Usuarios_Ventana(ventana, esAdmin));	
			}
		});
		//-----------------------------------------------------------
		btnUsuarios.setBackground(new Color(217, 198, 232));
		btnUsuarios.setBorder(null);
		btnUsuarios.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnUsuarios.setToolTipText("");
		btnUsuarios.setBounds(479, 581, 315, 37);
		add(btnUsuarios);
		
		//Hacemos que el botón usuarios sea visible o no depende del perfil.
		if(esAdmin == false) {
			btnUsuarios.setVisible(false);
		}else {
			btnUsuarios.setVisible(true);
		}
		//-----------------------------------------------------------
		
		JButton btnSalir = new JButton("Cerrar sesión");
		
		//-- ACCIÓN CERRAR SESIÓN --
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Utilizamos la referencia a Ventana para cambiar al panel Menu
	            ventana.nuevoPanel(new Login(ventana));
			}
		});
		//-------------------------------------------
		
		btnSalir.setForeground(new Color(0, 0, 0));
		btnSalir.setToolTipText("");
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnSalir.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBounds(479, 664, 111, 37);
		add(btnSalir);

	}
}
