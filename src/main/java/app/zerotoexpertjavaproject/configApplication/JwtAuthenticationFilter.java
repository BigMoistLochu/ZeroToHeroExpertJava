package app.zerotoexpertjavaproject.configApplication;


import app.zerotoexpertjavaproject.userLayer.repositories.UserRepository;
import app.zerotoexpertjavaproject.userLayer.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    /**
     *Klasa ktora jest jednym z filtrow i ma za zadanie sprawdzanie JWT przy kazdym requescie
     * to tutaj ustawiamy SecurityContextHolder.getContext().setAuthentication(auserWithCrede..)
     * dla dalszego przetwarzania w innych filtrach np w filtrze AutorizationFilter
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        
        //jesli header jest nullem lub nie posiada odpowiedniego prefixu "Bearer" to filter idzie dalej
        if(request.getHeader("Authorization") == null || !request.getHeader("Authorization").contains("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String extractedToken = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsernameFromPayloadSectionInToken(extractedToken);

        if(username==null){
            filterChain.doFilter(request,response);
            return;
        }

        try {
            UserDetails userToAuth= userRepository.findByUsername(username).orElseThrow();

            if(!jwtService.verifyToken(extractedToken,userToAuth)){
                filterChain.doFilter(request,response);
                return;
            }

            UsernamePasswordAuthenticationToken userTokenToAuth = new UsernamePasswordAuthenticationToken(
                    userToAuth.getUsername(),
                    null
                    ,userToAuth.getAuthorities());

            userTokenToAuth.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(userTokenToAuth);

            filterChain.doFilter(request,response);

        }
        catch (NoSuchElementException e){
            logger.info("No User to find with name: " + username);
        }


    }













}
