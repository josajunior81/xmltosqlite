package br.com.jojun.database.xmltosqlite.model.ar;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bible")
public class Biblia {
	
	private String fname;
	private String title;
	private String font;
	private String copyright;
	private String sizefactor;

	private List<Livro> livros;

	public String getFname() {
		return fname;
	}

	@XmlElement
	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getTitle() {
		return title;
	}
	
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getFont() {
		return font;
	}

	@XmlElement
	public void setFont(String font) {
		this.font = font;
	}

	public String getCopyright() {
		return copyright;
	}

	@XmlElement
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getSizefactor() {
		return sizefactor;
	}

	@XmlElement
	public void setSizefactor(String sizefactor) {
		this.sizefactor = sizefactor;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	@XmlElement(name="b")
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	
	
}
