package bank;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "bank")
@EnableWebMvc
@EnableTransactionManagement
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login**", "/registerView", "/register")
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.successHandler(new MyAuthSuccessHandler())
			.usernameParameter("email")
			.passwordParameter("password")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true);
			
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(encoder().encode("123456"));
		auth
		.userDetailsService(service)
		.passwordEncoder(encoder());
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver v = new InternalResourceViewResolver();
		v.setPrefix("/WEB-INF/views/");
		v.setSuffix(".jsp");
		return v;
	}
	
	@Bean
	public LocalSessionFactoryBean factory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setPackagesToScan("bank.models");
		Properties p = new Properties();
		p.setProperty("hibernate.hbm2ddl.auto", "none");
		p.setProperty("hibernate.show_sql", "true");
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		sessionFactory.setHibernateProperties(p);
		sessionFactory.setDataSource(dataSource());
		return sessionFactory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(factory().getObject());
		return manager;
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("eray123");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bankSpring");
		return dataSource;
	}
	
}
