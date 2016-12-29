package com.microton.calc.conf;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Spring REST configuration.
 *
 * @author Jan Šípek
 */
@Configuration
@EnableWebSecurity
public class RestConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Creates custom GSON converters.
     *
     * @return HTTP message converters
     */
    @Bean
    public HttpMessageConverters customConverters() {
        Collection<HttpMessageConverter<?>> messageConverters
                = new ArrayList<>();
        GsonHttpMessageConverter gsonHttpMessageConverter
                = new GsonHttpMessageConverter();
        Gson gson = new com.google.gson.GsonBuilder()
                .registerTypeAdapterFactory(
                        new com.microton.calc.dto.GsonAdaptersDto())
                .create();
        gsonHttpMessageConverter.setGson(gson);
        messageConverters.add(gsonHttpMessageConverter);
        return new HttpMessageConverters(true, messageConverters);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/math/**").permitAll()
                .anyRequest().authenticated();
    }
}
