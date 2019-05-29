package com.nosbielc.mixed.salad.bancocentral.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@Profile("!test")
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    private String scopeRead = "#oauth2.hasScope('read')";
    private String scopeWrite = "#oauth2.hasScope('write')";
    private String api = "/api/**";
    private String resourceId = "resources";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers(api)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .antMatchers(HttpMethod.GET, api).access(scopeRead)
                .antMatchers(HttpMethod.OPTIONS, api).access(scopeRead)
                .antMatchers(HttpMethod.POST, api).access(scopeWrite)
                .antMatchers(HttpMethod.PUT, api).access(scopeWrite)
                .antMatchers(HttpMethod.PATCH, api).access(scopeWrite)
                .antMatchers(HttpMethod.DELETE, api).access(scopeWrite);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId);
    }
}
