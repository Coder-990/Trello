package hr.ericsson.sample.trello.helper.env;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class EnvFileCreator {

    private EnvFileCreator() {
    }

    public static void createEnvFileIfNotExists() {
        File envFile = new File(".env");

        if (!envFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
                writer.write("H2_USERNAME=sa\n");
                writer.write("H2_PASSWORD=password\n");
                writer.write("H2_DB_NAME=trellodb\n");
                writer.write("H2_DB_SCHEMA=trello_schema\n");
                log.info(".env file created successfully.");
            } catch (IOException e) {
                log.info("Failed to create .env file: {}", e.getMessage());
            }
        } else {
            log.info(".env file already exists. Skipping creation.");
        }
    }
}
