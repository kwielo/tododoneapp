package co.wielo.tododone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final String ROLE_USER = "USER";

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/note/**").hasRole(ROLE_USER)
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(users.username("admin").password("admin").roles(ROLE_USER).build());

        return manager;
    }
}
