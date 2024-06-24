package com.fazol.Requester;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RequesterRegister extends Requester{
    

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
    public Object makeRequest(List<String> arguments){
        try{
            HttpClient client = HttpClient.newHttpClient();

            Map<String,String> mp = new HashMap<String,String>();

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

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            String retorno = response.body();
            int statusCode = response.statusCode(); // tem que ser 200 se deu certo
            
            /**
             * Resto do código
             */

        }catch(Exception e){
            e.printStackTrace();
            return e;
        }

        return null; // FIXME
    }
}
