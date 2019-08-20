package gk.gk.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AutherizationFilter extends BasicAuthenticationFilter {

    public AutherizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if (header ==null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken token = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getToken(HttpServletRequest request) {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if(header!=null){
            header = header.replace(SecurityConstants.TOKEN_PREFIX, "");
            String userName = Jwts.parser()
                    .setSigningKey(SecurityConstants.TOKEN_SECRET)
                    .parseClaimsJwt(header).getBody().getSubject();
            if(userName!=null){
                return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
            }
        }
        return null;

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if(header!=null){
            header = header.replace(SecurityConstants.TOKEN_PREFIX, "");
            String userName = Jwts.parser()
                    .setSigningKey(SecurityConstants.TOKEN_SECRET)
                    .parseClaimsJws(header).getBody().getSubject();
            if(userName!=null){
                return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
            }

        }
        return null;

    }
}
