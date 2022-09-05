import random
import string
import secrets

def GeraSenha(TamanhoSenha:int, QtdEspeciais:int):
    """Esta função gera uma senha aleatória (com base no gerador ramdomico do sistema operacional) com a quantidade de caractéres informada.
    :TamanhoSenha -> um inteiro com o tamanho da senha a ser gerada
    :QtdEspeciais -> inteiro que define a quantidade de números e caracteres especiais que estarão presentes na senha gerada
    :retorno -> a função retorna uma string com tamanho igual a TamanhoSenha
    """
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
    
