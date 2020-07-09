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

class Success(object):


    # consulta el mensaje que se retorna al usuario
    def obtain_message(self, code, message = ''):
    
        # obtiene el valor de respuesta de la excepción
        response = self.message(code, message)

        # mensaje para registro en el log
        logging.info('a new success action it was generated {}'.format(response))

        # retorna el mensaje generado
        return response


    # tiene los mensajes creados para la api
    def message (self, code, message=''):

        # elimina caracteres expeciales del mensaje para evitar fallos posteriores
        message = Util().clean_string_for_special_characters(message)

        codes = {
            200.1:"Save file Successfully in database"

        }

        # valida si el error hace parte de los errores personalizados
        if code in codes:

             # obtiene el mensaje de error
            message = codes.get(code)

        # retorna el error
        return json.dumps({'Success':{'code':code, 'message':message}})