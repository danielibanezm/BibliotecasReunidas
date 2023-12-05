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

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Insertar_Libro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEdicion;
	private JTextField txtPublicacion;
	private JTextField txtPais;
	private JTextField txtEditorial;
	private JTextField txtGenero;
	private JTextField txtIdioma;
	private JTextField txtPaginas;
	private JTextField txtUbicacion;
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JTextField txtAutores;

	private Libros nuevoLib = new Libros();
	private BaseDeDatos bd = new BaseDeDatos();
	private Errores err = new Errores();
	private Libros_Ventana libven;
	private ArrayList<Libros> arrlLibros = new ArrayList<>();
	private ComprobarCampos comprobar = new ComprobarCampos();

	public Insertar_Libro(String idBib, DefaultTableModel modeloTabla, Ventana ventana, boolean esAdmin) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Insertar_Libro.class.getResource("/img/libro.png")));
		setModal(true);
		setResizable(false);
		setBounds(180, 130, 1147, 698);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 1132, 578);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblAadir = new JLabel("Añadir");
		lblAadir.setVerticalAlignment(SwingConstants.TOP);
		lblAadir.setHorizontalAlignment(SwingConstants.LEFT);
		lblAadir.setForeground(new Color(130, 72, 172));
		lblAadir.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblAadir.setBounds(474, 24, 239, 81);
		contentPanel.add(lblAadir);

		JLabel lblLibro = new JLabel("Libro");
		lblLibro.setVerticalAlignment(SwingConstants.TOP);
		lblLibro.setHorizontalAlignment(SwingConstants.LEFT);
		lblLibro.setForeground(new Color(9, 3, 62));
		lblLibro.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblLibro.setBounds(498, 66, 239, 81);
		contentPanel.add(lblLibro);

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblIsbn.setBounds(195, 190, 50, 19);
		contentPanel.add(lblIsbn);

		txtIsbn = new JTextField();
		txtIsbn.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(293, 190, 212, 20);
		contentPanel.add(txtIsbn);

		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTitulo.setBounds(195, 232, 50, 19);
		contentPanel.add(lblTitulo);

		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(293, 232, 212, 20);
		contentPanel.add(txtTitulo);

		JLabel lblAutores = new JLabel("Autores:");
		lblAutores.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAutores.setBounds(195, 273, 73, 19);
		contentPanel.add(lblAutores);

		txtAutores = new JTextField();
		txtAutores.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtAutores.setColumns(10);
		txtAutores.setBounds(293, 273, 212, 20);
		contentPanel.add(txtAutores);

		JLabel lblEdicion = new JLabel("Edición:");
		lblEdicion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEdicion.setBounds(195, 383, 50, 19);
		contentPanel.add(lblEdicion);

		txtEdicion = new JTextField();
		txtEdicion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEdicion.setColumns(10);
		txtEdicion.setBounds(293, 383, 212, 20);
		contentPanel.add(txtEdicion);

		JLabel lblPublicacion = new JLabel("Publicación:");
		lblPublicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPublicacion.setBounds(195, 425, 88, 19);
		contentPanel.add(lblPublicacion);

		txtPublicacion = new JTextField();
		txtPublicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPublicacion.setColumns(10);
		txtPublicacion.setBounds(293, 425, 212, 20);
		contentPanel.add(txtPublicacion);

		JLabel lblPais = new JLabel("País:");
		lblPais.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPais.setBounds(195, 466, 73, 19);
		contentPanel.add(lblPais);

		txtPais = new JTextField();
		txtPais.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPais.setColumns(10);
		txtPais.setBounds(293, 466, 212, 20);
		contentPanel.add(txtPais);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEditorial.setBounds(602, 190, 73, 19);
		contentPanel.add(lblEditorial);

		txtEditorial = new JTextField();
		txtEditorial.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEditorial.setColumns(10);
		txtEditorial.setBounds(700, 190, 212, 20);
		contentPanel.add(txtEditorial);

		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblGenero.setBounds(602, 232, 73, 19);
		contentPanel.add(lblGenero);

		txtGenero = new JTextField();
		txtGenero.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtGenero.setColumns(10);
		txtGenero.setBounds(700, 232, 212, 20);
		contentPanel.add(txtGenero);

		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblIdioma.setBounds(602, 273, 50, 19);
		contentPanel.add(lblIdioma);

		txtIdioma = new JTextField();
		txtIdioma.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIdioma.setColumns(10);
		txtIdioma.setBounds(700, 273, 212, 20);
		contentPanel.add(txtIdioma);

		JLabel lblPaginas = new JLabel("Nº Páginas:");
		lblPaginas.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPaginas.setBounds(602, 383, 88, 19);
		contentPanel.add(lblPaginas);

		txtPaginas = new JTextField();
		txtPaginas.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPaginas.setColumns(10);
		txtPaginas.setBounds(700, 383, 212, 20);
		contentPanel.add(txtPaginas);

		JLabel lblUbicacion = new JLabel("Ubicación:");
		lblUbicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblUbicacion.setBounds(602, 425, 73, 19);
		contentPanel.add(lblUbicacion);

		txtUbicacion = new JTextField();
		txtUbicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(700, 425, 212, 20);
		contentPanel.add(txtUbicacion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(194, 164, 217));
			buttonPane.setBounds(0, 575, 1132, 83);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				// -- BOTÓN GUARDAR LIBRO --
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if (camposLlenos() && comprobar.validarCampos(txtIsbn, txtTitulo, txtAutores, txtEditorial, txtIdioma, txtEdicion, txtPublicacion,
								txtPais, txtPaginas, txtUbicacion)) {

							nuevoLib = rellenaObjeto();
							arrlLibros = bd.obtenerTodosLosLibros(idBib);

							if (comprobarLibro(nuevoLib, arrlLibros)) {
								insertar(nuevoLib, idBib);
								libven = new Libros_Ventana(ventana, esAdmin, idBib);
								libven.actualizarse(nuevoLib, modeloTabla);

							} else {
								JOptionPane.showMessageDialog(null,
										"Ya existen tres ejemplares con el mismo ISBN y título."
												+ " No es posible insertar este libro.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null, "Rellene todos los campos.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}

					
				});
				// ------------------------------------------------

				btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnGuardar.setBackground(Color.WHITE);
				btnGuardar.setActionCommand("OK");
				btnGuardar.setBounds(337, 21, 98, 39);
				buttonPane.add(btnGuardar);
			}
			{
				// -- BOTÓN CANCELAR --
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						txtIsbn.setText("");
						txtTitulo.setText("");
						txtAutores.setText("");
						txtGenero.setText("");
						txtEditorial.setText("");
						txtIdioma.setText("");
						txtEdicion.setText("");
						txtPublicacion.setText("");
						txtPais.setText("");
						txtPaginas.setText("");
						txtUbicacion.setText("");
						txtUbicacion.setText("");

						dispose();
					}
				});
				// ----------------------------------------

				btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setActionCommand("Cancel");
				btnCancelar.setBounds(749, 21, 111, 39);
				buttonPane.add(btnCancelar);
			}
		}
	}

	public boolean camposLlenos() {
		return !(txtIsbn.getText().isEmpty() || txtTitulo.getText().isEmpty() || txtAutores.getText().isEmpty()
				|| txtEditorial.getText().isEmpty() || txtIdioma.getText().isEmpty() || txtEdicion.getText().isEmpty()
				|| txtPublicacion.getText().isEmpty() || txtPais.getText().isEmpty() || txtPaginas.getText().isEmpty()
				|| txtGenero.getText().isEmpty() || txtUbicacion.getText().isEmpty());
	}

	public Libros rellenaObjeto() {
		Libros libro = new Libros();

		libro.setIsbn(txtIsbn.getText());
		libro.setTitulo(txtTitulo.getText());
		libro.setAutores(txtAutores.getText());
		libro.setEditorial(txtEditorial.getText());
		libro.setGenero(txtGenero.getText());
		libro.setIdioma(txtIdioma.getText());
		libro.setEdicion(txtEdicion.getText());

		libro.setPublicacion(txtPublicacion.getText());
		libro.setPais(txtPais.getText());
		libro.setPaginas(txtPaginas.getText());
		libro.setUbicacion(txtUbicacion.getText());

		return libro;
	}

	public void insertar(Libros nuevoLib, String idBib) {
		int opcion = 0;
		opcion = err.preguntarInsertar();

		if (opcion == 0) {
			bd.insertarLibro(nuevoLib, idBib);

		} else {

		}
	}

	public boolean comprobarLibro(Libros nuevoLib, ArrayList<Libros> arrlLibros) {
	    int contador = 0;

	    for (Libros recorrer : arrlLibros) {
	    	
	        if (recorrer.getIsbn().trim().equalsIgnoreCase(nuevoLib.getIsbn().trim())
	                && recorrer.getTitulo().trim().equalsIgnoreCase(nuevoLib.getTitulo().trim())) {
	            contador++;

	            if (contador >= 3) {
	                return false; //Ya hay tres libros con el mismo ISBN y título
	            }
	        }
	    }

	    return true; //Se puede insertar el libro
	}
	

}
