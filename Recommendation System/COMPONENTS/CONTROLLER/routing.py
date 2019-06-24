import math
import logging

"""
ECOSYSTEMA
ID 	 MOMENTO DE VIDA
0 	 Nacimiento
1 	 Educación  
2 	 Ahorro e Inversión
3 	 Movilidad
4 	 Vida en Pareja
5 	 Hábitat – Inmobiliaria
6 	 Disfrute
7 	 Momentos Dificiles
8 	 Retiro

SERVICIOS
ID   TIPO
0    Renta
1    Inversion
"""

class Routing():
    
    def fn_get_moment_trace(self, x):

        #Routing.fn_get_selected_moment_and_service_trace(self, form)
        #Routing.fn_validate_lifemoment_and_service(self)
        #Routing.fn_assign_moment_selected_module(self)

        print("sssssssssssssssss", type(x))
        #return  "pppppppppppppppppppppppp" ,x

    def fn_get_selected_moment_and_service_trace(self, form):

        vMomentDefined   = [
                                 "Birth",
                                 "Education",
                                 "Savings and Investment",
                                 "Mobility",
                                 "Life in Couple",
                                 "Habitat",
                                 "Leisure",
                                 "Difficult Moments",
                                 "Retirement"
                                ]
        
        vServiceDefined  = [
                                 "Rent",
                                 "Investment"
                                ] 

        #Routing.fn_validate_lifemoment_and_service(self, vMomentDefined, vServiceDefined, form)

        return form['ecosistema'][1]

    def fn_validate_lifemoment_and_service(self, vMomentDefined, vServiceDefined, form):
        
        vLifeMomentId    = form["ecosistema"]
        vServiceId       = form["ecosistema"]

        if(vLifeMomentId > len(vMomentDefined)):
            return None            
        
        elif(vServiceId > len(vServiceDefined)):
            vLifeMoment  = vMomentDefined[vLifeMomentId]

        else:
            vLifeMoment  = vMomentDefined[vLifeMomentId]
            vService     = vServiceDefined[vServiceId]            

        return None

    def fn_assign_moment_selected_module(self):

        #join input values for sql search assign
        self.vAssignedSearchId = str(self.vLifeMomentId), '.' , str(self.vServiceId)
        self.vAssignedSearchId =  ''.join(self.vAssignedSearchId)

        #home ground service module
        if(self.vLifeMomentId == 5):           
            if(self.vServiceId == 0):
                self.vAssignedSearchId = float(self.vAssignedSearchId)
                Routing.fn_success_moment_selected_module(self,1)
                
            elif(self.vServiceId == 1):
                self.vAssignedSearchId = float(self.vAssignedSearchId)
                Routing.fn_success_moment_selected_module(self,1)

            else:
                Routing.fn_error_moment_selected_module(self,1)
                
        #movility service module
        elif(self.vLifeMomentId == 3):             
            if(self.vServiceId == 0):
                self.vAssignedSearchId = float(self.vAssignedSearchId)
                Routing.fn_success_moment_selected_module(self,1)

            elif(self.vServiceId == 1):
                self.vAssignedSearchId = float(self.vAssignedSearchId)
                Routing.fn_success_moment_selected_module(self,1)

            else:
                Routing.fn_error_moment_selected_module(self,1)

        #no found module in ecosystem
        else:
            Routing.fn_error_moment_selected_module(self,2)

        return None

    def fn_error_business_moment_module(self, idLog):

        if(idLog == 1):
            pass
            self.vConsoleMessage                = "Ecosystem error 404 - the Service ID : " + str(self.vServiceId) + " does not exist for the given service " + str(self.vLifeMoment) + " Module"
            self.vLogMessage                    += self.vConsoleMessage
            logging.error(self.vLogMessage)
            self.vLogMessage                    = self.vLogMessage.strip(self.vConsoleMessage)
            return print(self.vConsoleMessage)

        if(idLog == 2):
            self.vConsoleMessage                = "Ecosystem error 404 - the Life Moment ID : " + str(self.vLifeMomentId) + " as a invalid Life Moment Module in Ecosystem"
            self.vLogMessage                    += self.vConsoleMessage 
            logging.error(self.vLogMessage)
            self.vLogMessage                    = self.vLogMessage.strip(self.vConsoleMessage)
            return print(self.vConsoleMessage)

        return None

    def fn_success_moment_selected_module(self, idLog):

        if(idLog == 1):
            self.vConsoleMessage                = "Access to Ecosystem in the " + str(self.vService) + " Service, from " + str(self.vLifeMoment) + " Life Moment Module"
            self.vLogMessage                    += self.vConsoleMessage
            logging.info(self.vLogMessage)      
            self.vLogMessage                    = self.vLogMessage.strip(self.vConsoleMessage)
            return print(self.vConsoleMessage)
            
        return None