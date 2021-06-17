package kr.ac.pcu.cyber.studentservice.config;

import kr.ac.pcu.cyber.studentservice.client.UserServiceClient;
import kr.ac.pcu.cyber.studentservice.filter.AuthenticationErrorFilter;
import kr.ac.pcu.cyber.studentservice.filter.AuthenticationFilter;
import kr.ac.pcu.cyber.studentservice.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.servlet.Filter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;
    private final UserServiceClient userServiceClient;

    public SecurityConfig(AuthenticationService authenticationService, UserServiceClient userServiceClient) {
        this.authenticationService = authenticationService;
        this.userServiceClient = userServiceClient;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        Filter authenticationFilter = new AuthenticationFilter(
                authenticationManager(),
                authenticationService,
                userServiceClient
        );

        Filter authenticationErrorFilter = new AuthenticationErrorFilter();

        httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .addFilter(authenticationFilter)
                .addFilterBefore(authenticationErrorFilter, AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }
}
