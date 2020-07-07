import json

from VALIDATIONS_AND_EXCEPTIONS.validations import Validator
from CONFIGURATION.config import UPLOAD_FILES_DIRECTORY, FILE_LOCATION_LABEL
from UTILS.save_files import Save
from PREPROCESSING.dataframe import DataFrameProcess

class Services(object):

    def copy_file_to_folder(self, request):

        # captura los valores obtenidos del archivo
        key_name_service, file_info, file_name = Validator().validate_upload_file(request)

        # guarda el archivo cargado en la carpeta UPLOAD_FILES_DIRECTORY
        message = Save().save_upload_file(file_info, file_name, UPLOAD_FILES_DIRECTORY) if file_name != '' \
                else json.loads(key_name_service)

        # retorna el mensaje generado al intentar guardar el archivo
        return message


    def save_data(self, request):

        # obtiene el mensaje de ejecutar la copia del archivo
        copy_file_message = self.copy_file_to_folder(request)

        # crea un dataframe del archivo cargado
        data_frame = DataFrameProcess().load_data_frame_for_save_in_database(copy_file_message[FILE_LOCATION_LABEL])
        
        message = {'copy_file_message':copy_file_message, 'save_data_message':[data_frame]}

        print(data_frame)

        # retorna el mensaje generado al intentar guardar el archivo
        return 'message'