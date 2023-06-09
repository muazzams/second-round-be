package dev.abidino.secondround.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abidino.secondround.exception.ErrorDto;
import dev.abidino.secondround.exception.ErrorMessageType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (response.getStatus() == 403) {
            ErrorDto errorDto = new ErrorDto(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name(), ErrorMessageType.FORBIDDEN.getMessage(), new Date());
            response.getWriter().write(convertObjectToJson(errorDto));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            ErrorDto errorDto = new ErrorDto(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name(), ErrorMessageType.UNAUTHORIZED.getMessage(), new Date());
            response.getWriter().write(convertObjectToJson(errorDto));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}