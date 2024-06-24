# Lê um nome, senha e cpf para criar conta

import requests
import json
URL = "http://localhost:8080/faisca-api/login"
email = input("insira email para \"" + URL + "\":")
password = input("insira a senha para \"" + URL + "\":")

PARAMS = {"email": email, "senha" : password}

print("request: " + str(PARAMS))
x = requests.post(url = URL, params = PARAMS)
print(x.status_code)
print(x.content.decode())
# print(json.loads(x.content.decode()))
