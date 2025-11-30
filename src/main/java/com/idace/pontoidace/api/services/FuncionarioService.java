package com.idace.pontoidace.api.services;

import com.idace.pontoidace.api.entities.Funcionario;

import java.util.Optional;

public interface FuncionarioService {

    /**
     * Persiste um funcionário na base de dados.
     *
     * @param funcionario
     * @return Funcionario
     */
    Funcionario persistir(Funcionario funcionario);

    /**
     * Busca e retorna um funcionário dado um CPF.
     *
     * @param cpf
     * @return Optional<Funcionario>
     */
    Optional<Funcionario> buscarPorCpf(String cpf);

    /**
     * Busca e retorna um funcionário dado um email.
     *
     * @param email
     * @return Optional<Funcionario>
     */
    Optional<Funcionario> buscarPorEmail(String email);

    /**
     * Busca e retorna um funcionário por ID.
     *
     * @param id
     * @return Optional<Funcionario>
     */
    Optional<Funcionario> buscarPorId(Long id);

    /**
     * Busca e retorna um funcionário por setor.
     *
     * @param setor
     * @return Optional<Funcionario>
     */
    Optional<Funcionario> buscarPorSetor(String setor);
}
