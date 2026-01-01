package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar (DadosAgendamentoConsulta dados){
    if (!pacienteRepository.existsById(dados.idPaciente())){
        throw new ValidacaoException("ID do paciente não existe no sistema!");
    }
    if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
        throw new ValidacaoException("ID do médico não existe no sistema!");
    }

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatório se não for selecionado" +
                                                 "nenhum médico");
        }
    return medicoRepository.escolherMedicoAleatorioDisponivel(dados.especialidade().name(), dados.data());
    }

    public void cancelar (DadosCancelamentoConsulta dadosCancelamentoConsulta,
                          DadosDetalhamentoConsulta dadosDetalhamentoConsulta){
        Duration diferenca = Duration.between(dadosCancelamentoConsulta.data(), LocalDateTime.now());
        if (diferenca.toHours() > 24){
            throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 " +
                                                 "horas.");
        }
        var cancelamentoConsulta = consultaRepository.getReferenceById(dadosDetalhamentoConsulta.idConsulta());
        consultaRepository.delete(cancelamentoConsulta);
    }
}
