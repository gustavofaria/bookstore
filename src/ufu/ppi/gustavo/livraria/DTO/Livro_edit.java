package ufu.ppi.gustavo.livraria.DTO;

import java.util.ArrayList;

public class Livro_edit {
	protected int id;
	protected String titulo;
	protected String autor;
	protected float preco;
	protected ArrayList<Genero> genero;

	public Livro_edit() {
		genero = new ArrayList<Genero>();
	}

	public Livro_edit(int id) {
		this();
		this.id = id;
	}
	public Livro_edit(int id, String title, String author, float price,ArrayList<Genero> genero) {
		this(id,title, author, price);
		for(Genero item: genero) {
			this.genero.add(item);
		}
	}
	public Livro_edit(int id, String title, String author, float price) {
		this(title, author, price);
		this.id = id;
	}

	private Livro_edit(String title, String author, float price) {
		this();
		this.titulo = title;
		this.autor = author;
		this.preco = price;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public float getPreco() {
		return preco;
	}
	
	public ArrayList<Genero> getGenero(){
		return genero;
	}
	
	public void appendGenero(Genero gen){
		genero.add(gen);
	}

}
