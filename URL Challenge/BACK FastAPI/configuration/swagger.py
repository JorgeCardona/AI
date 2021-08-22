from fastapi.openapi.utils import get_openapi

def custom_openapi(app):
    if app.openapi_schema:
        return app.openapi_schema
        
    openapi_schema = get_openapi(
        title="Challenge Api Documentation",
        version="1.0.1",
        terms_of_service="http://example.com/terms/",
        description="Documentation for my custom OpenAPI schema",
            contact={
        "name": "Jorge Cardona",
        "url": "https://github.com/JorgeCardona/AI",
        "email": "jorgecardona@utp.edu.co",
    },
    license_info={
        "name": "Apache 2.0",
        "url": "https://www.apache.org/licenses/LICENSE-2.0.html",
    },
    
        routes=app.routes,
    )
    openapi_schema["info"]["x-logo"] = {
        "url": "https://fastapi.tiangolo.com/img/logo-margin/logo-teal.png"
    }

    app.openapi_schema = openapi_schema

    return app.openapi_schema