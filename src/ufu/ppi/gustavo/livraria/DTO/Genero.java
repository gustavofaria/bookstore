package ufu.ppi.gustavo.livraria.DTO;

public class Genero {
	protected int id;
	protected String descricao;
	
	public Genero(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Genero(int id) {
		super();
		this.id = id;
	}
	
	public Genero(String descricao) {
		super();
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;	
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Genero) {
			if(((Genero) other).getId() == this.id) {
				return true;
			}
		}
		return false;
	}


}
