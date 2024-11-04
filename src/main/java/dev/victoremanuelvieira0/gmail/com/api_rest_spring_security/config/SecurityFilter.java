package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.config;

import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.repository.UserRepository;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private UserRepository repository;
    private JwtService jwt;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

       String token = getTokenFromHeader(request);

       if(token != null){
           String email = jwt.validToken(token);
           var user = repository.findByEmail(email);

           var auth = UsernamePasswordAuthenticationToken.authenticated
                   (user,null,user.get().getAuthorities());

           SecurityContextHolder.getContext().setAuthentication(auth);
       }
       filterChain.doFilter(request,response);
    }

    private String getTokenFromHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if(header == null){
            return null;
        }
        if(!header.split(" ")[0].equals("Bearer")){
            return null;
        }

        return header.split(" ")[1];
    }
}
