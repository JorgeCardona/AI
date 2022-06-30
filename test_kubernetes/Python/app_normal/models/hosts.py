from pydantic import BaseModel

class Host(BaseModel):
    hostname: str = 'http://localhost'
    port: int = 9999
        