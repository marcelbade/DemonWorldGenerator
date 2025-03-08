package marcel.demonworld.armygenerator.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import marcel.demonworld.armygenerator.dto.auth.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {


    // TODO what does ObjectMapper do, exactly?
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // TODO add to obisidan!
    @Override
    public void commence(HttpServletRequest request, //
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDTO("Unauthorized path"));

    }
}
