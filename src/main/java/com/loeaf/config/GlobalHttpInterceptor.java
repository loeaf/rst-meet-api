package com.loeaf.config;

import com.loeaf.common.misc.DateUtils;
import com.loeaf.siginin.dto.UserToken;
import com.loeaf.siginin.model.User;
import com.loeaf.siginin.util.JwtManager;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalHttpInterceptor implements HandlerInterceptor {
    @Autowired
    JwtManager jwtManager;

    @Autowired
    UserToken accountToken;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DateUtils.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Get the Authorization header from the request

        //로그인 경로 제외
        if(request.getRequestURI().contains("/login") ||
                request.getRequestURI().contains("/signUp") ||
                request.getRequestURI().contains("/logout") ||
                request.getRequestURI().contains("/resources")){
            return true;
        }

        String authHeader = request.getHeader("Authorization");

        // Check if the Authorization header is present and starts with "Bearer"
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            // Extract the JWT token from the Authorization header
            String jwtToken = authHeader.substring(7);
            // Use the JWT token for authentication or other purposes
            // For example, you can decode the token using a library like jjwt
            User account = null;
            try {
                account = this.jwtManager.getAccountByToken(jwtToken);
            } catch (ExpiredJwtException e) {
                LOGGER.warn("the token is expired and not valid anymore", e);
                response.sendError(401, "Session Expired");
                return false;
            } catch (Exception e) {
                LOGGER.warn("the token is invalid", e);
                response.sendError(401, "Invalid Token");
                return false;
            }
            accountToken.setUser(account);
            accountToken.setToken(jwtToken);
        }

        // Continue processing the request
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        var jwtToken = jwtManager.generateJwtToken(this.accountToken.getAccount());
//        response.addHeader("Authorization", "Basic " + jwtToken);
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}