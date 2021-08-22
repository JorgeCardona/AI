from fastapi.testclient import TestClient

from main import app

client = TestClient(app)


url_test = "http://challenge.com/"


def create_url(url_test=url_test):

    response = client.post(
        "/URL/",
        json={"original_url": url_test},
    )
 
    return response

def delete_url(response):

    id = response.json()
    id = id['id']

    response = client.delete(
        "/URL/" + str(id)
    )
 
    return response


def test_get_url_list():

    new_url = create_url()

    response = client.get("/URL/")

    assert len(response.json()) > 0
    assert response.status_code == 200

    delete_url(new_url)


def test_create_url_error():
    response = client.post(
        "/URL/",
        json={"original_url": "http@challenge.com"},
    )   
    
    assert response.status_code == 422


def test_create_url_ok():

    response = create_url()
    assert response.status_code == 200
    delete_url(response)



def test_create_url_exists():

    create_firts_url = create_url()
    response = create_url()

    assert response.status_code == 400

    delete_url(create_firts_url)


def test_delete_doesnot_url_exists():

    id = 1
    response = client.delete(
        "/URL/" + str(id)
    )
    assert response.status_code == 404


def test_update_url():

    url = create_url()
    json_url = url.json()

    response = client.put(
        "/URL/" , json = json_url
    )

    assert response.status_code == 200

    number_of_clicks = response.json()
    number_of_clicks = number_of_clicks['clicks']
    
    assert number_of_clicks == 1

    delete_url(url)
    

def test_update_url_click():

    url = create_url()
    short_url = url.json()
    short_url = short_url['short_url']
    short_url = short_url.replace( "/","CHALLENGE-HEY-URL")

    response = client.put(
        "/URL/" + str(short_url)
    )

    assert response.status_code == 200


    number_of_clicks = response.json()
    number_of_clicks = number_of_clicks['clicks']
    
    assert number_of_clicks == 1

    delete_url(url)

