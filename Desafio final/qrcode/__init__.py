import qrcode
import datetime

def CriaQRCODE(link:str):
    agora = datetime.datetime.now()
    QrcodeImagem = qrcode.make(link)
def SalvaQRCODE(imagem):
    QrcodeImagem.save(agora.strftime("qr_%d%m%y_%H%M%S"))
