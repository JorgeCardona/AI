package jorge.cardona.kubernetes.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.SpringVersion;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Info {

    public static Map<String, String> getInternalInfo() throws UnknownHostException {

        Map<String, String> map = new HashMap<>();

        Runtime.Version runtimeVersion = Runtime.version();
        String version = String.valueOf(runtimeVersion.version().get(0));
        InetAddress hostIp = InetAddress.getLocalHost();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String hostname = InetAddress.getLocalHost().getHostName();

        map.put("Pod - Image  -> Aplication", "Java Version " + System.getProperty("java.version"));
        map.put("Pod - Image  -> Spring Version", SpringVersion.getVersion());
        map.put("Pod - Image  -> Author", "Jorge Cardona");
        map.put("Pod - Image  -> hostname", hostname);
        map.put("Pod - Image  -> ip", ip);
        map.put("Pod - Image  -> hostname/ip", hostIp.toString());

        return map;

    }

    public static Map<Object, Object> getExternalWeb(String url) throws UnknownHostException {

        Map<Object, Object> map = new HashMap();
        Map<String, String> internalInfo = getInternalInfo();

        map.put("Local API Data Information", internalInfo);

        RestTemplate request = new RestTemplate();

        try{
            String response = request.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);

            map.put("Response from External API Consumed",node);

        } catch (Exception e) {
            map.put("error","website not found");
        }
        finally {
            return map;
        }
    }

    public static Map<Object, Object> getExternalApiResponse(String url, int port) throws UnknownHostException, JsonProcessingException {

        Map<Object, Object> map = new HashMap();
        Map<String, String> internalInfo = getInternalInfo();

        map.put("Local API Data Information",internalInfo);

        RestTemplate request = new RestTemplate();
        String address = url + ":" + port;

        try{
            String response = request.getForObject(address, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);

            map.put("Response from External API Consumed",node);

        } catch (Exception e) {
            map.put("error","website not found");
        }
        finally {
            return map;
        }
    }
}
