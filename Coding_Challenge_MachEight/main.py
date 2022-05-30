from fastapi import FastAPI
from config.utils import URL
from use_case.nbaplayers import RecoveryData

app = FastAPI()


@app.get("/{inches}")
def get_couples(inches: int):
    
    return RecoveryData(url=URL, inches=inches).get_process_data()