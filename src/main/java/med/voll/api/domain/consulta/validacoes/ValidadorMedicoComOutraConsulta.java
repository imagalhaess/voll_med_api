package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar (DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null) {
            return;
        }
        if (consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data())){
            throw new ValidacaoException("O médico escolhido já possui uma consulta agendada para o" +
                                                 " mesmo dia.");
        }

    }

}
