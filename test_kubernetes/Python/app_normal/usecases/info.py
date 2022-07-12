import socket
import sys
from fastapi import __version__
import requests
# poner los dos . puntos literales para importar el modulo, sino genera error al crear la imagen de docker
from models.hosts import Host
import os
from pathlib import Path


def get_enviroment_variables():
    return os.environ.items()

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

def get_complete_info():
    
    actualinfo = get_internal_info()
    actualinfo[ "Pod - Image  -> Environment Variables"] = get_enviroment_variables()
    return actualinfo

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
    response["Local API Base Data"] = get_complete_info() 
    
    return response
    
def get_external_web(url:str):
    
    try:
        response = requests.get(url).json()
    except Exception:        
        response ={"error": "website not found"}
    else:        
        response["Local API Base Data"] = get_complete_info()        
    finally:
        return response
   
   
def set_directory_files(directory:str='/data/files'):

    Path(f'{directory}').mkdir(parents=True, exist_ok=True)
    
    os.chdir(directory)
    
    return os.listdir()
 
def get_content_file(file_name:str='info.txt'):
    
    set_directory_files()
    print(os.getcwd())
    print(os.listdir())
    
    try:
        with open(file_name, "r") as file:
            content = file.readlines()
    except Exception:
        content = None
    finally:
        return content
    
    
def save_content_file(file_name:str='info.txt', message:str='Informacion Compartida'):
    
    set_directory_files()
    
    try:
        with open(file_name, "a") as file:
            file.write(f'Python {sys.version} Informacion Adicionada Usando Python -> {message} \n')
    except Exception:
        message ={"error": "File not found"}
    else:
        message = get_content_file()
    finally:
        return message