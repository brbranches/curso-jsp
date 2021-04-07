package beans;

public class BeanTelefone {

	private Long id;
	private String numero;
	private String tipo;
	private Long usuario;

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getTipo() {
		return tipo;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

}
