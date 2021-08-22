package jorge.cardona.concepts.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;


@RestController
@SpringBootApplication
public class MongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDBApplication.class, args);

		String data = "qtttrwetywrty";

		ArrayList<String> d = new ArrayList<String>();


		String[] strSplit = data.split("");

		System.out.println(strSplit);
	}

}