package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado não está disponível na data.")
    void escolherMedicoAleatorioDisponivelCenario1() {
        var data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico Teste", "medico.teste@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente Teste", "paciente.teste@teste.com", "00000000000");
        cadastrarConsulta(medico, paciente, data);
        var medicoLivre = medicoRepository.escolherMedicoAleatorioDisponivel(Especialidade.CARDIOLOGIA.name(), data);
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver disponível quando ele está disponível na data.")
    void escolherMedicoAleatorioDisponivelCenario2() {
        var data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico Teste", "medico.teste@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var medicoLivre = medicoRepository.escolherMedicoAleatorioDisponivel(Especialidade.CARDIOLOGIA.name(), data);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data){
        em.persist(new Consulta(null, medico, paciente, data, true, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade){
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return  new DadosCadastroMedico(
                nome,
                email,
                null,
                crm,
                especialidade,
                dadosEndereco()
        );
    }
    private Paciente cadastrarPaciente(String nome, String email, String cpf){
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }
    private DadosCadastroPaciente dadosPaciente (String nome, String email, String cpf){
        return new DadosCadastroPaciente(
                nome,
                email,
                null,
                cpf,
                dadosEndereco()
        );
    }
    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua teste",
                "bairro teste",
                "38418000",
                "Teste",
                "TT",
                null,
                null
        );
    }
}