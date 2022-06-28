from typing import Union
import sys
from fastapi import FastAPI

app = FastAPI()
import socket 

@app.get("/")
def read_root():
    hostname=socket.gethostname()   
    IPAddr=socket.gethostbyname(hostname) 

    return {
		"Pod - Image  -> Aplication": "Python " + sys.version,
        "Pod - Image  -> hostname": socket.gethostname(),
        "Pod - Image  -> Author": "Jorge Cardona",
        "Pod - Image  -> hostname/ip": f"{hostname}/{IPAddr}",
        "Pod - Image  -> ip": IPAddr
    }


@app.get("/items/{item_id}")
def read_item(item_id: int, q: Union[str, None] = None):
    return {"item_id": item_id, "q": q}