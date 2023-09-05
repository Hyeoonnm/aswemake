package com.aswemake.config;

import com.aswemake.entity.enums.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/member/api/**").permitAll()
                .antMatchers("/product/api/**", "/member/admin").hasAnyAuthority(MemberRole.ADMIN.name())
                .antMatchers("/security-login/info").authenticated()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .usernameParameter("loginId")
                .passwordParameter("password")
                .loginProcessingUrl("/login")  // 로그인 Form Action Url
                .loginPage("/member/login")
                .defaultSuccessUrl("/member/info")
                .failureUrl("/member/login")

                .and()
                .logout()
                .logoutUrl("/security-login/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
