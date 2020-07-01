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
import os
from configuraciones.config import DIRECTORIO_MODELOS
from configuraciones.config import DIRECTORIO_ARCHIVOS
from modelos.modelos_ml import ModelosML
from database.mongo import MongoDB
import pandas as pd

from database.consultas import AccesoDB
from datetime import datetime
from sklearn.model_selection import train_test_split
from sklearn.externals import joblib
from configuraciones.config import CLASIFICADORES
import numpy as np

class Servicios(object):

    
    def predecir(self, directorio_archivo, nombre_columna_binarizada, columna_decision, limite_binarizacion, coleccion_clasificador):

        # carga la informacion de la base de datos del mejor clasificador, los componentes principales, mejor modelo entrenado
        datos_en_db = AccesoDB().consultar_informacion('mejorclasificador')        
        tamano_lista_clasificadores = list(datos_en_db.shape)[0]
        ultimo_mejor_clasificador = datos_en_db['mejor_clasificador'][tamano_lista_clasificadores-1]
        # lee el nombre del clasificador con que se realizara la prediccion
        nombre_clasificador_entrenado = datos_en_db['nombre_clasificador_entrenado'][tamano_lista_clasificadores-1]
        # obtirnr los componentes principales del ultimo mejor clasificador
        listado_componentes_principales = datos_en_db['lista_nombre_componentes_principales'][tamano_lista_clasificadores-1]
        # obtengo solo las columnas que no son objetivo
        listado_nombres_columnas = [i for i in listado_componentes_principales if i != nombre_columna_binarizada]

        # fue el dataset que se quiere validar para prediccion
        data_original = self.crear_dataframe_original (directorio_archivo, nombre_columna_binarizada, columna_decision, limite_binarizacion)
        data_subida_para_prediccion = data_original.filter(listado_nombres_columnas, axis=1)
        # realiza pretratamiento de data, no acepta columnas con datos vacios
        data_subida_para_prediccion = data_subida_para_prediccion.dropna()
        
        # se ubica en el directorio donde estan los clasificadores entrenados
        os.chdir(DIRECTORIO_MODELOS)

        # carga el mejor clasificador para predecir la data
        clasificador_entrenado = joblib.load(nombre_clasificador_entrenado)

        # retorna al directorio principal del proyecto
        os.chdir(DIRECTORIO_ARCHIVOS)

        # obtiene los datos predecidos
        datos = clasificador_entrenado.predict(data_subida_para_prediccion)

        # reemplazo los valores numericos por etiquetas para la predccion
        datos = list(map(lambda x: 'BAJA HUMEDAD' if x==0 else 'ALTA HUMEDAD', datos))
        
        # crea el dataframe que se va a guardar con todo lo que se uso para la prediccion
        lista_dataframes = [pd.DataFrame({'Clasificador': ultimo_mejor_clasificador,'listado_componentes_principales':[listado_componentes_principales],'nombre_clasificador_entrenado_usado':nombre_clasificador_entrenado, 'Prediccion' :[datos], 'Cantidad de Datos Cargados':[{'Filas' : data_original.shape[0] - 1, 'Columnas' : data_original.shape[1]}], 'data_procesada_para_prediccion':[data_subida_para_prediccion], 'fecha':[str(datetime.now())]})]
        
        # guarda la informacion en base de datos del mejor clasificador en este entrenamiento
        conexion, _, _, coleccion_prediccion = MongoDB().conexion_mongoDB()
        datos_en_BD = AccesoDB().guardar_datos(lista_dataframes, conexion, coleccion_prediccion)

        print('Prediccion API ', datos)

        # retorna la prediccion del dataset
        return datos
       

    def recuperar_informacion_dataset(self, columna_decision, nombre_columna_binarizada):

        # guarda los datasets recuperados y filtrados
        listado_datasets = []

        # recupera la informacion del dataset y elimina la columna propia
        data_set_completo_original = AccesoDB().consultar_informacion()
        data_set_completo_clon = AccesoDB().consultar_informacion().drop(columns=['_id', "Identificador",'Procesamiento', columna_decision])
        # obtiene el listado de los componentes principales
        lista_componentes_principales   = ModelosML().analisis_pca(data_set_completo_clon, nombre_columna_binarizada)
        lista_componentes_principales.append(nombre_columna_binarizada)

        dataset_eliminacion = data_set_completo_original[data_set_completo_original['Procesamiento'] == 'ELIMINACION']
        dataset_eliminacion = dataset_eliminacion.filter(lista_componentes_principales, axis=1)
        dataset_promedio    = data_set_completo_original[data_set_completo_original['Procesamiento'] == 'PROMEDIO']
        dataset_promedio    = dataset_promedio.filter(lista_componentes_principales, axis=1)
        data_set_completo   = data_set_completo_original.filter(lista_componentes_principales, axis=1)

        # adiciona los dataset recuperados a la lista
        listado_datasets.append(data_set_completo)
        listado_datasets.append(dataset_eliminacion)
        listado_datasets.append(dataset_promedio)

        return listado_datasets, list(data_set_completo.columns)


    def fraccionar_datos_de_entrenamiento (self, listado_datasets, listado_nombres_columnas, nombre_columna_binarizada , split):
        
        columna_objetivo = nombre_columna_binarizada
        columnas_entrenamiento = listado_nombres_columnas

        lista_datos_entrenamiento = []

        
        for i in range(len(listado_datasets)):

            df_objetivo = pd.DataFrame()
            df_objetivo[columna_objetivo] = listado_datasets[i][columna_objetivo].values
            df_datos = listado_datasets[i].filter(columnas_entrenamiento).drop(columns=[columna_objetivo])

            X_train, X_test, y_train, y_test = train_test_split(df_datos, df_objetivo, test_size=split, random_state=324)

            lista_datos_entrenamiento.append([X_train, X_test, y_train, y_test])

        return lista_datos_entrenamiento



    def entrenar_modelo(self, columna_decision, nombre_columna_binarizada, split):
        
        # guarda los datasets recuperados y filtrados
        listado_datasets, listado_nombres_columnas = self.recuperar_informacion_dataset(columna_decision, nombre_columna_binarizada)

        datos_para_entrenamiento = self.fraccionar_datos_de_entrenamiento(listado_datasets, listado_nombres_columnas, nombre_columna_binarizada , split)

        lista_resultados = []        
        
        for i in range(len(listado_datasets)):

            for j in range(len(CLASIFICADORES)):

                mc = ModelosML().obtener_mejor_clasificador(CLASIFICADORES[j], datos_para_entrenamiento[i], '')
                lista_resultados.append(mc)

        conexion, _, coleccion_clasificador, _ = MongoDB().conexion_mongoDB()

        nombre_clasificador_entrenado = ''
        mejor_clasificador = ''
        valor              = 0

        for i in range(len(lista_resultados)):
            
            if(lista_resultados[i]['evaluacion_clasificador'] > valor):
                mejor_clasificador = lista_resultados[i]['clasificador']
                nombre_clasificador_entrenado = lista_resultados[i]['nombre_clasificador_entrenado']

        lista_dataframes = [pd.DataFrame( {'Clasificadores Evaluados' :[CLASIFICADORES],'mejor_clasificador':[mejor_clasificador], 'nombre_clasificador_entrenado' : nombre_clasificador_entrenado, 'lista_nombre_componentes_principales':[listado_nombres_columnas],'fecha':[str(datetime.now())]})]

        # guarda la informacion en base de datos del mejor clasificador en este entrenamiento
        datos_en_BD = AccesoDB().guardar_datos(lista_dataframes, conexion, coleccion_clasificador)

        return lista_resultados


    

    def crear_dataframe_original (self, directorio_archivo, nombre_columna_binarizada, columna_decision, limite_binarizacion):

        tipo_archivo = directorio_archivo.split('.')[::-1]

        # lee un xslx o un xls
        if(tipo_archivo[0].lower() == 'xlsx' or tipo_archivo[0].lower() == 'xls'):

            # lee el dataset
            df_original = pd.read_excel(directorio_archivo)

        # lee un .csv
        elif(tipo_archivo[0].lower() == 'csv'):
            
            df_original = pd.read_csv(directorio_archivo)
        else:

            print('Excepcion')

        # lee el dataset     
        df_original = pd.read_excel(directorio_archivo)
        # elimino la columna number que no aportan valor al dataset, dado que es un identificador de registro
        del df_original['number']
        # creo la columna identificador para diferenciar el dataset de los demas y concateno
        # el total de registros que hubo en la carga de datos del dataset
        df_original["Identificador"] =  str(datetime.now()) + '_' + str(len(df_original.index))
        # creo la columna procesamiento para conocer si es la data raw o ya fue procesada
        df_original["Procesamiento"] =  'DATA ORIGINAL'
        
        # binarizo la variable objetivo para procesarla en los modelos de ML        
        df_original[nombre_columna_binarizada] = (df_original[columna_decision] > limite_binarizacion) * 1

        # retorna el dataset orginal binarizado y con las columnas adicionales
        return df_original

    def crear_dataframes_procesados(self, df_original):

        lista_dataframes = []

        # se crea un data frame para promedios sin eliminar datos
        df_promedios = df_original.copy()
        df_promedios.fillna(df_original.mean(), inplace=True)

        # se crea un dataframe y se eliminan las filas con datos vacios
        df_eliminacion = df_original.copy()
        df_eliminacion = df_eliminacion.dropna()

        # asigno el valor de la etiqueta al dataframe
        df_eliminacion["Procesamiento"] =  'ELIMINACION'
        df_promedios["Procesamiento"] =  'PROMEDIO'

        # adiciono los dataframes a una lista
        #lista_dataframes.append(df_original)
        lista_dataframes.append(df_promedios)
        lista_dataframes.append(df_eliminacion)

        # retorna todos los dataframes creados
        return lista_dataframes

    def procesar_informacion (self, directorio_archivo, nombre_columna_binarizada, columna_decision, limite_binarizacion):        
        
        df_original = self.crear_dataframe_original (directorio_archivo, nombre_columna_binarizada, columna_decision, limite_binarizacion)        

        lista_dataframes = self.crear_dataframes_procesados(df_original)

        conexion, coleccion, _, _ = MongoDB().conexion_mongoDB()

        # guarda la informacion en base de datos
        datos_en_BD = AccesoDB().guardar_datos(lista_dataframes, conexion, coleccion)

        return datos_en_BD


