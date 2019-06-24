"""
CD: Fecha de creación (Creation Date)
LUD: Fecha última modificación (Last Updated Date)
V0.1: Versión script
#-------------------------------------------------------------------------
# Author: Name LastName #CD: 02/02/2018 #LUD:
# Description: Description of the script
#
# Run: python scriptName.py arg1 arg2
#
# v0.1
# Modification:
# Description:
#-------------------------------------------------------------------------
"""

#IMPORTA EL SCRIPT QUE INICIA LA APLICACION PARA RECIBIR PETICIONES
from COMPONENTS.SERVER.api_server_start import *

#inicia el servidor del aplicativo
if __name__ == "__main__":
    ecosystem_app.run(debug=True)