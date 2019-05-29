package com.nosbielc.mixed.salad.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends
        ResourceServerConfigurerAdapter {

    private String scopeRead = "#oauth2.hasScope('read')";
    private String scopeWrite = "#oauth2.hasScope('write')";

	@Value("${security.oauth2.client.resource-ids}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .antMatchers(HttpMethod.GET, "/**").access(scopeRead)
                .antMatchers(HttpMethod.OPTIONS, "/**").access(scopeRead)
                .antMatchers(HttpMethod.POST, "/**").access(scopeWrite)
                .antMatchers(HttpMethod.PUT, "/**").access(scopeWrite)
                .antMatchers(HttpMethod.PATCH, "/**").access(scopeWrite)
                .antMatchers(HttpMethod.DELETE, "/**").access(scopeWrite);
    }
}
