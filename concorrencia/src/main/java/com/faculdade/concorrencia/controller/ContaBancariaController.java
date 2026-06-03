package com.faculdade.concorrencia.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.faculdade.concorrencia.entity.ContaBancaria;
import com.faculdade.concorrencia.service.ContaBancariaService;

@RestController
@RequestMapping("/contas")
public class ContaBancariaController {

    private final ContaBancariaService service;

    public ContaBancariaController(ContaBancariaService service) {
        this.service = service;
    }

    @PostMapping("/{id}/deposito")
    public ContaBancaria deposito(@PathVariable Long id,
                                  @RequestParam BigDecimal valor) {

        return service.depositar(id, valor);
    }

    @PostMapping("/{id}/saque")
    public ContaBancaria saque(@PathVariable Long id,
                               @RequestParam BigDecimal valor) {

        return service.sacar(id, valor);
    }
    
    @PostMapping
    public ContaBancaria criarConta() {

        ContaBancaria conta = new ContaBancaria();

        conta.setSaldo(BigDecimal.ZERO);

        return service.criarConta(conta);
    }
}