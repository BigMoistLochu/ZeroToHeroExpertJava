package app.zerotoexpertjavaproject.config;

import app.zerotoexpertjavaproject.entities.Permission;
import app.zerotoexpertjavaproject.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilterConfig extends OncePerRequestFilter {



    /**
     *Klasa ktora jest jednym z filtrow i ma za zadanie sprawdzanie JWT przy kazdym requescie
     * to tutaj ustawiamy SecurityContextHolder.getContext().setAuthentication(auserWithCrede..)
     * dla dalszego przetwarzania w innych filtrach np w filtrze AutorizationFilter
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //jesli request posiada header Authorization to przefiltruj
        if(!request.getHeader("Authorization").contains("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String extractedTokenFromHeader = request.getHeader("Authorization").substring(7);

        //

        UserDetails userToAuth = new User("Jarek","Czuka","ape@wp.pl","$2a$12$9uxjqile.mIP9LWaf04h6u8JMB3QX.oza/dajLL.swRInewp9xzdO", Permission.ADMIN);

        UsernamePasswordAuthenticationToken userTokenToAuth = new UsernamePasswordAuthenticationToken(
                userToAuth,
                null
                ,userToAuth.getAuthorities());

        userTokenToAuth.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );


        SecurityContextHolder.getContext().setAuthentication(userTokenToAuth);

        filterChain.doFilter(request,response);
    }













}
