package beans;

public class BeanLivro {

	private Long id;
	private String titulo;
	private String autor;
	private String anoPublicacao;
	private int qntPaginas;
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public String getAnoPublicacao() {
		return anoPublicacao;
	}
	public int getQntPaginas() {
		return qntPaginas;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public void setQntPaginas(int qntPaginas) {
		this.qntPaginas = qntPaginas;
	}
	
	
	
}
