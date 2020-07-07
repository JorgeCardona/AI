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
import logging
import ast
import pandas as pd
from DATABASES.db_connection import ConnectionDB

class DAO(object):

    def save_data_in_nosql_database(self, data_frame_list, save_in_collection):

        # carga el nombre de la table donde se va a guardar la informacion
        connection, collection = ConnectionDB().connection_mongoDB(save_in_collection)

        # inicia el intento de guardar los datos
        try:
            # itera cada dataframe recibido
            for i in range(len(data_frame_list)):
                # itera cada fila de cada dataframe
                for j in range(len(data_frame_list[i])):
                    # parsea la respuesta a diccionario
                    data = ast.literal_eval(data_frame_list[i].iloc[j].to_json())
                    # guarda el registro en MongoDB
                    collection.insert_one(data)
        # hubo un fallo al guardar    
        except Exception as ex:
            message = "Fail to Save in NOSQL database " , ex
        
        # todo salio correctamente
        else:
            message = "Save file Successfully in database"

        # retorna el mensaje y cierra la conexion
        finally:

            # guarda la informacion en el log
            logging.info(message)

            # cierra la conexion a base de datos
            connection.close()

            # retorna el mensaje
            return message

    # guarda los dataframes en base de datos
    def save(self, data_frame, data_base_type, save_in):        

        if (data_base_type.upper() == 'NOSQL'):

            # guarda en base de datos nosql - MONGODB
            return self.save_data_in_nosql_database(data_frame, save_in)




