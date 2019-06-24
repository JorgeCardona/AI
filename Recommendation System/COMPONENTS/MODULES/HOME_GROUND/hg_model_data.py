import pandas as pd
import logging

class HgModelData():

    def fn_get_assigned_data_model_for_habitat(self):
        if(self.vServiceId == 0):
            self.vAssignedDataModel = HgModelData.fn_assign_service_rent_data_model(self)
        elif(self.vServiceId == 1):
            self.vAssignedDataModel = HgModelData.fn_assign_service_investment_data_model(self)
        else:
            print("Exception e:")
        
        return self.vAssignedDataModel

    def fn_assign_service_rent_data_model(self):
        self.service_rent_data_model_weight =  pd.DataFrame([{
                                                                "AREA":0,
                                                                "BUILT_TIME":0,                        
                                                                "BATH":0,
                                                                "BALCONY":0,
                                                                "SPORT_FIELDS":0,
                                                                "MALL":0,
                                                                "STORAGE_ROOM":0,
                                                                "OPEN_KITCHEN":0,                                        
                                                                "METRO":0, 
                                                                "DISTANCE":0.15,                                       
                                                                "ESTRATUM":0, 
                                                                "GYM":0,
                                                                "BEDROOMS":0.15,                                                                                                                           
                                                                "PARKING":0,
                                                                "CHILD_PARK":0,
                                                                "PET_FRIENDLY":0,
                                                                "POOL":0,
                                                                "RECEPTION":0,
                                                                "VALUE":0.5,                                        
                                                                "SOCIAL_ROOM":0,
                                                                "ECO_ROUTES":0,
                                                                "THEATER":0,
                                                                "COMMON_AREAS":0,
                                                                "WET_AREAS":0                                                                                                                                                           
                                                            }])

        self.features                = [{
                                          "BALCONY",
                                          "SPORT_FIELDS",
                                          "MALL",
                                          "STORAGE_ROOM",
                                          "OPEN_KITCHEN",                                        
                                          "METRO",                                        
                                          "ESTRATUM", 
                                          "GYM",
                                          "POOL",                                       
                                          "RECEPTION",
                                          "PARKING",
                                          "CHILD_PARK",
                                          "PET_FRIENDLY",                                        
                                          "SOCIAL_ROOM",
                                          "ECO_ROUTES",
                                          "THEATER",
                                          "COMMON_AREAS",
                                          "WET_AREAS"
                                        }]

        HgModelData.fn_create_log_message(self)     
        return None

    def fn_assign_service_investment_data_model(self):
        self.service_investment_data_model_weight =  pd.DataFrame([{
                                                                    "AREA":0,
                                                                    "BUILT_TIME":0,                        
                                                                    "BATH":0,
                                                                    "BALCONY":0,
                                                                    "SPORT_FIELDS":0,
                                                                    "MALL":0,
                                                                    "STORAGE_ROOM":0,
                                                                    "OPEN_KITCHEN":0,                                        
                                                                    "METRO":0, 
                                                                    "DISTANCE":0.15,                                       
                                                                    "ESTRATUM":0, 
                                                                    "GYM":0,
                                                                    "BEDROOMS":0.15,                                                                                                                           
                                                                    "PARKING":0,
                                                                    "CHILD_PARK":0,
                                                                    "PET_FRIENDLY":0,
                                                                    "POOL":0,
                                                                    "RECEPTION":0,
                                                                    "VALUE":0.5,                                        
                                                                    "SOCIAL_ROOM":0,
                                                                    "ECO_ROUTES":0,
                                                                    "THEATER":0,
                                                                    "COMMON_AREAS":0,
                                                                    "WET_AREAS":0                                                                                                                                                      
                                                                 }])

        self.features                = [{
                                          "BALCONY",
                                          "SPORT_FIELDS",
                                          "MALL",
                                          "STORAGE_ROOM",
                                          "OPEN_KITCHEN",                                        
                                          "METRO",                                        
                                          "ESTRATUM", 
                                          "GYM",
                                          "POOL",                                       
                                          "RECEPTION",
                                          "PARKING",
                                          "CHILD_PARK",
                                          "PET_FRIENDLY",                                        
                                          "SOCIAL_ROOM",
                                          "ECO_ROUTES",
                                          "THEATER",
                                          "COMMON_AREAS",
                                          "WET_AREAS"
                                        }]

        HgModelData.fn_create_log_message(self)    
        return None

    def fn_create_log_message(self):
        self.vConsoleMessage           = " Variables Weight for " + self.vLifeMoment + " Module and " + self.vService + " Service load and assigned successfully"
        self.vLogMessage               += self.vConsoleMessage
        logging.info(self.vLogMessage)
        self.vLogMessage               = self.vLogMessage.strip(self.vConsoleMessage)