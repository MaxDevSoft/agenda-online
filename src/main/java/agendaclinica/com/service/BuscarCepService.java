package agendaclinica.com.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import agendaclinica.com.models.BuscarCep;

public class BuscarCepService {

    BuscarCep buscarCep = new BuscarCep();

    public BuscarCep mostrarCep (String cep){
    
            try {

            HttpClient httpClient = HttpClient.newHttpClient(); // create http

            HttpRequest request = HttpRequest.newBuilder(URI.create("viacep.com.br/ws/"+cep+"/json/")).build();

           
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                ObjectMapper mapper = new ObjectMapper();

                buscarCep = mapper.readValue(response.body(), BuscarCep.class);

            } catch (IOException | InterruptedException e) {

                e.printStackTrace();
            }

            return buscarCep;
        
    }
    
}
