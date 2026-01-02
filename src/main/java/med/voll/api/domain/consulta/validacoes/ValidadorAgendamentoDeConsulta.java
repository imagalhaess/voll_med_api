package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public interface ValidadorAgendamentoDeConsulta {
    void validar (DadosAgendamentoConsulta dados);
}
