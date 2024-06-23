# Lê um nome, senha e cpf para criar conta

import requests
import json
URL = "http://localhost:8080/faisca-api/register"
name = input("insira nome para \"" + URL + "\":")
email = input("insira email para \"" + URL + "\":")
password = input("insira a senha para \"" + URL + "\":")
cpf = input("insira o CPF para \"" + URL + "\":")
print(cpf)

PARAMS = {"nome": name, "email": email, "senha" : password}

if(cpf != ""):
    PARAMS["cpf"] = cpf

print(PARAMS)
x = requests.post(url = URL, params = PARAMS)
print(x.status_code)
print(x.content.decode())
# print(json.loads(x.content.decode()))
