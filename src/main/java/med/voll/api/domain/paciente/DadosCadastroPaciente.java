package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPaciente(
        @NotBlank (message = "{nome.obrigatorio}")
        String nome,
        @NotBlank (message = "{email.obrigatorio}")
        @Email (message = "{email.invalido}")
        String email,
        @NotBlank (message = "{telefone.obrigatorio}")
        String telefone,
        @NotBlank (message = "{cpf.obrigatorio}")
        @CPF (message = "{cpf.invalido}")
        String cpf,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        DadosEndereco endereco
) {
}
