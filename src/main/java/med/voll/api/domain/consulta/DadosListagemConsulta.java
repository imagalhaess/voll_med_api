package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id, LocalDateTime data) {
    public DadosListagemConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getData());
    }
}
