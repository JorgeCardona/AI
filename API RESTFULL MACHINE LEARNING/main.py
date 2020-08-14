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
import urllib.request
import numpy as np
import json

from flask import Flask, jsonify, request, redirect

from CONFIGURATION.config import FLASK_PORT, FLASK_DEBUG, UPLOAD_FILES_DIRECTORY, MAX_CONTENT_LENGTH
from UTILS.save_files import Save
from VALIDATIONS_AND_EXCEPTIONS.validations import Validator
from SERVICES.services import Services
from UTILS.tables_collections import StoreData

# configuracion de flask
app = Flask('MachineLearnig Suite - Jorge Cardona')
app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FILES_DIRECTORY
app.config['MAX_CONTENT_LENGTH'] = MAX_CONTENT_LENGTH
app.debug = FLASK_DEBUG


@app.route('/save_data', methods=['POST'])
def save_data():
    	
	# captura el tipo de base de datos en la que se quiere persistir la informacion
	database_name = request.args.get('db')

	# define en cual tabla se quiere guardar
	table = StoreData.SAVE_ALL_DATA

	# crea los directorios para almacenar los archivos 
	message = Services().save_data(request, database_name, table)
		
	# retorna el resultado del procesamiento
	return message

# inicializa la aplicacion	
if __name__ == "__main__":    	
	app.run(port=FLASK_PORT)