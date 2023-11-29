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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.BaseDeDatos;
import modelo.Libros;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Hacer_Prestamo_Ventana extends JPanel {
	private JTextField txtComentario;
	private String socioSeleccionado = null;
	private String libroSeleccionado = null;
	private String bibliotecaSeleccionada = null;
	private String textoComentario = null;
	
	
	//Con el boolean esAdmin no tienes que hacer nada, es para que al retornar al menú sepa qué tipo de usuario está en la app.
	//Librito tiene toda la info del libro que se ha seleccionado en la tabla. A través del getter se obtienen sus datos.
	public Hacer_Prestamo_Ventana(Ventana ventana, boolean esAdmin, Libros librito) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		BaseDeDatos bd = new BaseDeDatos();

			txtComentario = new JTextField();
			txtComentario.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					textoComentario = txtComentario.getText();
				}
			});
			txtComentario.setBounds(649, 214, 207, 70);
			add(txtComentario);
			txtComentario.setColumns(10);
			
			JLabel lblSocio = new JLabel("ID SOCIO");
			lblSocio.setBounds(358, 163, 68, 24);
			add(lblSocio);
			
			JLabel lblLibro = new JLabel("ID LIBRO");
			lblLibro.setBounds(454, 163, 68, 24);
			add(lblLibro);
			
			JLabel lblBiblioteca = new JLabel("ID BIBLIOTECA");
			lblBiblioteca.setBounds(547, 163, 80, 24);
			add(lblBiblioteca);
			
			JLabel lblComentario = new JLabel("COMENTARIO");
			lblComentario.setBounds(710, 163, 80, 24);
			add(lblComentario);
			
			// ------------ SOCIOS -----------
			
			JComboBox cmbSocios = new JComboBox();
			// Obtener la lista de socios del método cargaComboSocios
			ArrayList<String> listaSocios = bd.cargaComboSocios();

			// Añadir los elementos al JComboBox
			for (String socio : listaSocios) {
			    cmbSocios.addItem(socio);
			}
			cmbSocios.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					// Obtener el socio seleccionado
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                    socioSeleccionado = (String) cmbSocios.getSelectedItem();
	                    System.out.println(socioSeleccionado);
	                }
				}
			});

			cmbSocios.setBounds(345, 214, 74, 38);
			add(cmbSocios);
			
			
			// ----------- LIBROS ---------
			
			JComboBox cmbLibros = new JComboBox();
			ArrayList<String> listaLibros = bd.cargaComboLibros();

			for (String libro : listaLibros) {
			    cmbLibros.addItem(libro);
			}
			cmbLibros.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					// Obtener el socio seleccionado
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                    libroSeleccionado = (String) cmbLibros.getSelectedItem();
	                    System.out.println(libroSeleccionado);
	                }
					
				}
			});
			cmbLibros.setBounds(448, 214, 74, 38);
			add(cmbLibros);
			
			
			//-------------- BIBLIOTECAS ---------	
			
			JComboBox cmbBibliotecas = new JComboBox();
			ArrayList<String> listaBibliotecas = bd.cargaComboBibliotecas();
			
			for (String biblio : listaBibliotecas) {
			    cmbBibliotecas.addItem(biblio);
			}
			cmbBibliotecas.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					// Obtener el socio seleccionado
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                    bibliotecaSeleccionada = (String) cmbBibliotecas.getSelectedItem();
	                    System.out.println(bibliotecaSeleccionada);
	                }				
				}
			});
			cmbBibliotecas.setBounds(547, 214, 74, 38);
			add(cmbBibliotecas);
			
			JButton btnInsertar = new JButton("INSERTAR NUEVO PRESTAMO");
			btnInsertar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bd.insertarPrestamo(socioSeleccionado, libroSeleccionado, bibliotecaSeleccionada, textoComentario);				
				}
			});
			btnInsertar.setBounds(448, 362, 216, 51);
			add(btnInsertar);
			
			// --	BOTÓN VOLVER --
			JButton btnVolver = new JButton("Cancelar");
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ventana.nuevoPanel(new Libros_Ventana(ventana, esAdmin));
				}
			});
			//--------------------------------------------
			
			btnVolver.setToolTipText("");
			btnVolver.setForeground(Color.BLACK);
			btnVolver.setFont(new Font("Verdana", Font.PLAIN, 11));
			btnVolver.setBorder(new LineBorder(new Color(88, 49, 117), 2, true));
			btnVolver.setBackground(Color.WHITE);
			btnVolver.setBounds(275, 495, 79, 37);
			add(btnVolver);

		}

}
