package ufu.ppi.gustavo.livraria.DTO;

public class Livro {
	protected int id;
	protected String titulo;
	protected String autor;
	protected float preco;

	public Livro() {
	}

	public Livro(int id) {
		this.id = id;
	}

	public Livro(int id, String title, String author, float price) {
		this(title, author, price);
		this.id = id;
	}
	
	public Livro(String title, String author, float price) {
		this.titulo = title;
		this.autor = author;
		this.preco = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitle(String title) {
		this.titulo = title;
	}

	public String getAutor() {
		return autor;
	}

	public void setAuthor(String author) {
		this.autor = author;
	}

	public float getPreco() {
		return preco;
	}

	public void setPrice(float price) {
		this.preco = price;
	}

}
