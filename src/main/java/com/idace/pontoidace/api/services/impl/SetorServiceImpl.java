package com.idace.pontoidace.api.services.impl;

import com.idace.pontoidace.api.entities.Setor;
import com.idace.pontoidace.api.repositories.SetorRepository;
import com.idace.pontoidace.api.services.SetorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SetorServiceImpl implements SetorService {

    private static final Logger log = LoggerFactory.getLogger(SetorServiceImpl.class);

    @Autowired
    private SetorRepository setorRepository;


    @Override
    public Optional<Setor> buscarPorNomeSetor(String nomeSetor) {
        log.info("Buscando uma empresa para o setor {}", nomeSetor);
        return Optional.ofNullable(setorRepository.findByNomeSetor(nomeSetor));
    }

    @Override
    public Setor persistir(Setor setor) {
        log.info("Persistindo setor: {}", setor);
        return this.setorRepository.save(setor);
    }
}
