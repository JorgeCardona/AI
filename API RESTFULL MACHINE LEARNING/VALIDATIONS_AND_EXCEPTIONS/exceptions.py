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
import json
from UTILS.util import Util

class Exceptions(object):

    def validation_exception(self, exception_number, message = ''):
    
        # obtiene el valor de respuesta de la excepción
        response = self.exception(exception_number, message)

        # mensaje para registro en el log
        logging.exception('a new exception it was generated {}'.format(response))

        # retorna la excepcion generada
        return response

    def exception (self, error, message=''):

        # elimina caracteres expeciales del mensaje para evitar fallos posteriores
        message = Util().clean_string_for_special_characters(message)

        errors = {
        74: 'corrupted file or unsupported attached file type',
        400.0: "Invalid Key for Service",
        400.1: 'No File Attached',
        800:'Unable to create file on disk'

        }

        # valida si el error hace parte de los errores personalizados
        if error in errors:

             # obtiene el mensaje de error
            message = errors.get(error)

        # retorna el error
        return json.dumps({'error':{'code':error, 'message':message}})