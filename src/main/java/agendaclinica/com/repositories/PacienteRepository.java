package agendaclinica.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendaclinica.com.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String>{
	
	Paciente findByNome(String nome);
}
