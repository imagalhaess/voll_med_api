package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(

        Long idConsulta,
        String medico,
        String paciente,
        LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(),
             consulta.getMedico().getNome(),
             consulta.getPaciente().getNome(),
             consulta.getData());
    }

}
