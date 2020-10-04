package ufu.ppi.gustavo.livraria.DTO;

public class Pessoa {
	protected int id;
	protected String nome;
	protected String endereco;
	protected String num_cartao;
	protected String cvv;
	protected int validade_cartao;

	public Pessoa(int id) {
		super();
		this.id = id;
	}

	public Pessoa(int id, String nome, String endereco, String num_cartao, String cvv, int validade_cartao) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.num_cartao = num_cartao;
		this.cvv = cvv;
		this.validade_cartao = validade_cartao;
	}

	public Pessoa(String nome, String endereco, String num_cartao, String cvv, int validade_cartao) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.num_cartao = num_cartao;
		this.cvv = cvv;
		this.validade_cartao = validade_cartao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNum_cartao() {
		return num_cartao;
	}

	public void setNum_cartao(String num_cartao) {
		this.num_cartao = num_cartao;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public int getValidade_cartao() {
		return validade_cartao;
	}

	public void setValidade_cartao(int validade_cartao) {
		this.validade_cartao = validade_cartao;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Pessoa) {
			if (((Pessoa) other).id == this.id) {
				return true;
			}
		}
		return false;
	}

}
