# docker run --name postgres-challenge -e POSTGRES_DB=challenge -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=root  -d -p 8001:5432 postgres

from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

DB_ENGINE = "postgresql"
DB_USER = "root"
DB_PASSWORD = "admin"
DB_HOST = "localhost"
DB_PORT = "8001"
DB_NAME = "challenge"


SQLALCHEMY_DATABASE_URL = "{ENGINE}://{USER}:{PASSWORD}@{HOST}:{PORT}/{NAME}".format(ENGINE=DB_ENGINE, USER=DB_USER, PASSWORD=DB_PASSWORD, HOST=DB_HOST, PORT=DB_PORT, NAME=DB_NAME)

engine = create_engine(SQLALCHEMY_DATABASE_URL)
Session = sessionmaker(bind=engine)
session = Session()
Base = declarative_base()


SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()
