package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.infra.exception.DadosErro;
import med.voll.api.infra.exception.DadosErroValidacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.status(404).body(new DadosErro("O servidor não encontrou o recurso solicitado."));
    }

//    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
//    public ResponseEntity tratarErro401() {
//        return ResponseEntity.status(401).body(new DadosErro("Requer autenticação para acessar o recurso."));
//    }
//
//    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
//    public ResponseEntity tratarErro403() {
//        return ResponseEntity.status(403).body(new DadosErro("O cliente não tem permissão para acessar o recurso, mesmo autenticado."));
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
            var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao:: new).toList());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex){
        return ResponseEntity.status(500)
                .body(new DadosErro("Erro interno do servidor: "
                                            + ex.getLocalizedMessage()));
    }
}


