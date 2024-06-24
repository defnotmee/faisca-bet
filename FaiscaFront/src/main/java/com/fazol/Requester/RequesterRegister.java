package com.fazol.Requester;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fazol.Requester.RException.InvalidCpfException;
import com.fazol.Requester.RException.InvalidEmailException;
import com.fazol.Requester.RException.PermissionException;
import com.fazol.Requester.RException.UserNotFoundException;
public class RequesterRegister extends Requester{
    

    /**
     * Argumentos
     *  arguments[0]: nome do usuário
     *  arguments[1]: email do usuario
     *  arguments[2]: senha do usuário
     *  arguments[3]: cpf do usuário (sem pontos, por exemplo 43759625851) [opicional]
     *  
     * Erros possíveis:
     *  400: request inválida
     *     - Retorna "Email inválido" ou "CPF inválido" caso seja o caso
     *     - Retrona "Email já existe" ou "CPF já existe" caso já tenha usuário com ele  
     * 
     * Retorno caso status code 200:
     *  String com o Id do usuário criado (em Long)
     */
    @Override
    public Long makeRequest(List<String> arguments)  throws InvalidEmailException, PermissionException, UserNotFoundException, InvalidCpfException {
        HttpClient client = HttpClient.newHttpClient();

        Map<String,String> mp = new HashMap<>();

        mp.put("nome", arguments.get(0));
        mp.put("email", arguments.get(1));
        mp.put("senha", arguments.get(2));
        if(arguments.size() > 2){
            mp.put("cpf", arguments.get(3));
        }

        String serviceUrl = "http://localhost:8080/faisca-api/register";
            
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
                if(retorno.equals("Email inválido")){
                    throw new InvalidEmailException(retorno);
                } else throw new InvalidCpfException(retorno);
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
