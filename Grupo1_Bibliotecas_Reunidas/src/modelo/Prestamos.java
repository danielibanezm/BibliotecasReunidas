package modelo;

public class Prestamos {
	private String id_prestamo;
	private String id_socio;
	private String id_libro ;
	private String id_biblioteca;
	private String fecha_prestamo;
	private String fecha_entrega_prevista;
	private String fecha_entrega;
	private String comentarios;

	
	public Prestamos() {
		id_prestamo = "a";
		id_socio = "b";
		id_libro = "c";
		id_biblioteca = "d";
		fecha_prestamo = "e";
		fecha_entrega_prevista = "f";
		fecha_entrega = "g";
		comentarios = "h";
	}

	public Prestamos(String id_prestamo, String id_socio, String id_libro, String id_biblioteca, String fecha_prestamo, String fecha_entrega_prevista, String fecha_entrega, String comentarios) {
		this.id_prestamo = id_prestamo;
		this.id_socio = id_socio;
		this.id_libro = id_libro;
		this.id_biblioteca = id_biblioteca;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_entrega_prevista = fecha_entrega_prevista;
		this.fecha_entrega = fecha_entrega;
		this.comentarios = comentarios;
	}

	public String getId_prestamo() {
		return id_prestamo;
	}

	public void setId_prestamo(String id_prestamo) {
		this.id_prestamo = id_prestamo;
	}

	public String getId_socio() {
		return id_socio;
	}

	public void setId_socio(String id_socio) {
		this.id_socio = id_socio;
	}

	public String getId_libro() {
		return id_libro;
	}

	public void setId_libro(String id_libro) {
		this.id_libro = id_libro;
	}

	public String getId_biblioteca() {
		return id_biblioteca;
	}

	public void setId_biblioteca(String id_biblioteca) {
		this.id_biblioteca = id_biblioteca;
	}

	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public String getFecha_entrega_prevista() {
		return fecha_entrega_prevista;
	}

	public void setFecha_entrega_prevista(String fecha_entrega_prevista) {
		this.fecha_entrega_prevista = fecha_entrega_prevista;
	}

	public String getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
}
