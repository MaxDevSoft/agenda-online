package agendaclinica.com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agendaclinica.com.models.Usuario;
import agendaclinica.com.repositories.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    UsuarioRepository userRepository;

    public Optional<Usuario> findUser (String nome){

        return userRepository.findById(nome);

    }

}
