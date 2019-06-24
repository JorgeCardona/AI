
class Exceptions (BaseException):

    def __init__(self, exceptionId):
        self.exceptionId = exceptionId

    def __str__(self):

        if(self.exceptionId == 1):
            self.vError = "No tiene acceso permitido"
        elif(self.exceptionId == 5):
            self.vError = "Gano la beca!!!!"
        else:
            self.vError = "Recontra Pailinga"

        return "Error " + str(self.vError)
