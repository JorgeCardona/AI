package jorge.cardona.concepts.util;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class RequestResponse {


    @Value("${id}")
    private UUID uuidData;

    public Map<String,Object> response(String path, HttpStatus status, Object data){

        Map<String,Object> response = new HashMap<>();

        response.put("UUID ", uuidData);
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.getReasonPhrase());
        response.put("code", status.value());
        response.put("data",data);
        response.put("path",path);

        return  response;

    }
}
