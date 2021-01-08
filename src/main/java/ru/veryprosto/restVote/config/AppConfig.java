package ru.veryprosto.restVote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.veryprosto.restVote.model.Dish;
import ru.veryprosto.restVote.model.Restaurant;
import ru.veryprosto.restVote.model.User;
import ru.veryprosto.restVote.model.UserRoles;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db/postgres.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("ru.veryprosto.**") })
public class AppConfig {

    @Autowired
    private Environment env;

    @Value("classpath:db/initDB.sql")
    private Resource initScript;

    @Value("classpath:db/populateDB.sql")
    private Resource dataScript;

    @PostConstruct
    public void initDb() {
        //  String encoded=new BCryptPasswordEncoder().encode("password");
        DataSource dataSource = getTransactionManager().getDataSource();
        String property = env.getProperty("database.init");
        if (dataSource != null && property != null && property.equalsIgnoreCase("true")) {
            DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        }
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setSqlScriptEncoding(StandardCharsets.UTF_8.name());
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(initScript);
        databasePopulator.addScript(dataScript);
        return databasePopulator;
    }

    @Bean
    public MethodInvokingBean getMethodInvokingBean() {
        MethodInvokingBean methodInvokingBean = new MethodInvokingBean();
        methodInvokingBean.setStaticMethod("org.slf4j.bridge.SLF4JBridgeHandler.install");
        return methodInvokingBean;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();

        // Setting JDBC properties
        props.put(DRIVER, env.getProperty("database.driverClassName"));
        props.put(URL, env.getProperty("database.url"));
        props.put(USER, env.getProperty("database.username"));
        props.put(PASS, env.getProperty("database.password"));

        // Setting Hibernate properties
        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        props.put(FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        props.put(USE_SQL_COMMENTS, true);
        props.put(JPA_PROXY_COMPLIANCE, false);
        props.put("hibernate.connection.CharSet", "utf-8");
        props.put("hibernate.connection.useUnicode", true);
        props.put("hibernate.connection.characterEncoding", "utf-8");
        //   props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

        // Setting C3P0 properties
        props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class, UserRoles.class, Restaurant.class, Dish.class);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}