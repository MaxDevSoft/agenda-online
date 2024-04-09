package agendaclinica.com.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Procedimento {

	@Id
	private String tipoProcedimento;
	
	@OneToMany
	private List<Consulta> consultas;
	
	@OneToMany
	private List<Prontuario> prontuario;

	public String getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	
	
	
}
