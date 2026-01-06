package med.voll.api.controller;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deveria retornar código http 200 quando informações estão válidas, mas corpo da requisição vazio.")
    @WithMockUser
    void listar_medicos_listavazia() throws Exception {
        //ARRANGE
        when(medicoRepository.findAllByAtivoTrue(any())).thenReturn(Page.empty());
        //ACT
        var response = mvc.perform(get("/medicos"))
                .andReturn()
                .getResponse();
        //ASSERT
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
