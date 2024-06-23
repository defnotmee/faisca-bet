# Lê um nome da entrada e faz a request para o servidor teste

import requests
import json
URL = "http://localhost:8080/faisca-api/teste"
name = input("insira nome para \"" + URL + "\":")
bet = input("insira a aposta para \"" + URL + "\":")

while(True):
    escolha = input("insira cor para \"" + URL + "\":")

    if(escolha == -1):
        break

    PARAMS = {"nome": name, "bet" : bet, "choice": escolha}

    x = requests.get(url = URL, params = PARAMS)
    print(x.content.decode())
    # print(json.loads(x.content.decode()))
    print(x.status_code)