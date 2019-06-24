import logging

class HgQuestions():
    
    def fn_get_data_question_for_habitat(self):
        if(self.vServiceId == 0):
            self.vAssignedQuestions = HgQuestions.fn_get_service_rent_questions(self)
        elif(self.vServiceId == 1):
            self.vAssignedQuestions = HgQuestions.fn_get_service_investment_questions(self)
        else:
            print("Exception e:")

        return self.vAssignedQuestions

    def fn_get_service_rent_questions(self): 
        return HgQuestions.fn_question_zero(self) , HgQuestions.fn_question_one(self) , HgQuestions.fn_question_two(self) , HgQuestions.fn_question_three(self) , HgQuestions.fn_question_four(self) , HgQuestions.fn_question_five(self) , HgQuestions.fn_question_six(self), HgQuestions.fn_balance_rent_questions(self), HgQuestions.fn_create_log_message(self)

    def fn_get_service_investment_questions(self): 
        return HgQuestions.fn_question_seven(self) ,HgQuestions.fn_question_eight(self) , HgQuestions.fn_question_nine(self) , HgQuestions.fn_question_ten(self), HgQuestions.fn_create_log_message(self)

    def fn_create_log_message(self):
        self.vConsoleMessage           = " Recalculate variables read for form in " + self.vLifeMoment + " Module and " + self.vService + " Service Reassigned successfully"
        self.vLogMessage               += self.vConsoleMessage
        logging.info(self.vLogMessage)
        self.vLogMessage               = self.vLogMessage.strip(self.vConsoleMessage)

    #balancea los campos basado en el transporte seleccionado
    def fn_balance_rent_questions(self):
        if(self.vIdTransportType == 1):
            self.service_rent_data_model_weight["METRO"] += 0.4
            self.vMetro   = 1
            self.vParking = 0
        else:
            self.service_rent_data_model_weight["METRO"] -= 0.4
            self.vMetro   = 0
            self.vParking = 1            

    #captura datos question 0
    def fn_question_zero (self):
        #las opciones para la question "compartir son"
        for option in self.form["preguntasHabitar"]["compartirCon"]:
            if option["relacion"] == "Padres":                    
                self.vBedrooms  += option["cantidad"]
                self.service_rent_data_model_weight["AREA"]  += 0.2
                self.service_rent_data_model_weight["VALUE"] += 0.2

            if option["relacion"] == "Pareja":
                self.vBedrooms += option["cantidad"]
                self.service_rent_data_model_weight["AREA"]  += 0.2
                self.service_rent_data_model_weight["VALUE"] += 0.2

            if option["relacion"] == "Hermanos":
                self.vBedrooms += option["cantidad"]               
                self.service_rent_data_model_weight["AREA"]  += 0.2
                self.service_rent_data_model_weight["VALUE"] += 0.2

            if option["relacion"] == "Hijos":
                self.vBedrooms += option["cantidad"]
                self.service_rent_data_model_weight["AREA"]  += 0.2
                self.service_rent_data_model_weight["VALUE"] += 0.2                

            if option["relacion"] == "Amigos":
                self.vBedrooms += option["cantidad"]           
                self.service_rent_data_model_weight["AREA"]  += 0.2
                self.service_rent_data_model_weight["VALUE"] += 0.2   
                
            if option["relacion"] == "Mascotas":
                self.vPetFriendly = 1
                self.service_rent_data_model_weight["PET_FRIENDLY"] = 1
                self.service_rent_data_model_weight["AREA"]  += 0.1
                self.service_rent_data_model_weight["VALUE"] += 0.1  

            if option["relacion"] == "Otros":
                self.vBedrooms += option["cantidad"]
                self.service_rent_data_model_weight["AREA"]  += 0.2
                self.service_rent_data_model_weight["VALUE"] += 0.2   
        return None

    #captura datos question 1
    def fn_question_one (self):        
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasHabitar"]["preguntas"]:
            for resp in option["respuestasRq"]:
                #OJO se debe revisar si el json trae datos en string o en int
                if resp["valor"] == '1':                   
                    # informacion question id question 1
                    # Escoge la opcion con la que mas te identificas y con la que menos te identificas
                    if option["idPregunta"] == '1':
                        #En mi tiempo libre me gusta realizar alguna actividad fisica, ya sea ir al gym, salir a trotar, nadar, entre otros
                        if resp["idRespuesta"] == '1':
                            #asigna a one las variable que deben estar presentes
                            self.sport_fields = 1
                            self.gym          = 1
                            self.pool         = 1 
                            self.vEcoRoutes   = 1
                            self.vWetAreas    = 1
                            #incrementa el valor de la caracteristica deseada
                            self.service_rent_data_model_weight["SPORT_FIELDS"] += 0.2
                            self.service_rent_data_model_weight["GYM"]          += 0.2   
                            self.service_rent_data_model_weight["POOL"]         += 0.2                                                      
                            self.service_rent_data_model_weight["ECO_ROUTES"]   += 0.2 
                            self.service_rent_data_model_weight["WET_AREAS"]    += 0.2 

                        #En mi tiempo libre me encanta ir a eventos culturales como cine, teatro, conciertos o museos                                  
                        if resp["idRespuesta"] == '2':
                            self.theater      = 1
                            self.vCommonAreas = 1 
                            self.vSocialRoom  = 1
                            self.service_rent_data_model_weight["THEATER"]      += 0.3
                            self.service_rent_data_model_weight["COMMON_AREAS"] += 0.1
                            self.service_rent_data_model_weight["SOCIAL_ROOM"]  += 0.1

                        #"La mejor opción para divertirme es visitar un centro comercial"
                        if resp["idRespuesta"] == '3':
                            self.vMall        = 1
                            self.vCommonAreas = 1
                            self.service_rent_data_model_weight["MALL"]         += 0.3        
                            self.service_rent_data_model_weight["COMMON_AREAS"] += 0.1                                             
        return None

    #captura datos question 2
    def fn_question_two (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasHabitar"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 2
                    # A la hora de elegir tu vivienda
                    #Prefieres vivir cerca a una vía principal para tener fácil acceso a los medios de transporte
                    if option["idPregunta"] == '2':
                        if resp["idRespuesta"] == '5':
                            self.service_rent_data_model_weight["METRO"] += 0.1
                    #Prefieres vivir en un entorno tranquilo y alejado de ruitwo aunque estés retirado de los lugares que frecuentas diariamente
                        if resp["idRespuesta"] == '6':
                            self.vEcoRoutes   = 1
                            self.service_rent_data_model_weight["METRO"]      -= 0.2
                            self.service_rent_data_model_weight["ECO_ROUTES"] += 0.2
                    #Prefieres vivir cerca a los lugares que frecuentas diariamente                                
                        if resp["idRespuesta"] == '7':
                            self.service_rent_data_model_weight["METRO"] += 0.1
        return None

    #captura datos question 3
    def fn_question_three (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasHabitar"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 3
                    # Cuando piensas en tu dinero
                    if option["idPregunta"] == '3':
                        #Ahorrar no es sólo guardar, sino saber gastar
                        if resp["idRespuesta"] == '8':
                            self.service_rent_data_model_weight["VALUE"] -= 0.2
                        #La vida es corta. Me gusta darme gusto y consentir a los míos
                        if resp["idRespuesta"] == '9':
                            self.service_rent_data_model_weight["VALUE"] += 0.3
        return None

    #captura datos question 4
    def fn_question_four (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasHabitar"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 4
                    # Si tuvieras las siguientes opciones ¿Cual seria la mas importante para ti?
                    if option["idPregunta"] == '4':
                        # Tener contacto con la naturaleza
                        if resp["idRespuesta"] == '10':
                            self.vEcoRoutes = 1
                            self.vWetAreas = 1
                            self.service_rent_data_model_weight["ECO_ROUTES"]  += 0.4                            
                            self.service_rent_data_model_weight["WET_AREAS"]   += 0.1
                        # Reunirme en casa con mi familia y amigos
                        if resp["idRespuesta"] == '11':
                            self.vEcoRoutes   = 1
                            self.vSocialRoom  = 1
                            self.vCommonAreas = 1
                            self.service_rent_data_model_weight["VALUE"]        += 0.2
                            self.service_rent_data_model_weight["SOCIAL_ROOM"]  += 0.3
                            self.service_rent_data_model_weight["COMMON_AREAS"] += 0.3
                        # Celebrar las fechas especiales como grado, cumpleaños, entre otros
                        if resp["idRespuesta"] == '12':
                            self.vEcoRoutes   = 1
                            self.vCommonAreas = 1
                            self.vSocialRoom  = 1
                            self.service_rent_data_model_weight["VALUE"]        += 0.1
                            self.service_rent_data_model_weight["SOCIAL_ROOM"]  += 0.3
                            self.service_rent_data_model_weight["COMMON_AREAS"] += 0.3
        return None

    #captura datos question 5
    def fn_question_five (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasHabitar"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 5
                    # Si tuvieras un bono adicional para tu vivienda
                    if option["idPregunta"] == '5':
                        # Me encantaría tener un espacio para trabajar desde la casa
                        if resp["idRespuesta"] == '13':
                            self.vBath     += 1
                            self.vBedrooms += 1
                            self.service_rent_data_model_weight["AREA"]  += 0.2
                            self.service_rent_data_model_weight["BATH"]  += 0.1
                            self.service_rent_data_model_weight["VALUE"] += 0.1 
                        # "Elegiría tener un espacio para mis plantas y/o mascotas                           
                        if resp["idRespuesta"] == '14':
                            self.vPetFriendly = 1
                            self.service_rent_data_model_weight["AREA"]         += 0.2
                            self.service_rent_data_model_weight["PET_FRIENDLY"] += 0.2
                            self.service_rent_data_model_weight["VALUE"]        += 0.1 
                        # endría un espacio para divertirme, como un cuarto de juegos para mis hijos, un cuarto de televisión o hobbies 
                        if resp["idRespuesta"] == '15':
                            self.vBedrooms += 1
                            self.vBath     += 1
                            self.service_rent_data_model_weight["AREA"]  += 0.3
                            self.service_rent_data_model_weight["BATH"]  += 0.1 
                            self.service_rent_data_model_weight["VALUE"] += 0.2 
                        # Quisiera tener un lugar amplio para almacenar
                        if resp["idRespuesta"] == '16':
                            self.vStorageRoom = 1
                            self.service_rent_data_model_weight["AREA"]         += 0.2
                            self.service_rent_data_model_weight["STORAGE_ROOM"] += 0.2
                            self.service_rent_data_model_weight["VALUE"]        += 0.2  
        return None

    #captura datos question 6
    def fn_question_six (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasHabitar"]["preguntas"]:
            for resp in option["respuestasRq"]:
                #actualmente es ahorrar / invertir, pero deberia ser one para el estandard que ya tienen las otras questions
                #if resp["valor"] == "Ahorrar/Invertir":
                if resp["valor"] == "1":
                    # informacion question id question 6
                    # Cuando piensas en el futuro, ¿Qué es lo más importante para ti?
                    if option["idPregunta"] == '6':
                        # Conformar familia
                        if resp["idRespuesta"] == '17':
                            self.vMall        = 1
                            self.vParking     = 1
                            self.vChildPark   = 1
                            self.vPetFriendly = 1
                            self.vSocialRoom  = 1
                            self.vCommonAreas = 1                            

                            self.service_rent_data_model_weight["METRO"]        += 0.1
                            self.service_rent_data_model_weight["MALL"]         += 0.1
                            self.service_rent_data_model_weight["PARKING"]      += 0.2
                            self.service_rent_data_model_weight["CHILD_PARK"]   += 0.2
                            self.service_rent_data_model_weight["PET_FRIENDLY"] += 0.2                            
                            self.service_rent_data_model_weight["VALUE"]        += 0.2
                            self.service_rent_data_model_weight["SOCIAL_ROOM"]  -= 0.2
                            self.service_rent_data_model_weight["COMMON_AREAS"] -= 0.2  
                        # Viajar                            
                        if resp["idRespuesta"] == '18':
                            self.service_rent_data_model_weight["METRO"] += 0.1
                            self.service_rent_data_model_weight["VALUE"] -= 0.1 
                        # Estudiar 
                        if resp["idRespuesta"] == '19':
                            self.service_rent_data_model_weight["METRO"] += 0.1 
                            self.service_rent_data_model_weight["VALUE"] -= 0.1 
                        # Ahorrar/Invertir 
                        if resp["idRespuesta"] == '20':
                            self.service_rent_data_model_weight["VALUE"] -= 0.2 
                        # impulsar su carrera" 
                        if resp["idRespuesta"] == '21':
                            self.service_rent_data_model_weight["VALUE"] -= 0.2  
                        # Otras
                        if resp["idRespuesta"] == '22':
                            self.service_rent_data_model_weight["VALUE"] -= 0.2    
        return None

    #/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    #/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    #/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    #CAPTURA DATOS DE LAS questionS DE LA PARTE DE INVERTIR DEL FORMULARIO

    #captura datos question 7
    def fn_question_seven (self):
        #las opciones para las otras secciones de questions 7 con
        for option in self.form["preguntasInvertir"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 7
                    # ¿Hay una zona o barrio que te llame más la atención para invertir?
                    if option["idPregunta"] == '7':
                        if(self.vTotalZones > 0):
                            
                            print("{{{{{{{{{{{{{{{{{{{", self.vTotalZones)
                            self.vQueryx = "(propertyValue BETWEEN " + str(self.vInversionCapacityMinimun)
                            pass

        return None

    #captura datos question 8
    def fn_question_eight (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasInvertir"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 8
                    # Cuánto estás pensando en  invertir?
                    if option["idPregunta"] == '8':     

                        self.vQueryx += " AND " + str(self.vInversionCapacityMaximun)                
                        pass

        return None

    #captura datos question 9
    def fn_question_nine (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasInvertir"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 9
                    # De las siguientes opciones selecciona con las mas te identificas Selecciona una opción?
                    if option["idPregunta"] == '9':
                        # No tengo afán para recuperar mi dinero. Prefiero esperar a que el producto esté listo y obtener una mayor rentabilidad.                            
                        if resp["idRespuesta"] == '23':
                            self.vMetro = 1
                            self.service_investment_data_model_weight["METRO"] += 0.1
                            self.service_investment_data_model_weight["VALUE"] -= 0.1 
                        # Me interesa un proyecto nuevo y con alta demanda con el que pueda hacer negocios rápidamente. 
                        if resp["idRespuesta"] == '24':
                            self.vMetro = 1
                            self.service_investment_data_model_weight["METRO"] += 0.2 
                            self.service_investment_data_model_weight["VALUE"] -= 0.1 
                        # Busco una inversión que tenga detalles que le agreguen valor al inmueble.
                        if resp["idRespuesta"] == '25':
                            self.service_investment_data_model_weight["VALUE"] -= 0.2    

        return None

    #captura datos question 10
    def fn_question_ten (self):
        #las opciones para las otras secciones de questions con
        for option in self.form["preguntasInvertir"]["preguntas"]:
            for resp in option["respuestasRq"]:
                if resp["valor"] == '1':
                    # informacion question id question 8
                    # Cuánto estás pensando en  invertir?
                    if option["idPregunta"] == '10':
                        # Seguridad                            
                        if resp["idRespuesta"] == '26':
                            self.vMetro     = 1
                            self.vReception = 1                            
                            self.service_investment_data_model_weight["RECEPTION"] += 0.3
                            self.service_investment_data_model_weight["METRO"]     += 0.1
                            self.service_investment_data_model_weight["VALUE"]     += 0.2 
                        # Fácil acceso a transporte público. 
                        if resp["idRespuesta"] == '27':
                            self.vMetro = 1
                            self.service_investment_data_model_weight["METRO"] += 0.4 
                            self.service_investment_data_model_weight["VALUE"] -= 0.1 
                        # Contacto con la naturaleza.
                        if resp["idRespuesta"] == '28':
                            self.service_investment_data_model_weight["VALUE"] -= 0.2 
                        # Diversión, deportes y entretenimiento
                        if resp["idRespuesta"] == '29':
                            self.vMetro = 1
                            self.service_investment_data_model_weight["METRO"] += 0.2 
                            self.service_investment_data_model_weight["VALUE"] -= 0.1 
                        # Espacios útiles y funcionales.
                        if resp["idRespuesta"] == '30':
                            self.service_investment_data_model_weight["VALUE"] -= 0.2 
        return None