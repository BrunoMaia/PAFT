import platform    
from subprocess import Popen, PIPE 

def ping(host):
    """
    Returns True if host (str) responds to a ping request.
    Remember that a host may not respond to a ping (ICMP) request even if the host name is valid.
    """

    # Option for the number of packets as a function of
    param = '-n' if platform.system().lower()=='windows' else '-c'

    # Building the command. Ex: "ping -c 1 google.com"
    command = ['ping', param, '1', host]
    result = ""
    #return subprocess.call(command) == 0
    process = Popen(command,stdout=PIPE,stderr=PIPE)
    stdout,stderr = process.communicate()
    return stdout.decode('ISO-8859-1')
print(ping("google.com"))
