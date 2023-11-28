package modelo;

public class Libros {
	private String isbn;
	private String titulo;
	private String autores;
	private String editorial;
	private String genero;
	private String idioma;
	private String edicion;
	private String publicacion;
	private String pais;
	private String paginas;
	private String ubicacion;
	
	public Libros() {
		isbn = "";
		titulo = "";
		autores = "";
		editorial = "";
		genero = "";
		idioma = "";
		edicion = "";
		publicacion = "";
		pais = "";
		paginas = "";
		ubicacion = "";
	}

	public Libros(String isbn, String titulo, String autores, String editorial, String genero, String idioma,
			String edicion, String publicacion, String pais, String paginas, String ubicacion) {

		this.isbn = isbn;
		this.titulo = titulo;
		this.autores = autores;
		this.editorial = editorial;
		this.genero = genero;
		this.idioma = idioma;
		this.edicion = edicion;
		this.publicacion = publicacion;
		this.pais = pais;
		this.paginas = paginas;
		this.ubicacion = ubicacion;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(String publicacion) {
		this.publicacion = publicacion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}
