package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("ID do paciente não existe no sistema!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID do médico não existe no sistema!");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoException("Não existe médico disponível para essa data.");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), true, null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatório se não for selecionado" +
                                                 "nenhum médico");
        }
        return medicoRepository.escolherMedicoAleatorioDisponivel(dados.especialidade().name(), dados.data());
    }

    public void cancelarConsulta(Long id, @Valid DadosCancelamentoConsulta dados) {
        var motivo = dados.motivo();
        var consulta = consultaRepository.getReferenceById(id);
        var data = consulta.getData();
        Duration diferenca = Duration.between(LocalDateTime.now(), data);
        if (diferenca.toHours() < 24) {
            throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 " +
                                                 "horas.");
        }
        consulta.cancelar(motivo);
    }
}
