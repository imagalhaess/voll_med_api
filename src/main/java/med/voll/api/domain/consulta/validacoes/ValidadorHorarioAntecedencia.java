package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar (DadosAgendamentoConsulta dados){
        var data = dados.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, data).toMinutes();

        if (diferenca < 30){
            throw new ValidacaoException("A consulta deve ser agendada com no mínimo 30 minutos de antecedência.");
        }
    }
}
