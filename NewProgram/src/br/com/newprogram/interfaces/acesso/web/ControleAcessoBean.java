package br.com.newprogram.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.newprogram.application.service.AcessoService;
import br.com.newprogram.application.util.ValidationException;
import br.com.newprogram.domain.acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable{

	@EJB
	private AcessoService acessoService;
	
	@Inject
	private FacesContext facesContext;
	
	private String id;
	private Integer cpf;
	
	public String registrarAcesso() {
		TipoAcesso tipoAcesso;
		
		try {
			tipoAcesso = acessoService.registrarAcesso(id, cpf);
		}catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		
		String msg;
		if(tipoAcesso == TipoAcesso.Entrada) {
			msg = "Entrada registrada!";
		}else if(tipoAcesso == TipoAcesso.Saída) {
			msg = "Saída registrada!";
		}else {
			msg = "Dados de acesso inconsistentes!";
		}
		facesContext.addMessage(null, new FacesMessage(msg));
		return null;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCpf() {
		return cpf;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}
	
}
