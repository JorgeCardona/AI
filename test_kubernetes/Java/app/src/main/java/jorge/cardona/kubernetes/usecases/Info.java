package jorge.cardona.kubernetes.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jorge.cardona.kubernetes.models.Config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Data
public class Info {

    @Autowired
    Config config;

    public String setDirectorioDeArchivos() {

        File directorio = new File(config.getDirectorio());
        String mensaje = null;

        if (!directorio.exists()) {
            try {
                directorio.mkdirs();
                mensaje = "Directorio creado";
            } catch (SecurityException e) {
                mensaje = "No se pudo crear el directorio";
            }
            return mensaje;

        } else {
            return "El directorio ya estaba creado";
        }
    }

    public String setContentFile(String message) throws IOException {

        setDirectorioDeArchivos();
        FileWriter archivoDeDatos = new FileWriter(config.getFullpath(), Charset.forName("UTF-8"), true);
        archivoDeDatos.append("Java Version " + System.getProperty("java.version") + " Host " + InetAddress.getLocalHost().getHostAddress() + "  Informacion Adicionada "+ message + "\n");
        archivoDeDatos.close();

        return getContentFile();

    }

    public String getContentFile() throws IOException {

        createFile();

        Path filePath = Path.of(config.getFullpath());

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }


    public Map<String, String> getEnvironmentVariables() {
        Map<String, String> envMap = System.getenv();

        return envMap;
    }

    public Map<String, Object> getInternalInfo() throws UnknownHostException {

        Map<String, Object> map = new HashMap<>();
        Map<String, String> environmentVariables = getEnvironmentVariables();

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

    public Map<String, Object> getAllInfo() throws UnknownHostException {

        Map<String, Object> map = getInternalInfo();
        Map<String, String> environmentVariables = getEnvironmentVariables();
        map.put("Pod - Image  -> Environment Variables", environmentVariables);

        return map;

    }

    public Map<Object, Object> getExternalWeb(String url) throws UnknownHostException {

        Map<Object, Object> map = new HashMap();
        Map<String, Object> internalInfo = getAllInfo();

        map.put("Local API Data Information", internalInfo);

        RestTemplate request = new RestTemplate();

        try {
            String response = request.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);

            map.put("Response from External API Consumed", node);

        } catch (Exception e) {
            map.put("error", "website not found");
        } finally {
            return map;
        }
    }

    public Map<Object, Object> getExternalApiResponse(String url, int port) throws UnknownHostException, JsonProcessingException {

        Map<Object, Object> map = new HashMap();
        Map<String, Object> internalInfo = getAllInfo();

        map.put("Local API Data Information", internalInfo);

        RestTemplate request = new RestTemplate();
        String address = url + ":" + port;

        try {
            String response = request.getForObject(address, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);

            map.put("Response from External API Consumed", node);

        } catch (Exception e) {
            map.put("error", "website not found");
        } finally {
            return map;
        }
    }

    public String deleteFile() {

        File file = new File(config.getFullpath());
        String mensaje = null;

        if (file.exists()) {
            try {
                file.delete();
                mensaje = "Archivo eliminado";
            } catch (SecurityException e) {
                mensaje = "No se pudo eliminar el archivo";
            }
            return mensaje;

        } else {
            return "El archivo no existe";
        }
    }

    public String createFile() {

        File file = new File(config.getFullpath());
        String mensaje = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
                mensaje = "Archivo creado";
            } catch (SecurityException | IOException e) {
                mensaje = "No se pudo crear el archivo";
            }
            return mensaje;

        } else {
            return "El archivo ya estaba creado";
        }
    }

}
