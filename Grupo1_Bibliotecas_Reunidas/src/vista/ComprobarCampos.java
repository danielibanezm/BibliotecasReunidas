package vista;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ComprobarCampos {

	boolean validarCamposLibros(JTextField txtIsbn, JTextField txtTitulo, JTextField txtAutores,
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

	public boolean validarCamposSocios(JTextField txtNombre, JTextField txtApellido, JTextField txtDni,
			JTextField txtDireccion, JTextField txtEmail, JTextField txtTelf, JTextField txtNacimiento) {
		
		if (txtNombre.getText().length() > 20) {
		    JOptionPane.showMessageDialog(null, "El campo de nombre no puede superar los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtApellido.getText().length() > 50) {
		    JOptionPane.showMessageDialog(null, "El campo de apellido no puede superar los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtDni.getText().length() != 9) {
		    JOptionPane.showMessageDialog(null, "El campo de DNI tiene que contener 8 números y una letra.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		if (txtDireccion.getText().length() > 50) {
		    JOptionPane.showMessageDialog(null, "La dirección no puede superar los 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		if (txtEmail.getText().length() > 50) {
		    JOptionPane.showMessageDialog(null, "El email no puede superar los 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		if (txtTelf.getText().length() != 9) {
		    JOptionPane.showMessageDialog(null, "El teléfono tiene que contener 9 números.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		String fecha = txtNacimiento.getText();
		if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
		    JOptionPane.showMessageDialog(null, "El formato de fecha de nacimiento debe ser yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		return true; //Todos los campos son válidos
	}
	
	
}
