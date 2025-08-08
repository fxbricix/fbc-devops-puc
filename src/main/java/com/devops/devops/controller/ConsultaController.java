package com.devops.devops.controller;

import com.devops.devops.domain.DadosDTO;
import com.devops.devops.service.ConsultaDadosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaController {

    private final ConsultaDadosService consultaDadosService;

    public ConsultaController(ConsultaDadosService consultaDadosService) {
        this.consultaDadosService = consultaDadosService;
    }

    @GetMapping("/consulta")
    public DadosDTO consultaDados(){
        return consultaDadosService.consultaDados();
    }
}
