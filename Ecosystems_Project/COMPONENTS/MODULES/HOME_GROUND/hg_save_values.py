import pandas as pd
import logging

class HgSaveValues():

    def fn_get_ideal_house(self):
        if(self.vServiceId == 0):
            self.vIdealHouse = HgSaveValues.fn_get_service_rent_ideal_house(self)
        elif(self.vServiceId == 1):
            self.vIdealHouse = HgSaveValues.fn_get_service_investment_ideal_house(self)
        else:
            print("Exception e:")

        return self.vIdealHouse

    #asigna los valores asignados por las funciones en el formulario de analisis                           
    def fn_get_service_rent_ideal_house(self):
        self.ideal_house_from_form = pd.DataFrame([{
                                                    "AREA":self.vArea,                      
                                                    "BATH":self.vBath,
                                                    "STORAGE_ROOM":self.vStorageRoom,
                                                    "OPEN_KITCHEN":self.vOpenKitchen,
                                                    "METRO":self.vMetro,
                                                    "SPORT_FIELDS":self.vSportFields,
                                                    "MALL":self.vMall,
                                                    "DISTANCE":self.vDistance,
                                                    "GYM":self.vGym,
                                                    "BEDROOMS":self.vBedrooms,
                                                    "CHILD_PARK":self.vChildPark,
                                                    "PARKING":self.vParking,
                                                    "VALUE":self.vValue,
                                                    "POOL":self.vPool,
                                                    "PET_FRIENDLY":self.vPetFriendly,
                                                    "SOCIAL_ROOM":self.vSocialRoom,
                                                    "ECO_ROUTES":self.vEcoRoutes,
                                                    "THEATER":self.vTheater,
                                                    "COMMON_AREAS":self.vCommonAreas,
                                                    "WET_AREAS":self.vWetAreas
                                                }])
        
        HgSaveValues.fn_create_log_message(self)
        return self.ideal_house_from_form

    #asigna los valores asignados por las funciones en el formulario de analisis                           
    def fn_get_service_investment_ideal_house(self):
        self.vIdealInversionFromForm = pd.DataFrame([{

                                                }])
        
        HgSaveValues.fn_create_log_message(self)
        return self.vIdealInversionFromForm

    def fn_create_log_message(self):
        self.vConsoleMessage           = " Ideal property wished for " + self.vLifeMoment + " Module and " + self.vService + " Service assigned and saved successfully"
        self.vLogMessage               += self.vConsoleMessage
        logging.info(self.vLogMessage)
        self.vLogMessage               = self.vLogMessage.strip(self.vConsoleMessage)