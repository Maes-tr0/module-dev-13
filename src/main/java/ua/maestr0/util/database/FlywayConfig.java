package ua.maestr0.util.database;

import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;

public class FlywayConfig {
    private static final Dotenv dotenv = Dotenv.configure().directory("config").load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }
}
