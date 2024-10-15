package com.dmtr.crud_empl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MySecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config -> config
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    //    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails user1 = User.builder()
//                .username("Ivan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("Oleg")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails user3 = User.builder()
//                .username("Ina")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2, user3);
//    }
}
