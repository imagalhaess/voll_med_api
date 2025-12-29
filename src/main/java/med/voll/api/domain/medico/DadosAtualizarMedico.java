package med.voll.api.domain.medico;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizarMedico(
        @NotNull (message = "{id.obrigatorio}")
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
