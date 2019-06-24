__init__.py                                     archivo de configuracion necesario para usar clases desde otros lados, debe estar en cada carpeta para ser usado
start.py                                        lanza la aplicacion

COMPONENTS                                      contiene todos los paquetes del aplicativo
COMPONENTS.ecosystem                            clase principal de la aplicacion que usa los recursos de los modulos que sean solicitados

COMPONENTS.ANALITICS                            directorio donde se encuentran los modulos procesadores de datos para el uso de las recomendaciones
COMPONENTS.CONTROLLER.routing                   asigna el ID de la consulta que se va a realizar segun el negocio solicitado
COMPONENTS.CONTROLLER.busisness_variables       carga toda la informacion referente a las variables de entrada segun el negocio solicitado

COMPONENTS.MODULES.HOME_GROUND                  contiene las clases del ecosistema habitat
hg_required_variables                           declara las variables necesarias que van a llegar desde el formulario y las inicializa
hg_model_data                                   declara e inicializa las caracteristicas que existen o no segun la preferencia del usuario y de las columnas del dataframe
hg_capture_variables_values_of_form             captura los valores que vienen del formulario
hg_question                                     recalcula los valores de las variables basado en la informacion que llego del formulario
hg_save_values_to_dataframe                     guarda la informacion colectada y actualizada en un dataframe de la casa ideal solicitada por el usuario y recalculada segun los gustos
