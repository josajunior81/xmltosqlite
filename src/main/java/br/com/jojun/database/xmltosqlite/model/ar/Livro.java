package br.com.jojun.database.xmltosqlite.model.ar;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Livro {
	private String nome;
	private List<Capitulo> capitulos;
	
	public String getNome() {
		return nome;
	}
	
	@XmlAttribute(name="n")
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Capitulo> getCapitulos() {
		return capitulos;
	}
	
	@XmlElement(name="c")
	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	
	
}
