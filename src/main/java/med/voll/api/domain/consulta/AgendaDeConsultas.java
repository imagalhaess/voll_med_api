package med.voll.api.domain.consulta;

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
        var consulta = new Consulta(null, medico, paciente, dados.data(), true);
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

    public void cancelarConsulta (DadosCancelamentoConsulta dados){

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var data = consulta.getData();
        Duration diferenca = Duration.between(LocalDateTime.now(), data);
        if (diferenca.toHours() < 24){
            throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 " +
                                                 "horas.");
        }
        consulta.cancelar();
    }
    }
