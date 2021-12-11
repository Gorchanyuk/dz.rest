package dz.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class jdbcTemplateConfig {

    @Bean
//    public JdbcTemplate jdbcTemplate(@Value("${url}")String url) throws ClassNotFoundException {
    public JdbcTemplate jdbcTemplate(){
        String url = "jdbc:sqlite:.\\sqlite.db";
//        Class.forName("org.sqlite.JDBC");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl( url);
        return new JdbcTemplate(dataSource);
    }


}
