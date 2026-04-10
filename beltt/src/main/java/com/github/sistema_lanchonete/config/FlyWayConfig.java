package com.github.sistema_lanchonete.config;

import org.flywaydb.core.Flyway;

public class FlyWayConfig {
    public static void migrate()
    {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/Lanchonete",


    "postgres",
                        "admin"
                )
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}
