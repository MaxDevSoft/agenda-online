package agendaclinica.com.repositories;

import org.springframework.data.repository.CrudRepository;

import agendaclinica.com.models.Paciente;
import agendaclinica.com.models.Prontuario;

public interface ProntuariosRepository extends CrudRepository<Prontuario, String>{
	Iterable<Prontuario> findByPaciente(Paciente paciente); 
	Prontuario findByData(String data);
}
