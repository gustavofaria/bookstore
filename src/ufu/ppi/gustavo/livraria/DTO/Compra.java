package ufu.ppi.gustavo.livraria.DTO;

public class Compra {
	protected int id;
	protected int pessoa_id;
	protected int livro_id;
	protected int quantidade;

	public Compra(int pessoa_id, int livro_id, int quantidade) {
		super();
		this.pessoa_id = pessoa_id;
		this.livro_id = livro_id;
		this.quantidade = quantidade;
	}

	public Compra(int id, int pessoa_id, int livro_id, int quantidade) {
		this(pessoa_id, livro_id, quantidade);
		this.id = id;
	}
	public Compra(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getPessoa_id() {
		return pessoa_id;
	}

	public void setPessoa_id(int pessoa_id) {
		this.pessoa_id = pessoa_id;
	}

	public int getLivro_id() {
		return livro_id;
	}

	public void setLivro_id(int livro_id) {
		this.livro_id = livro_id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Compra) {
			if (((Compra) other).getId() == this.id) {
				return true;
			}
		}
		return false;
	}

}
