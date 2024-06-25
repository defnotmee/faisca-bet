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
import com.fazol.Requester.RException.TooPoorException;
public class RequesterRoleta extends Requester{
    /**
     * Argumentos
     *  arguments[0]: id do usuário
     *  arguments[1]: qtd apostada
     *  arguments[2]: cor  (PRETO / VERMELHO / BRANCO)
     *  
     * Erros possíveis:
     *  400: request inválida
     *     - Quando não algum dos argumentos
     *     - Quando os argumentos tem tipo inválido
     *     - Quando a conta não tem dinheiro suficiente
     *     
     * 
     * Retorno caso status code 200:
     *  Cor que saiu na hora.
     * 
     **/

    @Override
    public String makeRequest(List<String> arguments)  throws InvalidDataException, TooPoorException {
        HttpClient client = HttpClient.newHttpClient();

        Map<String,String> mp = new HashMap<>();

        mp.put("id", arguments.get(0));
        mp.put("valor", arguments.get(1));
        mp.put("cor", arguments.get(2));
        String serviceUrl = getAddress("faisca-api/jogos/roleta");
            
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
