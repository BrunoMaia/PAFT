import requests
import json
from os import getenv
from os.path import join, dirname
from dotenv import dotenv_values

VariaveisAmbiente = dotenv_values(join(dirname(__file__), 'variaveis.env'))

def EncurtaUrl(url:str):
    """ Esta função faz a chamada da API da tinyurl, encurtando um link passado como parâmetro
    :url -> string com o caminho a url a ser encurtada
    :retorno -> retorna uma string com a url encurtada
    """
    Cabecalho = {'accept': 'application/json'}
    ApiToken = VariaveisAmbiente['API_TOKEN']
    Parametros = {'api_token': ApiToken}
    Request = requests.post('https://api.tinyurl.com/create', params=Parametros, headers=Cabecalho, json={'url': url})
    Resposta = json.loads(Request.text)
    return Resposta["data"]["tiny_url"]



    
