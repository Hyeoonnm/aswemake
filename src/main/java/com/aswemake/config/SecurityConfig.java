package com.aswemake.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/product/v1/api/add").hasRole("admin") // "/product/v1/api/add" 엔드포인트에는 "admin" 역할이 필요
                .antMatchers("/product/v1/api/update").hasRole("admin") // "/product/v1/api/add" 엔드포인트에는 "admin" 역할이 필요
                .antMatchers("/product/v1/api/delete").hasRole("admin") // "/product/v1/api/add" 엔드포인트에는 "admin" 역할이 필요
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
