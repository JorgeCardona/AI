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
from CONFIGURATION.config import MAIN_DIRECTORIES, LOG_FILE_NAME, LOG_FOLDER

class Save(object):
        
    def create_directory(self):

        # crea todos los directorios
        for i in MAIN_DIRECTORIES:

            # Create target Directory if don't exist
            if not os.path.exists(i):
                os.makedirs(i)
                # guarda el log
                logging.info('directory '+ i + ' Created')              


    def save_upload_file(self, file, file_name, directory):

        message = ''        

        # genera el nombre del archivo que se va a guardar sin caracteres especiales
        file_name = str(str(datetime.now()) + ' ' + Util().clean_string_for_special_characters(file_name)).replace(':','-')

        # intenta realizar el proceso
        try:
            # guarda el archivo cargado en la carpeta de archivos cargados
            file.save(os.path.join(directory, file_name))

        # el proceso fallo y genera una exception
        except Exception as ex:

            # lanza la excepcion debido al fallo
            message = Exceptions().validation_exception(800, ex)
        
        # el proceso se ejecuto con exito
        else:

            message = {'message' : 'File save Successfull in : ' + directory + '\\' + file_name}

        # retorna el mensaje generado en el proceso
        finally:
            # guarda el registro en el log
            logging.info(message)
            return message