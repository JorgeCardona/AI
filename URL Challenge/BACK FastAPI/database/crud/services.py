from pydantic.types import NoneBytes
from sqlalchemy.orm import Session
from fastapi import Depends, FastAPI, HTTPException
from database.models import Entity
from database.schemas import all_schemas
import random
import string

def get_url_list(db: Session, skip: int = 0, limit: int = 5):
    return db.query(Entity.Url).order_by(Entity.Url.updated_at.desc()).offset(skip).limit(limit).all()


def get_url_by_id(db: Session, id: int):

    return db.query(Entity.Url).filter(Entity.Url.id == id).first()


def get_url_by_short_url(db: Session, short_url: str):

    return db.query(Entity.Url).filter(Entity.Url.short_url == short_url).first()


def get_url_by_original_url(db: Session, original_url: str):

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

    return short_url_generated


def create_new_url(db: Session, url: all_schemas.UrlCreate):

    short_url_generate = generate_short_url(db, url.original_url)

    db_user = Entity.Url(original_url=url.original_url, short_url=short_url_generate)
    db.add(db_user)
    db.commit()
    db.refresh(db_user)

    return db_user


def update_url(db: Session, url: all_schemas.UrlRecovery):

    db.query(Entity.Url).filter(Entity.Url.short_url == url.short_url).update({"clicks": url.clicks + 1}, synchronize_session="fetch")
    db.commit()

    return db.query(Entity.Url).filter(Entity.Url.short_url == url.short_url).first()


def delete_link(db: Session, url: all_schemas.UrlRecovery):

    db.delete(url)
    db.commit()
    
    return {"Short URL": "{short_url} with ID {id} has been deleted succesfully".format(short_url= url.short_url, id= url.id)}