package agendaclinica.com.repositories;

import org.springframework.data.repository.CrudRepository;

import agendaclinica.com.models.Convenio;


public interface ConvenioRepository extends CrudRepository<Convenio, String>{ //CRUD: create remove update delete

}
