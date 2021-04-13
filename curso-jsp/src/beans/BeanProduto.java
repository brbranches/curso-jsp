package beans;

public class BeanProduto {
	
	private Long id;
	private String nome;
	private double quantidade;
	private double preco;
	
	
	
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
