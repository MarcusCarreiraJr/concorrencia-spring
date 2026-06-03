package com.faculdade.concorrencia.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.concorrencia.entity.ContaBancariaVersionada;
import com.faculdade.concorrencia.repository.ContaBancariaVersionadaRepository;

@Service
public class ContaBancariaVersionadaService {

    private final ContaBancariaVersionadaRepository repository;

    public ContaBancariaVersionadaService(
            ContaBancariaVersionadaRepository repository) {

        this.repository = repository;
    }

    @Transactional
    public ContaBancariaVersionada criarConta(
            ContaBancariaVersionada conta) {

        return repository.save(conta);
    }

    @Transactional
    public ContaBancariaVersionada depositar(
            Long id,
            BigDecimal valor) {

        ContaBancariaVersionada conta = repository.findById(id)
                .orElseThrow();

        conta.setSaldo(conta.getSaldo().add(valor));

        return repository.save(conta);
    }

    @Transactional
    public ContaBancariaVersionada sacar(
            Long id,
            BigDecimal valor) {

        ContaBancariaVersionada conta = repository.findById(id)
                .orElseThrow();

        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));

        return repository.save(conta);
    }
}