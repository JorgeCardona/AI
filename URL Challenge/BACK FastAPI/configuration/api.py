from configuration.cross_origin import origins

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware


def fastAPiConfiguration():

    app = FastAPI()

    app.add_middleware(
        CORSMiddleware,
        allow_origins=origins,
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )

    return app