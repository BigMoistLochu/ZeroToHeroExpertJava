package app.zerotoexpertjavaproject.Auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilterConfig extends OncePerRequestFilter {


    //klasa odpowiedzialna za przechwytywanie requesta przed sprawdzeniem danych uzytkownika
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //dzieki tej czesci ustawiasz autentykacje z obiektu ktory ma username i role
        //to bedzie uzywane do innych filtrow
        //HttpSessionRequestCache zapoznac sie z ta klasa potem,

//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        username, null, user.getAuthorities()
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);

        
        filterChain.doFilter(request,response);
    }













}
