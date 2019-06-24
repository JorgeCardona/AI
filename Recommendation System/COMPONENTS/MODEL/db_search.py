import pandas as pd
from COMPONENTS.MODEL.db_config               import *
from COMPONENTS.CONTROLLER.exceptions         import *
#http://chuwiki.chuidiang.org/index.php?title=Python_y_MYSQL
import pymysql
import logging

class SearchDB():

#configura la conexion a base de datos con las credenciales le
    def fn_get_database_credentials(self):
        vConection = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PASSWORD, MYSQL_SCHEMA)
        return vConection

#captura informacion de tipo de servicio solicitado y asigna la consulta a realizar
    def fn_execute_selected_search(self):        
        
        if(self.vAssignedSearchId == 5.0):
            vQuery = "SELECT * FROM property WHERE (propertyValue BETWEEN 2000000 AND 4000000) AND propertyEstate = 2 ORDER BY propertyValue ASC LIMIT 10"    
            SearchDB.fn_obtain_data_search_result(self,vQuery)
      
        if(self.vAssignedSearchId == 5.1):
            #vQuery = "SELECT * FROM property WHERE (propertyValue BETWEEN 2000000 AND 4000000) AND propertyEstate = 2 ORDER BY propertyValue ASC LIMIT 10"  
            vQuery = "SELECT * FROM property WHERE " + self.vQueryx + ") AND propertyEstate = 0 ORDER BY propertyValue ASC LIMIT 10"    

            print("LA consulta es: ",vQuery)
            SearchDB.fn_obtain_data_search_result(self,vQuery)

        return None

#obtiene el resultado de la consulta realizada       
    def fn_obtain_data_search_result (self,vQuery):

        vConection = SearchDB.fn_get_database_credentials(self)
        vCursor = vConection.cursor()
        
        try:
            vCursor.execute(vQuery)
            self.vQueryResult          = vCursor.fetchall()
            self.vPandasSearchDatabase = pd.read_sql_query(vQuery, vConection)

            self.vConsoleMessage          = " successfull query in database "
            self.vLogMessage             += self.vConsoleMessage
            logging.info(self.vLogMessage)
            self.vLogMessage              = self.vLogMessage.strip(self.vConsoleMessage)                 

        except Exception as e:

            self.vConsoleMessage          = " query error in database " + e
            self.vLogMessage             += self.vConsoleMessage
            logging.error(self.vLogMessage)
            self.vLogMessage              = self.vLogMessage.strip(self.vConsoleMessage) 
            #print("Sucedio un Error con Codigo : ", e)

        finally:
            vCursor.close()
            vConection.close()

            self.vConsoleMessage          = " database connection close"
            self.vLogMessage             += self.vConsoleMessage
            logging.info(self.vLogMessage)
            self.vLogMessage              = self.vLogMessage.strip(self.vConsoleMessage)

        print ("script base de datos>>>>>>>>>", self.vPandasSearchDatabase)
        return None
            #print ("Acceso a la base de datos Terminado")
            
        #print (">>>>>>>>>", self.vPandasSearchDatabase)
     

"""
    def fn_DB_read( sql):
        return pd.read_sql_query(sql, conn)

    def fn_rent_search(:
        sql = "SELECT *"
        sql += " FROM data_coord"
        sql += " WHERE "
        sql += " Business = '" + business + "' AND"
        sql += " City = '" + city + "';"
        return sql
        


"""

"""
#no, se va a usar debido a que se sabe si compra o renta
    def fn_define_filters( business):
        city = form["zone"]

        sql = "SELECT *"
        sql += " FROM data_coord"
        sql += " WHERE "
        sql += " Business = '" + business + "' AND"
        sql += " City = '" + city + "';"
        return sql

#no, se va a usar debido a que se sabe si compra o renta
    def fn_rules_offering(:

        age = form["age"]
        ocupation = ""
        for ocupatio in form["ocupation"][:-1]:
            #print(ocupatio)
            ocupation += ocupatio["type"]+"/"
            ocupation += form["ocupation"][-1]["type"]

        priority_form = sorted(form["priority"].items(), key=lambda x: x[1])[-1][0]

        if priority_form == "family":
            priority = "Conformar Familia"
        elif priority_form == "travel":
            priority = "Viajar"
        elif priority_form == "study":
            priority = "Estudiar"
        elif priority_form == "save":
            priority = "Ahorrar/invertir"

        sql =  " SELECT Opciones_para_ofrecer"
        sql += " FROM rules"
        sql += " WHERE"
        sql += " Edad_min <= " + str(age) + " AND"
        sql += " Edad_max >= " + str(age) + " AND"
        sql += " Ocupacion = '" + ocupation + "' AND"
        sql += " Prioridad = '" + priority + "';"

        offer = DB_read(sql)
        offer = offer.values[0][0].replace(" ","").split(",")
        #print(offer)
"""