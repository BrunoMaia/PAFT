import random
import string
import secrets

def GeraSenha(TamanhoSenha:int, QtdEspeciais:int):
    Caracteres = list(string.ascii_uppercase + string.ascii_letters)
    CaracteresEspeciais = list("!@#$%&*{}/-=" + string.digits)
    QtdChar = TamanhoSenha - QtdEspeciais
    if QtdEspeciais > TamanhoSenha:
        QtdEspeciais = TamanhoSenha
    Senha = ''.join(random.SystemRandom().choice(CaracteresEspeciais) for _ in range(QtdEspeciais))
    for _ in range(QtdChar):
        Senha += random.SystemRandom().choice(Caracteres)
    Senha = ''.join(random.sample(Senha,len(Senha)))
    return Senha
    
