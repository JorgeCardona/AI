package jorge.cardona.kubernetes;

import jorge.cardona.kubernetes.models.Content;
import jorge.cardona.kubernetes.models.Host;
import jorge.cardona.kubernetes.usecases.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class KubernetesApplication {

	@Autowired
	private Info info;

	public static void main(String[] args) {
		SpringApplication.run(KubernetesApplication.class, args);
	}

	@GetMapping("/")
	public Map<String, Object> getInfo() throws UnknownHostException {
		return info.getInternalInfo();
	}

	@PostMapping("/api")
	public Map<Object, Object> getApiResponse(@RequestBody Host host) throws IOException {
		return info.getExternalApiResponse(host.getHostname(), host.getPort());
	}

	@GetMapping("/all")
	public Map<String, Object> getAllInfo() throws UnknownHostException {
		return info.getAllInfo();
	}

	@GetMapping("/env")
	public Map<String, String> getVariables() {
		return info.getEnvironmentVariables();
	}

	@GetMapping("/web")
	public Map<Object, Object> getApiResponseExternalSite(@RequestParam String url) throws IOException {
		return info.getExternalWeb(url);
	}

	@PostMapping("/saveInfoIntoFile")
	public String getApiResponse(@RequestBody Content content) throws IOException {
		return info.setContentFile(content.getMessage());
	}

	@GetMapping("/readInfoFromFile")
	public String getInfoFromShareFile() throws IOException {
		return info.getContentFile();
	}

	@DeleteMapping("/deleteFile")
	public String deleteInfoFromShareFile() throws IOException {
		return info.deleteFile();
	}

	@PostMapping("/createFile")
	public String createShareFile() throws IOException {
		return info.createFile();
	}
}