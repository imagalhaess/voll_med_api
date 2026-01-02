package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsulta implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosAgendamentoConsulta dados) {
        var dataAntes = dados.data().withHour(7);
        var dataDepois = dados.data().withHour(18);
        if(consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), dataAntes, dataDepois)){
            throw new ValidacaoException("O paciente já possui uma consulta agendada para a data solicitada!");
        }
    }
}
