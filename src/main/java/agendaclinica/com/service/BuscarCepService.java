package agendaclinica.com.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import agendaclinica.com.models.Endereco;

public class BuscarCepService {

    Endereco buscarCep = new Endereco();

    public Endereco mostrarCep (String cep){
    
            try {

            HttpClient httpClient = HttpClient.newHttpClient(); // create http

            HttpRequest request = HttpRequest.newBuilder(URI.create("viacep.com.br/ws/"+cep+"/json/")).build();

           
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                ObjectMapper mapper = new ObjectMapper();

                buscarCep = mapper.readValue(response.body(), Endereco.class);

            } catch (IOException | InterruptedException e) {

                e.printStackTrace();
            }

            return buscarCep;
        
    }
    
}
