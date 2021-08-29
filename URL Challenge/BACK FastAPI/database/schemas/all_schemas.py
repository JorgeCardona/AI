from pydantic import BaseModel, AnyHttpUrl, constr
from datetime import datetime

class UrlCreate(BaseModel):

    original_url : AnyHttpUrl

    class Config:
        orm_mode = True

class UrlCreateRecovery(BaseModel):

    id: int
    original_url : str
    short_url: str

    class Config:
        orm_mode = True


class UrlRecovery(BaseModel):

    id: int
    original_url : str
    short_url: str
    clicks: int
    created_at: datetime
    updated_at: datetime

    class Config:
        orm_mode = True


class UrlUpdate(BaseModel):

    original_url: str

    class Config:
        orm_mode = True


class UrlOriginalUrl(BaseModel):

    original_url: str

    class Config:
        orm_mode = True

class UrlShortUrl(BaseModel):

    short_url: str

    class Config:
        orm_mode = True