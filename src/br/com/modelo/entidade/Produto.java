package br.com.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;




@Entity
@Table(name="produto")
public class Produto {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	
	@NotNull(message="O campo nome n�o pode ser vazio e/ou nulo, � obrigat�rio preencher.")
	@Length(min=3, message="O campo nome precisa ter no m�nimo 3 letras.")
	private String nome;
	
	@NotNull(message="O campo nome n�o pode ser vazio e/ou nulo, � obrigat�rio preencher.")
	@Length(max=40, message="O descri��o nome precisa ter no m�ximo 40 letras.")
	private String descricao;
	
	@NotNull(message="O campo pre�o n�o pode ser vazio e/ou nulo, � obrigat�rio preencher.")
	@Min(1)
	private Double preco;

	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
