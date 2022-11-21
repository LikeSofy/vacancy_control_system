package by.klishevich.vacancy_control_system.filter;

import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import by.klishevich.vacancy_control_system.security.CustomAuthentication;
import by.klishevich.vacancy_control_system.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends GenericFilter {

    private AuthService authService;

    @Override
    public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain
    ) throws IOException, ServletException
    {
        authService.resolveUser((HttpServletRequest) request).ifPresentOrElse(user -> {
            CustomAuthentication authentication = new CustomAuthentication(true, user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.setAttribute("user", user);
        }, () -> {
            UserEntity user = UserEntity.guest();
            CustomAuthentication authentication = new CustomAuthentication(false, user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.setAttribute("user", user);
        });

        chain.doFilter(request, response);
    }
}
