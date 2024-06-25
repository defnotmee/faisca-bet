package com.fazol.Requester;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fazol.Requester.RException.InvalidDataException;
public class RequesterInfo extends Requester{
    /**
     * Argumentos
     *  arguments[0]: id do usuário
     *  
     * Erros possíveis:
     *  400: request inválida
     *     - Quando não tem id ou o id não é um long  
     * 
     * Retorno caso status code 200:
     *  String com json das informações do usuario. Exemplo:
     * {
        "nome" : "Leonardo Valente",
        "hashSenha" : "qLe207uaSXGHcxfChy6+TvO17MoIZlGw4AaIKWsj0zI=",
        "id" : 5197068689946304799,
        "cpf" : "43759625851",
        "email" : "leonardovn2525@gmail.com",
        "bonusAvailable" : false,
        "balance" : 70
        }
        Use jackson pra obter informações disso
     **/

    @Override
    public String makeRequest(List<String> arguments)  throws InvalidDataException {
        HttpClient client = HttpClient.newHttpClient();

        Map<String,String> mp = new HashMap<>();

        mp.put("id", arguments.get(0));

        String serviceUrl = getAddress("faisca-api/info");
            
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(serviceUrl))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .timeout(Duration.ofSeconds(1))
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
                throw new InvalidDataException(retorno);
        }

        if(statusCode != 200){
            return null;
        }

        return retorno;
    }
}
