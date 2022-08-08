import gui
from ping import PingHost

class InterfaceUsuario(gui.Ui_MainWindow):
    def __init__():
        # definir as chamadas
        self.button_ping.clicked.connect(self.ChamaPing)
        self.button_gerar_senha.clicked.connect()
        self.button_encurtar.clicked.connect()
    
    def Sandbox(funcao):
        def ChamadaFuncao(funcao, *args, **kwargs):
            try:
                funcao(self, *args, **kwargs)
            except Exception as Erro:
                traceback.print_exception()
                Popup = QMessageBox.critical(self,'Erro!', str(Erro))
        return ChamadaFuncao
    
    def ChamaPing():
        HostPing = str(self.host_ping.value())
        QuantidadePing = str(self.spinBox_ping.value())
        ResultadoPing = PingHost(HostPing,QuantidadePing)
        self.label_resultado.value(ResultadoPing[0])
        self.resultado_ping_av.value(ResultadoPing[1])
