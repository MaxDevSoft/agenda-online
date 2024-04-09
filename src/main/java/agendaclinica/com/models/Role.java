package agendaclinica.com.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

//import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role  {//implements GrantedAuthority

	@Id
	private String nomeRole;

	@ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;
	
	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	//@Override
	//public String getAuthority() {
	//	return this.nomeRole;
	//}
	
	
}
