from fastapi.testclient import TestClient
from config.utils import URL
from main import app
from use_case.nbaplayers import RecoveryData

client = TestClient(app)


url_test = URL


def test_couples_ok():
    response = client.get("/139")
    assert response.status_code == 200
    
def test_couples_fail():
    response = client.get("/1")
    assert response.status_code == 200
    assert response.json() == 'app 1 - No matches found'
    
def test_invalid_data_type():
    response = client.get("/1.2")
    assert response.status_code == 422
    
#pytest -v