from fastapi import FastAPI, Body
from fastapi.middleware.cors import CORSMiddleware
# poner el . punto literal para importar el modulo, sino genera error al crear la imagen de docker
from .configuration.cors import origins
from .models.hosts import Host, Content
from .usecases.info import get_internal_info, get_external_api_response, get_external_web, get_enviroment_variables, get_complete_info, get_content_file, save_content_file
# uvicorn main:app --host localhost --reload --port 5555

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
def get_info():
    
    return get_internal_info()

@app.post("/api") # Body(embed=True) para que se tenga que declarar el objeto
#def get_api_response(host: Host = Body(embed=True)):
def get_api_response(host: Host):
    return get_external_api_response(host=host)

@app.get("/all")
def get_all_info():
    
    return get_complete_info()

@app.get("/env")
def get_variables():
    
    return get_enviroment_variables()

@app.get("/web")
def get_web(url:str):    
    return get_external_web(url)

@app.get("/info")
def get_info_from_share_file():    
    return get_content_file()

@app.post("/saveinfo")
def save_info_into_share_file(content:Content):    
    return save_content_file(message=content.message)