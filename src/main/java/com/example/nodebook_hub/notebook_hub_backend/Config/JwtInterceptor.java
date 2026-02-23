package com.example.nodebook_hub.notebook_hub_backend.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "❌ Missing or invalid Authorization header");
            return false;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "❌ Invalid or expired token");
            return false;
        }

        String role = jwtUtil.extractRole(token);
        String path = request.getRequestURI();

        // 💡 Path-based role verification
        if (path.contains("/admin") && !"ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "🚫 Only admins can access this route");
            return false;
        } else if (path.contains("/user") && !"USER".equals(role) && !"ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "🚫 Only users can access this route");
            return false;
        }

        // Set extracted details if needed
        request.setAttribute("role", role);
        request.setAttribute("email", jwtUtil.extractEmail(token));

        return true;
    }

}
