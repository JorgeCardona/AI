import datetime
from datetime import timedelta
import math
import logging

class HgVariables():

    def fn_get_comun_data(self):
        
        self.vName                          = self.form["datosPersonales"]["nombres"]
        self.vLastName                      = self.form["datosPersonales"]["apellidos"]
        self.vIdentificationType            = self.form["datosPersonales"]["tipoIdentificacion"]
        self.vIdentificationId              = self.form["datosPersonales"]["numeroIdentificacion"]
        self.vFinalcialIncome               = self.form["datosPersonales"]["ingresosFinancieros"]
        self.vIndebtednessCapacity          = self.form["datosPersonales"]["capacidadEndeudamiento"]
        self.vGender                        = self.form["datosPersonales"]["genero"] 
        self.vAge                           = self.form["datosPersonales"]["edad"]         
        self.vBirthDay                      = self.form["datosPersonales"]["fechaNacimiento"]["dia"]
        self.vBirthMonth                    = self.form["datosPersonales"]["fechaNacimiento"]["mes"]
        self.vBirthYear                     = self.form["datosPersonales"]["fechaNacimiento"]["anyo"]
        self.vActualDate                    = datetime.date.today()
        self.vBirthYearMonthDay             = datetime.date(self.vBirthYear, self.vBirthMonth, self.vBirthDay)
        self.vActualAge                     = math.floor(((self.vActualDate - self.vBirthYearMonthDay) / timedelta(days=1)) / 365.25 )

        return None
    
    def fn_get_data_value_for_habitat(self):
        if(self.vServiceId == 0):
            self.vAssignedDataValues = HgVariables.fn_get_comun_data(self) , HgVariables.fn_get_service_rent_values(self)
        elif(self.vServiceId == 1):
            self.vAssignedDataValues = HgVariables.fn_get_comun_data(self) , HgVariables.fn_get_service_investment_values(self)
        else:
            print("Exception e:")

        return self.vAssignedVariables

    def fn_get_service_rent_values(self):

        self.vOcupationType                 = self.form["datosPersonales"]["ocupacion"]["tipoOcupacion"]
        self.vCountryId                     = self.form["datosPersonales"]["ocupacion"]["idPais"]
        self.vEstateId                      = self.form["datosPersonales"]["ocupacion"]["idDepartamento"]
        self.vCityId                        = self.form["datosPersonales"]["ocupacion"]["idCiudad"]
        self.vNeighborhoodId                = self.form["datosPersonales"]["ocupacion"]["idBarrio"]
        self.vLatitudeCoordinateJob         = self.form["datosPersonales"]["ocupacion"]["coordenadasTrabajo"][0]["latitud"]
        self.vLongitudeCoordinateJob        = self.form["datosPersonales"]["ocupacion"]["coordenadasTrabajo"][0]["longitud"]
        self.vLatitudeCoordinateCollege     = self.form["datosPersonales"]["ocupacion"]["coordenadasEstudio"][0]["latitud"]
        self.vLongitudeCoordinateCollege    = self.form["datosPersonales"]["ocupacion"]["coordenadasEstudio"][0]["longitud"]
        
        self.vTransportType                 = self.form["preguntasHabitar"]["modoTransporte"][0]["transporte"]
        self.vIdTransportType               = self.form["preguntasHabitar"]["modoTransporte"][0]["idTransporte"]
        self.vSharedCosts                   = self.form["preguntasHabitar"]["compartirGastos"]
        self.vSharedCostsAmount             = self.form["preguntasHabitar"]["cantidadCompartirGastos"]
    
        HgVariables.fn_create_log_message(self)
        return None

    def fn_get_service_investment_values(self):

        self.vZone                      = self.form["preguntasInvertir"]["preguntas"][0]["respuestasRq"][0]["elegirZona"]
        self.vTotalZones                = self.form["preguntasInvertir"]["preguntas"][0]["respuestasRq"][0]["zona"][0]["totalZonas"]
        self.vMaximunAvailableZones     = 3 # por ahora se pueden elegir maximo 3 zonas en el formulario
        self.vCountryId                 = self.form["datosPersonales"]["ocupacion"]["idPais"]
        self.vInversionEstateId         = []
        self.vInversionCityId           = []
        self.vInversionNeighborhoodId   = []

        self.vInversionUserValueMinimun = self.form["preguntasInvertir"]["invertirDesde"]
        self.vInversionUserValueMaximun = self.form["preguntasInvertir"]["invertirHasta"]

        self.vInversionRangeMinimun     = self.form["preguntasInvertir"]["inversionMinima"]
        self.vInversionRangeMaximun     = self.form["preguntasInvertir"]["inversionMaxima"]
        self.vMeanReferenceValue        = (self.vInversionRangeMaximun  - self.vInversionRangeMinimun) / 2

        self.vInversionSlowRecovery     = 0
        self.vInversionFastRecovery     = 0
        self.vInversionExpensive        = 0
        
        if(self.vTotalZones > 0):
            if(self.vTotalZones  > self.vMaximunAvailableZones):
                self.vTotalZones = self.vMaximunAvailableZones
            HgVariables.fn_get_location_id(self,self.vTotalZones)

        return None
    
    def fn_get_location_id(self,vTotalZones):

        for i in range(vTotalZones):
            self.vValues                    = i + 1
            self.vData                      = "zona" + str(self.vValues)

            #itera cada una de las zonas disponibles y obtiene su valor en esa posicion
            self.vChainEstateId             = self.form["preguntasInvertir"]["preguntas"][0]["respuestasRq"][0]["zona"][0][self.vData]["idDepartamento"]
            self.vChainCityId               = self.form["preguntasInvertir"]["preguntas"][0]["respuestasRq"][0]["zona"][0][self.vData]["idCiudad"]
            self.vChainNeighborhoodId       = self.form["preguntasInvertir"]["preguntas"][0]["respuestasRq"][0]["zona"][0][self.vData]["idBarrio"]

            #captura los elementos y los guarda en un vector como texto
            self.vInversionEstateId        += str(self.vChainEstateId)
            self.vInversionCityId          += str(self.vChainCityId)
            self.vInversionNeighborhoodId  += str(self.vChainNeighborhoodId)

        #convierte los elementos del vector en enteros
        self.vInversionEstateId             = [int(i) for i in self.vInversionEstateId]
        self.vInversionCityId               = [int(i) for i in self.vInversionCityId]
        self.vInversionNeighborhoodId       = [int(i) for i in self.vInversionNeighborhoodId]

        return None

    def fn_create_log_message(self):
        self.vConsoleMessage           = " Data for form in " + self.vLifeMoment + " Module and " + self.vService + " Service Captured and assigned successfully"
        self.vLogMessage               += self.vConsoleMessage
        logging.info(self.vLogMessage)
        self.vLogMessage               = self.vLogMessage.strip(self.vConsoleMessage)