# docker build -t jorgecardona/python-ip-for-test-kubernetes .
# docker run --rm -d --name=pythonkubertest -p 5555:5000 jorgecardona/python-ip-for-test-kubernetes
# test la aplicacion en http://localhost:5555
# http://localhost:5555/items/7?q=31
# http://localhost:5555/docs

# define la version de python a instalar basado en la imagen oficial
FROM python:alpine

# directorio donde se va a copiar la aplicacion
WORKDIR /code

#actualiza el pip e instala los paquetes necesarios para correr la API
RUN pip3 -q install pip --upgrade
RUN pip3 install fastapi
RUN pip3 install uvicorn

# copia la aplicacion
COPY ./app /code/app

# comandos para iniciar la aplicacion en http://localhost:5000
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "5000"]