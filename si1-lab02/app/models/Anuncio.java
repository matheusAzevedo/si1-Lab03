package models;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Anuncio {
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String descricao;
	private String endereco;
	private List<String> instrumentos;
	private List<String> estilosQueGosta;
	private List<String> estilosQueNaoGosta;
	private String tipo;
	private String email;
	private String facebook;
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<String> getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(List<String> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public List<String> getEstilosQueGosta() {
		return estilosQueGosta;
	}

	public void setEstilosQueGosta(List<String> estilosQueGosta) {
		this.estilosQueGosta = estilosQueGosta;
	}

	public List<String> getEstilosQueNaoGosta() {
		return estilosQueNaoGosta;
	}

	public void setEstilosQueNaoGosta(List<String> estilosQueNaoGosta) {
		this.estilosQueNaoGosta = estilosQueNaoGosta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getData() {
		return DateFormat.getDateInstance(DateFormat.LONG).format(data);
		
	}

	public void setData(Date data) {
		this.data = data;
	}

	

	

	
}
