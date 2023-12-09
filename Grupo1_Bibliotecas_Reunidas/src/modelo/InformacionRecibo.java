package modelo;

public class InformacionRecibo {
	private String id_recibo;
	private String id_bib;
	private String id_socio;
    private String nombre_socio;
    private String apellido_socio;
    private String dni_socio;
    private String calle_biblioteca;
    private String provincia_biblioteca;
    private String codigo_postal_biblioteca;
    private String telefono_biblioteca;
    private boolean pagado;
    private boolean lista_negra;
	
	public InformacionRecibo() {
		id_recibo = "";
		id_bib = "";
		id_socio = "";
		nombre_socio = "";
		apellido_socio = "";
		dni_socio = "";
		calle_biblioteca = "";
		provincia_biblioteca = "";
		codigo_postal_biblioteca = "";
		telefono_biblioteca = "";
		pagado = true;
		lista_negra = true;
	}

	public InformacionRecibo(String id_recibo, String id_bib, String id_socio, String nombre_socio,
			String apellido_socio, String dni_socio, String calle_biblioteca, String provincia_biblioteca,
			String codigo_postal_biblioteca, String telefono_biblioteca, boolean pagado, boolean lista_negra) {
		super();
		this.id_recibo = id_recibo;
		this.id_bib = id_bib;
		this.id_socio = id_socio;
		this.nombre_socio = nombre_socio;
		this.apellido_socio = apellido_socio;
		this.dni_socio = dni_socio;
		this.calle_biblioteca = calle_biblioteca;
		this.provincia_biblioteca = provincia_biblioteca;
		this.codigo_postal_biblioteca = codigo_postal_biblioteca;
		this.telefono_biblioteca = telefono_biblioteca;
		this.pagado = pagado;
		this.lista_negra = lista_negra;
	}

	public String getId_recibo() {
		return id_recibo;
	}

	public void setId_recibo(String id_recibo) {
		this.id_recibo = id_recibo;
	}

	public String getId_bib() {
		return id_bib;
	}

	public void setId_bib(String id_bib) {
		this.id_bib = id_bib;
	}

	public String getId_socio() {
		return id_socio;
	}

	public void setId_socio(String id_socio) {
		this.id_socio = id_socio;
	}

	public String getNombre_socio() {
		return nombre_socio;
	}

	public void setNombre_socio(String nombre_socio) {
		this.nombre_socio = nombre_socio;
	}

	public String getApellido_socio() {
		return apellido_socio;
	}

	public void setApellido_socio(String apellido_socio) {
		this.apellido_socio = apellido_socio;
	}

	public String getDni_socio() {
		return dni_socio;
	}

	public void setDni_socio(String dni_socio) {
		this.dni_socio = dni_socio;
	}

	public String getCalle_biblioteca() {
		return calle_biblioteca;
	}

	public void setCalle_biblioteca(String calle_biblioteca) {
		this.calle_biblioteca = calle_biblioteca;
	}

	public String getProvincia_biblioteca() {
		return provincia_biblioteca;
	}

	public void setProvincia_biblioteca(String provincia_biblioteca) {
		this.provincia_biblioteca = provincia_biblioteca;
	}

	public String getCodigo_postal_biblioteca() {
		return codigo_postal_biblioteca;
	}

	public void setCodigo_postal_biblioteca(String codigo_postal_biblioteca) {
		this.codigo_postal_biblioteca = codigo_postal_biblioteca;
	}

	public String getTelefono_biblioteca() {
		return telefono_biblioteca;
	}

	public void setTelefono_biblioteca(String telefono_biblioteca) {
		this.telefono_biblioteca = telefono_biblioteca;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	public boolean isLista_negra() {
		return lista_negra;
	}

	public void setLista_negra(boolean lista_negra) {
		this.lista_negra = lista_negra;
	}
}