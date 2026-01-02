package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeConsulta {

    public void validar (DadosAgendamentoConsulta dados){
        var data = dados.data();
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDeAbertura = data.getHour() < 7;
        var depoisDaAbertura = data.getHour() > 18 || (data.getHour() == 18 && data.getMinute() > 0);

        if (domingo || antesDeAbertura || depoisDaAbertura){
            throw  new ValidacaoException("Consulta fora do horário de antedimento. O horário de funcionamento da " +
                                                  "clínica é de segunda a sábado, das 07:00 às 19:00");
        }
    }

}
