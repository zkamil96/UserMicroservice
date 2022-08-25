package com.example.usermicroservice.Config;

import com.example.usermicroservice.Model.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    public final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if(!hasAuthorizationHeader(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = getAccessToken(request);

        if(!jwtTokenUtil.validateAccessToken(accessToken)){
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationContext(accessToken, request);
        filterChain.doFilter(request, response);

    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request){
        UserDetails userDetails = getUserDetails(accessToken);
        log.info("User details: " + userDetails.getUsername());

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, null, null);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UserDetails getUserDetails(String accessToken){
        Users userDetails = new Users();
        String[] subjectArray = jwtTokenUtil.getSubject(accessToken).split(",");
        userDetails.setId(Integer.parseInt(subjectArray[0]));
        userDetails.setLogin(subjectArray[1]);

        return userDetails;
    }

    private boolean hasAuthorizationHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")){
            return false;
        }

        return true;
    }

    private String getAccessToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();

        log.info("Access token " + token);

        return token;
    }
}
