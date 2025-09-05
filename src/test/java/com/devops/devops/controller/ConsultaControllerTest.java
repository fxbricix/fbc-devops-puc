package com.devops.devops.controller;

import com.devops.devops.domain.DadosDTO;
import com.devops.devops.service.ConsultaDadosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConsultaController.class)
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultaDadosService consultaDadosService;

    @Test
    void consultaEndpoint_deveRetornarDadosDTO() throws Exception {
        DadosDTO mockDados = DadosDTO.builder()
                .nome("José")
                .sobrenome("Ficticio")
                .materia("DEVOPS")
                .universidade("Pontificia Universidade Catolica do Paraná - PUC PR")
                .periodo(5)
                .diaDaConsulta("08/08/2025 17:01")
                .build();
        when(consultaDadosService.consultaDados()).thenReturn(mockDados);

        mockMvc.perform(get("/consulta")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nome").value("José"))
                .andExpect(jsonPath("$.sobrenome").value("Ficticio"))
                .andExpect(jsonPath("$.materia").value("DEVOPS"))
                .andExpect(jsonPath("$.universidade").value("Pontificia Universidade Catolica do Paraná - PUC PR"))
                .andExpect(jsonPath("$.periodo").value(5))
                .andExpect(jsonPath("$.diaDaConsulta").value("08/08/2025 17:01"));
    }

    @Test
    void consultaEndpoint_deveRetornar404QuandoDadosNaoEncontrados() throws Exception {
        when(consultaDadosService.consultaDados()).thenReturn(null);

        mockMvc.perform(get("/consulta"))
                .andExpect(status().isNotFound());
    }

    @Test
    void consultaEndpoint_deveRetornar400QuandoServicoLancaExcecao() throws Exception {
        when(consultaDadosService.consultaDados()).thenThrow(new IllegalArgumentException("Dados inválidos"));

        mockMvc.perform(get("/consulta"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void consultaEndpoint_deveRetornarCamposCorretosNoJson() throws Exception {
        DadosDTO mockDados = DadosDTO.builder()
                .nome("Ana")
                .sobrenome("Silva")
                .materia("Engenharia")
                .universidade("UFPR")
                .periodo(8)
                .build();

        when(consultaDadosService.consultaDados()).thenReturn(mockDados);

        mockMvc.perform(get("/consulta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ana"))
                .andExpect(jsonPath("$.sobrenome").value("Silva"))
                .andExpect(jsonPath("$.materia").value("Engenharia"))
                .andExpect(jsonPath("$.universidade").value("UFPR"))
                .andExpect(jsonPath("$.periodo").value(8));
    }
}
