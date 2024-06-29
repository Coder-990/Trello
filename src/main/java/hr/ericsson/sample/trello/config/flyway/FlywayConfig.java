package hr.ericsson.sample.trello.config.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file:.env")
public class FlywayConfig {

    @Value("${H2_DB_NAME}")
    private String dbName;

    @Value("${H2_USERNAME}")
    private String username;

    @Value("${H2_PASSWORD}")
    private String password;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource("jdbc:h2:mem:" + dbName + ";DB_CLOSE_ON_EXIT=FALSE", username, password)
                .locations("classpath:db/migration")
                .load();
    }
}
