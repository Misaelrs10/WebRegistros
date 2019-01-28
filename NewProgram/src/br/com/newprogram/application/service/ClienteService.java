package br.com.newprogram.application.service;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.newprogram.application.util.StringUtil;
import br.com.newprogram.application.util.Validation;
import br.com.newprogram.application.util.ValidationException;
import br.com.newprogram.domain.acesso.Acesso;
import br.com.newprogram.domain.cliente.Cliente;
import br.com.newprogram.domain.cliente.Cliente.Situacao;
import br.com.newprogram.domain.cliente.ClienteRepository;

@Stateless
public class ClienteService {
	
	@EJB
	private ClienteRepository clienteRepository;
	
	public void createOrUpdate(Cliente cliente) {
		if(StringUtil.isEmpty(cliente.getId())) {
			create(cliente);
		} else {
			update(cliente);
		}
	}

	private void create(Cliente cliente) {
		Validation.assertNotEmpty(cliente);
		
		String maxId = clienteRepository.getMaxIdAno();
		cliente.gerarId(maxId);
		clienteRepository.store(cliente);
	}
	
	public void delete(String id) {
		clienteRepository.delete(id);
	}
	
	private void update(Cliente cliente) {
		Validation.assertNotEmpty(cliente);
		Validation.assertNotEmpty(cliente.getId());
		clienteRepository.update(cliente);
		
	}
	
	public Cliente findById(String id) {
		return clienteRepository.findById(id);
	}
	
	public List<Cliente> listClientes(String id, String nome, Integer cpf, Integer celular){
		if (StringUtil.isEmpty(id) && StringUtil.isEmpty(nome) && cpf == null && celular == null) {
			throw new ValidationException("Fornecer ao menos um dado para pesquisa!");
	}
			return clienteRepository.listClientes(id, nome, cpf, celular);
	}
	
	public List<Cliente> listSituacoesClientes(Situacao situacao){
		Validation.assertNotEmpty(situacao);
		return clienteRepository.listSituacoesClientes(situacao);
	}
	
	public List<Acesso> listAcessosClientes(String id, LocalDate dataInicial, LocalDate dataFinal){
		if(StringUtil.isEmpty(id) && dataInicial == null && dataFinal == null) {
			throw new ValidationException("Fornecer ao menos um dados para pesquisa!");
		}
		
		return clienteRepository.listAcessosClientes(id, dataInicial, dataFinal);
	}
	
}
