# Lê um nome da entrada e faz a request para o servidor teste

import requests
import json
URL = "http://localhost:8080/faisca-api/teste"
req = input("insira nome para \"" + URL + "\":")

PARAMS = {"nome": req}

x = requests.get(url = URL, params = PARAMS)
print(x.content.decode())
print(json.loads(x.content.decode()))
print(x.status_code)
