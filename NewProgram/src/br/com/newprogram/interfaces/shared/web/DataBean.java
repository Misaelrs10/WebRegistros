package br.com.newprogram.interfaces.shared.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import br.com.newprogram.application.service.DataService;
import br.com.newprogram.domain.cliente.Cliente.Sexo;
import br.com.newprogram.domain.cliente.Cliente.Situacao;
import br.com.newprogram.domain.cliente.Estado;


@Named
@ApplicationScoped
public class DataBean implements Serializable{

	@EJB
	private DataService dataService;
	
	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}
	
	public List<Estado> getEstados(){
		return dataService.listEstados();
	}
}
