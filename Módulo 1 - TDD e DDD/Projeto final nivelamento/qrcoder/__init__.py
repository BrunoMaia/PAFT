import qrcode
import datetime

def CriaQRCODE(Link:str):
    """Essa função cria um QRcode e salva no disco, a partir de uma string, que espera-se ser um link
    :Link -> uma string com um link a ser criado o QRcode
    :retorno -> retorna o caminho do qrcode criada, é uma string
    """
    Agora = datetime.datetime.now()
    Agora = Agora.strftime("qr_%d%m%y_%H%M%S")
    QrcodeImagem = qrcode.make(Link)
    QrcodeImagem.save('./qrcodes/' + Agora)
    return './qrcodes/' + Agora
