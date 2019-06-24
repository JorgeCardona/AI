import logging

class HgRequiredVariables():

        def fn_get_assigned_variables_for_habitat(self):
            if(self.vServiceId == 0):
                self.vAssignedVariables = HgRequiredVariables.fn_assign_rent_variables(self)
            elif(self.vServiceId == 1):
                self.vAssignedVariables = HgRequiredVariables.fn_assign_investment_variables(self)
            else:
                print("Exception e:")

            return self.vAssignedVariables

        def fn_assign_rent_variables(self):
            self.vArea               = 0
            self.vBuiltTime          = 0
            self.vBath               = 0
            self.vBalcony            = 0
            self.vSportFields        = 0
            self.vMetro              = 0
            self.vMall               = 0
            self.vStorageRoom        = 0
            self.vOpenKitchen        = 0
            self.vDistance           = 0
            self.vStratum            = 0 
            self.vGym                = 0
            self.vBedrooms           = 1        
            self.vParking            = 0
            self.vChildPark          = 0
            self.vPetFriendly        = 0
            self.vPool               = 0
            self.vReception          = 0 
            self.vValue              = 0
            self.vSocialRoom         = 0
            self.vEcoRoutes          = 0
            self.vTheater            = 0
            self.vCommonAreas        = 0      
            self.vWetAreas           = 0
            self.vIdealHouseFromForm = ''

            HgRequiredVariables.fn_create_log_message(self)
            return None        

        def fn_assign_investment_variables(self):
            
            self.vZone                     = 0
            self.vInversionEstateId        = []
            self.vInversionCityId          = []
            self.vInversionNeighborhoodId  = []

            self.vInversionCapacityMinimun = 0
            self.vInversionCapacityMaximun = 0

            self.vInversionSlowRecovery    = 0
            self.vInversionFastRecovery    = 0
            self.vInversionExpensive       = 0

            self.vMetro                    = 0
            self.vMall                     = 0
            self.vStorageRoom              = 0
            self.vOpenKitchen              = 0
            self.vStratum                  = 0 
            self.vGym                      = 0      
            self.vParking                  = 0
            self.vChildPark                = 0
            self.vPetFriendly              = 0
            self.vPool                     = 0
            self.vReception                = 0 
            self.vValue                    = 0
            self.vSocialRoom               = 0
            self.vEcoRoutes                = 0
            self.vTheater                  = 0
            self.vCommonAreas              = 0      
            self.vWetAreas                 = 0
            self.vBuiltTime                = 0
            self.vStratum                  = 0 
            
            self.vIdealInversionFromForm = ''

            HgRequiredVariables.fn_create_log_message(self)
            return None 
        
        def fn_create_log_message(self):
            self.vConsoleMessage           = " initials variables for " + self.vLifeMoment + " Module and " + self.vService + " Service load and assigned successfully"
            self.vLogMessage               += self.vConsoleMessage
            logging.info(self.vLogMessage)
            self.vLogMessage               = self.vLogMessage.strip(self.vConsoleMessage)