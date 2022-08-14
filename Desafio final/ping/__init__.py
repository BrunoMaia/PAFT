import platform    
from subprocess import Popen, PIPE, call

def PingHost(host:str, quantidade:str):
    """
    Retorna uma tupla com a resposta e o resultado verboso do ping
    """
    # Option for the number of packets as a function of
    # param = '-n' if platform.system().lower()=='windows' else '-c'
    parametro = '-n' if platform.system().lower()=='windows' else '-c'
    comando = ['ping', parametro, quantidade, host]
    resultado = ""
    process = Popen(comando,stdout=PIPE,stderr=PIPE)
    stdout,stderr = process.communicate()
    return ((call(comando) == 0),stdout.decode('ISO-8859-1'))

