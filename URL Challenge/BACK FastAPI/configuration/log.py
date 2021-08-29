import logging as log_api
import os

ROOT_DIRECTORY = str(os.getcwd()).replace('\\','/')
LOG_FILE_NAME  = 'logging.log'
LOG_FOLDER = ROOT_DIRECTORY + '/log'
LOG_DIRECTORY = LOG_FOLDER  + '/' + LOG_FILE_NAME

format_log = '%(asctime)s | %(levelname)s | message = %(message)s | %(name)s | line %(lineno)d | package %(filename)s | %(funcName)s'
log_api.basicConfig(filename=LOG_DIRECTORY,
                    level=log_api.DEBUG,
                    format= format_log)