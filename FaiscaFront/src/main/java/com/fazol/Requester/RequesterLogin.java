package com.fazol.Requester;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fazol.Requester.RException.InvalidEmailException;
import com.fazol.Requester.RException.PermissionException;
import com.fazol.Requester.RException.UserNotFoundException;

public class RequesterLogin extends Requester{
    
    RequesterLogin RequesterLogin(){
        return new RequesterLogin();
    }

    /**
     * Argumentos
     *  arguments[0]: email do usuario
     *  arguments[1]: senha do usuário
     * 
     * Erros possíveis:
     *  400: request inválida (provavelmente por email inválido)
     *  401: não autorizado (senha errada)
     *  404: não achado (não foi encontrado usuário com aquele email)
     * 
     * Retorno caso status code 200:
     *  String com o Id do usuário (em Long)
     */
    @Override
    public Long makeRequest(List<String> arguments) throws InvalidEmailException, PermissionException, UserNotFoundException{
        HttpClient client = HttpClient.newHttpClient();

        Map<String,String> mp = new HashMap<>();

        mp.put("email", arguments.get(0));
        mp.put("senha", arguments.get(1));

        String serviceUrl = "http://localhost:8080/faisca-api/login";
            
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(serviceUrl))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(mp)))
        .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null; // código pra erro genérico
        }
        
        String retorno = response.body();
        int statusCode = response.statusCode(); // tem que ser 200 se deu certo
        
    
        switch(statusCode){
            case 400:
                throw new InvalidEmailException(retorno);
            case 401:
                throw new PermissionException(retorno);
            case 404:
                throw new UserNotFoundException(retorno);
        }

        if(statusCode != 200){
            return null;
        }

        return Long.valueOf(retorno);
    }
}
