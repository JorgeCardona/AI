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
import pathlib
import logging

# keys del request para guradar la informacion
KEYS_FROM_REQUEST = ['SERVICE', 'DATABASE-TYPE', 'DATABASE', 'TABLE']

# lee la ruta del directorio del proyecto
ROOT_DIRECTORY = str(os.getcwd()).replace('\\','/')

# asigna los directorios para acceder a los recursos de la API
RESOURCES_DIRECTORY      = ROOT_DIRECTORY      + '/RESOURCES'
LOG_FILE_NAME            = 'logging.log'
LOG_FOLDER               = RESOURCES_DIRECTORY + '/LOG'
LOG_DIRECTORY            = LOG_FOLDER          + '/' + LOG_FILE_NAME
UPLOAD_FILES_DIRECTORY   = RESOURCES_DIRECTORY + '/FILES'
FILE_LOCATION_LABEL      = 'FILE_LOCATION'
CONFIGURATION_DIRECTORY  = RESOURCES_DIRECTORY + '/CONFIGURATION'
TRAINED_MODELS_DIRECTORY = RESOURCES_DIRECTORY + '/TRAINED_MACHINE_LEARNING_MODELS'
MAIN_DIRECTORIES         = [LOG_FOLDER, UPLOAD_FILES_DIRECTORY, TRAINED_MODELS_DIRECTORY]
URL_COMPLEMENTARY        = '_URL_CONNECTION'
FILE_DIRECTORY_LOCATION  = 'FILE_DIRECTORY_LOCATION'

# configuraciones flash 
logging.basicConfig(filename=LOG_DIRECTORY, level=logging.INFO)
FLASK_PORT = 5000
FLASK_DEBUG = True
FLASH_ENVIROMENT = 'development'

# archivos soportados y tamano maximo permitido
MAX_CONTENT_LENGTH      = 16 * 1024 * 1024
ALLOWED_SAVE_SERVICES   = ['SUPERVISED','NLP','COMPUTER_VISION','TEXT_ANALYTICS','UNSUPERVISED']
ALLOWED_EXTENTION_FILES = set(['CSV','XLS','XLSX'])

# credenciales de la base de datos
DATA_DATABASE_NAME           = 'db'

#MONGODB
MONGODB_HOST                  = 'localhost'
MONGODB_PORT                  = '27017'
MONGODB_USER                  = ''
MONGODB_PASS                  = ''
MONGODB_URL_CONNECTION        = 'mongodb://' + MONGODB_HOST +':' + MONGODB_PORT +'/'

# MYSQL
MYSQL_CHARSET               = '?charset=utf8mb4&binary_prefix=true'
MYSQL_HOST                  = 'localhost'
MYSQL_PORT                  = '3306'
MYSQL_USER                  = 'root'
MYSQL_PASS                  = '12345678'
MYSQL_TABLE                 = ''
MYSQL_URL_CONNECTION        = 'mysql+pymysql://' + MYSQL_USER + ':' + MYSQL_PASS  + '@' +  MYSQL_HOST + ':' + MYSQL_PORT +'/' + DATA_DATABASE_NAME + MYSQL_CHARSET

# POSTGRES
POSTGRES_HOST                  = 'localhost'
POSTGRES_PORT                  = '5432'
POSTGRES_USER                  = 'postgres'
POSTGRES_PASS                  = 'postgres'
POSTGRES_TABLE                 = ''
POSTGRES_URL_CONNECTION        = 'postgresql://' + POSTGRES_USER + ':' + POSTGRES_PASS  + '@' +  POSTGRES_HOST + ':' + POSTGRES_PORT +'/' + DATA_DATABASE_NAME

# dataframe
NA_REPLACE_SYMBOL = '^-^'
NA_RECOVERY_VALUE = ''
ID_FOR_DATA_SAVE  = 'ID_FILE_DIRECTORY_LOCATION'


# Tablas y colecciones para persistencia
ALL_DATA     = 'clima'
PREDICTION   = 'datos_prediccion'
CLASIFICATOR = 'mejor_clasificador'


# database types
DATA_BASES_TYPES = ['MONGODB','MYSQL','POSTGRES']

DEFAULT_SERVICE              = ALLOWED_SAVE_SERVICES[0]
DEFAULT_DATABASE_TYPE        = DATA_BASES_TYPES[0]
DEFAULT_DATABASE_NAME        = DATA_DATABASE_NAME
DEFAULT_TABLE_NAME           = ALL_DATA
DATA_BASE_DEFAULT_VALUES     = [DEFAULT_SERVICE, DEFAULT_DATABASE_TYPE, DEFAULT_DATABASE_NAME, DEFAULT_TABLE_NAME]


# tratamiento de data para entrenamiento

TRAINING_PORCENTAGE = 0.3
BINARIZATION_LIMIT  = 24.5
TARGET_COLUMN       = ''
BINARIZATED_TARGET_COLUMN = ''