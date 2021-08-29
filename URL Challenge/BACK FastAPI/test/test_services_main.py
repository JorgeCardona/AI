from fastapi.testclient import TestClient
from functools import wraps
from main import app
from database.configuration.database import get_db, engine
from utils.constants import ENCODING_DECODING_WORD

client = TestClient(app)


base_path = "/URL/"
base_ur_test = "http://myapi.com"
url_test = base_ur_test + "/fastapi/uvicorn/pydantic/postgresql/pytest/coverage/docker"
short_url_test = base_ur_test + "Ab123"
bad_url_test = url_test.replace("//", "@//")


#######################################################################################
#######################################################################################

def create_url(url_test=url_test):

    response = client.post(
        base_path,
        json={"original_url": url_test},
    )
 
    return response

def delete_url(response):

    id = response.json()
    id = id['id']

    response = client.delete(
        base_path + str(id)
    )
 
    return response


#######################################################################################

def test_create_url_ok():

    response = create_url()
    delete_url(response)
    assert response.status_code == 200


def test_create_url_exists():

    create_firts_url = create_url()
    response = create_url()

    delete_url(create_firts_url)
    assert response.status_code == 400


def test_bad_url_create_url_error():
    response = client.post(
        base_path,
        json={"original_url": bad_url_test},
    )   
    
    assert response.status_code == 422


def test_get_url_list():

    new_url = create_url()

    response = client.get(base_path)
    delete_url(new_url)

    assert len(response.json()) > 0
    assert response.status_code == 200

    
def test_get_register_by_original_url_ok():
    response = create_url()
    original_urL = response.json()
    original_urL = original_urL["original_url"]

    original_urL_to_validate = client.patch(base_path + "original_url",
    json={"original_url":original_urL})
    delete_url(response)

    assert original_urL_to_validate.status_code == 200

def test_get_register_by_original_url_not_found():

    original_urL_to_validate = client.patch(base_path + "original_url",
    json={"original_url":url_test})

    assert original_urL_to_validate.status_code == 404


def test_get_register_by_short_url_ok():
    response = create_url()
    short_urL_created = response.json()
    short_urL_created = short_urL_created["short_url"]

    short_urL_to_validate = client.patch(base_path + "short_url",
    json={"short_url":short_urL_created})
    delete_url(response)

    assert short_urL_to_validate.status_code == 200

def test_get_register_by_short_url_not_found():

    short_urL_to_validate = client.patch(base_path + "short_url",
    json={"short_url":short_url_test})

    assert short_urL_to_validate.status_code == 404

def test_update_original_url_ok():

    url = create_url()
    json_url = url.json()
    

    response = client.put(
        base_path, json = json_url
    )

    delete_url(url)

    assert response.status_code == 200

def test_update_original_url_not_found():

    url = create_url()
    json_url = url.json()
    delete_url(url)

    response = client.put(
        base_path , json = json_url
    )

    assert response.status_code == 404


def test_update_url_click_ok():

    url = create_url()
    short_url = url.json()
    short_url = short_url['short_url']
    short_url = short_url.replace( "/", ENCODING_DECODING_WORD)

    response = client.patch(
        base_path + str(short_url)
    )

    delete_url(url)

    assert response.status_code == 200

    number_of_clicks = response.json()
    number_of_clicks = number_of_clicks['clicks']
    
    assert number_of_clicks == 1

    
def test_update_url_click_not_found():

    short_url = short_url_test.replace( "/", ENCODING_DECODING_WORD)

    response = client.patch(
        base_path + str(short_url)
    )

    assert response.status_code == 404  


def test_delete_doesnot_url_exists():

    id = 1
    response = client.delete(
        base_path + str(id)
    )
    assert response.status_code == 404