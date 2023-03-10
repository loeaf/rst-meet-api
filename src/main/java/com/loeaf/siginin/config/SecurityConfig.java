package com.loeaf.siginin.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/", "/**/*").permitAll();
//                .authorizeRequests()
//                .antMatchers("/", "/users").permitAll()
//                .antMatchers(HttpMethod.GET,"/board/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/boardRest/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/CmmnCode/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/Restaurant/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/Media/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/ReView/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/TasteRoom/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/UserAccount/**").permitAll();
//                .antMatchers("/edit").hasRole(Authority.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=true")
//                .loginProcessingUrl("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")
//                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/webjars/**","/**.ico", "/**.jpg", "/**.png");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}

