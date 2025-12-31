/*
package med.voll.api.infra.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TratadorErroAutenticacao implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        String mensagem;


        if (authException instanceof BadCredentialsException) {

            mensagem = "{\"mensagem\": \"Credenciais inválidas. Usuário ou senha incorretos.\"}";
        } else {

            mensagem = "{\"mensagem\": \"Token não enviado ou inválido. Acesso negado.\"}";
        }

        response.getWriter().write(mensagem);
    }
}*/
