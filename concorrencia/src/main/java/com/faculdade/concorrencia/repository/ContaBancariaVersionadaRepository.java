package com.faculdade.concorrencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.concorrencia.entity.ContaBancariaVersionada;

public interface ContaBancariaVersionadaRepository
        extends JpaRepository<ContaBancariaVersionada, Long> {

}