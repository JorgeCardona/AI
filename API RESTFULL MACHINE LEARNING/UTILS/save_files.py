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
import os
import re
import logging
from datetime import datetime

from UTILS.util import Util
from VALIDATIONS_AND_EXCEPTIONS.exceptions import Exceptions
from CONFIGURATION.config import MAIN_DIRECTORIES, LOG_FILE_NAME, LOG_FOLDER, FILE_LOCATION_LABEL

class Save(object):
        
    def create_directory(self):

        # crea todos los directorios
        for i in MAIN_DIRECTORIES:

            # Create target Directory if don't exist
            if not os.path.exists(i):
                os.makedirs(i)
                # guarda el log
                logging.info('directory ' + i + ' Created')              


    def save_upload_file(self, file_info, file_name, directory):

        # crea los directorios donde se va a guardar los archivos a procesar y los modelos entrenados
        self.create_directory()

        # variable que guarda el emnsaje a retornar
        message = ''        

        # genera el nombre del archivo que se va a guardar sin caracteres especiales
        rename_file_name = str(str(datetime.now()) + ' ' + Util().clean_string_for_special_characters(file_name)).replace(':','-')

        # intenta realizar el proceso
        try:
            # guarda el archivo cargado en la carpeta de archivos cargados
            file_info.save(os.path.join(directory, rename_file_name))

        # el proceso fallo y genera una exception
        except Exception as ex:

            # lanza la excepcion debido al fallo
            message = Exceptions().validation_exception(800, ex)
        
        # el proceso se ejecuto con exito
        else:
            # define la ruta de acceso al archivo cargado
            FILE_LOCATION = directory + '/' + rename_file_name
            # guarda el mensaje de exito
            message = {'message' : 'Original Upload File : ' + file_name + ' Was Saved Successfull Like  : ' + rename_file_name +' in ' + directory, FILE_LOCATION_LABEL:FILE_LOCATION}

        # retorna el mensaje generado en el proceso
        finally:
            # guarda el registro en el log
            logging.info(message)
            return message