package br.com.jojun.database.xmltosqlite.model.ar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Versiculo {
	private int numero;
	private String versiculo;

	public int getNumero() {
		return numero;
	}
	@XmlAttribute(name="n")
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getVersiculo() {
		return versiculo;
	}
	
	@XmlValue
	public void setVersiculo(String versiculo) {
		this.versiculo = versiculo;
	}
	
	

}
