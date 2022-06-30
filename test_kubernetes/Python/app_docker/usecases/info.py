import socket
import sys
from fastapi import __version__
import requests
# poner los dos . puntos literales para importar el modulo, sino genera error al crear la imagen de docker
from ..models.hosts import Host

def get_internal_info():
    hostname=socket.gethostname()   
    IPAddr=socket.gethostbyname(hostname)

    return {
		"Pod - Image  -> Aplication": "Python " + sys.version,
        "Pod - Image  -> FastApi Version ": __version__,
        "Pod - Image  -> hostname": socket.gethostname(),
        "Pod - Image  -> Author": "Jorge Cardona",
        "Pod - Image  -> hostname/ip": f"{hostname}/{IPAddr}",
        "Pod - Image  -> ip": IPAddr
    }

def get_external_info(host: Host):
    try:
        response = requests.get(f"{host.hostname}:{host.port}").json()
    except Exception:
        response ={"error": "Host or Port not found"}
    else: 
        response     
    finally:
        return response
        
def get_external_api_response(host: Host):
    
    response = {}
    
    response["Response from External API Consumed"] = get_external_info(host)
    response["Local API Base Data"] = get_internal_info() 
    
    return response
    
def get_external_web(url:str):
    
    try:
        response = requests.get(url).json()
    except Exception:        
        response ={"error": "website not found"}
    else:        
        response["Local API Base Data"] = get_internal_info()        
    finally:
        return response