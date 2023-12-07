package modelo;

public class Recibos {
	private String id_bib;
	private String id_socio;
	private boolean pagado;
	
	public Recibos() {
		id_bib = "";
		id_socio = "";
		pagado = true;
	}

	public Recibos(String id_bib, String id_socio, boolean pagado) {
		super();
		this.id_bib = id_bib;
		this.id_socio = id_socio;
		this.pagado = pagado;
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

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
}
