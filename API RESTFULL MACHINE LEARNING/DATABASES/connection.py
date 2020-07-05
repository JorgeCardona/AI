############################################################################################
################################ Descripcion################################################
__author__ = "Jorge Cardona"
__copyright__ = "Copyright 2020, MACHINE LEARNING PROJECT"
__credits__ = "Jorge Cardona"
__license__ = "MIT"
__version__ = "1.0"
__maintainer__ = "Jorge cardona "
__email__ = "https://github.com/JorgeCardona"
__status__ = "Production"
###############################################################################################
###############################################################################################

from pymongo import MongoClient
import pymysql
from sqlalchemy import create_engine
from enum import Enum

from configuration.config import MONGODB_URL_CONNECTION, MONGODB_DATABASE_NAME
from configuration.config import MYSQL_URL_CONNECTION, MYSQL_DATABASE_NAME
from utils.tables_collections import StoreData


class ConnectionDB(object):

    # Function Annotations
    def connection_mongoDB(self, collection: Enum):

        connection  = MongoClient(MONGODB_URL_CONNECTION)
        database = connection[MONGODB_DATABASE_NAME]
        database_collection  = database[collection.name]

        # retorna el objeto de conexion
        return connection, database_collection


    def connection_mysql(self, table: str):        
        
        connection = create_engine(MYSQL_URL_CONNECTION)        
        connection = connection.connect()

        # retorna el objeto de conexion
        return connection