from sqlalchemy import Column, Integer, String, DateTime
from sqlalchemy.sql.sqltypes import DateTime

import datetime
from database.configuration.database import Base

class Url(Base):
    
    __tablename__ = "links"
    # Ctrl + K then press Ctrl + C  to Comment a block of code
    # Ctrl + K then Ctrl + U to uncomment a block of code
    id = Column(Integer, primary_key=True, index=True)
    original_url = Column(String)
    short_url = Column(String, unique=True, index=True)
    clicks = Column(Integer, default=0)
    created_at = Column(DateTime, default=datetime.datetime.now)
    updated_at = Column(DateTime, default=datetime.datetime.now, onupdate=datetime.datetime.now)