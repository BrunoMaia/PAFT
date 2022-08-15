import qrcode
import datetime

def CriaQRCODE(link:str):
    agora = datetime.datetime.now()
    agora = agora.strftime("qr_%d%m%y_%H%M%S")
    QrcodeImagem = qrcode.make(link)
    QrcodeImagem.save('./qrcodes/' + agora)
    return './qrcodes/'+agora
