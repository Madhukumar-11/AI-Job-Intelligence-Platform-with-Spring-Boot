package com.madhu.jobintelligenceplatformspringboot.Config;

import com.madhu.jobintelligenceplatformspringboot.Security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/* so spring security by default blocks all incoming requests even for signup or login
   and says every request must have token to access.
   but here we need no token access for signup or login or GET /jobs api requesr.Right?
   so for that we need to tell spring security that what requests needed access without a token
   and what need access with token. So to tell spring that we need this class. */
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // here below line tells spring to disable the protection for jwt requests APIs
          // for signup and login we tell below to give access for these requests even without a token
        http.csrf(csrf -> csrf.disable()); //Cross Site Request Forgery

        /* below line tells spring that don't create any http sessions for every request
           just rely on jwt token request REST APIs */
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /*
            here below line adds Authenticatiom that means username and role is extracted and added to
            security context of request so that spring security makes decision so that request is accepted or rejected.
            This jwtfilter is called and acts as a guard and writes a note(username and role) to request.
            And then after request is checked by Spring's decision maker and accepts the request cause it sees
            username and role otherwise it rejects the request which are not valid ones(without username and role).
         */
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // below is where we tell what is needed access/authentication and what not
        SecurityFilterChain securityFilterChain = http.authorizeHttpRequests(authorize ->
                    // here we tell all requests with "/auth/..." will get access without a jwt token
                    authorize.requestMatchers("/auth/**").permitAll().

                    /* here it tells all requests that contains httpmethods "GET /jobs/..." are accessed without
                       jwt token cause it doesn't modify any data at all
                     - but not allowed access for methods like POST,PUT,PATCH,DELETE cause these methods can
                        modify data but only has access to ADMIN role*/
                    requestMatchers(HttpMethod.GET, "/jobs/**").permitAll().
                     //Now here comes real "AUTHORIZATION" only "ADMIN" has access to POST,PUT,DELETE
                    requestMatchers(HttpMethod.POST,"/jobs/**").hasRole("ADMIN").
                    requestMatchers(HttpMethod.PUT,"/jobs/**").hasRole("ADMIN").
                    requestMatchers(HttpMethod.DELETE,"/jobs/**").hasRole("ADMIN").

                    // here below it tells other than above specified all requests are authenticated.
                    anyRequest().authenticated()).build();
        return securityFilterChain;
    }
}
