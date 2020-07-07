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
import numpy as np
import pandas as pd
from pandas   import DataFrame
from datetime import datetime
from CONFIGURATION.config import NA_REPLACE_SYMBOL, NA_RECOVERY_VALUE, ID_FOR_DATA_SAVE


class DataFrameProcess(object):

    # guarda los nombres de las columnas en que todos sus datos son iguales, usando el criterio en el cual si hay un solo dato de tipo unique, es que todos son el mismo dato, osea es un valor constante en toda la columna
    delete_columns_repeat_name = lambda columns_name, data_frame : [columns_name[i] for i in range(len(columns_name)) if (len(data_frame[columns_name[i]].unique()) == 1)]

    # obtiene el nombre de las columnas de un dataframe
    def list_columns_name(self, data_frame):

        # retorna el nombre de las columnas de un data frame
        return list(data_frame.columns)


    # crea un lista de las columnas a eliminas    
    def list_columns_to_delete(self, data_frame):

        # obtiene el nombre de las columnas del dataframe
        columns_name = self.list_columns_name(data_frame)

        # lista las columnas que tienen solo uno o dos datoa en la fila 
        delete_columns_list = [[i, list(data_frame[i].unique())] for i in columns_name if len(list(data_frame[i].unique())) < 3]
        delete_columns_list = [i[0]  for i in delete_columns_list if (len(i[1]) == 1 or len(i[1]) == 2 and NA_REPLACE_SYMBOL in i[1])]

        # retorna la lista de las columnas que debes eliminar
        return delete_columns_list

  
    # crea un lista de las filas a eliminas
    def list_rows_to_delete(self, data_frame):        

        # lista las columnas que tienen solo uno o dos datoa en la fila 
        delete_rows_list = [[i, list(data_frame.iloc[i].unique())] for i in range(data_frame.shape[0]) if len(list(data_frame.iloc[i].unique())) <= 2]
        delete_rows_list = [i[0]  for i in delete_rows_list if (len(i[1]) == 1 or len(i[1]) == 2 and NA_REPLACE_SYMBOL in i[1])]

        # retorna la lista de las filas que debes eliminar
        return delete_rows_list[::-1]



    # elimina columnas y filas que sean repetidas o con valores NaN
    def basic_preprocessing_dataframe(self, data_frame:DataFrame):

        # elimina TODAS LAS COLUMNAS donde las celdas TENGAN VALOR NaN o NaT.
        data_frame = data_frame.T.dropna(how='all').T

        # elimina TODAS LAS COLUMNAS repetidas
        data_frame = data_frame.T.drop_duplicates().T

        # elimina TODAS LAS FILAS donde las celdas TENGAN VALOR NaN o NaT.
        data_frame = data_frame.dropna(how='all')

        # elimina TODAS LAS FILAS repetidas
        data_frame = data_frame.drop_duplicates()

        # reemplaza los valores NaN y NaT por el simbolo NA_REPLACE_SYMBOL
        data_frame = data_frame.fillna(NA_REPLACE_SYMBOL)

        # obtiene la lista de columnas que solo tienen un mismo valor o un valor y NaN o NaT
        delete_columns_list = self.list_columns_to_delete(data_frame)

        # elimina TODAS LAS COLUMNAS que solo tienen un mismo valor o un valor y NaN o NaT
        data_frame  = data_frame.drop(delete_columns_list , axis=1)

        # obtiene la lista de filas que solo tienen un mismo valor o un valor y NaN o NaT
        delete_rows_list = self.list_rows_to_delete(data_frame)

        # elimina TODAS LAS FILAS que solo tienen un solo valor
        data_frame = data_frame.drop(delete_rows_list)

        # retorna los valores NA_RECOVERY_VALUE donde tenia los NaN o NaT en el dataframe
        data_frame = data_frame.replace(NA_REPLACE_SYMBOL, NA_RECOVERY_VALUE)

        # se le asigna un identificador de carga a los datos
        data_frame[ID_FOR_DATA_SAVE] = str(datetime.now())

        # guarda el log de las columnas eliminadas del dataframe
        if(len(delete_columns_list) > 0) : logging.info('Columns deleted from dataframe for has unique value : ' + str(delete_columns_list))

        # guarda el log de las filas eliminadas del dataframe
        if(len(delete_rows_list) > 0) : logging.info('Rows deleted from dataframe for has unique value : ' + str(delete_rows_list))

        # retorna el dataframe preprocesado
        return data_frame


    # crea un data frame del archivo leido
    def create_data_frame_from_file(self, file_directory_location : str):
    
        # obtiene la extension del archivo a procesar
        file_type = file_directory_location.split('.')[::-1]

        # valida si es un xslx o un xls y lo lee
        if(file_type[0].upper() == 'XLSX' or file_type[0].lower() == 'XLS'):
            # lee el dataset
            df_original = pd.read_excel(file_directory_location)

        # lee un .csv
        elif(file_type[0].upper() == 'CSV'):
            # lee el dataset  
            df_original = pd.read_csv(file_directory_location)

        # retorna el dataset original leido
        return df_original


    # crea un dataframe con el preproesamiento minimo basico
    def basic_clean_data_frame(self, file_directory_location : str):

        # crea el dataframe del archivo
        data_frame = self.create_data_frame_from_file(file_directory_location)

        # realiza el primer preprocesamiento del dataset
        data_frame = self.basic_preprocessing_dataframe(data_frame)

        # retorna el dataset con el preprocesaamiento basico
        return data_frame

    # crea el dataframe preprocesado
    def load_data_frame_for_save_in_database(self, file_directory_location : str):
    
        # obtiene el dataframe con el preprocesameinto minimo basico
        data_frame = self.basic_clean_data_frame(file_directory_location)

        # retorna el dataframe procesado y listo para gusradr en base de datos
        return data_frame
