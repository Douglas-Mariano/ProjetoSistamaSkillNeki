package br.com.neki.sistemaSkill.security;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.neki.sistemaSkill.handler.ConversorData;
import br.com.neki.sistemaSkill.model.error.ErrorResposta;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        String data = ConversorData.converterDataParaDataHora(new Date());
        ErrorResposta erro = new ErrorResposta(401, "Unauthorized",
                "Usuário não autenticado, favor efetuar a autenticação.", data);

        response.setStatus(401);
        // response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        // Aqui pego o objeto de erro e devolvo como um json.
        response.getWriter().println(new ObjectMapper().writeValueAsString(erro));
    }
}
