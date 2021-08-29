from configuration.log import log_api

from configuration.api import fastAPiConfiguration
from configuration.swagger import custom_openapi
from database.schemas import all_schemas
from database.models import Entity
from database.crud import services
from database.configuration.database import get_db, engine
from fastapi import Depends, HTTPException
from typing import List
from utils.constants import SERVICE_PATH, TAG_SERVICE, GET_LIST, GET_BY_ID, POST_BY_INFO, PUT_BY_INFO, DELETE_BY_ID

import uvicorn
from sqlalchemy.orm import Session

app = fastAPiConfiguration()

# for create table in database
Entity.Base.metadata.create_all(bind=engine)

@app.get(SERVICE_PATH, tags=[TAG_SERVICE],name=GET_LIST, response_model=List[all_schemas.UrlRecovery])
def get_register_list(skip: int = 0, limit: int = 5, db: Session = Depends(get_db)):

    return services.get_url_list(db, skip=skip, limit=limit)

@app.patch(SERVICE_PATH + "original_url", tags=[TAG_SERVICE],name=GET_BY_ID, response_model=all_schemas.UrlRecovery)
def get_register_by_original_url(original_url: all_schemas.UrlOriginalUrl, db: Session = Depends(get_db)):  
 
    db_atribute = services.get_url_by_original_url(db, original_url.original_url)
    
    if db_atribute is None:
        message = "Original URL not found"
        log_api.warning(message)
        raise HTTPException(status_code=404, detail = message)
    return db_atribute

@app.patch(SERVICE_PATH + "short_url", tags=[TAG_SERVICE],name=GET_BY_ID, response_model=all_schemas.UrlRecovery)
def get_register_by_short_url(short_url: all_schemas.UrlShortUrl, db: Session = Depends(get_db)):  

    db_atribute = services.get_url_by_short_url(db, short_url.short_url)
    
    if db_atribute is None:
        message = "Short URL not found"
        log_api.warning(message)
        raise HTTPException(status_code=404, detail = message)
    return db_atribute


@app.post(SERVICE_PATH, tags=[TAG_SERVICE],name=POST_BY_INFO, response_model=all_schemas.UrlCreateRecovery)
def save_register(url: all_schemas.UrlCreate, db: Session = Depends(get_db)):

    db_atribute = services.get_url_by_original_url(db, url.original_url)

    if db_atribute:
        message = "Original URL already registered"
        log_api.warning(message)
        raise HTTPException(status_code=400, detail=message)
    return services.create_new_url(db=db, url=url)



@app.put(SERVICE_PATH, tags=[TAG_SERVICE],name=PUT_BY_INFO, response_model=all_schemas.UrlRecovery)
def update_register(original_url: all_schemas.UrlUpdate, db: Session = Depends(get_db)):

    db_atribute = services.get_url_by_original_url(db, original_url.original_url)

    if db_atribute is None:
        message = "Original URL not found"
        log_api.warning(message)
        raise HTTPException(status_code=404, detail=message)
    return services.update_url(db, db_atribute)



@app.patch(SERVICE_PATH + "{short_url}", tags=[TAG_SERVICE],name=PUT_BY_INFO, response_model=all_schemas.UrlRecovery, include_in_schema=False)
def update_register_using_click(short_url: str, db: Session = Depends(get_db)):

    short_url = services.decoding_url(short_url)

    db_atribute = services.get_url_by_short_url(db, short_url)

    if db_atribute is None:
        message = "Short URL not found"
        log_api.warning(message)
        raise HTTPException(status_code=404, detail=message)
    return services.update_url(db, db_atribute)



@app.delete(SERVICE_PATH +"{id}", tags=[TAG_SERVICE],name=DELETE_BY_ID)
def delete_register_by_id(id: int, db: Session = Depends(get_db)):
    
    db_atribute = services.get_url_by_id(db, id)

    if db_atribute is None:
        message = "Id URL not found"
        log_api.warning(message)
        raise HTTPException(status_code=404, detail=message)
    return services.delete_link(db, db_atribute)

if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000, debug = True)