package vista;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ComprobarCampos {

	boolean validarCampos(JTextField txtIsbn, JTextField txtTitulo, JTextField txtAutores,
			JTextField txtEditorial, JTextField txtIdioma, JTextField txtEdicion,
			JTextField txtPublicacion, JTextField txtPais, JTextField txtPaginas,
			JTextField txtUbicacion) {
		
		if (txtIsbn.getText().length() != 17) {
		    JOptionPane.showMessageDialog(null, "El ISBN debe tener exactamente 17 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtTitulo.getText().length() > 50) {
		    JOptionPane.showMessageDialog(null, "El título no puede superar los 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtAutores.getText().length() > 50) {
		    JOptionPane.showMessageDialog(null, "El campo de autores no puede superar los 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtEditorial.getText().length() > 20) {
		    JOptionPane.showMessageDialog(null, "La editorial no puede superar los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		if (txtIdioma.getText().length() > 20) {
		    JOptionPane.showMessageDialog(null, "El idioma no puede superar los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		if (txtEdicion.getText().length() > 20) {
		    JOptionPane.showMessageDialog(null, "La edición no puede superar los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		String fechaPublicacion = txtPublicacion.getText();
		if (!fechaPublicacion.matches("\\d{4}-\\d{2}-\\d{2}")) {
		    JOptionPane.showMessageDialog(null, "El formato de fecha de publicación debe ser yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtPais.getText().length() > 20) {
		    JOptionPane.showMessageDialog(null, "El país no puede superar los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtPaginas.getText().length() > 4) {
		    JOptionPane.showMessageDialog(null, "La cantidad de páginas no puede superar los 4 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		if (txtUbicacion.getText().length() > 20) {
		    JOptionPane.showMessageDialog(null, "La ubicación no puede superar los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		return true; //Todos los campos son válidos
	}
}
