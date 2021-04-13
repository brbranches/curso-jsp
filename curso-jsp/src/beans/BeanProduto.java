package beans;

public class BeanProduto {
	
	private Long id;
	private String nome;
	private double quantidade;
	private double preco;
	private Long categoria_id;
	
	
	public Long getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String getPrecoEmTexto() {
		return Double.toString(preco).replace('.', ',');
	}
	
	
	

}
