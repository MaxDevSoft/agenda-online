package agendaclinica.com.repositories;

import org.springframework.data.repository.CrudRepository;

import agendaclinica.com.models.Consulta;

public interface ConsultaRepository extends CrudRepository<Consulta, Long>{


	Iterable<Consulta> findByData(String data);
	//Iterable<Consulta> findByCodigo(long codigo);
	Consulta findByCodigo(long codigo);
}
