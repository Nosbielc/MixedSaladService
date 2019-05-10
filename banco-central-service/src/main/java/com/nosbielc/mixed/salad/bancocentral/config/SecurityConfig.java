package com.nosbielc.mixed.salad.bancocentral.config;

import io.micrometer.core.instrument.Metrics;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll().and().csrf().disable();
        http.headers().frameOptions().disable();

//        http.authorizeRequests()
//                .antMatchers("/actuator/health").permitAll().anyRequest().authenticated();
//        http.headers().frameOptions().disable();

//        http.authorizeRequests()
//                .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
//                .hasRole("ADMIN")
//                .requestMatchers(EndpointRequest.to(
//                        HealthEndpoint.class, InfoEndpoint.class, Metrics.class))
//                .permitAll()
//                .requestMatchers(EndpointRequest.toAnyEndpoint())
//                .fullyAuthenticated()
//                .and().httpBasic();

//        http.headers().frameOptions().disable();
    }
}
