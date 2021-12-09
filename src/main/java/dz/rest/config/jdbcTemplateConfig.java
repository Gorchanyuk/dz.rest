package dz.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class jdbcTemplateConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {

        String url = "jdbc:sqlite:C:\\Users\\gorch\\OneDrive\\Рабочий стол\\JAVA\\Мои программы\\dz.rest\\sqlite.db";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        return new JdbcTemplate(dataSource);
    }


}
