package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosCancelamentoConsulta(@NotBlank(message = "O motivo do cancelamento é obrigatório.") String motivo,
                                        LocalDateTime data) {
}
