package com.qintess.comercio.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDate dtCompra;
	
	@Column(nullable = false, scale = 2)
	private BigDecimal valor = new BigDecimal(0);
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompraProduto> produtos = new ArrayList<CompraProduto>();
	
	//Método que irá adicionar um novo produto a compra
	public void adicionaProduto(Produto produto, int quantidade) {
		CompraProduto compraProduto = new CompraProduto(this, produto, quantidade);
		//relacionamento bilateral
		produtos.add(compraProduto);
		produto.getCompras().add(compraProduto);;
		
		//atualizando valor da compra de acordo com o total de produtos e suas quantidades
		double valorCompra = this.valor.doubleValue();
		double valorProdutoUnit = produto.getValorUnitario().doubleValue();
		
		valorCompra += (valorProdutoUnit * quantidade);
		
		this.valor = new BigDecimal(valorCompra);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDtCompra() {
		return dtCompra;
	}

	public void setDtCompra(LocalDate dtCompra) {
		this.dtCompra = dtCompra;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<CompraProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<CompraProduto> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
