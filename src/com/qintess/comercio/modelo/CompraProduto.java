package com.qintess.comercio.modelo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Compra_Produto")
public class CompraProduto {

	@EmbeddedId
	private CompraProdutoId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("compraId")
	private Compra compra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("produtoId")
	private Produto produto;
	
	@Column(nullable = false)
	private int quantidade;
	
	private CompraProduto() {}
	
	public CompraProduto(Compra compra, Produto produto, int quantidade) {
		this.compra = compra;
		this.produto = produto;
		this.quantidade = quantidade;
		this.id = new CompraProdutoId(compra.getId(), produto.getId());
	}

	public CompraProdutoId getId() {
		return id;
	}

	public void setId(CompraProdutoId id) {
		this.id = id;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}

