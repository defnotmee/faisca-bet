package com.fazol.Requester;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RequesterLogin extends Requester{
    
    @Override
    public Object makeRequest(List<?> arguments){
        try{
            HttpClient client = HttpClient.newHttpClient();

            Map<String,String> mp = new HashMap<String,String>();

            mp.put("nome", "Leo");
            mp.put("email", "a@aa.com");
            mp.put("senha", "pipipipipipo");
            mp.put("cpf", "43759625851");

            String serviceUrl = "http://localhost:8080/faisca-api/register";
                
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(serviceUrl))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(mp)))
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
            
        }catch(Exception e){
            e.printStackTrace();
            return e;
        }
    }
}
