package br.com.newprogram.interfaces.shared.cliente;

import javax.annotation.ManagedBean;
import jdk.jfr.Name;

@Name("mascara")
@ManagedBean
public class Mascara {

    private String celular;
     
    public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
  
}
