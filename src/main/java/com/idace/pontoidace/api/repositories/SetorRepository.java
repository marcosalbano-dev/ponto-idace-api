package com.idace.pontoidace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.idace.pontoidace.api.entities.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {

    @Transactional(readOnly = true)
    Setor findByNomeSetor(String nomeSetor);

}