from sqlalchemy import log
from configuration.log import log_api
from pydantic.types import NoneBytes
from sqlalchemy.orm import Session
from fastapi import Depends, FastAPI, HTTPException
from database.models import Entity
from database.schemas import all_schemas
from utils.constants import ENCODING_DECODING_WORD
import random
import string


def decoding_url(short_url):
    log_api.info("Decoding Url "+ str(short_url))
    return short_url.replace(ENCODING_DECODING_WORD, "/")


def encoding_url(short_url):

    log_api.info("Encoding Url " + str(short_url))
    return short_url.replace("/", ENCODING_DECODING_WORD)

def get_url_list(db: Session, skip: int = 0, limit: int = 5):

    log_api.info("Getting response for Data list")
    return db.query(Entity.Url).order_by(Entity.Url.updated_at.desc()).offset(skip).limit(limit).all()


def get_url_by_id(db: Session, id: int):

    log_api.info("Getting URL by ID " + str(id))
    return db.query(Entity.Url).filter(Entity.Url.id == id).first()


def get_url_by_short_url(db: Session, short_url: str):

    log_api.info("Validate Short URL " + str(short_url))
    return db.query(Entity.Url).filter(Entity.Url.short_url == short_url).first()


def get_url_by_original_url(db: Session, original_url: str):

    log_api.info("Validate Original URL " + str(original_url))
    return db.query(Entity.Url).filter(Entity.Url.original_url == original_url).first()


def generate_short_url(db, original_url):

    url_base = original_url.split("/", 3)
    url_base = url_base[0] + "//" + url_base[2] + "/"

    letters = string.ascii_letters + string.digits
    short_url_generated = url_base + ''.join(random.choice(letters) for i in range(5))
    data = True

    while (data):        

        if not get_url_by_short_url(db, short_url_generated):
            data = False

    log_api.info("New Short URL was created -> " + str(short_url_generated) + " from original URL -> " + str(original_url))
    return short_url_generated


def create_new_url(db: Session, url: all_schemas.UrlCreate):

    short_url_generate = generate_short_url(db, url.original_url)

    db_url = Entity.Url(original_url=url.original_url, short_url=short_url_generate)
    db.add(db_url)
    db.commit()
    db.refresh(db_url)

    log_api.info("New URL data created -> " + str(url))
    return db_url


def update_url(db: Session, url: all_schemas.UrlRecovery):

    print("sssss " , url)
    db.query(Entity.Url).filter(Entity.Url.short_url == url.short_url).update({"clicks": url.clicks + 1}, synchronize_session="fetch")
    db.commit()

    log_api.info("There is a new click in the Short URL -> " + str(url.short_url) + " now the URL has {clicks} clicks".format(clicks=url.clicks))
    return db.query(Entity.Url).filter(Entity.Url.short_url == url.short_url).first()


def delete_link(db: Session, url: all_schemas.UrlRecovery):

    db.delete(url)
    db.commit()

    log_api.info("deleted information -> " + str(url.__dict__))
    
    return {"Short URL": "{short_url} with ID {id} has been deleted succesfully".format(short_url= url.short_url, id = url.id)}