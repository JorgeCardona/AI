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
from CONFIGURATION.config import TRAINING_PORCENTAGE, BINARIZATION_LIMIT, TARGET_COLUMN, BINARIZATED_TARGET_COLUMN
from UTILS.save_files import Save
from VALIDATIONS_AND_EXCEPTIONS.validations import Validator
from SERVICES.services import Services

# configuracion de flask
app = Flask('MachineLearnig Suite - Jorge Cardona')
app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FILES_DIRECTORY
app.config['MAX_CONTENT_LENGTH'] = MAX_CONTENT_LENGTH
app.debug = FLASK_DEBUG


@app.route('/save_data', methods=['POST'])
def save_data():
	# crea los directorios para almacenar los archivos 
	message = Services().save_data(request)
		
	# retorna el resultado del procesamiento
	return message

	
if __name__ == "__main__":    	
	app.run(port=FLASK_PORT)
	
	