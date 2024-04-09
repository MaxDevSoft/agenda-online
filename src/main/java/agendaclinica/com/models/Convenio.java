package agendaclinica.com.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Convenio {

	@Id
	private String nomeConvenio;
	
	@OneToMany //um convenio para muitos pacientes, ou seja, One=convenio toMany=pacientes.
	private List<Paciente> pacientes;

	public String getNomeConvenio() {
		return nomeConvenio;
	}

	public void setNomeConvenio(String nomeConvenio) {
		this.nomeConvenio = nomeConvenio;
	}
	
	
	
}
