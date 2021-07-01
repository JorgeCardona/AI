package jorge.cardona.concepts.others.util;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RequestResponse {

    public static Map<String,Object> response(String path, HttpStatus status, Object data){

        Map<String,Object> response = new HashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.getReasonPhrase());
        response.put("code", status.value());
        response.put("data",data);
        response.put("path",path);

        return  response;

    }
}
