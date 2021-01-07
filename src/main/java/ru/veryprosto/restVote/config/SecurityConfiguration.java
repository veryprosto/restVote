package ru.veryprosto.restVote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.veryprosto.restVote.model.Role;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String USER = Role.USER.name();
    private final String OWNER = Role.OWNER.name();
    private final String ADMIN = Role.ADMIN.name();

    @Override
    protected void configure(HttpSecurity config) throws Exception {
        config
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/restaurants").hasRole(USER)
//                .antMatchers("/repairs").permitAll()
//                .antMatchers("/clients").permitAll()
//                .antMatchers("/report").permitAll()
//                .antMatchers("/h2admin/**").hasRole("ADMIN")
//                .antMatchers("/cars/addcar").hasRole("ADMIN")
//                .antMatchers("/cars/car/*").hasRole("ADMIN")
//                .antMatchers("/cars/del/*").hasRole("ADMIN")
//                .antMatchers("/cars/car/*/enroll").hasRole("ADMIN")
//                .antMatchers("/repairs/repair/*").hasRole("ADMIN")
//                .antMatchers("/repairs/del/*").hasRole("ADMIN")
//                .antMatchers("/clients/del/*").hasRole("ADMIN")
//                .antMatchers("/clients/add").hasRole("ADMIN")
//                .antMatchers("/clients/owner/*").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                // logout через get запрос
                // https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf-logout
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
        config.csrf().disable();
        config.httpBasic();

        config.headers().frameOptions().sameOrigin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("master").password("{noop}pass").roles("ADMIN");
    }
}