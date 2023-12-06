package modelo;

public class Usuarios {
	private String idUsuario;
	private String idBib;
	private String email;
	private String contrasenia;
	
	
	
	public Usuarios() {
		idUsuario = "";
		idBib = "";
		email = "";
		contrasenia = "";
	}

	public Usuarios(String idUsuario, String idBib, String email, String contrasenia) {
		super();
		this.idUsuario = idUsuario;
		this.idBib = idBib;
		this.email = email;
		this.contrasenia = contrasenia;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdBib() {
		return idBib;
	}

	public void setIdBib(String idBib) {
		this.idBib = idBib;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}
