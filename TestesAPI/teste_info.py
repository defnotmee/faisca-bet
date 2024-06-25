# Lê id, senha secreta e quantidade para fazer deposito

import requests
URL = "http://192.168.33.251:8080/faisca-api/info"

PARAMS = dict()

def readParam(param):
    cur = input("insira " + param + " para  \"" + URL + "\":")
    PARAMS[param] = cur

readParam("id")

print("request: " + str(PARAMS))
x = requests.post(url = URL, params = PARAMS)
print(x.status_code)
print(x.content.decode())
# print(json.loads(x.content.decode()))
