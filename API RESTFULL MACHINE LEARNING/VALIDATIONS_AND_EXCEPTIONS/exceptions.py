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

    # consulta el mensaje que se retorna al usuario
    def obtain_message(self, code, message = ''):
    
        # obtiene el valor de respuesta de la excepción
        response = self.message(code, message)

        # mensaje para registro en el log
        logging.exception('a new exception it was generated {}'.format(response))

        # retorna el mensaje generado
        return response


    # tiene los mensajes creados para la api
    def message (self, code, message=''):

        # elimina caracteres expeciales del mensaje para evitar fallos posteriores
        message = Util().clean_string_for_special_characters(message)

        codes = {
            74: 'corrupted file or unsupported attached file type',
            400.0: 'No compatible DataBase',
            400.1: 'Invalid Key for Service',
            400.2: 'No File Attached',
            800: 'Unable to create file on disk'
        }

        # valida si el error hace parte de los errores personalizados
        if code in codes:

             # obtiene el mensaje de error
            message = codes.get(code)

        # retorna el error
        return json.dumps({'error':{'code':code, 'message':message}})