package com.qintess.comercio.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qintess.comercio.dao.Dao;
import com.qintess.comercio.modelo.Cliente;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private Dao dao;
	
	@RequestMapping("")
	public String carrega(Model model) {
		
		model.addAttribute("cliente", new Cliente());
		List<Cliente> clientes = dao.buscaTodos(Cliente.class);
		model.addAttribute("clientes", clientes);
		
		return "cliente";
	}
	
	@RequestMapping("/salva")
	public String salva(@ModelAttribute Cliente cliente,
					    RedirectAttributes redirectAtt) {
		
		try {
			if(cliente.getId() == 0) {
				dao.salva(cliente);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Cliente cadastrado com sucesso!");
			}
			else {
				dao.altera(cliente);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Cliente alterado com sucesso!");
			}
				
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "redirect:/cliente";
	}
	
	@RequestMapping("/altera/{id}")
	public String carregaAltera(@PathVariable("id") int id,
								Model model,
								RedirectAttributes redirectAtt) {
		try {
			model.addAttribute("cliente", dao.buscaPorId(Cliente.class, id));
			model.addAttribute("clientes", dao.buscaTodos(Cliente.class));
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "cliente";
	}
	
	@RequestMapping("/deleta/{id}")
	public String deleta(@PathVariable("id") int id,
						 RedirectAttributes redirectAtt) {
		Cliente cliente = dao.buscaPorId(Cliente.class, id);
		dao.deleta(cliente);
		redirectAtt.addFlashAttribute("mensagemSucesso", "Cliente deletado com sucesso!");
		return "redirect:/cliente";
	}

}
