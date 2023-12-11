package modelo;

public class Socios {
	private String idSocio;
	private String idBiblioteca;
	private String nombre;
	private String apellido;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	private boolean listaNegra;
	private String fechaNacimiento;
	
	public Socios() {	
		idSocio = "";
		idBiblioteca = "";
		nombre = "";
		apellido = "";
		dni = "";
		direccion = "";
		telefono = "";
		email = "";
		listaNegra = false;
		fechaNacimiento = "";
	}

	public Socios(String idSocio, String idBiblioteca, String nombre, String apellido, String dni, String direccion,
			String telefono, String email, boolean listaNegra, String fechaNacimiento) {
		this.idSocio = idSocio;
		this.idBiblioteca = idBiblioteca;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.listaNegra = listaNegra;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(String idSocio) {
		this.idSocio = idSocio;
	}

	public String getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(String idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isListaNegra() {
		return listaNegra;
	}

	public void setListaNegra(boolean listaNegra) {
		this.listaNegra = listaNegra;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
}
