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

from CONFIGURATION.config import MONGODB_URL_CONNECTION
from CONFIGURATION.config import DATA_DATABASE_NAME, MYSQL_URL_CONNECTION
from UTILS.tables_collections import StoreData


class ConnectionDB(object):

    # retorna el string de conexion para la base de datos
    def sql_data_url_connection(self, database_name):

        # almacena los datos de conexion de cada base de datos mysql
        database_url = {
            'MYSQL': MYSQL_URL_CONNECTION
        }

        # valida si el error hace parte de los errores personalizados
        if database_name in database_url:

             # obtiene el mensaje de error
            url_connection = database_url.get(database_name)

        # retorna el error
        return url_connection


    # Function Annotations
    # retorna el objeto de conexion para base de datos mongodb
    def connection_mongoDB(self, collection: Enum):

        # crea la url de conexion a la base de datos
        connection  = MongoClient(MONGODB_URL_CONNECTION)

         # crea el objeto para la conexion a la base de datos
        database = connection[DATA_DATABASE_NAME]

        # crea la conexion a la base de datos 
        database_collection  = database[collection.value]

        # retorna el objeto de conexion
        return connection, database_collection


    # retorna el objeto de conexion para base de datos sql
    def connection_sql(self, SQL_TYPE : str, save_in_table : Enum):   

        # crea la url de conexion a la base de datos
        SQL_URL_CONNECTION = self.sql_data_url_connection(SQL_TYPE)
        
        # crea el objeto para la conexion a la base de datos
        connection = create_engine(SQL_URL_CONNECTION)           

        # crea la conexion a la base de datos      
        connection = connection.connect()

        # retorna el objeto de conexion
        return connection, save_in_table.value