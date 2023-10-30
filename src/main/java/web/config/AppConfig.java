package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

   private final Environment env;

   @Autowired
   public AppConfig(Environment env) {
      this.env = env;
   }

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();

      dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));

      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setDataSource(dataSource);
      entityManagerFactoryBean.setPackagesToScan("web");

      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

      Properties jpaProperties = new Properties();
      jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
      jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
      jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      entityManagerFactoryBean.setJpaProperties(jpaProperties);

      return entityManagerFactoryBean;
   }

   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
      return new JpaTransactionManager(entityManagerFactory);
   }
}
