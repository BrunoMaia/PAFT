import platform    
from subprocess import Popen, PIPE, call

def PingHost(Host:str, Quantidade:str):
    """
    Esta função faz uma chamada ao sistema, para rodar o comando de ping, retornando a resposta obtida.
    :Host -> uma string com o caminho do host, podendo ser um ip ou caminho dns
    :Quantidade -> quantidade de execuções do ping, é uma string
    :retorno -> tupla(bool -> resposta do host aos pings, string -> resposta completa do sistema, descrevendo o ping)
    """
    Parametro = '-n' if platform.system().lower()=='windows' else '-c'
    Comando = ['ping', Parametro, Quantidade, Host]
    Resultado = ""
    Processo = Popen(Comando,stdout=PIPE,stderr=PIPE)
    stdout,stderr = Processo.communicate()
    return ((call(Comando) == 0),stdout.decode('ISO-8859-1'))

