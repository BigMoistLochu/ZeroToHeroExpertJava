package app.zerotoexpertjavaproject.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider daoAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request.requestMatchers(HttpMethod.GET,"/login")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/login")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/images/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/js/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/logowanie")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/account")
                        .permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(daoAuthenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
