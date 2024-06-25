package com.fazol;

import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazol.Requester.RequesterInfo;

public class Account {
    
    String nome;
    Long id;
    String cpf;
    String email;
    boolean bonusAvailable;
    BigDecimal balance;

    public Account() {
        nome = "Admin";
        id = Long.valueOf("-2894575177183267944");
        email = "admin@admin.com";
        bonusAvailable = false;
        balance = new BigDecimal(1100);
    }
    
    Account(String json) {
        try {
            final JsonNode node = new ObjectMapper().readTree(json);


            if (node.has("nome")) {
                this.nome = node.get("nome").asText();
            }
            if (node.has("id")) {
                this.id = node.get("id").asLong();
            }
            if (node.has("cpf")) {
                this.cpf = node.get("cpf").asText();
            }
            if (node.has("email")) {
                this.email = node.get("email").asText();
            }
            if (node.has("bonusAvailable")) {
                this.bonusAvailable = node.get("bonusAvailable").asBoolean();
            }
            if (node.has("balance")) {
                this.balance = new BigDecimal(node.get("balance").asDouble());
            }
        } catch (JsonProcessingException e) {
            System.out.println("Fudeu! Não era pra entrar aqui");
        }
        
    }

    public void refresh(){
        RequesterInfo req = new RequesterInfo();
        try {
            String json = (String)req.makeRequest(Arrays.asList(getId().toString()));
            Account cur = new Account(json);
            cur.setBalance(cur.getBalance());
            cur.setBonusAvailable(cur.getBonusAvailable());
        } catch (Exception e) {
            // Nunca vai entrar aqui
        }
        

    }
    public boolean getBonusAvailable() {
        return this.bonusAvailable;
    }

    public void setBonusAvailable(boolean bonusAvailable) {
        this.bonusAvailable = bonusAvailable;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getNome() {
        return this.nome;
    }
    public Long getId() {
        return this.id;
    }
    public String getCpf() {
        return this.cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isBonusAvailable() {
        return this.bonusAvailable;
    }
    public BigDecimal getBalance() {
        return this.balance;
    }
}
