from fastapi import FastAPI
from config.utils import URL
from use_case.nbaplayers import RecoveryData

app = FastAPI()


@app.get("/{inches}")
def get_couples(inches: int):
    
    return RecoveryData(inches=inches).get_process_data(url=URL)


# pytest -v --cov=. --cov-report=html