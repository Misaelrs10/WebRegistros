package br.com.newprogram.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.newprogram.application.util.StringUtil;
import br.com.newprogram.application.util.ValidationException;
import br.com.newprogram.domain.acesso.Acesso;
import br.com.newprogram.domain.acesso.AcessoRepository;
import br.com.newprogram.domain.acesso.TipoAcesso;
import br.com.newprogram.domain.cliente.Cliente;
import br.com.newprogram.domain.cliente.ClienteRepository;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private ClienteRepository clienteRepository;
	
	public TipoAcesso registrarAcesso(String id, Integer cpf) {
		
		if(StringUtil.isEmpty(id) && cpf == null) {
			throw new ValidationException("É preciso fornecer a matrícula ou cpf!");
		}
		
		Cliente cliente;
		if (StringUtil.isEmpty(id)) {
			cliente = clienteRepository.findByCpf(cpf);
		} else {
			cliente = clienteRepository.findById(id);
		}
		
		if (cliente == null) {
			throw new ValidationException("O cliente não foi encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(cliente);
		TipoAcesso tipoAcesso;
		
		if (ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso = new Acesso();
			ultimoAcesso.setCliente(cliente);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);
		
		} else {
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}
		
		return tipoAcesso;
	}
}
