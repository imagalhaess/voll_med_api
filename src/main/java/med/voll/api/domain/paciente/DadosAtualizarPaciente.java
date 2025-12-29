package med.voll.api.domain.paciente;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizarPaciente(
        @NotNull (message = "{id.obrigatorio}")
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
