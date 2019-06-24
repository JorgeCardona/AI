#https://docs.python.org/2/howto/logging.html
import time, datetime

from COMPONENTS.CONTROLLER.config_controller  import *
from COMPONENTS.MODULES.HOME_GROUND.config_hg import *
from COMPONENTS.MODEL.config_model            import *


class Ecosystem(BaseException):

    global total
    
    #metodo  contructor de la clase
    def __init__(self, form):
        self.vMomentDefined = [2,3,4,4,5]
        self.vLogDate           = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        self.vLogMessage        = self.vLogDate + ' [DESCRIPTION] -> ' 
        self.vConsoleMessage    = ''
    #contiene los datos del json enviado via POST
        self.form               = form
        self.df_result          = []   # {error}    
        self.revisar            = ""   

        self.main(self.form)   #metodo principal que lanza la aplicacion
                                       
    #lanza todas los metodos necesarios para realizar la recomendacion
    def main(self,form):
               
        pass
        #print("ewuyrweuiyruiewyuriu",form)
        #vInversionEstateId = Routing.fn_get_moment_trace(self, form)  

        
        #BusinessVariables.fn_moment_control(self, self.vLifeMomentId, self.vServiceId)
        #SearchDB.fn_execute_selected_search(self)
        
        #logging.debug(self.vLogMessage)
        #logging.info(self.vLogMessage)
        #logging.warn(self.vLogMessage)
        #logging.error(self.vLogMessage)
        #logging.critical(self.vLogMessage)

        #vrQuery = "SELECT * FROM property WHERE (propertyValue BETWEEN " + str(self.vInversionCapacityMinimun) + " AND " + str(self.vInversionCapacityMaximun) + ") AND propertyEstate = 2 ORDER BY propertyValue ASC LIMIT 10"   
        #if(self.vAssignedSearchId == 1.1):
        #print("gol de colombia",vrQuery)

        #print("departamentos",self.vInversionEstateId)
        #print("ciudades",self.vInversionCityId)
        #print("barrio",self.vInversionNeighborhoodId)
        #print("agrupados ids ",self.vInversionCapacityMinimun)
        #print("??????????????????",self.vMeanReferenceValue)

        #print("=========", self.vLogMessage.strip(self.vConsoleMessage))
        #print("wwwwwwwwwwwwwwwwww",self.vActualAge)
        #return vInversionEstateId
