#https://es.wikipedia.org/wiki/Anexo:C%C3%B3digos_de_estado_HTTP

from flask import Flask, jsonify, request
import json
from COMPONENTS.ecosystem import *
#from DATABASE.db_config  import *

ecosystem_app = Flask(__name__)

@ecosystem_app.route('/', methods=['GET','POST'])
def index():

    if request.method == 'POST':

        #obtiene la informacion del formulario en formato json
        request_json_data_input_of_form   = request.get_json()

        #almacena el resultado de la recomendacion
        recommendation_response_generated = Ecosystem(request_json_data_input_of_form)

        #return Ecosystem.solve()
        #return jsonify({'recommendations': responsedata.result()})
        #return json.dumps(request_json_data_input_of_form)
        return jsonify({'recommendations': Ecosystem.main('','')})

