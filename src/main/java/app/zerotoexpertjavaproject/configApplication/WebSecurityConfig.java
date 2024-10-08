package app.zerotoexpertjavaproject.configApplication;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig{

    private final JwtAuthenticationCustomFilter jwtAuthenticationCustomFilter;
    private final AuthenticationProvider daoAuthenticationProvider;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                        .requestMatchers(HttpMethod.GET,"/images/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/services/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/components/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.GET,"/error").permitAll()
                        .requestMatchers(HttpMethod.GET,"/").permitAll()
                        .requestMatchers(HttpMethod.GET,"/logowanie").permitAll()
                        .requestMatchers(HttpMethod.GET,"/account").permitAll()
                        .requestMatchers(HttpMethod.POST,"/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(daoAuthenticationProvider)
                .addFilterBefore(jwtAuthenticationCustomFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
