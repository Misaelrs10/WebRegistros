package br.com.newprogram.interfaces.shared.cliente;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import br.com.newprogram.application.service.ClienteService;
import br.com.newprogram.application.util.ValidationException;
import br.com.newprogram.domain.cliente.Cliente;

@Named
@SessionScoped
public class PesquisaClienteBean implements Serializable{
	
	@EJB
	private ClienteService clienteService;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;
	
	private String id;
	private String nome;
	private Integer cpf;
	private Integer celular;
	
	private List<Cliente> clientes;
	
	public void check() {
		String clear = requestParamsMap.get("clear");
		
		if (clear != null && Boolean.valueOf(clear)) {
			id = null;
			nome = null;
			cpf = null;
			celular = null;
			clientes = null;
		}
	}
	
	public String pesquisar() {
		try {
		clientes = clienteService.listClientes(id,nome,cpf,celular);
		}catch(ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String excluir(String id) {
		clienteService.delete(id);
		return pesquisar();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCpf() {
		return cpf;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}
	public Integer getCelular() {
		return celular;
	}
	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
}
