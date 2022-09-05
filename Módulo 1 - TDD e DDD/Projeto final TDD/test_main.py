from main import *
import pandas as pd
def test_abrir_csv_com_caminho_nao_existe():
    assert carrega_csv('ksodkosd/dskosdko.csv') == "Houve um erro na função carrega_csv"

def test_abrir_csv_com_caminho_nao_existe():
    assert carrega_csv('ksodkosd/dskosdko.csv') == "Houve um erro na função carrega_csv"

def test_retornar_dataframe():
    dataframe_mock = pd.DataFrame({'FECHAMENTO': [32.41, 31.73],
                                   'MÍNIMO': [30.48, 31.33],
                                   'MAXIMO': [32.58, 33.15]})
    assert dataframe_mock.equals(carrega_csv('teste.csv')) == True

def test_calcula_canal_acao_ira_calcular_media_movel():
    media_movel = 31.946666666666665
    dataframe_mock = pd.DataFrame({'FECHAMENTO': [32.41, 31.73],
                                   'MÍNIMO': [30.48, 31.33],
                                   'MÁXIMO': [32.58, 33.15]})
    assert calcula_canal_acao(dataframe_mock)[0] == media_movel

def test_calcula_canal_acao_ira_calcular_canal_superior():
    canal_superior = 32.4675
    dataframe_mock = pd.DataFrame({'FECHAMENTO': [32.41, 31.73],
                                   'MÍNIMO': [30.48, 31.33],
                                   'MÁXIMO': [32.58, 33.15]})
    assert calcula_canal_acao(dataframe_mock)[1] == canal_superior

def test_calcula_canal_acao_ira_calcular_canal_inferior():
    canal_inferior = 31.4875
    dataframe_mock = pd.DataFrame({'FECHAMENTO': [32.41, 31.73],
                                   'MÍNIMO': [30.48, 31.33],
                                   'MÁXIMO': [32.58, 33.15]})
    assert calcula_canal_acao(dataframe_mock)[2] == canal_inferior

def test_calcula_nota_recebeu_tudo_zero():
    assert calcula_nota((0,0,0),0) == 'Nota D'

def test_calcula_nota_calcula_com_strings():
    assert calcula_nota(('0','0','0'),'0') == 'Nota D'

def test_calcula_nota_calcula_corretamente_nota_D():
    assert calcula_nota((0.5,1,0),0) == 'Nota D'

def test_calcula_nota_calcula_corretamente_nota_C():
    assert calcula_nota((0.5,1,0),0.3) == 'Nota C'

def test_calcula_nota_calcula_corretamente_nota_B():
    assert calcula_nota((0.5,1,0),0.5) == 'Nota B'

def test_calcula_nota_calcula_corretamente_nota_A():
    assert calcula_nota((0.5,1,0),0.7) == 'Nota A'

def test_executa_interface_usuario():
    assert interface_usuario() == True

def test_chama_interface_com_parametros():
    assert interface_usuario(lucro_venda = 2,arquivo = 'dados.csv') == 'Nota A'