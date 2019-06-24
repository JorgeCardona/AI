from COMPONENTS.MODULES.HOME_GROUND.config_hg import *

class BusinessVariables():

    def fn_moment_control(self,vLifeMomentId,vServiceId):

        if(vLifeMomentId == 5):
            HgRequiredVariables.fn_get_assigned_variables_for_habitat(self)
            HgModelData.fn_get_assigned_data_model_for_habitat(self)
            HgVariables.fn_get_data_value_for_habitat(self)
            HgQuestions.fn_get_data_question_for_habitat(self)
            HgSaveValues.fn_get_ideal_house(self)                               
        else:
            print("Exception e:")
        return None