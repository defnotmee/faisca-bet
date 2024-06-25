package com.fazol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Account {
    
    String nome;
    int id;
    String cpf;
    String email;
    boolean bonusAvailable;
    int balance;

    public Account() {
    }
    
    Account(String json) throws JsonMappingException, JsonProcessingException{
        final JsonNode node = new ObjectMapper().readTree(json);

        Account account = new Account();

        if (node.has("nome")) {
            account.nome = node.get("nome").asText();
        }
        if (node.has("id")) {
            account.id = node.get("id").asInt();
        }
        if (node.has("cpf")) {
            account.cpf = node.get("cpf").asText();
        }
        if (node.has("email")) {
            account.email = node.get("email").asText();
        }
        if (node.has("bonusAvailable")) {
            account.bonusAvailable = node.get("bonusAvailable").asBoolean();
        }
        if (node.has("balance")) {
            account.balance = node.get("balance").asInt();
        }

    }
}
