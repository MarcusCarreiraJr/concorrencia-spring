package com.faculdade.concorrencia.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.faculdade.concorrencia.entity.ContaBancariaVersionada;
import com.faculdade.concorrencia.service.ContaBancariaVersionadaService;

@RestController
@RequestMapping("/contas-versionadas")
public class ContaBancariaVersionadaController {

    private final ContaBancariaVersionadaService service;

    public ContaBancariaVersionadaController(
            ContaBancariaVersionadaService service) {

        this.service = service;
    }

    @PostMapping
    public ContaBancariaVersionada criarConta() {

        ContaBancariaVersionada conta =
                new ContaBancariaVersionada();

        conta.setSaldo(BigDecimal.ZERO);

        return service.criarConta(conta);
    }

    @PostMapping("/{id}/deposito")
    public ContaBancariaVersionada deposito(
            @PathVariable Long id,
            @RequestParam BigDecimal valor) {

        return service.depositar(id, valor);
    }

    @PostMapping("/{id}/saque")
    public ContaBancariaVersionada saque(
            @PathVariable Long id,
            @RequestParam BigDecimal valor) {

        return service.sacar(id, valor);
    }
}