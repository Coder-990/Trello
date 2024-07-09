package hr.ericsson.sample.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static hr.ericsson.sample.trello.helper.env.EnvFileCreator.createEnvFileIfNotExists;

@SpringBootApplication
public class TrelloApplication {

    public static void main(String[] args) {
        createEnvFileIfNotExists();
        SpringApplication.run(TrelloApplication.class, args);
    }

}
