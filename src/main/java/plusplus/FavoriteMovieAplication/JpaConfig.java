package plusplus.FavoriteMovieAplication;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class JpaConfig {
    private Connection connection;
    private DataSource ds;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/springdemodb");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("hoangnt1@");
        ds = dataSourceBuilder.build();
        return ds;
    }

    @Bean
    public Connection getConnection() {
        try
        {if (connection == null) {
            return ds.getConnection();
        }}
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
