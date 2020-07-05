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
from CONFIGURATION.config import ALLOWED_SAVE_SERVICES, ALLOWED_EXTENTION_FILES, UPLOAD_FILES_DIRECTORY
from VALIDATIONS_AND_EXCEPTIONS.exceptions import Exceptions
from UTILS.save_files import Save
from flask import request

class Validator(object):   

    def validate_save_file(self, request:request):

        # obtiene el nombre de la key enviaada en la peticion
        key_name   = self.allowed_services(list(request.files))
 
        # valida que exista una key de servicio permitida 
        if(not key_name):
            
            # lanza la excepcion debido al fallo
            Exceptions().validation_exception(400.0)

        # obtiene la informacion del archivo adjuntado
        file = request.files[key_name]

        # obtiene el nombre del fichero adjuntado
        file_name  = file.filename if file else file

        # valida que se adjunto un archivo
        if (not file_name):

            # lanza la excepcion debido al fallo
            Exceptions().validation_exception(400.1)

        # valida si la extension del archivo es valida para la carga de datos
        file_allowed = self.allowed_file(file_name)
        
        # valida que la extension del archivo cargado sea compatible con la plataforma
        if (not file_allowed):

            # lanza la excepcion debido al fallo
            Exceptions().validation_exception(74)

        # guarda el archivo caragdo en la carpeta UPLOAD_FILES_DIRECTORY
        Save().save_upload_file(file, file_name, UPLOAD_FILES_DIRECTORY)

    # validqa que el nombre de la key enviada en la solicitud corresponda a ALLOWED_SAVE_SERVICES para guardar los datos
    def allowed_services(self, filename: list):

        # retorna el nombre de la key que se le puso al archivo al realizar  la peticion, si la key es valida
        return filename[0] if filename[0].upper() in ALLOWED_SAVE_SERVICES else ['']

    # valida que el archivo cuente con las extensiones permitidas
    def allowed_file(self, filename: str):
	    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENTION_FILES