import json

from VALIDATIONS_AND_EXCEPTIONS.validations import Validator
from CONFIGURATION.config import UPLOAD_FILES_DIRECTORY, FILE_LOCATION_LABEL
from UTILS.save_files import Save
from PREPROCESSING.dataframe import DataFrameProcess
from DATABASES.db_connection import ConnectionDB
from DATABASES.db_dao import DAO
from UTILS.tables_collections import StoreData


class Services(object):

    # copia el archivo subido a una carpeta local    
    def copy_file_to_folder(self, database_name, request):

        # captura los valores obtenidos del archivo
        key_name_service, file_info, file_name = Validator().validate_upload_file(database_name, request)

        # guarda el archivo cargado en la carpeta UPLOAD_FILES_DIRECTORY
        message = Save().save_upload_file(file_info, file_name, UPLOAD_FILES_DIRECTORY) if file_name != '' \
                else json.loads(key_name_service)

        # retorna el mensaje generado al intentar guardar el archivo
        return message

    # guarda la informacion en base de datos
    def save_data(self, request, database_name):

        # obtiene el mensaje de ejecutar la copia del archivo
        copy_file_message = self.copy_file_to_folder(database_name, request)

        # crea un dataframe del archivo cargado, si no existe ninguna excepcion
        data_frame = DataFrameProcess().load_data_frame_for_save_in_database(copy_file_message[FILE_LOCATION_LABEL]) if ( type(copy_file_message) != dict) else {}

        # carga el nombre de la table donde se va a guardar la informacion
        save_in = StoreData.SAVE_ALL_DATA

        # guarda la informacion en base de datos, si no existe ninguna excepcion
        datos_en_BD = DAO().save([data_frame], database_name, save_in) if (type(data_frame) != dict) else {}
        
        # mensaje de retorno al usuario
        message = {'copy_file_message':copy_file_message, 'save_data_message':datos_en_BD}

        # retorna el mensaje generado al intentar guardar el archivo
        return message