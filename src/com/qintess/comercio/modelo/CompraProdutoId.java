package com.qintess.comercio.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CompraProdutoId implements Serializable {

	private int compraId;
	
	private int produtoId;
	
	private CompraProdutoId() {}
	
	public CompraProdutoId(int compraId, int produtoId) {
		this.compraId = compraId;
		this.produtoId = produtoId;
	}
	
	// é necessário fazer a implementação dos métodos equals e hashcode
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		
		if(o == null || getClass() != o.getClass())
			return false;
		
		CompraProdutoId that = (CompraProdutoId) o;
		return Objects.equals(compraId, that.compraId) &&
			   Objects.equals(produtoId, that.produtoId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(compraId, produtoId);
	}

	public int getCompraId() {
		return compraId;
	}

	public void setCompraId(int compraId) {
		this.compraId = compraId;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}
	
	
}

