import socket
import sys
from fastapi import __version__
import requests
# poner los dos . puntos literales para importar el modulo, sino genera error al crear la imagen de docker
from models.hosts import Host
import os
from pathlib import Path


directorio = os.getenv('DIRECTORIO', default='/data/files')
archivo = os.getenv('ARCHIVO', default='info.txt')
fullpath = directorio + '/' + archivo

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
   
   
def set_directory_files(directory:str=directorio):

    Path(f'{directory}').mkdir(parents=True, exist_ok=True)
    
    os.chdir(directory)
    
    create_file()
    
    return os.listdir()
 
def get_content_file(file_name:str=archivo):
    
    set_directory_files()
    
    try:
        with open(file_name, "r") as file:
            content = file.readlines()
    except Exception:
        content = None
    finally:
        return content
    
    
def save_content_file(file_name:str=archivo, message:str='Informacion Compartida'):
    
    set_directory_files()
    hostname=socket.gethostname()
    
    try:
        with open(file_name, "a") as file:
            file.write(f'Python {sys.version} Host {hostname} Informacion Adicionada-> {message} \n')
    except Exception:
        message ={"error": "File not found"}
    else:
        message = get_content_file()
    finally:
        return message
    
def delete_file(file_name:str=archivo):
    
    try:
        os.remove(archivo)
        os.chdir(directorio)
    except Exception:
        message ={"error": f"El archivo {fullpath} no existe"}
    else:
        message = f"El archivo {fullpath} fue eliminado"
    finally:
        return message
    
def create_file(file_name:str=archivo):
    
    try:
        with open(file_name, "x") as file:
            file.write(f'')
    except Exception:
        message ={"error": f"El archivo {fullpath} ya existe"}
    else:
        message = f"El archivo {fullpath} fue creado"
    finally:
        return message