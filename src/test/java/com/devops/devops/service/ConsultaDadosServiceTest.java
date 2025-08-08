package com.devops.devops.service;

import com.devops.devops.domain.DadosDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaDadosServiceTest {
    ConsultaDadosService service = new ConsultaDadosService();

    @Test
    void consultaDados_deveRetornarDadosCorretos() {
        DadosDTO dados = service.consultaDados();
        assertNotNull(dados);
        assertEquals("José", dados.getNome());
        assertEquals("Ficticio", dados.getSobrenome());
        assertEquals("DEVOPS", dados.getMateria());
        assertEquals("Pontificia Universidade Catolica do Paraná - PUC PR", dados.getUniversidade());
        assertEquals(5, dados.getPeriodo());
        assertNotNull(dados.getDiaDaConsulta());
    }
}

