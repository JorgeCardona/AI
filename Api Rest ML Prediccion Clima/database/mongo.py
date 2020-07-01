############################################################################################
################################ Descripcion################################################
__author__ = "Jorge Cardona"
__copyright__ = "Copyright 2020, The Cogent Project"
__credits__ = "Jorge Cardona"
__license__ = "MIT"
__version__ = "1.0"
__maintainer__ = "Jorge cardona "
__email__ = "https://github.com/JorgeCardona"
__status__ = "Production"
###############################################################################################
###############################################################################################
from pymongo import MongoClient
from configuraciones.config import URL_CONEXION
from configuraciones.config import NOMBRE_BD
from configuraciones.config import NOMBRE_COLECCION
from configuraciones.config import NOMBRE_COLECCION_CLASIFICADOR

class MongoDB(object):

    def conexion_mongoDB(self):

        conexion    = MongoClient(URL_CONEXION)
        basededatos = conexion[NOMBRE_BD]
        coleccion   = basededatos[NOMBRE_COLECCION]
        colleccion_prediccion = basededatos[NOMBRE_COLECCION_CLASIFICADOR]
            
        # retorna el objeto de conexion
        return conexion, coleccion, colleccion_prediccion

