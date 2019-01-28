package br.com.newprogram.interfaces.shared.cliente;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.newprogram.application.service.ClienteService;
import br.com.newprogram.application.util.StringUtil;
import br.com.newprogram.domain.cliente.Cliente;

@Named
@RequestScoped
public class ClienteBean implements Serializable {
	
	@EJB
	private ClienteService clienteService;
	
	@Inject
	private FacesContext facesContext;
	
	private Cliente cliente = new Cliente();
	
	private String id;
	
	private String titulo = "Novo Cliente";
	
	public void carregar() {
		if (!StringUtil.isEmpty(id)) {
			cliente = clienteService.findById(id);
			titulo = "Alterar Cliente";
		}
	}
	
	public String gravar() {
		clienteService.createOrUpdate(cliente);
		facesContext.addMessage(null, new FacesMessage("Dados gravados com sucesso!"));
		return null;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
}
