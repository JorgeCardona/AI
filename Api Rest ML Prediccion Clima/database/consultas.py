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
from database.mongo import MongoDB
import ast
import pandas as pd

class AccesoDB(object):

    def guardar_datos(self, lista_dataframes, conexion, coleccion):
    
        try:
            # itera cada dataframe recibido
            for i in range(len(lista_dataframes)):
                # itera cada fila de cada dataframe
                for j in range(len(lista_dataframes[i])):
                    # parsea la respuesta a diccionario
                    dato = ast.literal_eval(lista_dataframes[i].iloc[j].to_json())
                    # guarda el registro en MongoDB
                    coleccion.insert_one(dato)
            
        except Exception as ex:
            mensaje = "No se pudo guardar la de Informacion en la base de datos" , ex
        else:
            mensaje = "Guardado completado Exitosamente"

        finally:
            conexion.close()

        print(mensaje)

        return mensaje


    
    def consultar_informacion(self, consulta =''):
            
        # conecta a la base de datos y trae toda la informacion guardada
        conexion, coleccion, coleccion_clasificador = MongoDB().conexion_mongoDB()

        if(consulta != ''):

            coleccion = coleccion_clasificador

        data_set_completo = pd.DataFrame(list(coleccion.find()))
        conexion.close()

        return data_set_completo