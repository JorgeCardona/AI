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

import re

class Util(object):


    def clean_string_for_special_characters(self, name :str) -> str:    

        # elimina caracteres expeciales del mensaje para evitar fallos posteriores
        return re.sub('[^A-Za-z0-9\.\s]+','',str(name))


