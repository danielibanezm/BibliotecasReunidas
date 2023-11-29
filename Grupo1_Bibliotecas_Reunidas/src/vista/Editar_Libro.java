package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.BaseDeDatos;
import modelo.Libros;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Editar_Libro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JTextField txtAutores;
	private JTextField txtEditorial;
	private JTextField txtGenero;
	private JTextField txtIdioma;
	private JTextField txtEdicion;
	private JTextField txtPublicacion;
	private JTextField txtPais;
	private JTextField txtPaginas;
	private JTextField txtUbicacion;
	
	private Libros libro = new Libros();
	private BaseDeDatos bd = new BaseDeDatos();

	public Editar_Libro(Libros libro, DefaultTableModel modeloTabla) {
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
		
		JLabel lblEditor = new JLabel("Editar");
		lblEditor.setVerticalAlignment(SwingConstants.TOP);
		lblEditor.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditor.setForeground(new Color(130, 72, 172));
		lblEditor.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblEditor.setBounds(474, 28, 239, 81);
		contentPanel.add(lblEditor);
		
		JLabel lblLibro = new JLabel("Libro");
		lblLibro.setVerticalAlignment(SwingConstants.TOP);
		lblLibro.setHorizontalAlignment(SwingConstants.LEFT);
		lblLibro.setForeground(new Color(9, 3, 62));
		lblLibro.setFont(new Font("Gabriola", Font.BOLD, 55));
		lblLibro.setBounds(498, 70, 239, 81);
		contentPanel.add(lblLibro);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblIsbn.setBounds(191, 201, 50, 19);
		contentPanel.add(lblIsbn);
		
		txtIsbn = new JTextField();
		txtIsbn.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIsbn.setBounds(289, 201, 212, 20);
		contentPanel.add(txtIsbn);
		txtIsbn.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTitulo.setBounds(191, 243, 50, 19);
		contentPanel.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(289, 243, 212, 20);
		contentPanel.add(txtTitulo);
		
		JLabel lblAutores = new JLabel("Autores:");
		lblAutores.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAutores.setBounds(191, 284, 73, 19);
		contentPanel.add(lblAutores);
		
		txtAutores = new JTextField();
		txtAutores.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtAutores.setColumns(10);
		txtAutores.setBounds(289, 284, 212, 20);
		contentPanel.add(txtAutores);
		
		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEditorial.setBounds(598, 201, 73, 19);
		contentPanel.add(lblEditorial);
		
		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblGenero.setBounds(598, 243, 73, 19);
		contentPanel.add(lblGenero);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblIdioma.setBounds(598, 284, 50, 19);
		contentPanel.add(lblIdioma);
		
		txtEditorial = new JTextField();
		txtEditorial.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEditorial.setColumns(10);
		txtEditorial.setBounds(696, 201, 212, 20);
		contentPanel.add(txtEditorial);
		
		txtGenero = new JTextField();
		txtGenero.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtGenero.setColumns(10);
		txtGenero.setBounds(696, 243, 212, 20);
		contentPanel.add(txtGenero);
		
		txtIdioma = new JTextField();
		txtIdioma.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIdioma.setColumns(10);
		txtIdioma.setBounds(696, 284, 212, 20);
		contentPanel.add(txtIdioma);
		
		JLabel lblEdicion = new JLabel("Edición:");
		lblEdicion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEdicion.setBounds(191, 394, 50, 19);
		contentPanel.add(lblEdicion);
		
		JLabel lblPublicacion = new JLabel("Publicación:");
		lblPublicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPublicacion.setBounds(191, 436, 88, 19);
		contentPanel.add(lblPublicacion);
		
		JLabel lblPais = new JLabel("País:");
		lblPais.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPais.setBounds(191, 477, 73, 19);
		contentPanel.add(lblPais);
		
		txtEdicion = new JTextField();
		txtEdicion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEdicion.setColumns(10);
		txtEdicion.setBounds(289, 394, 212, 20);
		contentPanel.add(txtEdicion);
		
		txtPublicacion = new JTextField();
		txtPublicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPublicacion.setColumns(10);
		txtPublicacion.setBounds(289, 436, 212, 20);
		contentPanel.add(txtPublicacion);
		
		txtPais = new JTextField();
		txtPais.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPais.setColumns(10);
		txtPais.setBounds(289, 477, 212, 20);
		contentPanel.add(txtPais);
		
		JLabel lblPaginas = new JLabel("Nº Páginas:");
		lblPaginas.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPaginas.setBounds(598, 394, 88, 19);
		contentPanel.add(lblPaginas);
		
		JLabel lblUbicacion = new JLabel("Ubicación:");
		lblUbicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblUbicacion.setBounds(598, 436, 73, 19);
		contentPanel.add(lblUbicacion);
		
		txtPaginas = new JTextField();
		txtPaginas.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPaginas.setColumns(10);
		txtPaginas.setBounds(696, 394, 212, 20);
		contentPanel.add(txtPaginas);
		
		txtUbicacion = new JTextField();
		txtUbicacion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(696, 436, 212, 20);
		contentPanel.add(txtUbicacion);
		
		// -- RELLENAMOS LOS TEXTFIELD --
		if (libro != null) {
			txtIsbn.setText(libro.getIsbn());
			txtTitulo.setText(libro.getTitulo());
			txtAutores.setText(libro.getAutores());
			txtEditorial.setText(libro.getEditorial());
			txtGenero.setText(libro.getGenero());
			txtIdioma.setText(libro.getIdioma());
			txtEdicion.setText(libro.getEdicion());
			txtPublicacion.setText(libro.getPublicacion());
			txtPais.setText(libro.getPais());
			txtPaginas.setText(libro.getPaginas());
			txtUbicacion.setText(libro.getUbicacion());
		}else{
			System.out.println("Estoy vacío");
		}
		//-----------------------------------------
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(194, 164, 217));
			buttonPane.setBounds(0, 577, 1131, 82);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				// -- BOTÓN GUARDAR --
				btnGuardar = new JButton("Guardar");
				btnGuardar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						editar(modeloTabla);
					}
				});
				//------------------------------------------
				
				btnGuardar.setBackground(new Color(255, 255, 255));
				btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnGuardar.setBounds(335, 23, 98, 39);
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				// -- BOTÓN CANCELAR --
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(new Color(255, 255, 255));
				btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnCancelar.setBounds(749, 23, 111, 39);
				btnCancelar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	public Libros rellenaObjeto() {
		Libros libro = new Libros();
		
		if(!((txtIsbn == null) && (txtTitulo == null) && (txtAutores == null) && (txtEditorial == null) && (txtIdioma == null)
				 && (txtEdicion == null) && (txtPublicacion == null) && (txtPais == null)
				 && (txtPaginas == null) && (txtUbicacion == null))){
			
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
		}

		return libro;
	}
	
    // Método para editar un libro
    public void editar(DefaultTableModel modeloTabla) {
        Libros libro = rellenaObjeto();
        int opcion = 0;
        String id = "";

        if (libro == null) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            opcion = preguntarEditar();

            if (opcion == 0) {
                id = bd.obtenerIdLibro(libro);
                bd.editarLibro(libro, id);
                confirmarUpdate();
                
                modeloTabla.setRowCount(0);
                
                dispose(); // Cerrar la ventana después de editar
            }
        }
    }
	
    public int preguntarEditar() {
		String titulo1 = "Confirmación";
		String titulo2 = "Cancelación";
		
		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea realmente editar el registro?",
				titulo1, JOptionPane.YES_NO_OPTION);
		if(opcion == 0) {
			
		}else if(opcion == 1) {
			JOptionPane.showMessageDialog(null, "La edición del registro se ha cancelado.", titulo2, JOptionPane.INFORMATION_MESSAGE);
		}
		
		return opcion;
	}
	
	public void confirmarUpdate() {
		String titulo = "Aviso";
		JOptionPane.showMessageDialog(null, "Registro editado correctamente.", titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}
