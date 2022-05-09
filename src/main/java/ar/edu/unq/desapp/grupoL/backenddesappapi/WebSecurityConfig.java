package ar.edu.unq.desapp.grupoL.backenddesappapi;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();
        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests()
                .antMatchers("/console/**").permitAll();
        http.headers().frameOptions().disable();
    }

}
