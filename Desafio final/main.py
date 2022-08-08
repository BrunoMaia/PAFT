from gui import *
from qrcode import *
from gerador_senha import *
from ping import *
from encurtador import *
from PyQt5 import QtCore, QtGui, QtWidgets

if __name__ =  __main__:
    app = QtWidgets.QApplication()
    MainWindow = QtWidgets.QMainWindow()
    ui = gui.InterfaceUsuario()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())