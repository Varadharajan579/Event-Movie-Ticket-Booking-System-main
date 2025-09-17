package pbl.project.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DatabaseConfig {
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource data_source) {
        return new NamedParameterJdbcTemplate(data_source);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource data_source = new DriverManagerDataSource();
        data_source.setDriverClassName("org.h2.Driver");
        data_source.setUrl("jdbc:h2:mem:testdb");
        data_source.setUsername("sa");
        data_source.setPassword("");
        return data_source;
    }

    @Bean
    public DataSource loadSchema() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:data.sql")
                .addScript("classpath:schema.sql")
                .build();
    }

}
