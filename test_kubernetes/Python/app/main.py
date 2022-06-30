from fastapi import FastAPI, Body
from fastapi.middleware.cors import CORSMiddleware
from configuration.cors import origins
from models.hosts import Host
from usecases.info import get_internal_info, get_external_api_response, get_external_web
# uvicorn main:app --host 0.0.0.0 --reload --port 5555
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

@app.get("/web")
def get_web(url:str):    
    return get_external_web(url)
