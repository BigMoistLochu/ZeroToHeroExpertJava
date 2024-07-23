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
     * dla dalszego przetwarzania w innych filtrach
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        var securityValueToCheck = SecurityContextHolder.getContext().getAuthentication();
//
//        UserDetails userToAuth = new User("Czajka","Czuka","ape@wp.pl","$2a$12$9uxjqile.mIP9LWaf04h6u8JMB3QX.oza/dajLL.swRInewp9xzdO", Permission.ADMIN);
//
//        UsernamePasswordAuthenticationToken userTokenToAuth = new UsernamePasswordAuthenticationToken(
//                userToAuth,
//                null
//                ,userToAuth.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(userTokenToAuth);
//
//
//        var secondsecurityValueToCheck = SecurityContextHolder.getContext().getAuthentication();
        //Użytkownik wysyła dane uwierzytelniające (np. nazwa użytkownika i hasło) w żądaniu HTTP.
        //Filtr uwierzytelniający (np. UsernamePasswordAuthenticationFilter) przechwytuje żądanie.
        //Filtr tworzy obiekt uwierzytelniający (np. UsernamePasswordAuthenticationToken) i przekazuje go do AuthenticationManager.
        //AuthenticationManager wywołuje odpowiedni AuthenticationProvider, który wykonuje faktyczne uwierzytelnienie (np. sprawdzenie nazwy użytkownika i hasła).
        //Jeśli uwierzytelnienie się powiedzie, AuthenticationProvider zwraca w pełni uwierzytelniony obiekt Authentication.
        //Filtr zapisuje obiekt Authentication w kontekście bezpieczeństwa (SecurityContext).
        //wydaje mi sie ze jesli tak jest to po co
        if (!request.getServletPath().contains("/secure")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println(request.getHeader("Authorization"));

        if(!request.getHeader("Authorization").equals("hash123.hash123.hash123")){
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userToAuth = new User("Jarek","Czuka","ape@wp.pl","$2a$12$9uxjqile.mIP9LWaf04h6u8JMB3QX.oza/dajLL.swRInewp9xzdO", Permission.ADMIN);

        UsernamePasswordAuthenticationToken userTokenToAuth = new UsernamePasswordAuthenticationToken(
                userToAuth,
                null
                ,userToAuth.getAuthorities());

        userTokenToAuth.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // Ustawienie uwierzytelnienia w SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(userTokenToAuth);

        filterChain.doFilter(request,response);
    }













}
