package agendaclinica.com.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agendaclinica.com.models.Paciente;
import agendaclinica.com.repositories.PacienteRepository;

@Service
public class PacienteService implements PacienteServiceImplement{

    @Autowired
    PacienteRepository pr;

    public Optional<Paciente> findPaciente (String nome){

        return pr.findById(nome);

    }

    public Paciente savePaciente (Paciente paciente){

        return pr.save(paciente);
    }


    
}
