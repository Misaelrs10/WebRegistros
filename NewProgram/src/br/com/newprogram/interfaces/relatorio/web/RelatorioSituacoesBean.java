package br.com.newprogram.interfaces.relatorio.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.newprogram.application.service.ClienteService;
import br.com.newprogram.domain.cliente.Cliente;
import br.com.newprogram.domain.cliente.Cliente.Situacao;

@Named
@SessionScoped
public class RelatorioSituacoesBean implements Serializable{

	@EJB
	private ClienteService clienteService;
	
	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;
	
	private Situacao situacao;
	
	private List<Cliente> clientes;
	
	public void check() {
		String clear = requestParamsMap.get("clear");
		
		if (clear != null && Boolean.valueOf(clear)) {
			situacao = null;
			clientes = null;
		}
	}
	
	public String gerarRelatorio() {
		clientes = clienteService.listSituacoesClientes(situacao);
		return null;
	}
	
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
}
