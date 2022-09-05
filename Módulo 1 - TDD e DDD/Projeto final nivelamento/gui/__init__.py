from ping import PingHost
from gerador_senha import GeraSenha
from encurtador import EncurtaUrl
from qrcoder import CriaQRCODE
from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtWidgets import QMessageBox, QApplication
from PyQt5.QtGui import QPixmap
import traceback
import pyperclip
from art import *

class InterfaceUsuario(object):
    def ExibeJanelaErro(self, erro: str):
        PopupErro = QMessageBox()
        PopupErro.setIcon(QMessageBox.Information)
        PopupErro.setWindowTitle("Erro")
        PopupErro.setText("Ocorreu um erro na execução do programa!\nCaso precisse de ajuda, contate o suporte")
        PopupErro.setDetailedText("O log do erro é:\n"+ erro)
        PopupErro.exec_()
        
    def Sandbox(funcao):
        def funcao_decorada(self, *args, **kwargs):
            try:
                funcao(self, *args, **kwargs)
            except Exception as erro:
                traceback.print_exc()
                self.ExibeJanelaErro(traceback.format_exc())
        return funcao_decorada
    @Sandbox
    def ExecutaPing(self, *args,**kwargs):
        HostPing = str(self.host_ping.text())
        if HostPing.lower() == 'alegrete':
            self.ExecutaEgg()
            return False
        QuantidadePing = str(self.spinBox_ping.value())
        ResultadoPing = PingHost(HostPing,QuantidadePing)
        HostAtivo = "O host não respondeu ao ping"
        if ResultadoPing[0] == True:
            HostAtivo = "O host respondeu ao ping"
        self.label_resultado.setText(HostAtivo)
        self.resultado_ping_av.setText(ResultadoPing[1])
        return True
    
    @Sandbox
    def ChamaGeradorSenha(self, *args, **kwargs):
        TamanhoSenha = int(self.tamanho_senha.value())
        TamanhoEspeciais = int(self.qtd_chars_especial.value())
        SenhaGerada = GeraSenha(TamanhoSenha, TamanhoEspeciais)
        Copiar = QApplication.clipboard()
        self.senha_gerada.setText(SenhaGerada)
        Copiar.setText(SenhaGerada)
        return True

    @Sandbox
    def ChamaEncurtadorURL(self, *args, **kwargs):
        Url = str(self.lineEdit_url.text())
        if Url.lower() == 'alegrete':
            self.ExecutaEgg()
            return False
        UrlEncurtada = EncurtaUrl(Url)
        Copiar = QApplication.clipboard()
        Copiar.setText(UrlEncurtada)
        Qrcode = CriaQRCODE(UrlEncurtada)
        self.resultado_url.setHtml(UrlEncurtada)
        self.qrcode.setPixmap(QPixmap(Qrcode))
        self.qrcode.setScaledContents(True)

    
    @Sandbox
    def AtualizaTitulosSenha(self, valor,slider):
        if slider == 'senha':
            self.label_tamanho_senha.setText('Tamanho da senha: '+ str(valor))
        if slider == 'char':
            self.label_char_especial.setText('Caracteres especiais: '+str(valor))

    @Sandbox
    def ExecutaEgg(self):
        Imagem = text2art("TOP","random")+'\n'+art('thanks')+'\n'+art('coffee now')
        PopupEgg = QMessageBox()
        PopupEgg.setIcon(QMessageBox.Question)
        PopupEgg.setWindowTitle("404 "+art("bear"))
        PopupEgg.setText("Ocorreu um erro na execução do programa!\nCaso precisse de ajuda, contate o suporte: 4002-8922")
        PopupEgg.setDetailedText("Onde o Alegrete fica? Não sei, pode ser a cidade, ou o paraíso, enquanto pensa nisso, veja meu github e tome uma xícara de chá =D\nhttps://github.com/BrunoMaia/\n"+ Imagem)
        PopupEgg.exec_()
        self.resultado_ping_av.setText(Imagem)

    
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(640, 480)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(MainWindow.sizePolicy().hasHeightForWidth())
        MainWindow.setSizePolicy(sizePolicy)
        MainWindow.setMinimumSize(QtCore.QSize(640, 480))
        MainWindow.setMaximumSize(QtCore.QSize(640, 480))
        MainWindow.setAutoFillBackground(False)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.centralwidget.sizePolicy().hasHeightForWidth())
        self.centralwidget.setSizePolicy(sizePolicy)
        self.centralwidget.setObjectName("centralwidget")
        self.tabWidget = QtWidgets.QTabWidget(self.centralwidget)
        self.tabWidget.setGeometry(QtCore.QRect(0, 0, 661, 471))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.tabWidget.sizePolicy().hasHeightForWidth())
        self.tabWidget.setSizePolicy(sizePolicy)
        self.tabWidget.setLayoutDirection(QtCore.Qt.LeftToRight)
        self.tabWidget.setTabPosition(QtWidgets.QTabWidget.West)
        self.tabWidget.setTabShape(QtWidgets.QTabWidget.Rounded)
        self.tabWidget.setIconSize(QtCore.QSize(16, 16))
        self.tabWidget.setDocumentMode(False)
        self.tabWidget.setTabsClosable(False)
        self.tabWidget.setTabBarAutoHide(False)
        self.tabWidget.setObjectName("tabWidget")
        self.ping_tab = QtWidgets.QWidget()
        self.ping_tab.setObjectName("ping_tab")
        self.host_ping = QtWidgets.QLineEdit(self.ping_tab)
        self.host_ping.setGeometry(QtCore.QRect(20, 40, 331, 20))
        self.host_ping.setToolTip("")
        self.host_ping.setObjectName("host_ping")
        self.label_host = QtWidgets.QLabel(self.ping_tab)
        self.label_host.setGeometry(QtCore.QRect(10, 20, 26, 13))
        self.label_host.setObjectName("label_host")
        self.label_resultado = QtWidgets.QLabel(self.ping_tab)
        self.label_resultado.setGeometry(QtCore.QRect(10, 90, 180, 16))
        self.label_resultado.setObjectName("label_resultado")
        self.resultado_ping_av = QtWidgets.QTextBrowser(self.ping_tab)
        self.resultado_ping_av.setGeometry(QtCore.QRect(20, 140, 581, 301))
        self.resultado_ping_av.setTextInteractionFlags(QtCore.Qt.TextBrowserInteraction)
        self.resultado_ping_av.setObjectName("resultado_ping_av")
        self.label_resultado_av = QtWidgets.QLabel(self.ping_tab)
        self.label_resultado_av.setGeometry(QtCore.QRect(10, 120, 121, 16))
        self.label_resultado_av.setObjectName("label_resultado_av")
        self.button_ping = QtWidgets.QPushButton(self.ping_tab)
        self.button_ping.setGeometry(QtCore.QRect(520, 40, 75, 23))
        self.button_ping.setObjectName("button_ping")
        self.spinBox_ping = QtWidgets.QSpinBox(self.ping_tab)
        self.spinBox_ping.setGeometry(QtCore.QRect(400, 40, 42, 22))
        self.spinBox_ping.setMinimum(1)
        self.spinBox_ping.setMaximum(4)
        self.spinBox_ping.setObjectName("spinBox_ping")
        self.label_n_ping = QtWidgets.QLabel(self.ping_tab)
        self.label_n_ping.setGeometry(QtCore.QRect(380, 20, 81, 16))
        self.label_n_ping.setObjectName("label_n_ping")
        self.tabWidget.addTab(self.ping_tab, "")
        self.senha_tab = QtWidgets.QWidget()
        self.senha_tab.setObjectName("senha_tab")
        self.tamanho_senha = QtWidgets.QSlider(self.senha_tab)
        self.tamanho_senha.setGeometry(QtCore.QRect(30, 40, 160, 22))
        self.tamanho_senha.setTabletTracking(False)
        self.tamanho_senha.setContextMenuPolicy(QtCore.Qt.DefaultContextMenu)
        self.tamanho_senha.setMinimum(8)
        self.tamanho_senha.setMaximum(64)
        self.tamanho_senha.setOrientation(QtCore.Qt.Horizontal)
        self.tamanho_senha.setInvertedAppearance(False)
        self.tamanho_senha.setTickPosition(QtWidgets.QSlider.NoTicks)
        self.tamanho_senha.setObjectName("tamanho_senha")
        self.qtd_chars_especial = QtWidgets.QSlider(self.senha_tab)
        self.qtd_chars_especial.setGeometry(QtCore.QRect(300, 40, 160, 22))
        self.qtd_chars_especial.setMaximum(64)
        self.qtd_chars_especial.setOrientation(QtCore.Qt.Horizontal)
        self.qtd_chars_especial.setObjectName("qtd_chars_especial")
        self.label_tamanho_senha = QtWidgets.QLabel(self.senha_tab)
        self.label_tamanho_senha.setGeometry(QtCore.QRect(20, 20, 150, 13))
        self.label_tamanho_senha.setObjectName("label_tamanho_senha")
        self.label_char_especial = QtWidgets.QLabel(self.senha_tab)
        self.label_char_especial.setGeometry(QtCore.QRect(290, 20, 200, 13))
        self.label_char_especial.setObjectName("label_char_especial")
        self.button_gerar_senha = QtWidgets.QPushButton(self.senha_tab)
        self.button_gerar_senha.setGeometry(QtCore.QRect(520, 30, 75, 23))
        self.button_gerar_senha.setObjectName("button_gerar_senha")
        self.senha_gerada = QtWidgets.QTextBrowser(self.senha_tab)
        self.senha_gerada.setGeometry(QtCore.QRect(30, 110, 561, 41))
        self.senha_gerada.setObjectName("senha_gerada")
        self.label_senha_gerada = QtWidgets.QLabel(self.senha_tab)
        self.label_senha_gerada.setGeometry(QtCore.QRect(20, 90, 91, 16))
        self.label_senha_gerada.setObjectName("label_senha_gerada")
        self.tabWidget.addTab(self.senha_tab, "")
        self.encurtar_tab = QtWidgets.QWidget()
        self.encurtar_tab.setObjectName("encurtar_tab")
        self.lineEdit_url = QtWidgets.QLineEdit(self.encurtar_tab)
        self.lineEdit_url.setGeometry(QtCore.QRect(20, 40, 450, 20))
        self.lineEdit_url.setObjectName("lineEdit_url")
        self.label_url = QtWidgets.QLabel(self.encurtar_tab)
        self.label_url.setGeometry(QtCore.QRect(10, 20, 47, 13))
        self.label_url.setObjectName("label_url")
        self.qrcode = QtWidgets.QLabel(self.encurtar_tab)
        self.qrcode.setGeometry(QtCore.QRect(160, 190, 240, 240))
        self.qrcode.setObjectName("qrcode")
        self.resultado_url = QtWidgets.QTextEdit(self.encurtar_tab)
        self.resultado_url.setGeometry(QtCore.QRect(20, 100, 450, 30))
        self.resultado_url.setReadOnly(True)
        self.resultado_url.setObjectName("resultado_url")
        self.button_encurtar = QtWidgets.QPushButton(self.encurtar_tab)
        self.button_encurtar.setGeometry(QtCore.QRect(510, 40, 75, 23))
        self.button_encurtar.setObjectName("button_encurtar")
        self.label_url_encurtada = QtWidgets.QLabel(self.encurtar_tab)
        self.label_url_encurtada.setGeometry(QtCore.QRect(10, 80, 80, 13))
        self.label_url_encurtada.setObjectName("label_url_encurtada")
        self.label_qrcode = QtWidgets.QLabel(self.encurtar_tab)
        self.label_qrcode.setGeometry(QtCore.QRect(250, 160, 60, 13))
        self.label_qrcode.setObjectName("label_qrcode")
        self.tabWidget.addTab(self.encurtar_tab, "")
        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.button_ping.clicked.connect(self.ExecutaPing)
        self.button_gerar_senha.clicked.connect(self.ChamaGeradorSenha)
        self.button_encurtar.clicked.connect(self.ChamaEncurtadorURL)
        self.tamanho_senha.valueChanged.connect(lambda: self.AtualizaTitulosSenha(self.tamanho_senha.value(),slider='senha'))
        self.qtd_chars_especial.valueChanged.connect(lambda: self.AtualizaTitulosSenha(self.qtd_chars_especial.value(),slider='char'))

        self.retranslateUi(MainWindow)
        self.tabWidget.setCurrentIndex(0)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "Canivete curitibano"))
        self.label_host.setText(_translate("MainWindow", "Host"))
        self.label_resultado.setText(_translate("MainWindow", ""))
        self.resultado_ping_av.setHtml(_translate("MainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:\'MS Shell Dlg 2\'; font-size:8.25pt; font-weight:400; font-style:normal;\">\n"
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><br /></p></body></html>"))
        self.label_resultado_av.setText(_translate("MainWindow", "Resultado avançado"))
        self.button_ping.setText(_translate("MainWindow", "Pingar!"))
        self.spinBox_ping.setToolTip(_translate("MainWindow", "<html><head/><body><p>É a quantidade de vezes que o ping é executado</p></body></html>"))
        self.label_n_ping.setText(_translate("MainWindow", "Quantidade"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.ping_tab), _translate("MainWindow", "Ping"))
        self.label_tamanho_senha.setText(_translate("MainWindow", "Tamanho da senha: 8"))
        self.label_char_especial.setText(_translate("MainWindow", "Caracteres especiais e números: 0"))
        self.button_gerar_senha.setText(_translate("MainWindow", "Gerar!"))
        self.label_senha_gerada.setText(_translate("MainWindow", "Senha gerada"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.senha_tab), _translate("MainWindow", "Gerar senha"))
        self.label_url.setText(_translate("MainWindow", "URL"))
        self.button_encurtar.setText(_translate("MainWindow", "Encurtar!"))
        self.label_url_encurtada.setText(_translate("MainWindow", "URL encurtado"))
        self.label_qrcode.setText(_translate("MainWindow", "QR CODE"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.encurtar_tab), _translate("MainWindow", "Encurtar URL"))

