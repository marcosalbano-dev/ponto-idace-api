package com.idace.pontoidace.api.services.impl;

import com.idace.pontoidace.api.entities.Funcionario;
import com.idace.pontoidace.api.repositories.FuncionarioRepository;
import com.idace.pontoidace.api.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario persistir(Funcionario funcionario) {
        log.info("Persistindo funcionário: {}", funcionario);
        return this.funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> buscarPorCpf(String cpf) {
        log.info("Buscando funcionário pelo CPF {}", cpf);
        return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
    }

    public Optional<Funcionario> buscarPorEmail(String email) {
        log.info("Buscando funcionário pelo email {}", email);
        return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        log.info("Buscando funcionário pelo IDl {}", id);
        return Optional.ofNullable(this.funcionarioRepository.findById(id).orElse(null));
    }

    public Optional<Funcionario> buscarPorSetor(String setor) {
        log.info("Buscando funcionário pelo Setor {}", setor);
        return Optional.ofNullable(this.funcionarioRepository.findBySetor(setor));
    }

}
