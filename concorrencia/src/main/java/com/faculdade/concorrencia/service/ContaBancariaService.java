package com.faculdade.concorrencia.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.concorrencia.entity.ContaBancaria;
import com.faculdade.concorrencia.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {

    private final ContaBancariaRepository repository;

    public ContaBancariaService(ContaBancariaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ContaBancaria depositar(Long id, BigDecimal valor) {

        ContaBancaria conta = repository.findById(id)
                .orElseThrow();

        conta.setSaldo(conta.getSaldo().add(valor));

        return repository.save(conta);
    }

    @Transactional
    public ContaBancaria sacar(Long id, BigDecimal valor) {

        ContaBancaria conta = repository.findById(id)
                .orElseThrow();

        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));

        return repository.save(conta);
    }
    
    @Transactional
    public ContaBancaria criarConta(ContaBancaria conta) {
        return repository.save(conta);
    }
}