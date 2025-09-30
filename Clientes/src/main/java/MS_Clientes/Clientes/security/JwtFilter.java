package MS_Clientes.Clientes.security;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();


        if ((path.equals("/api/clientes") && method.equals("POST")) ||
                (path.equals("/api/estados") && method.equals("GET")) ||
                (path.equals("/api/clientes") && method.equals("GET")) ||
                (path.startsWith("/api/clientes/") && method.equals("GET"))||
                (path.startsWith("/api/estados/") && method.equals("GET"))) {
            filterChain.doFilter(request, response);
            return;
        }



        String jwt = request.getHeader("Authorization");
        if (jwt != null && jwt.startsWith("Bearer ")) {
            try {
                String token = jwt.substring(7).trim();


                String correo = jwtUtil.getUsernameFromToken(token);
                jwtUtil.validarToken(token);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(correo, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (Exception ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o expirado: " + ex.getMessage());
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Falta token de autorización");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
