package jorge.cardona.concepts.service;

import jorge.cardona.concepts.configuration.LoadYamlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
@EnableScheduling
public class Scheduling {


    @Autowired
    private LoadYamlProperties loadYamlProperties;

    // https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/scheduling.html
    @Scheduled(fixedDelay = 3000, initialDelay = 5000)
    private void repetitiveTask(){

        log.error("THIS IT IS A SCHEDULE TASK LOGGING " + LocalDateTime.now());

    }

    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
    @Scheduled(cron = "58 59 23 ? * 5 ")
    public void monthlyTask() {

        log.warn("THIS TASK IT IS EXECUTED TO THE 58 SECONDS, 59 MINUTES, 23 HOURS, EVERY MONTH, EVERY FRIDAY(5 DAY OF THE WEEK) " + LocalDateTime.now());
    }

    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
    @Scheduled(fixedDelay = 1000)
    public void loadYamlProperties() {

        log.info("Name " + loadYamlProperties.getName());
        log.info("Release " + loadYamlProperties.getRelease());
        log.info("Enable " + loadYamlProperties.isEnable());
    }
}
