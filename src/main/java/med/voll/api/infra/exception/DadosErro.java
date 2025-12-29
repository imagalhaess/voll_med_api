package med.voll.api.infra.exception;

import java.time.LocalDateTime;

public record DadosErro(String mensagem, LocalDateTime timestamp) {
    public DadosErro (String mensagem){
        this(mensagem, LocalDateTime.now());
    }
}
