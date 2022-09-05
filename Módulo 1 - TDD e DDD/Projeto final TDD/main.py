"""
Descrição:
Com base no livro "Aprenda a operar no Mercado de ações" do Dr. Alexander Elder,
CAP 8, seção Planilha do Operador. Nele a nota da operação é calculada como função do valor obtido em
relação à largura do canal. Sendo até 10% nota C, de 10 a 20% nota B e acima de 20% nota A. Farei uma
modificação para ficar mais interessante. Sendo de 0 a 10% nota D, de 10 a 20% nota C, de 20 a 30% nota
B e acima de 30% nota A. Além de adotar o valor do canal como 2 vezes a amplitude, para ser mais desafiador.
O arquivo csv será pego do site: https://www.infomoney.com.br/cotacoes/b3/acao/petrobras-petr4/historico/
Com um período de 01/08/2022 até 22/08/2022
O canal será feito de forma simplificada, sendo o a média simples do primeiro e do último membro do dataframe.
    valor central = (valor fechamento + valor máximo + valor mínimo)/3
    valor superior = (valor máximo + fechamento)/2
    valor inferior = (valor mínimo + fechamento)/2
Para simplificar, o calculo da nota, tomará por base a amplitude do canal, ou seja, sua máxima - sua mínima
Requisitos:
Deve-se criar uma funcão carrega_csv que:
    Fará a abertura e carregamento do arquivo csv
    Transformará ela em um Pandas Dataframe
calcula_canal_acao que:
    Receberá um dataframe
    Calcula a média móvel para o dataframe
    Calcula o canal superior
    Calcula o canal inferior
    Retorna o um dicionário com o valor da média, canal superior e inferior
calcula_nota que:
    Recebe uma tupla de valores de canal
    Recebe o valor da venda
    Calcula a porcentagem de retorno
    Retorna a nota na forma de string
Caso haja erros em qualquer função, retornar a seguinte mensagem "Houve um erro na função {nome da função}"
interface_usuario:
    chama o imput para receber uma lista de valores
    chama as funcões correspondentes
    retorna a nota
"""
import pandas as pd
def carrega_csv(caminho:str):
    try:
        dataframe = pd.read_csv(caminho, decimal =',')
    except:
        return "Houve um erro na função carrega_csv"
    return dataframe

def calcula_canal_acao(dataframe)->tuple:
    """valor central = (valor fechamento + valor máximo + valor mínimo) / 3
        valor superior = (valor máximo + fechamento) / 2
        valor inferior = (valor mínimo + fechamento) / 2"""
    colunas_remover = ["DATA","ABERTURA","VARIAÇÃO","VOLUME"]
    dataframe.drop(colunas_remover, axis=0, errors='ignore')
    dataframe['VALOR CENTRAL'] = dataframe.mean(axis=1)
    valor_maximo1 = float(dataframe['MÁXIMO'].iloc[0])
    valor_maximo2 = float(dataframe['MÁXIMO'].iloc[-1])
    valor_fechamento1 = float(dataframe['FECHAMENTO'].iloc[0])
    valor_fechamento2 = float(dataframe['FECHAMENTO'].iloc[-1])
    valor_minimo1 = float(dataframe['MÍNIMO'].iloc[0])
    valor_minimo2 = float(dataframe['MÍNIMO'].iloc[-1])

    valor_central1 = float(dataframe['VALOR CENTRAL'].iloc[0])
    valor_central2 = float(dataframe['VALOR CENTRAL'].iloc[-1])

    valor_superior1 = (valor_maximo1 + valor_fechamento1) / 2
    valor_superior2 = (valor_maximo2 + valor_fechamento2) / 2

    valor_inferior1 = (valor_minimo1 + valor_fechamento1) / 2
    valor_inferior2 = (valor_minimo2 + valor_fechamento2) / 2

    valor_canal_central = (valor_central1 + valor_central2) / 2
    valor_canal_superior = (valor_superior1 + valor_superior2) / 2
    valor_canal_inferior = (valor_inferior1 + valor_inferior2) / 2

    return (valor_canal_central,valor_canal_superior,valor_canal_inferior)

def calcula_nota(valores_canal:tuple, lucro_venda: any) -> str:
    amplitude = 2*(float(valores_canal[1]) - float(valores_canal[2]))
    if amplitude == 0 or amplitude < 0:
        return 'Nota D'
    lucro_venda = float(lucro_venda)
    resultado = lucro_venda / amplitude
    if resultado < 0.1:
        return 'Nota D'
    elif 0.1 <= resultado < 0.2:
        return  'Nota C'
    elif 0.2 <= resultado < 0.3:
        return 'Nota B'
    elif 0.3 <= resultado:
        return 'Nota A'

def interface_usuario(*args, **kwargs) -> bool:
    if 'lucro_venda' and 'arquivo' in kwargs:
        try:
            lucro_venda = float(kwargs.get('lucro_venda'))
            arquivo = str(kwargs.get('arquivo'))
        except ValueError:
            return 'Houve um erro na função interface_usuario'
        dataframe = carrega_csv(arquivo)
        tupla_canal_acao = calcula_canal_acao(dataframe)
        return calcula_nota(tupla_canal_acao, lucro_venda)
    print('*'*10+' Script de análise de trades '+'*'*10)
    try:
        arquivo = str(input('Digite o arquivo .csv a ser lido:\n'))
        lucro_venda = float(input('Digite o lucro da venda, por ação, separando os centavos com ponto:\n'))
        dataframe = carrega_csv(arquivo)
        tupla_canal_acao = calcula_canal_acao(dataframe)
        nota_trade = calcula_nota(tupla_canal_acao,lucro_venda) 
        print('*'*40+f'\nEssa foi sua nota do trade:\n{nota_trade}')
    except:
        print('Você digitou algo errado, verifique os campos')
    return True

if __name__ == "__main__":
    interface_usuario()