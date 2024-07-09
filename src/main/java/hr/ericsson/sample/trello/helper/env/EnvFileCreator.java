package hr.ericsson.sample.trello.helper.env;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EnvFileCreator {

    public static void createEnvFileIfNotExists() {
        File envFile = new File(".env");
        if (!envFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
                writer.write("H2_USERNAME=sa\n");
                writer.write("H2_PASSWORD=password\n");
                writer.write("H2_DB_NAME=trellodb\n");
                writer.write("H2_DB_SCHEMA=trello_schema\n");
                System.out.println(".env file created successfully.");
            } catch (IOException e) {
                System.err.println("Failed to create .env file: " + e.getMessage());
            }
        } else {
            System.out.println(".env file already exists. Skipping creation.");
        }
    }
}
