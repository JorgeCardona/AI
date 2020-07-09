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
from CONFIGURATION.config import URL_COMPLEMENTARY
from VALIDATIONS_AND_EXCEPTIONS.exceptions import Exceptions
from VALIDATIONS_AND_EXCEPTIONS.success_messages import Success

class DAO(object):

    # guarda en base de datos nosql como MONGODB
    def save_data_in_nosql_database(self, data_frame_list, save_in_collection):

        # inicia el intento de guardar los datos
        try:
            # carga el nombre de la table donde se va a guardar la informacion
            connection, collection = ConnectionDB().connection_mongoDB(save_in_collection)

            # itera cada dataframe recibido
            for i in range(len(data_frame_list)):
                # itera cada fila de cada dataframe
                 # parsea la respuesta a diccionario
                data = ast.literal_eval(data_frame_list.iloc[i].to_json())
                # guarda el registro en MongoDB
                collection.insert_one(data)

        # hubo un fallo al guardar    
        except Exception as ex:
            message = Exceptions().obtain_message(500.1, ex) 
        
        # todo salio correctamente
        else:
            message = Success().obtain_message(200.1)

        # retorna el mensaje y cierra la conexion
        finally:

            # guarda la informacion en el log
            logging.info(message)

            # cierra la conexion a base de datos
            connection.close()

            # retorna el mensaje
            return message


    # guarda la informacion en la base de datos sql que sea declarada para la api y que se haya solicitado en la peticion
    def save_sql_database(self, data_frame, data_base_type, save_in_table):

        message = ''

        # inicia el intento de guardar los datos
        try:
            # obtiene el objeto de conexion a base de datos
            connection, table = ConnectionDB().connection_sql(data_base_type, save_in_table)            

            # crea la tabla si no existe y adiciona los datos a la base de datos
            data_frame.to_sql(table, con = connection, index=False, if_exists ='append')

        # hubo un fallo al guardar 
        except Exception as ex:
            
            # obtiene el mensaje de excepcion
            message = Exceptions().obtain_message(500.1, ex)      

        # todo salio correctamente
        else:
            message = Success().obtain_message(200.1)
        
        # retorna el mensaje y cierra la conexion
        finally:

            # guarda la informacion en el log
            logging.info(message)

            # retorna el mensaje
            return message


    # guarda los dataframes en base de datos
    def save(self, data_frame, data_base_type, save_in_table):     

        # valida si se quiere guardar en mongodb
        if (data_base_type.upper() == 'MONGODB'):

            # guarda en base de datos nosql - MONGODB
            return self.save_data_in_nosql_database(data_frame, save_in_table)

        else:
            # guarda en base de datos SQL
            return self.save_sql_database(data_frame, data_base_type.upper(), save_in_table)




