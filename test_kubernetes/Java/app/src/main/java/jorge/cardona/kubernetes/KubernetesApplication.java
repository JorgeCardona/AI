package jorge.cardona.kubernetes;

import jorge.cardona.kubernetes.models.Host;
import jorge.cardona.kubernetes.usecases.Info;
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

	public static void main(String[] args) {
		SpringApplication.run(KubernetesApplication.class, args);
	}

	@GetMapping("/")
	public Map<String, Object> getInfo() throws UnknownHostException {
		return Info.getInternalInfo();
	}

	@PostMapping("/api")
	public Map<Object, Object> getApiResponse(@RequestBody Host host) throws IOException {
		return Info.getExternalApiResponse(host.getHostname(), host.getPort());
	}

	@GetMapping("/all")
	public Map<String, Object> getAllInfo() throws UnknownHostException {
		return Info.getAllInfo();
	}

	@GetMapping("/env")
	public Map<String, String> getVariables() {
		return Info.getEnvironmentVariables();
	}

	@GetMapping("/web")
	public Map<Object, Object> getApiResponseExternalSite(@RequestParam String url) throws IOException {
		return Info.getExternalWeb(url);
	}

}