package med.voll.api.domain.consulta.validacoes;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if (!paciente.isAtivo()) {
            throw new ValidacaoException("Não é possível agendar uma consulta para um" +
                                                 "paciente inativo!");
        }
    }
}