package com.idace.pontoidace.api.services;

import com.idace.pontoidace.api.entities.Setor;

import java.util.Optional;

public interface SetorService {

    /**
     * Retorna um setor dado um nomeSetor.
     *
     * @param nomeSetor
     * @return Optional<Setor>
     */
    Optional<Setor> buscarPorNomeSetor(String nomeSetor);

    /**
     * Cadastra uma nova setor na base de dados.
     *
     * @param setor
     * @return Setor
     */
    Setor persistir(Setor setor);
}
