package com.fazol.Requester;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Object makeRequest(List<String> arguments){
        try{
            HttpClient client = HttpClient.newHttpClient();

            Map<String,String> mp = new HashMap<String,String>();

            mp.put("email", arguments.get(0));
            mp.put("senha", arguments.get(1));

            String serviceUrl = "http://localhost:8080/faisca-api/login";
                
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(serviceUrl))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(mp)))
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            String retorno = response.body();
            int statusCode = response.statusCode(); // tem que ser 200 se deu certo
            
            List<Object> res = new ArrayList<Object>(2);
            res.add(response.body());
            res.add(response.statusCode());

            return res;

            /**
             * Resto do código
             */

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        //return null; // FIXME
    }
}
