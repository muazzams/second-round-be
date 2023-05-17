package dev.abidino.secondround.security;

import dev.abidino.secondround.exception.ErrorMessageType;
import dev.abidino.secondround.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ApiJWTAuthorizationFilter extends BasicAuthenticationFilter {

    public ApiJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {

        String token = getTokenInCookies(req.getCookies());
        if (!StringUtils.hasLength(token)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;

        try {
            authentication = getAuthentication(token);
        } catch (Exception exception) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String[] values = token.split("&");
        if (values.length == 2) {
            String username = values[0];
            String secret = values[1];
            String calculateHmac = JwtTokenUtil.calculateHmac(username);
            if (Objects.equals(secret, calculateHmac)) {
                return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            }
        }

        throw new UnauthorizedException(ErrorMessageType.UNAUTHORIZED.getMessage());
    }

    private static String getTokenInCookies(Cookie[] cookies) {
        if (Objects.isNull(cookies)) {
            return null;
        }

        Optional<Cookie> optCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(SecurityConstant.TOKEN_COOKIE_NAME))
                .findFirst();

        return optCookie.map(Cookie::getValue).orElse(null);
    }

    public static String getAuthenticatedUserName() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            HttpServletRequest request = requestAttributes.getRequest();
            Cookie[] cookies = request.getCookies();
            return getTokenInCookies(cookies);
        }
        throw new UnauthorizedException(ErrorMessageType.UNAUTHORIZED.getMessage());
    }
}
