package com.systempro.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco_entrada;
	private Double preco;
	private Integer quantidade;
	private String nota_fiscal;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	// estamos utilizando a anotação HashSet<>(); para garantir que não haja
	// produtos repetidos.
	// estamos fazendo uso da anotação @JsonIgnore, que seja ignorado os itens de
	// pedido em uma busca por produtos

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();

	// o mapeamento esta sendo feito no atributo categorias...

	@JsonIgnore
	@ManyToMany // muitos para muitos
	@JoinTable(name = "PRODUTO_FORNECEDOR", // junção de tabelas e nome utilizado um padrão para os dados que estão se
											// relacionando
			joinColumns = @JoinColumn(name = "produto_id"), // nome da coluna de um lado
			inverseJoinColumns = @JoinColumn(name = "fornecedor_id") // nome da coluna do outro lado
	)
	private List<Fornecedor> fornecedores = new ArrayList<>();

	public Produto() {
	}

	@JsonIgnore
	public Produto(Integer id, String nome, Double preco_entrada, Double preco, Integer quantidade,
			String nota_fiscal) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco_entrada = preco_entrada;
		this.preco = preco;
		this.quantidade = quantidade;
		this.nota_fiscal = nota_fiscal;
	}

	// este metodo faz um get em pedido, vare a lista de pedidos e monta uma lista
	// de pedidos, associados aos itens do pedido.

	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnore
	public Double getPreco_entrada() {
		return preco_entrada;
	}

	public void setPreco_entrada(Double preco_entrada) {
		this.preco_entrada = preco_entrada;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;

	}
	public Integer getRemoveQuantidade() {
		return quantidade -= quantidade;

	}
	public void setAddQuantidade(Integer quantidade) {
		this.quantidade += quantidade;

	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@JsonIgnore
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	@JsonIgnore
	public String getNota_fiscal() {
		return nota_fiscal;
	}

	public void setNota_fiscal(String nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}

}
