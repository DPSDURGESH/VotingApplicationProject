//package com.voting.application.security;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.voting.application.service.TokenService;
//
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/*")
//@Slf4j
//public class TokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private TokenService tokenLogService;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        
//
//        String token = request.getHeader("Authorization");
//        if (token == null || token.isEmpty()) {
//            sendUnauthorizedResponse(response, "Missing token");
//            return;
//        }
//
//        token = token.replace("Bearer ", "");
//        if (!TokenService.isValidToken(token)) {
//            sendUnauthorizedResponse(response, "Invalid or expired token");
//            return;
//        }
//
//        String userName = jwtService.verifyToken(token);
//        if (userName == null) {
//            sendUnauthorizedResponse(response, "Invalid or expired token");
//            return;
//        }
//
//        request.setAttribute("tokenLog", userName);
//
//        // Token is valid, continue with the filter chain
//        filterChain.doFilter(request, response);
//    }
//
//    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write(message);
//    }
//}
