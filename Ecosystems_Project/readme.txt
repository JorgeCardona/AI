__init__.py                     archivo de configuracion necesario para usar clases desde otros lados, debe estar en cada carpeta para ser usado
start.py                        lanza la aplicacion

DATABASE.db_config              contiene la configuracion de las credenciales de la base de datos
DATABASE.db_search              accede a la base de datos y realiza las consultas solicitadas

MODULES.business                identifica el tipo de negocio en el ecosistema y asigna el servicio que se va a recomendar  
MODULES.data_form               captura la informacion basica del usario apartir de los datos del json que viene del formulario web
MODULES.ecosystem               clase principal de la aplicacion donde se trae todos los recursos de otras clases
MODULES.variable_case           carga las variables necesarias para el tipo de servicio que selecciono el usuario para recomendacion

MODULES.home_request            guarda toda la informacion de la casa ideal que el cliente solicito en el formulario web 
MODULES.initial_data            asigna los valores iniciales propuestos para las variables antes de procesar los datos del formulario 
MODULES.inversion_question      captura la informacion de las preguntas relacionadas con la parte de compra de inmueble 
MODULES.model_function          contiene los metodosque procesaran la informacion del formulario y del dataframe
MODULES.rent_question           captura la informacion de las preguntas relacionadas con la parte de renta de inmueble 
MODULES.service_type            selecciona cual servicio se va a usar

SERVER.server_start             inicia el servidor flask e inicia la aplicacion

FILES.*                         carpeta con archivos para pruebas de concepto