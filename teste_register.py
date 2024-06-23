# Lê um nome, senha e cpf para criar conta

import requests
import json
URL = "http://localhost:8080/faisca-api/register"
name = input("insira nome para \"" + URL + "\":")
password = input("insira a senha para \"" + URL + "\":")
cpf = input("insira o CPF para \"" + URL + "\":")


PARAMS = {"nome": name, "senha" : password, "cpf": cpf}

x = requests.post(url = URL, params = PARAMS)
print(x.status_code)
print(x.content.decode())
# print(json.loads(x.content.decode()))
