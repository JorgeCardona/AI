import re

class Util(object):


    def clean_string_for_special_characters(self, name :str) -> str:    

        # elimina caracteres expeciales del mensaje para evitar fallos posteriores
        return re.sub('[^A-Za-z0-9\.\s]+','',str(name))


