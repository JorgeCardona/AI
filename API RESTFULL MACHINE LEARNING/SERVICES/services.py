import json

from VALIDATIONS_AND_EXCEPTIONS.validations import Validator
from CONFIGURATION.config import UPLOAD_FILES_DIRECTORY, FILE_LOCATION_LABEL, KEYS_FROM_REQUEST, DATA_BASE_DEFAULT_VALUES
from UTILS.save_files import Save
from PREPROCESSING.dataframe import DataFrameProcess
from DATABASES.db_connection import ConnectionDB
from DATABASES.db_dao import DAO

class Services(object):

        # extrae los valores del header para consumo del servicio
    def extract_values_from_request(self, request):

        # obtiene el nombre de la key enviaada en la peticion
        key_name_from_file  = list(request.files)

        # obtiene la informacion del archivo adjuntado
        file_info = request.files[key_name_from_file[0]]

        # obtiene el nombre del fichero adjuntado
        file_name  = file_info.filename if file_info else file_info

        # extrae las keys del header del request
        header_keys = dict(request.headers)
        
        # obtiene las llaves enviadas en el request y su valor,las cuales son las esperadas para el proceso
        database_variables_list = {i.upper():j.upper() for i,j in header_keys.items() if i.upper() in KEYS_FROM_REQUEST if j.strip() != ''}

        # asigna los valores capturados del request en las variables
        service, database_type, database_name, save_in_table = [database_variables_list[i] if i in database_variables_list.keys() else DATA_BASE_DEFAULT_VALUES[j] for j,i in enumerate(KEYS_FROM_REQUEST)]

        # retorna los valores asignados
        return service, database_type, database_name, save_in_table, file_name


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
    def save_data(self, request, database_name, table):

        service, database_type, database_name, save_in_table, file_name = self.extract_values_from_request(request)

        # obtiene el mensaje de ejecutar la copia del archivo
        copy_file_message = self.copy_file_to_folder(database_type, request)

        # crea un dataframe del archivo cargado, si no existe ninguna excepcion
        data_frame = DataFrameProcess().load_data_frame_for_save_in_database(copy_file_message[FILE_LOCATION_LABEL]) if not 'error' in copy_file_message else {}

        # guarda la informacion en base de datos, si no existe ninguna excepcion
        datos_en_BD = DAO().save(data_frame, database_type, database_name, save_in_table) if (type(data_frame) != dict) else {}
               
        # datos usados para la base de datos
        database_data = {'database_type':database_type, 'database_name':database_name, 'database_table':save_in_table}

        # mensaje de retorno al usuario
        message = {'copy_file_message':copy_file_message, 'save_data_message':datos_en_BD, 'database_data':database_data}

        # retorna el mensaje generado al intentar guardar el archivo
        return message