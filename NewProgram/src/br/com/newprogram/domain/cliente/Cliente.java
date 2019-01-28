package br.com.newprogram.domain.cliente;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.newprogram.application.util.StringUtil;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable{

	@Id
	@Column(name = "ID", nullable = false, length = 10)
	private String id;
	
	@Column(name = "CPF", nullable = false, length = 11)
	private Integer cpf;
	
	@Column(name = "NOME", nullable = false, length = 64)
	private String nome;
	
	@Column(name = "EMAIL", nullable = false, length = 64)
	private String email;
	
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private LocalDate dataNascimento;
	
	@Enumerated
	@Column(name = "SITUACAO", nullable = false, length = 1)
	private Situacao situacao;
	
	@Enumerated
	@Column(name = "SEXO", nullable = false, length = 1)
	private Sexo sexo;
	
	@Column(name = "CELULAR", nullable = false, length = 11)
	private Integer celular;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	public void gerarId(String maxId) {
		Year year = Year.now();
		
		if(maxId == null) {
			maxId = year + StringUtil.leftZeroes(0, 4);
		}
		
		int sequential = Integer.parseInt(maxId.substring(4));
		sequential++;
		
		this.id = year + StringUtil.leftZeroes(sequential, 4);
	}
	
	public enum Sexo{
		Masculino, Feminino;
	}
	
	public enum Situacao{
		Em_Dia, Pendente;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", situacao=" + situacao + ", sexo=" + sexo + ", celular=" + celular + ", endereco="
				+ endereco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo != other.sexo)
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}
	
}
