package com.group3.backend.config;

import com.group3.backend.security.JwtAuthenticationEntryPoint;
import com.group3.backend.security.JwtAuthenticationProvider;
import com.group3.backend.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }



    private final String[] PUBLIC = new String[] {"/auth/login", "/auth/register"};
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                /*.authorizeRequests().anyRequest().authenticated();
                //.authorizeRequests().antMatchers("api/interface/student/**").authenticated().and()
                //.authorizeRequests().antMatchers("api/interface/course/**").authenticated();

                //.csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //.authorizeRequests()
                //.antMatchers("api/auth/**").permitAll()
                //.anyRequest().authenticated();


        //httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        //httpSecurity.headers().cacheControl();*/
                .authorizeRequests().antMatchers(PUBLIC).permitAll()
                .anyRequest().authenticated().and()
                .csrf().disable();

                httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
                httpSecurity.headers().cacheControl();
    }

        /*@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("api/auth/login");
    }*/
}
