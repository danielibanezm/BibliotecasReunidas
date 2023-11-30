package vista;

import javax.swing.JOptionPane;

public class Errores {

	public void baseDatosVacia() {
		String titulo = "Error";
		JOptionPane.showMessageDialog(null, "La base de datos está vacía.", titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	public void baseDatosNoConexion() {
		String titulo = "Error";
		JOptionPane.showMessageDialog(null, "No se ha podido establecer conexión con la base de datos.", titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	public int preguntarEditar() {
		String titulo1 = "Confirmación";
		String titulo2 = "Cancelación";

		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea realmente editar el registro?", titulo1,
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {

		} else if (opcion == 1) {
			JOptionPane.showMessageDialog(null, "La edición del registro se ha cancelado.", titulo2,
					JOptionPane.INFORMATION_MESSAGE);
		}

		return opcion;
	}

	public void confirmarUpdate() {
		String titulo = "Aviso";
		JOptionPane.showMessageDialog(null, "Registro editado correctamente.", titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public int preguntarEliminar() {
		String titulo1 = "Confirmación";
		String titulo2 = "Cancelación";

		int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", titulo1,
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {

		} else if (opcion == 1) {
			JOptionPane.showMessageDialog(null, "No se va a eliminar el registro.", titulo2,
					JOptionPane.INFORMATION_MESSAGE);
		}

		return opcion;
	}
	
	public void confirmarEliminar() {
		String titulo = "Aviso";
		JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.", titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public int preguntarInsertar() {
		String titulo1 = "Confirmación";
		String titulo2 = "Cancelación";
		
		int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de insertar el registro?",
				titulo1, JOptionPane.YES_NO_OPTION);
		if(opcion == 0) {
			
		}else if(opcion == 1) {
			JOptionPane.showMessageDialog(null, "No se va a insertare el registro.", titulo2, JOptionPane.INFORMATION_MESSAGE);
		}
		
		return opcion;
	}
	
	public void confirmarInsert() {
		String titulo = "Aviso";
		JOptionPane.showMessageDialog(null, "Registro insertado correctamente.", titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}
