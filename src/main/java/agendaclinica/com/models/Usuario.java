package agendaclinica.com.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import org.hibernate.annotations.Columns;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements Serializable{// UserDetails
	
private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long codigo;
	
	@Id
	private String nome;
	
	@NotEmpty
	private String senha;
	
	//@JoinTable(name="usuario_roles", joinColumns = @JoinColumn (name="usuario_nome"), inverseJoinColumns = @JoinColumn(name="roles_nome_role"))
	@ManyToMany
	@JoinTable( 
	        name = "usuarios_roles", 
	        joinColumns = @JoinColumn(
	          name = "usuario_id", referencedColumnName = "nome"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_id", referencedColumnName = "nomeRole")) 
    private List<Role> roles;
		
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
/*
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	
	@Override
	public String getPassword() {
		
		return this.senha;
	}

	@Override
	public String getUsername() {
		
		return this.nome;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
*/
	

}

