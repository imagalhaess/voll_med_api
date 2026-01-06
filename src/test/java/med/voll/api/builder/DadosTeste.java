package med.voll.api.builder;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;

public class DadosTeste {

    public static DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(nome, email, "61999999999", crm, especialidade, dadosEndereco());
    }

    public static DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(nome, email, "61988888888", cpf, dadosEndereco());
    }

    public static DadosEndereco dadosEndereco() {
        return new DadosEndereco("rua teste", "bairro teste", "00000000", "Brasilia", "DF", null, null);
    }

    public static Medico criarMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new Medico(dadosMedico(nome, email, crm, especialidade));
    }

    public static Paciente criarPaciente(String nome, String email, String cpf) {
        return new Paciente(dadosPaciente(nome, email, cpf));
    }
}