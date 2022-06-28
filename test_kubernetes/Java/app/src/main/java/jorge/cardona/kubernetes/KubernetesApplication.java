package jorge.cardona.kubernetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class KubernetesApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubernetesApplication.class, args);
	}


	@GetMapping("/")
	public HashMap<String, String> getIpNameHost() throws UnknownHostException {

		HashMap<String, String> map = new HashMap<>();

		Runtime.Version runtimeVersion = Runtime.version();
		String version = String.valueOf(runtimeVersion.version().get(0));
		InetAddress hostIp = InetAddress.getLocalHost();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String hostname = InetAddress.getLocalHost().getHostName();

		map.put("Pod - Image  -> Java Version ", System.getProperty("java.version"));
		map.put("Pod - Image  -> Spring Version ", SpringVersion.getVersion());
		map.put("Pod - Image  -> Author", "Jorge Cardona");
		map.put("Pod - Image  -> hostname", hostname);
		map.put("Pod - Image  -> ip", ip);
		map.put("Pod - Image  -> hostname/ip", hostIp.toString());

		return map;

	}

}


