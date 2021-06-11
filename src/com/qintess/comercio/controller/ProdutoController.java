package com.qintess.comercio.controller;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qintess.comercio.dao.Dao;
import com.qintess.comercio.modelo.Produto;



@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private Dao dao;
	
	@RequestMapping("")
	public String carrega(Model model) {
		
		model.addAttribute("produtos", dao.buscaTodos(Produto.class));
		model.addAttribute("produto", new Produto());
		
		return "produto";
	}
	
	@RequestMapping("/salva")
	public String salva(@ModelAttribute Produto produto,
						@RequestParam(required=false, value="cancela") String cancela,
						@RequestParam(required=false, value="imagem") MultipartFile imagem,
						RedirectAttributes redirectAtt) {
		
		byte[] bImagem;
		
		try {
			if(cancela != null) {
				return "redirect:/produto";
			}
			
			if(imagem != null && imagem.getSize() > 0) {
				bImagem = imagem.getBytes();
				produto.setImagemProd(bImagem);
			}

			if(produto.getId() == 0) {
				dao.salva(produto);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Produto cadastrado com sucesso!");
			} else {
				dao.altera(produto);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Produto alterado com sucesso!");
			}
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "redirect:/produto";
	}
	
	
	@RequestMapping("/deleta/{id}")
	public String deleta(@PathVariable(name="id") int id,
						 RedirectAttributes redirectAtt) {
		Produto produto = dao.buscaPorId(Produto.class, id);
		dao.deleta(produto);
		redirectAtt.addFlashAttribute("mensagemSucesso", "Produto deletado com sucesso!");
		return "redirect:/produto";
	}
	
	@RequestMapping("/altera/{id}")
	public String carregaAlterar(@PathVariable(name="id") int id,
			                     Model model,
			                     RedirectAttributes redirectAtt) {
		try {
			
			//Estou encodando o array de bytes em base64.. somente assim o HTML irá renderizar essa imagem
			Produto produto = dao.buscaPorId(Produto.class, id);
			byte[] encodeBase64 = Base64.getEncoder().encode(produto.getImagemProd());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			produto.setImagemEncoded(base64Encoded);
			
			model.addAttribute("produtos", dao.buscaTodos(Produto.class));
			model.addAttribute("produto", produto);
			//model.addAttribute("imagemProduto", base64Encoded);
			
		} catch (UnsupportedEncodingException e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "produto";
	}

}
