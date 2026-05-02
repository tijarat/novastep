package com.step.nova.security;

import java.util.List;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtFilter extends OncePerRequestFilter 
{
    private final JwtUtil jwtUtil;
    public JwtFilter(JwtUtil jwtUtil) {this.jwtUtil = jwtUtil;}

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)  throws ServletException, IOException 
    {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) 
        {
            String token = header.substring(7);
            if (!jwtUtil.isValid(token))
            {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return;
            }
            SecurityContextHolder.getContext().setAuthentication
            (
                    new UsernamePasswordAuthenticationToken(jwtUtil.getUsername(token),null,List.of(new SimpleGrantedAuthority(jwtUtil.getRole(token))))
            );
        }
        filterChain.doFilter(request, response);
    }
}