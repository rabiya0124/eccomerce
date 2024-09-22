package check.checks;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Service
public class jwtauth extends OncePerRequestFilter {
 //   @Autowired jwtbuilder jwtbuilder;
    @Autowired verification verification;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
      //  System.out.println("OK "+header);

        String token, name;

        if (header== null){
      //      System.out.println("NULL HAI");
            filterChain.doFilter(request,response);
           // System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return;}
//        if (header != null && header.startsWith("Bearer")) {

//            token = header.substring(7);
//            name = jwtbuilder.extractUserName(token);
//
//           // System.out.println("BINDO"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//            if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//        //
//                // Loading UserDetails by username extracted from the token
//                UserDetails userDetails = verification.loadUserByUsername(name);
//
//                // Validating the token with loaded UserDetails
//                if (jwtbuilder.validateToken(token, verification)) {
//                    // Creating an authentication token using UserDetails
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    // Setting authentication details
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    // Setting the authentication token in the SecurityContext
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//        }
            // Proceeding with the filter chain
            filterChain.doFilter(request, response);

    }}

