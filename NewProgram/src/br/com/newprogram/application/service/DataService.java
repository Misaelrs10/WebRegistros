package br.com.newprogram.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.newprogram.domain.cliente.Estado;
import br.com.newprogram.domain.cliente.EstadoRepository;
import br.com.newprogram.domain.cliente.Cliente.Sexo;
import br.com.newprogram.domain.cliente.Cliente.Situacao;

@Stateless
public class DataService {
		
	@EJB	
	private EstadoRepository estadoRepository;
		
		public List<Estado> listEstados(){
			return estadoRepository.listEstados();
		}
		
		public Sexo[] getSexos() {
			return Sexo.values();
		}
		
		public Situacao[] getSituacoes() {
			return Situacao.values();
		}
}
