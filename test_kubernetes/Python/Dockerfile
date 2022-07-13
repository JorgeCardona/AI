# docker build -t jorgecardona/test-kubernetes-ip-python-3.10.5:v1 .
# docker run --rm -d --name=pythonkubertest -p 5555:5000 jorgecardona/test-kubernetes-ip-python-3.10.5:v1
# test la aplicacion en 
# http://localhost:5555
# http://localhost:5555/docs -> swagger
# http://localhost:5555/env
# http://localhost:5555/all
# http://localhost:5555/api POST Method -> body {"hostname": "http://localhost","port": 9888}
# http://localhost:5555/web -> GET METHOD http://localhost:5555/web?url=http://localhost:9888/env
# http://localhost:5555/web?url=https://raw.githubusercontent.com/JorgeCardona/skills/master/Python/DB/PeriodicTable.json
# http://localhost:5555/readInfoFromFile -> read data from file
# http://localhost:5555/saveInfoToFile POST->  POST Method -> body {"message": "Jorge Cardona have added Information"}
# http://localhost:5555/createFile -> POST no boby is needed
# http://localhost:5555/deleteFile DELETE -> delete created file

# define la version de python a instalar basado en la imagen oficial -> python 3.10.5
FROM python:alpine3.16

# directorio donde se va a copiar la aplicacion
WORKDIR /code

#actualiza el pip e instala los paquetes necesarios para correr la API
RUN pip3 -q install pip --upgrade
RUN pip3 install fastapi
RUN pip3 install uvicorn
RUN pip3 install requests

# copia todos los archivos desde la carpeta app -> debe ser la aplicacion
# la diferencia del doccker radica en que importa los paquetes precedido por . punto literal y en normal no, porque no corre la aplicacion
COPY ./app_docker /code/app

# comandos para iniciar la aplicacion en http://localhost:5000
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "5000"]