package br.com.jojun.database.xmltosqlite.model.ar;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Capitulo {

	private int numero;
	private List<Versiculo> versiculos;

	public int getNumero() {
		return numero;
	}
	
	@XmlAttribute(name="n")
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public List<Versiculo> getVersiculos() {
		return versiculos;
	}
	
	@XmlElement(name="v")
	public void setVersiculos(List<Versiculo> versiculos) {
		this.versiculos = versiculos;
	}
	
	
}
